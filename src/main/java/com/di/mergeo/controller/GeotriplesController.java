package com.di.mergeo.controller;

import com.di.mergeo.model.MapInputModel;
import com.di.mergeo.model.RdfInputModel;
import com.di.mergeo.service.GeotriplesService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;

@Controller
public class GeotriplesController {

    @Autowired
    ServletContext context;

    /*******************************************************************************************************************
     *                                       Mapping Transformation!
     ******************************************************************************************************************/

    /************************************** Handler for Relational Databse input **************************************/
    @RequestMapping(value = "/geotriples_rdb", method = RequestMethod.POST)
    public ModelAndView rdbInput(@ModelAttribute("SpringWeb")MapInputModel inputmodel, ModelMap model) throws Exception {

        inputmodel.setUploadpath(context.getRealPath(""));
        inputmodel.setType("rdb");

        GeotriplesService.GTRdbMapping(inputmodel);

        model.addAttribute("name", inputmodel.getName());
        model.addAttribute("outmap_fullpath", inputmodel.getOutmap_fullpath());
        model.addAttribute("type", inputmodel.getType());

        return new ModelAndView("geotriples_rdf", "command", new RdfInputModel());
    }

    /****************************************** Handler for ShapeFile input *******************************************/
    @RequestMapping(value = "/geotriples_shape", method = RequestMethod.POST)
    public ModelAndView shpInput(@ModelAttribute("SpringWeb")MapInputModel inputmodel, ModelMap model) throws Exception {

        inputmodel.setUploadpath(context.getRealPath(""));
        inputmodel.setType("shp");

        GeotriplesService.GTShapeMapping(inputmodel);

        model.addAttribute("name", inputmodel.getName());
        model.addAttribute("outmap_fullpath", inputmodel.getOutmap_fullpath());
        model.addAttribute("type", inputmodel.getType());

        return new ModelAndView("geotriples_rdf", "command", new RdfInputModel());
    }

    /******************************************* Handler for XML file input *******************************************/
    @RequestMapping(value = "/geotriples_xml", method = RequestMethod.POST)
    public ModelAndView xmlInput(@ModelAttribute("SpringWeb")MapInputModel inputmodel, ModelMap model) throws Exception {

        inputmodel.setUploadpath(context.getRealPath(""));
        inputmodel.setType("xml");

        GeotriplesService.GTXmlMapping(inputmodel);

        model.addAttribute("name", inputmodel.getName());
        model.addAttribute("outmap_fullpath", inputmodel.getOutmap_fullpath());
        model.addAttribute("type", inputmodel.getType());

        return new ModelAndView("geotriples_rdf", "command", new RdfInputModel());
    }

    /*******************************************************************************************************************
    *                                           RDF Transformation!
     ******************************************************************************************************************/

    /*************************************** Handler for RDF Relational Database **************************************/
    @RequestMapping(value = "/geotriples_rdf_rdb", method = RequestMethod.POST)
    public String rdf_rdbInput(@ModelAttribute("SpringWeb")RdfInputModel rdfinputmodel, ModelMap model) throws Exception {

        rdfinputmodel.setUploadpath(context.getRealPath(""));
        rdfinputmodel.setType("rdb");

        GeotriplesService.GTRdbToRdf(rdfinputmodel);

        return "geotriples_final";
    }
    /******************************************** Handler for RDF Shapefile *******************************************/
    @RequestMapping(value = "/geotriples_rdf_shp", method = RequestMethod.POST)
    public String rdf_shpInput(@ModelAttribute("SpringWeb")RdfInputModel rdfinputmodel, ModelMap model) throws Exception {

        rdfinputmodel.setUploadpath(context.getRealPath(""));
        rdfinputmodel.setType("shp");

        GeotriplesService.GTShpToRdf(rdfinputmodel);

        return "geotriples_final";
    }
    /******************************************** Handler for RDF XML/JSON ********************************************/
    @RequestMapping(value = "/geotriples_rdf_xml", method = RequestMethod.POST)
    public String rdf_xmlInput(@ModelAttribute("SpringWeb")RdfInputModel rdfinputmodel, ModelMap model) throws Exception {

        rdfinputmodel.setUploadpath(context.getRealPath(""));
        rdfinputmodel.setType("xml");

        GeotriplesService.GTXmlToRdf(rdfinputmodel);

        return "geotriples_final";
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String handleResourceNotFoundException() {
//        return "error";
//    }
}