package com.di.mergeo.controller;


import com.di.mergeo.GeneralSPARQLEndpoint;
import com.di.mergeo.PostgreSQLJDBC;
import com.di.mergeo.model.EndpointModel;
import com.di.mergeo.service.EndpointService;
import eu.earthobservatory.org.StrabonEndpoint.client.EndpointResult;
import org.openrdf.rio.RDFFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openrdf.query.resultio.stSPARQLQueryResultFormat;

import eu.earthobservatory.org.StrabonEndpoint.client.SPARQLEndpoint;

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
        EndpointModel endmodel = (EndpointModel)request.getSession().getAttribute("endpoint");
        GeneralSPARQLEndpoint endpoint = new GeneralSPARQLEndpoint("localhost", 8080, endmodel.getEndpointname().concat("/Store"));
        endpoint.setUser(endmodel.getCp_username());
        endpoint.setPassword(endmodel.getCp_password());

        boolean check = endpoint.store(testTriplet, RDFFormat.NTRIPLES, null);

        ModelAndView mav = new ModelAndView("index");
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

        // var format will be XML/HTML from html form
        boolean check = endpoint.update(query);
        ModelAndView mav = new ModelAndView("endpoint_done");
        return mav;

    }
}
