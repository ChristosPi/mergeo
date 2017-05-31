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

import javax.servlet.ServletContext;

@Controller
public class GeotriplesController {

    @Autowired
    ServletContext context;

    /************************************** Handler for Relational Databse input **************************************/
    @RequestMapping(value = "/geotriplesRdb", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView geotriplesRdbHandler(@RequestParam("inputfile") MultipartFile file,
                                      @RequestParam("baseuri") String baseuri,
                                      @RequestParam("rml") String rml) throws Exception {

        return GeotriplesService.geotriplesRdbServ();
    }
    /****************************************** Handler for ShapeFile input *******************************************/
    @RequestMapping(value = "/geotriplesShape", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView geotriplesShapeHandler(@RequestParam("inputfile") MultipartFile file,
                                   @RequestParam("baseuri") String baseuri,
                                   @RequestParam(value="rml", required = false) String rml) throws Exception {

        String uploadPath = context.getRealPath("");
        return GeotriplesService.geotriplesShapeServ(file, uploadPath, baseuri, rml);
    }

    /******************************************* Handler for XML file input *******************************************/
    @RequestMapping(value = "/geotriplesXml", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView geotriplesXmlHandler(@RequestParam("inputfile") MultipartFile file,
                                        @RequestParam("baseuri") String baseuri,
                                        @RequestParam("rml") String rml) throws Exception {

        String uploadPath = context.getRealPath("");

        ModelAndView mav = GeotriplesService.geotriplesShapeServ(file, uploadPath, baseuri, rml);

        return mav;
    }
}