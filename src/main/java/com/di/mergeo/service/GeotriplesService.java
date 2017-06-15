package com.di.mergeo.service;

import com.di.mergeo.model.InputModel;
import eu.linkedeodata.geotriples.GeoTriplesCMD;
import jdk.internal.util.xml.impl.Input;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GeotriplesService {

    /************************************ Relational Database Service Method ******************************************/
//    public static ModelAndView GTRdbMapping(String baseuri, String user, String password, String driver,
//                                            String jdbcurl, String rml, String uploadpath) throws Exception {
//
//        File dir = new File(uploadpath + File.separator + "datafiles");
//        if (!dir.exists()) dir.mkdirs();
//
//        File dir2 = new File(uploadpath + File.separator + "datafiles" + File.separator + "map-data");
//        if (!dir2.exists()) dir2.mkdirs();
//
//        String dbname = jdbcurl.substring(jdbcurl.lastIndexOf("/")+1);
//        String outmap_fullpath = dir2.getAbsolutePath() + File.separator + dbname + "-map.ttl";
//
//        List<String> cmdlist = new ArrayList<String>();
//        cmdlist.add("generate_mapping");
//        cmdlist.add("-b");
//        cmdlist.add(baseuri);
//
//        if(user != null && !user.isEmpty()){ cmdlist.add("-u"); cmdlist.add(user); }
//        if(password != null && !password.isEmpty()){ cmdlist.add("-p"); cmdlist.add(password); }
//        if(driver != null && !driver.isEmpty()){ cmdlist.add("-d"); cmdlist.add(driver); }
//
//        cmdlist.add("-o");
//        cmdlist.add(outmap_fullpath);
//        if(rml != null && !rml.isEmpty()){ cmdlist.add("-rml"); }
//        cmdlist.add(jdbcurl);
//
//        String[] mapping_cmd = cmdlist.toArray(new String[0]);
//
//        GeoTriplesCMD.main(mapping_cmd);
//        System.out.println("HERE IS RELATIONAL DB");
//
//        ModelAndView mav = new ModelAndView("redirect:/geotriples");
//        mav.addObject("fileName", dbname);
//        mav.addObject("outmap_fullpath", outmap_fullpath);
//
//        return mav;
//    }

    public static void GTRdbMapping(InputModel inputmodel) throws Exception {

        File dir = new File(inputmodel.getUploadpath() + File.separator + "datafiles");
        if (!dir.exists()) dir.mkdirs();

        File dir2 = new File(inputmodel.getUploadpath() + File.separator + "datafiles" + File.separator + "map-data");
        if (!dir2.exists()) dir2.mkdirs();

        String dbname = inputmodel.getJdbcurl().substring(inputmodel.getJdbcurl().lastIndexOf("/")+1);
        String outmap_fullpath = dir2.getAbsolutePath() + File.separator + dbname + "-map.ttl";

        List<String> cmdlist = new ArrayList<String>();
        cmdlist.add("generate_mapping");
        cmdlist.add("-b");
        cmdlist.add(inputmodel.getBaseuri());

        if(inputmodel.getUser() != null && !inputmodel.getUser().isEmpty()){ cmdlist.add("-u"); cmdlist.add(inputmodel.getUser()); }
        if(inputmodel.getPassword() != null && !inputmodel.getPassword().isEmpty()){ cmdlist.add("-p"); cmdlist.add(inputmodel.getPassword()); }
        if(inputmodel.getDriver() != null && !inputmodel.getDriver().isEmpty()){ cmdlist.add("-d"); cmdlist.add(inputmodel.getDriver()); }

        cmdlist.add("-o");
        cmdlist.add(outmap_fullpath);
//        if(rml != null && !rml.isEmpty()){ cmdlist.add("-rml"); }
        if( inputmodel.isRml() ){ cmdlist.add("-rml"); }
        cmdlist.add(inputmodel.getJdbcurl());

        String[] mapping_cmd = cmdlist.toArray(new String[0]);

        GeoTriplesCMD.main(mapping_cmd);
        System.out.println("HERE IS RELATIONAL DB");

        ModelAndView mav = new ModelAndView("redirect:/geotriples");
//        mav.addObject("fileName", dbname);
        inputmodel.setFilename(dbname);
//        mav.addObject("outmap_fullpath", outmap_fullpath);
        inputmodel.setOutmap_fullpath(outmap_fullpath);
    }

    /***************************************** Shapefile Service Method ***********************************************/
    public static ModelAndView GTShapeMapping(MultipartFile file, String uploadpath, String baseuri, String rml)
            throws Exception {

        if (!file.isEmpty()) {

            String name = file.getOriginalFilename();

            byte[] bytes = new byte[0];
            bytes = file.getBytes();

            File dir = new File(uploadpath + File.separator + "datafiles");
            if (!dir.exists()) dir.mkdirs();

            File dir2 = new File(uploadpath + File.separator + "datafiles" + File.separator + "input-data");
            if (!dir2.exists()) dir2.mkdirs();

            File dir3 = new File(uploadpath + File.separator + "datafiles" + File.separator + "map-data");
            if (!dir3.exists()) dir3.mkdirs();

            File serverFile = new File(dir2.getAbsolutePath() + File.separator + name);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            String inputfile_name = name.substring(0, name.indexOf('.'));
            String outmap_fullpath = dir3.getAbsolutePath() + File.separator + inputfile_name + "-map.ttl";

            List<String> cmdlist = new ArrayList<String>();
            cmdlist.add("generate_mapping");
            cmdlist.add("-b");
            cmdlist.add(baseuri);
            cmdlist.add("-o");
            cmdlist.add(outmap_fullpath);
            if(rml != null && !rml.isEmpty()){ cmdlist.add("-rml"); }
            cmdlist.add(serverFile.getAbsolutePath());

            String[] mapping_cmd = cmdlist.toArray(new String[0]);

            GeoTriplesCMD.main(mapping_cmd);
            System.out.println("HERE IS SHAPEFILE");

            ModelAndView mav = new ModelAndView("redirect:/geotriples");
            mav.addObject("fileName", name);
            mav.addObject("outmap_fullpath", outmap_fullpath);
            return mav;
        }
        else {
            System.out.println("BUG BUG BUG");
            ModelAndView mav = new ModelAndView("index");
            return mav;
        }
    }

    /****************************************** XMLfile Service Method ************************************************/
    public static ModelAndView GTXmlMapping(MultipartFile file, String uploadpath, String baseuri, String rootpath,
                                            String rootelement, String namespace, String namespaces,
                                            MultipartFile xsdfile, String rml) throws Exception {

        if (!file.isEmpty()) {

            String name = file.getOriginalFilename();
            byte[] bytes = new byte[0];
            bytes = file.getBytes();

            String xsd_name = null;
            byte[] xsd_bytes = new byte[0];
            File xsd_serverFile = null;

            File dir = new File(uploadpath + File.separator + "datafiles");
            if (!dir.exists()) dir.mkdirs();

            File dir2 = new File(uploadpath + File.separator + "datafiles" + File.separator + "input-data");
            if (!dir2.exists()) dir2.mkdirs();

            File dir3 = new File(uploadpath + File.separator + "datafiles" + File.separator + "map-data");
            if (!dir3.exists()) dir3.mkdirs();

            File serverFile = new File(dir2.getAbsolutePath() + File.separator + name);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            String inputfile_name = name.substring(0, name.indexOf('.'));
            String outmap_fullpath = dir3.getAbsolutePath() + File.separator + inputfile_name + "-map.ttl";

            /* Process for the XSD given file */
            if (!xsdfile.isEmpty()) {
                xsd_name = xsdfile.getOriginalFilename();
                xsd_bytes = xsdfile.getBytes();
                xsd_serverFile = new File(dir2.getAbsolutePath() + File.separator + xsd_name);

                BufferedOutputStream xsd_stream = new BufferedOutputStream(new FileOutputStream(xsd_serverFile));
                xsd_stream.write(xsd_bytes);
                xsd_stream.close();
            }

            //TODO NAMESPACES as List not only a String
            List<String> cmdlist = new ArrayList<String>();
            cmdlist.add("generate_mapping");
            cmdlist.add("-b");
            cmdlist.add(baseuri);
            cmdlist.add("-o");
            cmdlist.add(outmap_fullpath);
            if(rootpath != null && !rootpath.isEmpty()){ cmdlist.add("-rp"); cmdlist.add(rootpath); }
            if(rootelement != null && !rootelement.isEmpty()){ cmdlist.add("-r"); cmdlist.add(rootelement); }
            if(namespace != null && !namespace.isEmpty()){ cmdlist.add("-onlyns"); cmdlist.add(namespace); }
            if(namespaces != null && !namespaces.isEmpty()){ cmdlist.add("-ns"); cmdlist.add(namespaces); }
            if(xsd_name != null && !xsd_name.isEmpty()){ cmdlist.add("-x"); cmdlist.add(xsd_serverFile.getAbsolutePath()); }
            cmdlist.add(serverFile.getAbsolutePath());

            String[] mapping_cmd = cmdlist.toArray(new String[0]);

            GeoTriplesCMD.main(mapping_cmd);
            System.out.println("HERE IS SHAPEFILE");

            ModelAndView mav = new ModelAndView("redirect:/geotriples");
            mav.addObject("fileName", name);
            mav.addObject("outmap_fullpath", outmap_fullpath);
            return mav;
        }
        else {
            System.out.println("BUG BUG BUG");
            ModelAndView mav = new ModelAndView("index");
            return mav;
        }
    }
}
