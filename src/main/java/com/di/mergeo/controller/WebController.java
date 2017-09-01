package com.di.mergeo.controller;

import com.di.mergeo.model.EndpointModel;
import com.di.mergeo.model.MapInputModel;
import com.di.mergeo.service.EndpointService;
import com.di.mergeo.service.StartupService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.io.IOException;


@Controller
public class WebController {

    @Autowired
    ServletContext context;

    public static String tomcatPath = System.getProperty("catalina.home"); // Something like foo/fee/tomcat
    public static String endpointFolder = "/opt/tomcat/endpoint/strabon-endpoint-3.3.2-SNAPSHOT/.";
    private boolean startupFlag = false;

    /**************************************** Does the startup work... ************************************************/
    public void startup_jobs() throws IOException, InterruptedException {

        /* Deploy a default Strabon Endpoint */
        String warPath = context.getRealPath("/WEB-INF/classes/strabon-endpoint-3.3.2-SNAPSHOT.war");
        String webappsPath = tomcatPath.concat("/webapps/");
        StartupService.loadApplication(webappsPath, warPath);

        /* Deploy a Sextant application */
//        warPath = context.getRealPath("/WEB-INF/classes/SEXTANT.war");
//        webappsPath = tomcatPath.concat("/webapps/");
//        StartupService.loadApplication(webappsPath, warPath);

    }
    /******************************************************************************************************************/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() throws IOException, InterruptedException {

        if( !startupFlag ){
            startup_jobs();
            startupFlag = true;
        }

        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/geotriples", method = RequestMethod.GET)
    public ModelAndView geotriples() {
        return new ModelAndView("geotriples", "command", new MapInputModel());
    }

    @RequestMapping(value = "/endpoint", method = RequestMethod.GET)
    public ModelAndView endpoint() throws Exception {
        return new ModelAndView("endpoint", "command", new EndpointModel());
    }

    @RequestMapping(value = "/sextant", method = RequestMethod.GET)
    public String sextant() {
        return "sextant";
    }


    /********************************** Testing methods for Exception Handling ****************************************/
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView httpEx(){
        return new ModelAndView("error");
    }

    @ExceptionHandler(value={Exception.class})
    public ModelAndView allEx(){
        return new ModelAndView("error");
    }
    /******************************************************************************************************************/
}