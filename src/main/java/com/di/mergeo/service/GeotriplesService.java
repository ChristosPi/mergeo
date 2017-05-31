package com.di.mergeo.service;

import eu.linkedeodata.geotriples.GeoTriplesCMD;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GeotriplesService {

    /************************************ Relational Database Service Method ******************************************/
    public static ModelAndView geotriplesRdbServ(){
        return null;
    }
    /***************************************** Shapefile Service Method ***********************************************/
    public static ModelAndView geotriplesShapeServ(MultipartFile file, String uploadPath, String baseuri, String rml)
            throws Exception {

        if (!file.isEmpty()) {

            String name = file.getOriginalFilename();

            byte[] bytes = new byte[0];
            bytes = file.getBytes();

            File dir = new File(uploadPath + File.separator + "datafiles");
            if (!dir.exists()) dir.mkdirs();

            File dir2 = new File(uploadPath + File.separator + "datafiles" + File.separator + "input-data");
            if (!dir2.exists()) dir2.mkdirs();

            File dir3 = new File(uploadPath + File.separator + "datafiles" + File.separator + "map-data");
            if (!dir3.exists()) dir3.mkdirs();

            File serverFile = new File(dir2.getAbsolutePath() + File.separator + name);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            String inputfile_name = name.substring(0, name.indexOf('.'));
            String outmap_fullpath = dir3.getAbsolutePath() + File.separator + inputfile_name + "-map.tll";

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
            System.out.println("HERE HERE HERE");

            FileInputStream outmap_code_st = new FileInputStream(outmap_fullpath);

            String outmap_code = org.apache.commons.io.IOUtils.toString(outmap_code_st);

            ModelAndView mav = new ModelAndView("redirect:/geotriples");
            mav.addObject("fileName", name);
            mav.addObject("outmap_code", outmap_code);
            return mav;
        }
        else {
            System.out.println("BUG BUG BUG");
            ModelAndView mav = new ModelAndView("index");
            return mav;
        }
    }

    /****************************************** XMLfile Service Method ************************************************/
    public static ModelAndView geotriplesXmlServ(){
        return null;
    }
}
