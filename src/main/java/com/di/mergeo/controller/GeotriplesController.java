package com.di.mergeo.controller;

import com.di.mergeo.model.EndpointModel;
import com.di.mergeo.model.MapInputModel;
import com.di.mergeo.model.RdfInputModel;
import com.di.mergeo.service.GeotriplesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.IOException;

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

        ModelAndView mav = new ModelAndView("geotriples_rdf", "command", new RdfInputModel());

        mav.addObject("name", inputmodel.getName());
        mav.addObject("outmap_fullpath", inputmodel.getOutmap_fullpath());
        mav.addObject("type", inputmodel.getType());

        return mav;
    }

    /****************************************** Handler for ShapeFile input *******************************************/
    @RequestMapping(value = "/geotriples_shape", method = RequestMethod.POST)
    public ModelAndView shpInput(@ModelAttribute("SpringWeb")MapInputModel inputmodel, ModelMap model) throws Exception {

        inputmodel.setUploadpath(context.getRealPath(""));
        inputmodel.setType("shp");

        GeotriplesService.GTShapeMapping(inputmodel);

        ModelAndView mav = new ModelAndView("geotriples_rdf", "command", new RdfInputModel());

        mav.addObject("name", inputmodel.getName());
        mav.addObject("outmap_fullpath", inputmodel.getOutmap_fullpath());
        mav.addObject("type", inputmodel.getType());

        return mav;
    }

    /******************************************* Handler for XML file input *******************************************/
    @RequestMapping(value = "/geotriples_xml", method = RequestMethod.POST)
    public ModelAndView xmlInput(@ModelAttribute("SpringWeb")MapInputModel inputmodel, ModelMap model) throws Exception {

        inputmodel.setUploadpath(context.getRealPath(""));
        inputmodel.setType("xml");

        GeotriplesService.GTXmlMapping(inputmodel);

        ModelAndView mav = new ModelAndView("geotriples_rdf", "command", new RdfInputModel());

        mav.addObject("name", inputmodel.getName());
        mav.addObject("outmap_fullpath", inputmodel.getOutmap_fullpath());
        mav.addObject("type", inputmodel.getType());

        return mav;
    }

    /*******************************************************************************************************************
    *                                           RDF Transformation!
     ******************************************************************************************************************/

    /*************************************** Handler for RDF Relational Database **************************************/
    @RequestMapping(value = "/geotriples_rdf_rdb", method = RequestMethod.POST)
    public ModelAndView rdf_rdbInput(@ModelAttribute("SpringWeb")RdfInputModel rdfinputmodel, ModelMap model) throws Exception {

        rdfinputmodel.setUploadpath(context.getRealPath(""));
        rdfinputmodel.setType("rdb");

        GeotriplesService.GTRdbToRdf(rdfinputmodel);

        ModelAndView mav = new ModelAndView("geotriples_final", "command", new EndpointModel());

        mav.addObject("name", rdfinputmodel.getName());
        mav.addObject("outrdf_fullpath", rdfinputmodel.getOutrdf_fullpath());

        return mav;
    }

    /******************************************** Handler for RDF Shapefile *******************************************/
    @RequestMapping(value = "/geotriples_rdf_shp", method = RequestMethod.POST)
    public ModelAndView rdf_shpInput(@ModelAttribute("SpringWeb")RdfInputModel rdfinputmodel, ModelMap model) throws Exception {

        rdfinputmodel.setUploadpath(context.getRealPath(""));
        rdfinputmodel.setType("shp");

        GeotriplesService.GTShpToRdf(rdfinputmodel);

        ModelAndView mav = new ModelAndView("geotriples_final", "command", new EndpointModel());

        mav.addObject("name", rdfinputmodel.getName());
        mav.addObject("outrdf_fullpath", rdfinputmodel.getOutrdf_fullpath());

        return mav;
    }

    /******************************************** Handler for RDF XML/JSON ********************************************/
    @RequestMapping(value = "/geotriples_rdf_xml", method = RequestMethod.POST)
    public ModelAndView rdf_xmlInput(@ModelAttribute("SpringWeb")RdfInputModel rdfinputmodel, ModelMap model) throws Exception {

        rdfinputmodel.setUploadpath(context.getRealPath(""));
        rdfinputmodel.setType("xml");

        GeotriplesService.GTXmlToRdf(rdfinputmodel);

        ModelAndView mav = new ModelAndView("geotriples_final", "command", new EndpointModel());

        mav.addObject("name", rdfinputmodel.getName());
        mav.addObject("outrdf_fullpath", rdfinputmodel.getOutrdf_fullpath());

        return mav;
    }

    /*******************************************************************************************************************
    *******************************************************************************************************************/
    @RequestMapping(value = "/geotriples_map_save", method = RequestMethod.POST)
    public ModelAndView map_saveChanges(@RequestParam("name") String name,
                                        @RequestParam("outmap_fullpath") String outmap_fullpath,
                                        @RequestParam("type") String type,
                                        @RequestParam("map_data") String map_data) throws IOException {

        ModelAndView mav = new ModelAndView("geotriples_rdf", "command", new RdfInputModel());

        mav.addObject("name", name);
        mav.addObject("outmap_fullpath", outmap_fullpath);
        mav.addObject("type", type);
        mav.addObject("changed",true);

        GeotriplesService.saveFileChanges(outmap_fullpath, map_data);

//        System.out.println(map_data);
//        System.out.println(name);
//        System.out.println(outmap_fullpath);
//        System.out.println(type);

        return mav;
    }
    /*******************************************************************************************************************
     ******************************************************************************************************************/
    @RequestMapping(value = "/geotriples_rdf_save", method = RequestMethod.POST)
    public ModelAndView rdf_saveChanges(@RequestParam("name") String name,
                                        @RequestParam("outrdf_fullpath") String outrdf_fullpath,
                                        @RequestParam("rdf_data") String rdf_data) throws IOException {

        ModelAndView mav = new ModelAndView("geotriples_final", "command", new EndpointModel());

        mav.addObject("name", name);
        mav.addObject("outrdf_fullpath", outrdf_fullpath);
        mav.addObject("changed",true);

        GeotriplesService.saveFileChanges(outrdf_fullpath, rdf_data);

        return mav;
    }
}