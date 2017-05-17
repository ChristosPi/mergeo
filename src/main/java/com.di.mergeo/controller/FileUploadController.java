package com.di.mergeo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
    RedirectView uploadFileHandler(@RequestParam("file") MultipartFile file) {

        String name = file.getOriginalFilename();

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                String uploadPath = context.getRealPath("");

//                String type = file.getContentType();
//                System.out.println(name + " --- " + type);
//                String rootPath = System.getProperty("catalina.home");

                File dir = new File(uploadPath + File.separator + "tmpFiles");

                if (!dir.exists()) dir.mkdirs();

                File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                RedirectView rv = new RedirectView("index");
                return rv;
//                return "You successfully uploaded file=" + name;
            } catch (Exception e) {
//                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
//            return "You failed to upload " + name
//                    + " because the file was empty.";
        }
        return null;
    }
}