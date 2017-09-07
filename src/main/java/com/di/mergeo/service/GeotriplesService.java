package com.di.mergeo.service;

import com.di.mergeo.model.MapInputModel;
import com.di.mergeo.model.RdfInputModel;
import eu.linkedeodata.geotriples.GeoTriplesCMD;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GeotriplesService {

    /*******************************************************************************************************************
     *
     *                                      Generate Mapping Methods!
     *
     ******************************************************************************************************************/

    /************************************ Relational Database Mapping Method ******************************************/
    /******************************************************************************************************************/
    public static void GTRdbMapping(MapInputModel inputmodel) throws Exception {

        File dir = new File(inputmodel.getUploadpath() + File.separator + "datafiles");
        if (!dir.exists()) dir.mkdirs();

        File dir2 = new File(inputmodel.getUploadpath() + File.separator + "datafiles" + File.separator + "map-data");
        if (!dir2.exists()) dir2.mkdirs();

        String dbname = inputmodel.getRdb_jdbcurl().substring(inputmodel.getRdb_jdbcurl().lastIndexOf("/")+1);
        String outmap_fullpath = dir2.getAbsolutePath() + File.separator + dbname + "-map.ttl";

        List<String> cmdlist = new ArrayList<String>();
        cmdlist.add("generate_mapping");
        cmdlist.add("-o");
        cmdlist.add(outmap_fullpath);
        cmdlist.add("-b");
        cmdlist.add(inputmodel.getRdb_baseuri());

        if(inputmodel.getRdb_user() != null && !inputmodel.getRdb_user().isEmpty()){
            cmdlist.add("-u");
            cmdlist.add(inputmodel.getRdb_user());
        }
        if(inputmodel.getRdb_password() != null && !inputmodel.getRdb_password().isEmpty()){
            cmdlist.add("-p");
            cmdlist.add(inputmodel.getRdb_password());
        }
        if(inputmodel.getRdb_driver() != null && !inputmodel.getRdb_driver().isEmpty()){
            cmdlist.add("-d");
            cmdlist.add(inputmodel.getRdb_driver());
        }

        if( inputmodel.isRdb_rml() ){
            cmdlist.add("-rml");
        }

        cmdlist.add(inputmodel.getRdb_jdbcurl());

        String[] mapping_cmd = cmdlist.toArray(new String[0]);

        GeoTriplesCMD.main(mapping_cmd);
        System.out.println("[Status] Mapping generation of RDB is done");

        inputmodel.setName(dbname);
        inputmodel.setOutmap_fullpath(outmap_fullpath);
    }

    /***************************************** Shapefile Mapping Method ***********************************************/
    /******************************************************************************************************************/
    public static void GTShapeMapping(MapInputModel inputmodel) throws Exception {

        if (!inputmodel.getShp_inputfile().isEmpty()) {

            String name = inputmodel.getShp_inputfile().getOriginalFilename();

            byte[] bytes = new byte[0];
            bytes = inputmodel.getShp_inputfile().getBytes();

            File dir = new File(inputmodel.getUploadpath() + File.separator + "datafiles");
            if (!dir.exists()) dir.mkdirs();

            File dir2 = new File(inputmodel.getUploadpath() + File.separator + "datafiles" + File.separator + "input-data");
            if (!dir2.exists()) dir2.mkdirs();

            File dir3 = new File(inputmodel.getUploadpath() + File.separator + "datafiles" + File.separator + "map-data");
            if (!dir3.exists()) dir3.mkdirs();

            File serverFile = new File(dir2.getAbsolutePath() + File.separator + name);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            String inputfile_name = name.substring(0, name.indexOf('.'));
            String outmap_fullpath = dir3.getAbsolutePath() + File.separator + inputfile_name + "-map.ttl";

            List<String> cmdlist = new ArrayList<String>();
            cmdlist.add("generate_mapping");
            cmdlist.add("-o");
            cmdlist.add(outmap_fullpath);
            cmdlist.add("-b");
            cmdlist.add(inputmodel.getShp_baseuri());

            if(inputmodel.getShp_epsgcode() != null && inputmodel.getShp_epsgcode().isEmpty()){
                cmdlist.add("-s");
                cmdlist.add(inputmodel.getShp_epsgcode());
            }

            if( inputmodel.isShp_rml()){
                cmdlist.add("-rml");
            }

            cmdlist.add(serverFile.getAbsolutePath());

            String[] mapping_cmd = cmdlist.toArray(new String[0]);

            GeoTriplesCMD.main(mapping_cmd);
            System.out.println("[Status] Mapping generation of Shapefile is done");

            inputmodel.setName(name);
            inputmodel.setOutmap_fullpath(outmap_fullpath);
        }
        else {
            System.out.println("[Error] Error in mapping generation of Shapefile");
        }
    }

    /****************************************** XMLfile Mapping Method ************************************************/
    /******************************************************************************************************************/
    public static void GTXmlMapping(MapInputModel inputmodel) throws Exception {

        if (!inputmodel.getXml_inputfile().isEmpty()) {

            String name = inputmodel.getXml_inputfile().getOriginalFilename();
            byte[] bytes = new byte[0];
            bytes = inputmodel.getXml_inputfile().getBytes();

            String xsd_name = null;
            byte[] xsd_bytes = new byte[0];
            File xsd_serverFile = null;

            File dir = new File(inputmodel.getUploadpath() + File.separator + "datafiles");
            if (!dir.exists()) dir.mkdirs();

            File dir2 = new File(inputmodel.getUploadpath() + File.separator + "datafiles" + File.separator + "input-data");
            if (!dir2.exists()) dir2.mkdirs();

            File dir3 = new File(inputmodel.getUploadpath() + File.separator + "datafiles" + File.separator + "map-data");
            if (!dir3.exists()) dir3.mkdirs();

            File serverFile = new File(dir2.getAbsolutePath() + File.separator + name);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            String inputfile_name = name.substring(0, name.indexOf('.'));
            String outmap_fullpath = dir3.getAbsolutePath() + File.separator + inputfile_name + "-map.ttl";

            /* Process for the XSD given file */
            if (inputmodel.getXml_xsdfile() != null && !inputmodel.getXml_xsdfile().isEmpty()) {
                xsd_name = inputmodel.getXml_xsdfile().getOriginalFilename();
                xsd_bytes = inputmodel.getXml_xsdfile().getBytes();
                xsd_serverFile = new File(dir2.getAbsolutePath() + File.separator + xsd_name);

                BufferedOutputStream xsd_stream = new BufferedOutputStream(new FileOutputStream(xsd_serverFile));
                xsd_stream.write(xsd_bytes);
                xsd_stream.close();
            }

            // TODO = NAMESPACES as List not only a String
            List<String> cmdlist = new ArrayList<String>();
            cmdlist.add("generate_mapping");
            cmdlist.add("-o");
            cmdlist.add(outmap_fullpath);
            cmdlist.add("-b");
            cmdlist.add(inputmodel.getXml_baseuri());

            if(inputmodel.getXml_rootpath() != null && !inputmodel.getXml_rootpath().isEmpty()){
                cmdlist.add("-rp");
                cmdlist.add(inputmodel.getXml_rootpath());
            }
            if(inputmodel.getXml_rootelement() != null && !inputmodel.getXml_rootelement().isEmpty()){
                cmdlist.add("-r");
                cmdlist.add(inputmodel.getXml_rootelement());
            }
            if(inputmodel.getXml_namespace() != null && !inputmodel.getXml_namespace().isEmpty()){
                cmdlist.add("-onlyns");
                cmdlist.add(inputmodel.getXml_namespace());
            }
            if(inputmodel.getXml_namespaces() != null && !inputmodel.getXml_namespaces().isEmpty()){
                cmdlist.add("-ns");
                cmdlist.add(inputmodel.getXml_namespaces());
            }
            if(xsd_name != null && !xsd_name.isEmpty()){
                cmdlist.add("-x");
                cmdlist.add(xsd_serverFile.getAbsolutePath());
            }

            cmdlist.add(serverFile.getAbsolutePath());

            String[] mapping_cmd = cmdlist.toArray(new String[0]);

            GeoTriplesCMD.main(mapping_cmd);
            System.out.println("[Status] Mapping generation of XML-file is done");

            inputmodel.setName(name);
            inputmodel.setOutmap_fullpath(outmap_fullpath);
        }
        else {
            System.out.println("[Error] Error in mapping generation of XML-file");
        }
    }

    /*******************************************************************************************************************
     *
     *                                          Dump to RDF Methods!
     *
     ******************************************************************************************************************/

    /**************************************** RDB to RDF Service Method ***********************************************/
    /******************************************************************************************************************/
    public static void GTRdbToRdf(RdfInputModel rdfInputModel) throws Exception {

        File dir = new File(rdfInputModel.getUploadpath() + File.separator + "datafiles");
        if (!dir.exists()) dir.mkdirs();

        File dir2 = new File(rdfInputModel.getUploadpath() + File.separator + "datafiles" + File.separator + "rdf-data");
        if (!dir2.exists()) dir2.mkdirs();

        String dbname = rdfInputModel.getRdb_jdbcurl().substring(rdfInputModel.getRdb_jdbcurl().lastIndexOf("/")+1);
        String outrdf_fullpath = dir2.getAbsolutePath() + File.separator + dbname + "-rdf.nt";

        List<String> cmdlist = new ArrayList<String>();
        cmdlist.add("dump_rdf");
        cmdlist.add("-o");
        cmdlist.add(outrdf_fullpath);
        cmdlist.add("-b");
        cmdlist.add(rdfInputModel.getRdb_baseuri());

        if(rdfInputModel.getRdb_user() != null && !rdfInputModel.getRdb_user().isEmpty()){
            cmdlist.add("-u");
            cmdlist.add(rdfInputModel.getRdb_user());
        }
        if(rdfInputModel.getRdb_password() != null && !rdfInputModel.getRdb_password().isEmpty()){
            cmdlist.add("-p");
            cmdlist.add(rdfInputModel.getRdb_password());
        }
        if(rdfInputModel.getRdb_driver() != null && !rdfInputModel.getRdb_driver().isEmpty()){
            cmdlist.add("-d");
            cmdlist.add(rdfInputModel.getRdb_driver());
        }

        cmdlist.add("-f");
        cmdlist.add(rdfInputModel.getRdb_format());

        if( rdfInputModel.isRdb_rml() ){
            cmdlist.add("-rml");
        }

        cmdlist.add("-jdbc");
        cmdlist.add(rdfInputModel.getRdb_jdbcurl());

        cmdlist.add(rdfInputModel.getRdb_mapfullpath());

        String[] dumprdf_cmd = cmdlist.toArray(new String[0]);

        GeoTriplesCMD.main(dumprdf_cmd);

        rdfInputModel.setOutrdf_fullpath(outrdf_fullpath);
        rdfInputModel.setName(dbname + "-rdf.nt");

        System.out.println("[Status] RDB dumped to RDF");
    }

    /************************************* Shapefile to RDF Service Method ********************************************/
    /******************************************************************************************************************/
    public static void GTShpToRdf(RdfInputModel rdfInputModel) throws Exception {

        File dir2 = new File(rdfInputModel.getUploadpath() + File.separator + "datafiles" + File.separator + "rdf-data");
        if (!dir2.exists()) dir2.mkdirs();

        String sourcefile_name = rdfInputModel.getShp_sourcefile();
        String sourcefile_path = rdfInputModel.getUploadpath()  + "datafiles" + File.separator
                                    + "input-data" + File.separator + sourcefile_name;

        String outrdf_fullpath;
        String sourceShpFile = "";

        List<String> cmdlist = new ArrayList<String>();
        cmdlist.add("dump_rdf");
        cmdlist.add("-o");

        //If N-Triples
        if(rdfInputModel.getShp_format().equals("N3")){
            outrdf_fullpath = dir2.getAbsolutePath() + File.separator +
                    sourcefile_name.substring(0, sourcefile_name.indexOf('.')) + "-rdf.nt";
            cmdlist.add(outrdf_fullpath);
            sourceShpFile = rdfInputModel.getUploadpath()  + "datafiles" + File.separator
                    + "input-data" + File.separator + sourcefile_name;
        }
        //If Turtle
        else if(rdfInputModel.getShp_format().equals("TURTLE")){
            outrdf_fullpath = dir2.getAbsolutePath() + File.separator +
                    sourcefile_name.substring(0, sourcefile_name.indexOf('.')) + "-rdf.ttl";
            cmdlist.add(outrdf_fullpath);
            cmdlist.add("-f");
            cmdlist.add(rdfInputModel.getShp_format());
        }
        //If RDFXML
        else{
            outrdf_fullpath = dir2.getAbsolutePath() + File.separator +
                    sourcefile_name.substring(0, sourcefile_name.indexOf('.')) + "-rdf.xml";
            cmdlist.add(outrdf_fullpath);
            cmdlist.add("-f");
            cmdlist.add(rdfInputModel.getShp_format());
        }

//        cmdlist.add("-sh");
//        cmdlist.add(sourcefile_path);

        cmdlist.add("-b");
        cmdlist.add(rdfInputModel.getShp_baseuri());

        if(rdfInputModel.getShp_format().equals("N3")){
            cmdlist.add("-sh");
            cmdlist.add(sourceShpFile);
        }

        /*  RML checkbox */
        if( rdfInputModel.isRdb_rml() ){
            cmdlist.add("-rml");
        }

        /*  EPSG Code input */
//        if(rdfInputModel.getShp_epsgcode() != null && !rdfInputModel.getShp_epsgcode().isEmpty()){
//            cmdlist.add("-s");
//            cmdlist.add(rdfInputModel.getShp_epsgcode());
//        }

        cmdlist.add(rdfInputModel.getShp_mapfullpath());

        String[] dumprdf_cmd = cmdlist.toArray(new String[0]);

        GeoTriplesCMD.main(dumprdf_cmd);

        rdfInputModel.setOutrdf_fullpath(outrdf_fullpath);
        rdfInputModel.setName(sourcefile_name.substring(0, sourcefile_name.indexOf('.')) + "-rdf.nt");

        System.out.println("[Status] Shapefile dumped to RDF");

    }

    /************************************* XML/JSON to RDF Service Method *********************************************/
    /******************************************************************************************************************/
    public static void GTXmlToRdf(RdfInputModel rdfInputModel) throws Exception {

        File dir2 = new File(rdfInputModel.getUploadpath() + File.separator + "datafiles" + File.separator + "rdf-data");
        if (!dir2.exists()) dir2.mkdirs();

        File temp = new File(rdfInputModel.getXml_mapfullpath());
        String out_name = temp.getName();
        out_name = out_name.substring(0, out_name.indexOf('-'));
        out_name += "-rdf.nt";

        String outrdf_fullpath = dir2.getAbsolutePath() + File.separator + out_name;

        List<String> cmdlist = new ArrayList<String>();
        cmdlist.add("dump_rdf");
        cmdlist.add("-o");
        cmdlist.add(outrdf_fullpath);
        cmdlist.add("-b");
        cmdlist.add(rdfInputModel.getXml_baseuri());

        if( rdfInputModel.isXml_rml() ){
            cmdlist.add("-rml");
        }
        cmdlist.add("-f");
        cmdlist.add(rdfInputModel.getXml_format());

        if(rdfInputModel.getXml_epsgcode() != null && !rdfInputModel.getXml_epsgcode().isEmpty()){
            cmdlist.add("-s");
            cmdlist.add(rdfInputModel.getXml_epsgcode());
        }

        cmdlist.add(rdfInputModel.getXml_mapfullpath());

        String[] dumprdf_cmd = cmdlist.toArray(new String[0]);

        GeoTriplesCMD.main(dumprdf_cmd);
        System.out.println("[Status] XML-file dumped to RDF");
    }

    /******************************************************************************************************************
     *
     *                                          Other helpful methods!
     *
     ******************************************************************************************************************/
    public static void saveFileChanges(String file_fullpath, String new_data) throws IOException {

        File serverFile = new File(file_fullpath);
        FileUtils.writeStringToFile(serverFile, new_data);
        System.out.println("[Status] Changes successfully saved");
    }

    /******************************************************************************************************************/
}
