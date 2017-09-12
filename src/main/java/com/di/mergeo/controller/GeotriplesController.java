package com.di.mergeo.controller;

import com.di.mergeo.GeneralSPARQLEndpoint;
import com.di.mergeo.PostgreSQLJDBC;
import com.di.mergeo.model.EndpointModel;
import com.di.mergeo.model.MapInputModel;
import com.di.mergeo.model.RdfInputModel;
import com.di.mergeo.service.EndpointService;
import com.di.mergeo.service.GeotriplesService;
import com.di.mergeo.validator.EndpointValidator;
import org.openrdf.rio.RDFFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

@Controller
@SessionAttributes({"endpoint", "workEndpoint"})
public class GeotriplesController {

    @Autowired
    ServletContext context;

    /*******************************************************************************************************************
     *
     *                                          Mapping Transformation!
     *
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
     *
     *                                          RDF Transformation!
     *
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
        mav.addObject("outrdf_format", rdfinputmodel.getRdb_format());

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
        mav.addObject("outrdf_format", rdfinputmodel.getShp_format());

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
        mav.addObject("outrdf_format", rdfinputmodel.getXml_format());

        return mav;
    }

    /*******************************************************************************************************************
     *
     *                                         Additional Handlers()
     *
     ******************************************************************************************************************/

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

        return mav;
    }

    /*******************************************************************************************************************
     ******************************************************************************************************************/
    @RequestMapping(value = "/geotriples_rdf_save", method = RequestMethod.POST)
    public ModelAndView rdf_saveChanges(@RequestParam("name") String name,
                                        @RequestParam("outrdf_fullpath") String outrdf_fullpath,
                                        @RequestParam("outrdf_format") String outrdf_format,
                                        @RequestParam("rdf_data") String rdf_data) throws IOException {

        ModelAndView mav = new ModelAndView("geotriples_final", "command", new EndpointModel());

        mav.addObject("name", name);
        mav.addObject("outrdf_fullpath", outrdf_fullpath);
        mav.addObject("outrdf_format", outrdf_format);
        mav.addObject("changed",true);

        GeotriplesService.saveFileChanges(outrdf_fullpath, rdf_data);

        return mav;
    }

    /*******************************************************************************************************************
     ******************************************************************************************************************/
    @RequestMapping(value = "/geotriples_defstore", method = RequestMethod.POST)
    public ModelAndView gt_defStore(@RequestParam("rdf_input_path") String rdf_input_path,
                                    @RequestParam("rdf_input_format") String rdf_input_format,
                                    HttpServletRequest request) throws IOException {

        EndpointModel endmodel = (EndpointModel) context.getAttribute("defEndpoint");
        request.getSession().setAttribute("workEndpoint", endmodel);

        GeneralSPARQLEndpoint endpoint = new GeneralSPARQLEndpoint("localhost", 8080, endmodel.getEndpointname().concat("/Store"));
        endpoint.setUser(endmodel.getCp_username());
        endpoint.setPassword(endmodel.getCp_password());

        RDFFormat format;
        String correct_path = "file://" + rdf_input_path;

        URL input = new URL(correct_path);

        if( rdf_input_format.equals("NTRIPLES") ) format = RDFFormat.NTRIPLES;
        else if( rdf_input_format.equals("RDFXML") ) format = RDFFormat.RDFXML;
        else format = RDFFormat.TURTLE;

        // TODO - 3rd parameter "graph" must be URL type
        boolean storeStatus = endpoint.store(input, format, null);

        ModelAndView mav = new ModelAndView("endpoint_done");
        mav.addObject("workEndpoint", endmodel);
        mav.addObject("storeStatus", storeStatus);
        return mav;
    }

    /*******************************************************************************************************************
     ******************************************************************************************************************/
    @RequestMapping(value = "/geotriples_curstore", method = RequestMethod.POST)
    public ModelAndView gt_curStore(@RequestParam("rdf_input_path") String rdf_input_path,
                                    @RequestParam("rdf_input_format") String rdf_input_format,
                                    HttpServletRequest request) throws IOException {

        EndpointModel endmodel = (EndpointModel)request.getSession().getAttribute("endpoint");
        request.getSession().setAttribute("workEndpoint", endmodel);

        GeneralSPARQLEndpoint endpoint = new GeneralSPARQLEndpoint("localhost", 8080, endmodel.getEndpointname().concat("/Store"));
        endpoint.setUser(endmodel.getCp_username());
        endpoint.setPassword(endmodel.getCp_password());

        RDFFormat format;
        String correct_path = "file://" + rdf_input_path;

        URL input = new URL(correct_path);

        if( rdf_input_format.equals("NTRIPLES") ) format = RDFFormat.NTRIPLES;
        else if( rdf_input_format.equals("RDFXML") ) format = RDFFormat.RDFXML;
        else format = RDFFormat.TURTLE;

        // TODO - 3rd parameter "graph" must be URL type
        boolean storeStatus = endpoint.store(input, format, null);

        ModelAndView mav = new ModelAndView("endpoint_done");
        mav.addObject("workEndpoint", endmodel);
        mav.addObject("storeStatus", storeStatus);
        return mav;
    }

    /*******************************************************************************************************************
     ******************************************************************************************************************/
    @RequestMapping(value = "/geotriples_createnstore", method = RequestMethod.POST)
    public ModelAndView endpointCreate(@Valid @ModelAttribute("command")EndpointModel endmodel, ModelMap model, BindingResult result,
                                       @RequestParam("rdf_input_path") String rdf_input_path,
                                       @RequestParam("rdf_input_format") String rdf_input_format,
                                       @RequestParam("rdf_input_name") String rdf_input_name
                                       ) throws SQLException, ClassNotFoundException, IOException, InterruptedException {

        EndpointValidator endValidator = new EndpointValidator();
        endValidator.validate(endmodel, result);

        if (result.hasErrors()){
            System.out.println(result.getFieldError().toString());

            ModelAndView mav = new ModelAndView("geotriples_final", "command", endmodel);

            mav.addObject("name", rdf_input_name);
            mav.addObject("outrdf_fullpath", rdf_input_path);
            mav.addObject("outrdf_format", rdf_input_format);
            mav.addObject("formError", true);
            mav.addObject("errors", result);
            return mav;
        }

        PostgreSQLJDBC.dbCreate(endmodel);
        EndpointService.strabonDeploy(endmodel);

        TimeUnit.SECONDS.sleep(15);

        GeneralSPARQLEndpoint endpoint = new GeneralSPARQLEndpoint("localhost", 8080, endmodel.getEndpointname().concat("/Store"));
        endpoint.setUser(endmodel.getCp_username());
        endpoint.setPassword(endmodel.getCp_password());

        RDFFormat format;
        String correct_path = "file://" + rdf_input_path;

        URL input = new URL(correct_path);

        if( rdf_input_format.equals("NTRIPLES") ) format = RDFFormat.NTRIPLES;
        else if( rdf_input_format.equals("RDFXML") ) format = RDFFormat.RDFXML;
        else format = RDFFormat.TURTLE;

        // TODO - 3rd parameter "graph" must be URL type
        boolean storeStatus = endpoint.store(input, format, null);

        ModelAndView mav = new ModelAndView("endpoint_done");
        mav.addObject("endpoint", endmodel);
        mav.addObject("workEndpoint", endmodel);
        mav.addObject("storeStatus", storeStatus);
        return mav;

    }
}