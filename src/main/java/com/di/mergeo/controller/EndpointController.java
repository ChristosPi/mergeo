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
    @RequestMapping(value = "/do_store", method = RequestMethod.POST)
    public ModelAndView doStore(HttpServletRequest request)  throws IOException {


//        String rdf_file = "/home/chrispi/mergeo/target/mergeo/datafiles/rdf-data/endpoint-rdf.nt";
//        FileInputStream filestream = new FileInputStream(rdf_file);
//        String rdf_string= org.apache.commons.io.IOUtils.toString(filestream,"UTF-8");

//        GeneralSPARQLEndpoint testend = new GeneralSPARQLEndpoint("test.strabon.di.uoa.gr",8080,"testEndpoint");
//        testend.setUser("test");
//        testend.setPassword("test");
//        boolean flag = testend.store(testTriplet, RDFFormat.NTRIPLES,null);



//        EndpointModel endmodel = (EndpointModel)request.getSession().getAttribute("endpoint");
//        GeneralSPARQLEndpoint endpoint = new GeneralSPARQLEndpoint("localhost", 8080, endmodel.getEndpointname().concat("/Store"));
//        endpoint.setUser(endmodel.getCp_username());
//        endpoint.setPassword(endmodel.getCp_password());
//
//        boolean check = endpoint.store(testTriplet, RDFFormat.NTRIPLES, null);

        ModelAndView mav = new ModelAndView("endpoint_store");
        return mav;
    }

    /******************************************************************************************************************/
    @RequestMapping(value = "/do_query", method = RequestMethod.POST)
    public ModelAndView doQuery(@RequestParam("query") String query, @RequestParam("format") String format,
                                HttpServletRequest request) throws IOException {

        EndpointModel endmodel = (EndpointModel)request.getSession().getAttribute("workEndpoint");

        GeneralSPARQLEndpoint endpoint = new GeneralSPARQLEndpoint("localhost", 8080, endmodel.getEndpointname().concat("/Query"));
        endpoint.setUser(endmodel.getCp_username());
        endpoint.setPassword(endmodel.getCp_password());

        EndpointResult result = endpoint.query(query, (stSPARQLQueryResultFormat) stSPARQLQueryResultFormat.valueOf("XML"), "strabon");

        ModelAndView mav = new ModelAndView("endpoint_done");
        mav.addObject("endpointResults", result);
        return mav;
    }

    /******************************************************************************************************************/
    @RequestMapping(value = "/do_update", method = RequestMethod.POST)
    public ModelAndView doUpdate(@RequestParam("query") String query, HttpServletRequest request) throws IOException {

        EndpointModel endmodel = (EndpointModel)request.getSession().getAttribute("workEndpoint");
        GeneralSPARQLEndpoint endpoint = new GeneralSPARQLEndpoint("localhost", 8080, endmodel.getEndpointname().concat("/Update"));
        endpoint.setUser(endmodel.getCp_username());
        endpoint.setPassword(endmodel.getCp_password());

        // var format will be XML/HTML/GeoJSON from html form
        boolean check = endpoint.update(query);
        ModelAndView mav = new ModelAndView("endpoint_done");
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

        // SELECT * WHERE { ?s ?p ?o }
        if( example.equals("1") ){
            query = "SELECT * \n" +
                    "WHERE {\n" +
                    "\t?s ?p ?o \n" +
                    "}";
        }
        // SELECT DISTINCT (?s AS ?subject) WHERE { ?s ?p ?o }
        else if( example.equals("2")){
            query = "SELECT DISTINCT (?s AS ?subject)\n" +
                    "WHERE {\n" +
                    "\t?s ?p ?o \n" +
                    "}";
        }
        // SELECT (COUNT(?s) AS ?NumOfTriples) WHERE { ?s ?p ?o }
        else if( example.equals("3")){
            query = "SELECT (COUNT(?s) AS ?NumOfTriples)\n" +
                    "WHERE {\n" +
                    "\t?s ?p ?o \n" +
                    "}";
        }
        // SELECT * WHERE { ?s ?p ?o } LIMIT 10
        else{
            query = "SELECT * \n" +
                    "WHERE {\n" +
                    "\t?s ?p ?o \n" +
                    "}\n" +
                    "LIMIT 10";
        }

        EndpointResult results = endpoint.query(query, (stSPARQLQueryResultFormat) stSPARQLQueryResultFormat.valueOf("HTML"), "strabon");
        ModelAndView mav = new ModelAndView("endpoint_done");
        mav.addObject("endpointResults", results);
        mav.addObject("query", query);
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

        RDFFormat format;

        if(inference != null)
        { }
        else
        { }

        if( rdfformat.equals("NTRIPLES") ) format = RDFFormat.NTRIPLES;
        else if( rdfformat.equals("XML") ) format = RDFFormat.RDFXML;
        else format = RDFFormat.TURTLE;

        // TODO - 3rd parameter "graph" must be URL type
        boolean storeStatus = endpoint.store(input, format, null);

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

        RDFFormat format;
        URL input = new URL(uri_input);

        if( rdfformat.equals("NTRIPLES") ) format = RDFFormat.NTRIPLES;
        else if( rdfformat.equals("XML") ) format = RDFFormat.RDFXML;
        else format = RDFFormat.TURTLE;

        // TODO - 3rd parameter "graph" must be URL type
        boolean storeStatus = endpoint.store(input, format, null);

        ModelAndView mav = new ModelAndView("endpoint_done");
        mav.addObject("storeStatus", storeStatus);
        return mav;
    }
    /******************************************************************************************************************/

}
