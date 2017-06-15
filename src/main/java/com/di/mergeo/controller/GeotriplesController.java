package com.di.mergeo.controller;

import com.di.mergeo.model.InputModel;
import com.di.mergeo.service.GeotriplesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.ui.ModelMap;

import javax.servlet.ServletContext;

@Controller
public class GeotriplesController {

    @Autowired
    ServletContext context;

    /************************************** Handler for Relational Databse input **************************************/
//    @RequestMapping(value = "/geotriplesrdb", method = RequestMethod.POST)
//    public @ResponseBody
//    ModelAndView geotriplesRdbHandler(@RequestParam("baseuri") String baseuri,
//                                      @RequestParam(value="user", required = false) String user,
//                                      @RequestParam(value="password", required = false) String password,
//                                      @RequestParam(value="driver", required = false) String driver,
//                                      @RequestParam("jdbcurl") String jdbcurl,
//                                      @RequestParam(value="rml", required = false) String rml) throws Exception {
//
//        String uploadPath = context.getRealPath("");
//        return GeotriplesService.GTRdbMapping(baseuri, user, password, driver, jdbcurl, rml, uploadPath);
//    }

    @RequestMapping(value = "/geotriplesrdb", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("SpringWeb")InputModel inputmodel, ModelMap model) throws Exception {
        model.addAttribute("baseuri", inputmodel.getBaseuri());
        model.addAttribute("user", inputmodel.getUser());
        model.addAttribute("password", inputmodel.getPassword());
        model.addAttribute("driver", inputmodel.getDriver());
        model.addAttribute("jdbcurl", inputmodel.getJdbcurl());
        model.addAttribute("rml", inputmodel.isRml());
        inputmodel.setUploadpath(context.getRealPath(""));
        model.addAttribute("uploadpath", inputmodel.getUploadpath());

        GeotriplesService.GTRdbMapping(inputmodel);

        model.addAttribute("filename", inputmodel.getFilename());
        model.addAttribute("outmap_fullpath", inputmodel.getOutmap_fullpath());
        return "geotriples";
    }

    /****************************************** Handler for ShapeFile input *******************************************/
    @RequestMapping(value = "/geotriplesshape", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView geotriplesShapeHandler(@RequestParam("inputfile") MultipartFile file,
                                   @RequestParam("baseuri") String baseuri,
                                   @RequestParam(value="rml", required = false) String rml) throws Exception {

        String uploadpath = context.getRealPath("");
        return GeotriplesService.GTShapeMapping(file, uploadpath, baseuri, rml);
    }

    /******************************************* Handler for XML file input *******************************************/
    @RequestMapping(value = "/geotriplesxml", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView geotriplesXmlHandler(@RequestParam("inputfile") MultipartFile file,
                                      @RequestParam("baseuri") String baseuri,
                                      @RequestParam(value="rootpath", required = false) String rootpath,
                                      @RequestParam(value="rootelement", required = false) String rootelement,
                                      @RequestParam(value="namespace", required = false) String namespace,
                                      @RequestParam(value="namespaces", required = false) String namespaces,
                                      @RequestParam(value="xsdfile", required = false) MultipartFile xsdfile,
                                      @RequestParam("rml") String rml) throws Exception {

        String uploadpath = context.getRealPath("");
        return GeotriplesService.GTXmlMapping(file, uploadpath, baseuri, rootpath, rootelement, namespace,
                namespaces, xsdfile, rml);
    }
}