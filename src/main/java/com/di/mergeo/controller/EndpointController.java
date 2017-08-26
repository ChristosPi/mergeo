package com.di.mergeo.controller;


import com.di.mergeo.GeneralSPARQLEndpoint;
import com.di.mergeo.PostgreSQLJDBC;
import com.di.mergeo.model.EndpointModel;
import com.di.mergeo.service.EndpointService;
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

import eu.earthobservatory.org.StrabonEndpoint.client.SPARQLEndpoint;

@Controller
public class EndpointController {

    @RequestMapping(value = "/endpoint_create", method = RequestMethod.POST)
    public ModelAndView endpointCreate(@ModelAttribute("SpringWeb")EndpointModel endmodel, ModelMap model) throws SQLException, ClassNotFoundException, IOException, InterruptedException {

        PostgreSQLJDBC.dbCreate(endmodel);
        EndpointService.strabonDeploy(endmodel);

        TimeUnit.SECONDS.sleep(10);

//        EndpointService.strabonSetCredentials(endmodel);

        ModelAndView mav = new ModelAndView("endpoint_done");
        mav.addObject("endpointName", endmodel.getEndpointname());

        return mav;
    }

    /* ************************************************************************************************************** */
    /******************************************************************************************************************/
    @RequestMapping(value = "/do_store", method = RequestMethod.POST)
    public ModelAndView doStore(@RequestParam("endpointName") String endpointName)  throws IOException {

        String testTriplet = "<http://example.org/#Spiderman> <http://www.perceive.net/schemas/relationship/enemyOf> <http://example.org/#green-goblin> .";

//        String rdf_file = "/home/chrispi/mergeo/target/mergeo/datafiles/rdf-data/endpoint-rdf.nt";
//        FileInputStream filestream = new FileInputStream(rdf_file);
//        String rdf_string= org.apache.commons.io.IOUtils.toString(filestream,"UTF-8");

//        GeneralSPARQLEndpoint testend = new GeneralSPARQLEndpoint("test.strabon.di.uoa.gr",8080,"testEndpoint");
//        testend.setUser("test");
//        testend.setPassword("test");
//        boolean flag = testend.store(testTriplet, RDFFormat.NTRIPLES,null);

        GeneralSPARQLEndpoint endpoint = new GeneralSPARQLEndpoint("localhost", 8080, endpointName.concat("/Store"));
        endpoint.setUser(endpointName);
        endpoint.setPassword(endpointName);

        boolean check = endpoint.store(testTriplet, RDFFormat.NTRIPLES, null);

        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    /******************************************************************************************************************/
    @RequestMapping(value = "/do_query", method = RequestMethod.POST)
    public ModelAndView doQuery(){

        ModelAndView mav = new ModelAndView("endpoint_done");
        return mav;
    }

    /******************************************************************************************************************/
    @RequestMapping(value = "/do_update", method = RequestMethod.POST)
    public ModelAndView doUpdate(){

        ModelAndView mav = new ModelAndView("endpoint_done");
        return mav;
    }
}
