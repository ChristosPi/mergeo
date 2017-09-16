package com.di.mergeo.controller;

import com.di.mergeo.GeneralSPARQLEndpoint;
import com.di.mergeo.PostgreSQLJDBC;
import com.di.mergeo.model.EndpointModel;
import com.di.mergeo.service.EndpointService;
import com.di.mergeo.strabonEndpointClient.EndpointResult;
import com.di.mergeo.strabonEndpointClient.stSPARQLQueryResultFormat;
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
public class EndpointController {

    @Autowired
    ServletContext context;

    public static String QUERY_PREFIX = "PREFIX lgd:<http://linkedgeodata.org/triplify/>\n" +
            "PREFIX lgdgeo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\n" +
            "PREFIX lgdont:<http://linkedgeodata.org/ontology/>\n" +
            "PREFIX geonames:<http://www.geonames.org/ontology#>\n" +
            "PREFIX clc: <http://geo.linkedopendata.gr/corine/ontology#>\n" +
            "PREFIX gag: <http://geo.linkedopendata.gr/greekadministrativeregion/ontology#>\n" +
            "PREFIX geo: <http://www.opengis.net/ont/geosparql#>\n" +
            "PREFIX geof: <http://www.opengis.net/def/function/geosparql/>\n" +
            "PREFIX geor: <http://www.opengis.net/def/rule/geosparql/>\n" +
            "PREFIX strdf: <http://strdf.di.uoa.gr/ontology#>\n" +
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX uom: <http://www.opengis.net/def/uom/OGC/1.0/>\n\n";

    @RequestMapping(value = "/endpoint_create", method = RequestMethod.POST)
    public ModelAndView endpointCreate(@Valid @ModelAttribute("command") EndpointModel endmodel, ModelMap model,
                                       BindingResult result)
            throws SQLException, ClassNotFoundException, IOException, InterruptedException {

        EndpointValidator endValidator = new EndpointValidator();
        endValidator.validate(endmodel, result);

        if (result.hasErrors()){
            System.out.println(result.getFieldError().toString());

            ModelAndView mav = new ModelAndView("endpoint", "command", endmodel);
            mav.addObject("formError", true);
            mav.addObject("errors", result);
            return mav;
        }

        PostgreSQLJDBC.dbCreate(endmodel);
        EndpointService.strabonDeploy(endmodel);

        TimeUnit.SECONDS.sleep(10);

        ModelAndView mav = new ModelAndView("endpoint_done");
        mav.addObject("endpoint", endmodel);
        mav.addObject("workEndpoint", endmodel);

        return mav;
    }
    /******************************************************************************************************************/
    @RequestMapping(value = "/endpoint_run", method = RequestMethod.GET)
    public ModelAndView endpointRun(HttpServletRequest request){

        EndpointModel endpoint = (EndpointModel)request.getSession().getAttribute("endpoint");

        if(endpoint != null){
            ModelAndView mav = new ModelAndView("endpoint_done");
            mav.addObject("workEndpoint", endpoint);
            return mav;
        }
        else{
            ModelAndView mav = new ModelAndView("endpoint", "command", new EndpointModel());
            mav.addObject("no_end", true);
            return mav;
        }
    }
    /******************************************************************************************************************/
    @RequestMapping(value = "/endpoint_default", method = RequestMethod.GET)
    public ModelAndView endpointDefault(){

        ModelAndView mav = new ModelAndView("endpoint_done");
        mav.addObject("workEndpoint", ((EndpointModel) context.getAttribute("defEndpoint")));

        return mav;
    }

    /* ************************************************************************************************************** */
    /******************************************************************************************************************/

    @RequestMapping(value = "/do_query", method = RequestMethod.POST)
    public ModelAndView doQuery(@RequestParam("query") String query, @RequestParam("format") String format,
                                HttpServletRequest request) throws IOException {

        EndpointModel endmodel = (EndpointModel)request.getSession().getAttribute("workEndpoint");

        GeneralSPARQLEndpoint endpoint = new GeneralSPARQLEndpoint("localhost", 8080, endmodel.getEndpointname().concat("/Query"));
        endpoint.setUser(endmodel.getCp_username());
        endpoint.setPassword(endmodel.getCp_password());

        EndpointResult result = endpoint.query(query, (stSPARQLQueryResultFormat) stSPARQLQueryResultFormat.valueOf(format), "strabon");

        ModelAndView mav = new ModelAndView("endpoint_done");
        mav.addObject("endpointResults", result);
        mav.addObject("query", query);
        mav.addObject("out_format", format);

        return mav;
    }

    /******************************************************************************************************************/
    @RequestMapping(value = "/do_update", method = RequestMethod.POST)
    public ModelAndView doUpdate(@RequestParam("query") String query, @RequestParam("format") String format,
                                 HttpServletRequest request) throws IOException {

        EndpointModel endmodel = (EndpointModel)request.getSession().getAttribute("workEndpoint");
        GeneralSPARQLEndpoint endpoint = new GeneralSPARQLEndpoint("localhost", 8080, endmodel.getEndpointname().concat("/Update"));
        endpoint.setUser(endmodel.getCp_username());
        endpoint.setPassword(endmodel.getCp_password());

        // TODO - Return something like flag, to inform the user about the update Status
        boolean check = endpoint.update(query);
        ModelAndView mav = new ModelAndView("endpoint_done");
        mav.addObject("query", query);
        mav.addObject("out_format", format);

        return mav;

    }
    /******************************************************************************************************************/
    @RequestMapping(value = "/endpoint/exquery", method = RequestMethod.POST)
    public ModelAndView doExQuery(@RequestParam("example") String example, HttpServletRequest request) throws IOException {

        EndpointModel endmodel = (EndpointModel) request.getSession().getAttribute("workEndpoint");
//        EndpointModel endmodel = (EndpointModel)context.getAttribute("defEndpoint");

        GeneralSPARQLEndpoint endpoint = new GeneralSPARQLEndpoint("localhost", 8080, endmodel.getEndpointname().concat("/Query"));
        endpoint.setUser(endmodel.getCp_username());
        endpoint.setPassword(endmodel.getCp_password());
        String query;
        String format = "HTML";

        // SELECT * WHERE { ?s ?p ?o }
        if( example.equals("1") ){
            query = QUERY_PREFIX + "SELECT * \n" +
                    "WHERE {\n" +
                    "\t?s ?p ?o \n" +
                    "}";
        }
        // SELECT DISTINCT (?s AS ?subject) WHERE { ?s ?p ?o }
        else if( example.equals("2")){
            query = QUERY_PREFIX + "SELECT DISTINCT (?s AS ?subject)\n" +
                    "WHERE {\n" +
                    "\t?s ?p ?o \n" +
                    "}";
        }
        // SELECT (COUNT(?s) AS ?NumOfTriples) WHERE { ?s ?p ?o }
        else if( example.equals("3")){
            query = QUERY_PREFIX + "SELECT (COUNT(?s) AS ?NumOfTriples)\n" +
                    "WHERE {\n" +
                    "\t?s ?p ?o \n" +
                    "}";
        }
        // SELECT * WHERE { ?s ?p ?o } LIMIT 10
        else{
            query = QUERY_PREFIX + "SELECT * \n" +
                    "WHERE {\n" +
                    "\t?s ?p ?o \n" +
                    "}\n" +
                    "LIMIT 10";
        }

        EndpointResult results = endpoint.query(query, (stSPARQLQueryResultFormat) stSPARQLQueryResultFormat.valueOf(format), "strabon");
        ModelAndView mav = new ModelAndView("endpoint_done");
        mav.addObject("endpointResults", results);
        mav.addObject("query", query);
        mav.addObject("out_format", format);

        /*  Prints to console the query results as string */
//        System.out.println(results.getResponse().toString());

        return mav;
    }
    /******************************************************************************************************************/

    @RequestMapping(value = "/endpoint/direct_store", method = RequestMethod.POST)
    public ModelAndView directStore(@RequestParam(value = "graph", required=false) String graph,
                                    @RequestParam("dinput") String input,
                                    @RequestParam("rdfformat") String rdfformat,
                                    @RequestParam(value = "inference", required=false) String inference,
                                    HttpServletRequest request) throws IOException {

//        Store N-Triples example :)
//        String testTriplet = "<http://example.org/#Spiderman> <http://www.perceive.net/schemas/relationship/enemyOf> <http://example.org/#green-goblin> .";

        EndpointModel endmodel = (EndpointModel)request.getSession().getAttribute("workEndpoint");
        GeneralSPARQLEndpoint endpoint = new GeneralSPARQLEndpoint("localhost", 8080, endmodel.getEndpointname().concat("/Store"));
        endpoint.setUser(endmodel.getCp_username());
        endpoint.setPassword(endmodel.getCp_password());

        boolean storeStatus;
        RDFFormat format;
        URL graphURL;

        if(inference != null)
        { /* TODO Something */ }
        else
        { /* TODO Something else */ }

        if( rdfformat.equals("NTRIPLES") ) format = RDFFormat.NTRIPLES;
        else if( rdfformat.equals("XML") ) format = RDFFormat.RDFXML;
        else format = RDFFormat.TURTLE;

        /* If Graph input has been given */
        if( graph != null && !graph.isEmpty()){
            graphURL = new URL(graph);
            storeStatus = endpoint.store(input, format, graphURL);
        }
        /* Else sent null as graph */
        else{
            storeStatus = endpoint.store(input, format, null);
        }

        ModelAndView mav = new ModelAndView("endpoint_done");
        mav.addObject("storeStatus", storeStatus);
        return mav;
    }
    /******************************************************************************************************************/

    @RequestMapping(value = "/endpoint/uri_store", method = RequestMethod.POST)
    public ModelAndView uriStore(@RequestParam("uri_input") String uri_input,
                                 @RequestParam("uri_rdfformat") String rdfformat,
                                 @RequestParam(value = "uri_graph", required=false) String graph,
                                 HttpServletRequest request) throws IOException {

        EndpointModel endmodel = (EndpointModel)request.getSession().getAttribute("workEndpoint");
        GeneralSPARQLEndpoint endpoint = new GeneralSPARQLEndpoint("localhost", 8080, endmodel.getEndpointname().concat("/Store"));
        endpoint.setUser(endmodel.getCp_username());
        endpoint.setPassword(endmodel.getCp_password());

        boolean storeStatus;
        RDFFormat format;
        URL input = new URL(uri_input);
        URL graphURL;

        if( rdfformat.equals("NTRIPLES") ) format = RDFFormat.NTRIPLES;
        else if( rdfformat.equals("XML") ) format = RDFFormat.RDFXML;
        else format = RDFFormat.TURTLE;

        /* If Graph input has been given */
        if( graph != null && !graph.isEmpty()){
            graphURL = new URL(graph);
            storeStatus = endpoint.store(input, format, graphURL);
        }
        /* Else sent null as graph */
        else{
            storeStatus = endpoint.store(input, format, null);
        }

        ModelAndView mav = new ModelAndView("endpoint_done");
        mav.addObject("storeStatus", storeStatus);
        return mav;
    }
    /******************************************************************************************************************/

}
