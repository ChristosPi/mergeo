package com.di.mergeo.controller;

import com.di.mergeo.model.InputModel;
import com.di.mergeo.service.GeotriplesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.ui.ModelMap;

import javax.servlet.ServletContext;

@Controller
public class GeotriplesController {

    @Autowired
    ServletContext context;

    /************************************** Handler for Relational Databse input **************************************/
    @RequestMapping(value = "/geotriples_rdb", method = RequestMethod.POST)
    public String rdbInput(@ModelAttribute("SpringWeb")InputModel inputmodel, ModelMap model) throws Exception {

        inputmodel.setUploadpath(context.getRealPath(""));
        inputmodel.setType("rdb");

        GeotriplesService.GTRdbMapping(inputmodel);

        model.addAttribute("name", inputmodel.getName());
        model.addAttribute("outmap_fullpath", inputmodel.getOutmap_fullpath());
        model.addAttribute("type", inputmodel.getType());

        return "geotriples";
    }

    /****************************************** Handler for ShapeFile input *******************************************/
    @RequestMapping(value = "/geotriples_shape", method = RequestMethod.POST)
    public String shpInput(@ModelAttribute("SpringWeb")InputModel inputmodel, ModelMap model) throws Exception {

        inputmodel.setUploadpath(context.getRealPath(""));
        inputmodel.setType("shp");

        GeotriplesService.GTShapeMapping(inputmodel);

        model.addAttribute("name", inputmodel.getName());
        model.addAttribute("outmap_fullpath", inputmodel.getOutmap_fullpath());
        model.addAttribute("type", inputmodel.getType());

        return "geotriples";
    }

    /******************************************* Handler for XML file input *******************************************/
    @RequestMapping(value = "/geotriples_xml", method = RequestMethod.POST)
    public String xmlInput(@ModelAttribute("SpringWeb")InputModel inputmodel, ModelMap model) throws Exception {

        inputmodel.setUploadpath(context.getRealPath(""));
        inputmodel.setType("xml");

        GeotriplesService.GTXmlMapping(inputmodel);

        model.addAttribute("name", inputmodel.getName());
        model.addAttribute("outmap_fullpath", inputmodel.getOutmap_fullpath());
        model.addAttribute("type", inputmodel.getType());

        return "geotriples";
    }
}