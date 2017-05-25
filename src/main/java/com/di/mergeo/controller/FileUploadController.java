package com.di.mergeo.controller;

import com.di.mergeo.service.GeotriplesService;
import eu.linkedeodata.geotriples.GeoTriplesCMD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class FileUploadController {

    @Autowired
    ServletContext context;

    @RequestMapping(value = "/geotriplesFile", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView uploadFileHandler(@RequestParam("file") MultipartFile file) throws Exception {

        String name = file.getOriginalFilename();

        if (!file.isEmpty()) {

            byte[] bytes = new byte[0];
            bytes = file.getBytes();

            String uploadPath = context.getRealPath("");

//                String type = file.getContentType();
//                System.out.println(name + " --- " + type);
//                String rootPath = System.getProperty("catalina.home");

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

            String[] geotriples_mapping = new GeotriplesService().geotriplesMapping(
                    name.substring(0, name.indexOf('.')), serverFile.getAbsolutePath(), dir3.getAbsolutePath());

            GeoTriplesCMD.main(geotriples_mapping);
            System.out.println("HERE HERE HERE");

//            RedirectView rv = new RedirectView("geotriples_success");
//            rv.addStaticAttribute("fileName", name);
            ModelAndView mav = new ModelAndView("redirect:/geotriples_success");
            mav.addObject("fileName", name);
            return mav;
        }
        else {
            System.out.println("BUG BUG BUG");
            RedirectView rv = new RedirectView("index");
            return null;
        }
    }
}