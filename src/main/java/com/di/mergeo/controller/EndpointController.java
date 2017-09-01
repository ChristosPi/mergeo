package com.di.mergeo.controller;


import com.di.mergeo.GeneralSPARQLEndpoint;
import com.di.mergeo.PostgreSQLJDBC;
import com.di.mergeo.model.EndpointModel;
import com.di.mergeo.service.EndpointService;
import eu.earthobservatory.org.StrabonEndpoint.client.EndpointResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openrdf.query.resultio.stSPARQLQueryResultFormat;
import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("endpoint")
public class EndpointController {

    @RequestMapping(value = "/endpoint_create", method = RequestMethod.POST)
    public ModelAndView endpointCreate(@ModelAttribute("SpringWeb")EndpointModel endmodel, ModelMap model) throws SQLException, ClassNotFoundException, IOException, InterruptedException {

        PostgreSQLJDBC.dbCreate(endmodel);
        EndpointService.strabonDeploy(endmodel);

        TimeUnit.SECONDS.sleep(10);

//        EndpointService.strabonSetCredentials(endmodel);

        ModelAndView mav = new ModelAndView("endpoint_done");
        mav.addObject("endpoint", endmodel);

        return mav;
    }
    /******************************************************************************************************************/
    @RequestMapping(value = "/endpoint_run", method = RequestMethod.GET)
    public ModelAndView endpointRun(HttpServletRequest request){

        EndpointModel endpoint = (EndpointModel)request.getSession().getAttribute("endpoint");

        if(endpoint != null){
            return new ModelAndView("endpoint_done");
        }
        else{
            ModelAndView mav = new ModelAndView("endpoint", "command", new EndpointModel());
            mav.addObject("no_end", true);
            return mav;
        }
    }

    /* ************************************************************************************************************** */
    /******************************************************************************************************************/
    @RequestMapping(value = "/do_store", method = RequestMethod.POST)
    public ModelAndView doStore(HttpServletRequest request)  throws IOException {

        String testTriplet = "<http://example.org/#Spiderman> <http://www.perceive.net/schemas/relationship/enemyOf> <http://example.org/#green-goblin> .";

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

        EndpointModel endmodel = (EndpointModel)request.getSession().getAttribute("endpoint");
        GeneralSPARQLEndpoint endpoint = new GeneralSPARQLEndpoint("localhost", 8080, endmodel.getEndpointname().concat("/Query"));
        endpoint.setUser(endmodel.getCp_username());
        endpoint.setPassword(endmodel.getCp_password());

        // var format will be XML/HTML from html form
        EndpointResult result = endpoint.query(query, (stSPARQLQueryResultFormat) stSPARQLQueryResultFormat.valueOf("XML"), "strabon");
        ModelAndView mav = new ModelAndView("endpoint_done");
        return mav;
    }

    /******************************************************************************************************************/
    @RequestMapping(value = "/do_update", method = RequestMethod.POST)
    public ModelAndView doUpdate(@RequestParam("query") String query, HttpServletRequest request) throws IOException {

        EndpointModel endmodel = (EndpointModel)request.getSession().getAttribute("endpoint");
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

        EndpointModel endmodel = (EndpointModel)request.getSession().getAttribute("endpoint");
        GeneralSPARQLEndpoint endpoint = new GeneralSPARQLEndpoint("localhost", 8080, endmodel.getEndpointname().concat("/Query"));
        endpoint.setUser(endmodel.getCp_username());
        endpoint.setPassword(endmodel.getCp_password());
        String query;

        // SELECT * WHERE { ?s ?p ?o }
        if( example.equals("1") ){
            query = "SELECT * \n" +
                    "WHERE {\n" +
                    "   ?s ?p ?o \n" +
                    "}";
        }
        // SELECT DISTINCT (?s AS ?subject) WHERE { ?s ?p ?o }
        else if( example.equals("2")){
            query = "SELECT DISTINCT (?s AS ?subject)\n" +
                    "WHERE {\n" +
                    "   ?s ?p ?o \n" +
                    "}";
        }
        // SELECT (COUNT(?s) AS ?NumOfTriples) WHERE { ?s ?p ?o }
        else if( example.equals("3")){
            query = "SELECT (COUNT(?s) AS ?NumOfTriples)\n" +
                    "WHERE {\n" +
                    "   ?s ?p ?o \n" +
                    "}";
        }
        // SELECT * WHERE { ?s ?p ?o } LIMIT 10
        else{
            query = "SELECT * \n" +
                    "WHERE {\n" +
                    "   ?s ?p ?o \n" +
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
                                    @RequestParam(value = "inference", required=false) String inference){

        if(inference != null)
        {
            System.out.println("checkbox is checked");
        }
        else
        {
            System.out.println("checkbox is not checked");
        }

        // TODO ΕΔΩ ΘΑ ΚΑΛΕΙ ΤΙΣ ΚΑΤΑΛΛΗΛΕΣ ΣΥΝΑΡΤΗΣΕΙΣ ΑΠ ΤΟ ΑΡΧΕΙΟ ΤΟΥ ΓΙΩΡΓΟΥ

        ModelAndView mav = new ModelAndView("endpoint_done");
        return mav;
    }
    /******************************************************************************************************************/

    @RequestMapping(value = "/endpoint/uri_store", method = RequestMethod.POST)
    public ModelAndView uriStore(@RequestParam("uri_input") String uri_input){

        // TODO ΕΔΩ ΘΑ ΚΑΛΕΙ ΤΙΣ ΚΑΤΑΛΛΗΛΕΣ ΣΥΝΑΡΤΗΣΕΙΣ ΑΠ ΤΟ ΑΡΧΕΙΟ ΤΟΥ ΓΙΩΡΓΟΥ

        ModelAndView mav = new ModelAndView("endpoint_done");
        return mav;
    }
    /******************************************************************************************************************/

}
