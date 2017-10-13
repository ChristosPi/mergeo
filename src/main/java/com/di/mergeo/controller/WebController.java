package com.di.mergeo.controller;

import com.di.mergeo.model.EndpointModel;
import com.di.mergeo.model.MapInputModel;
import com.di.mergeo.service.StartupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

//import org.apache.velocity.exception.ResourceNotFoundException;


@Controller
public class WebController {

    @Autowired
    ServletContext context;

    /* Tomcat's path in system, something like /dir1/dir2/tomcat */
    public static String tomcatPath = System.getProperty("catalina.home");

    /* Must be changed in case something else is given as a name in startup_jobs() */
    public static String endpointFolder = "/opt/tomcat/endpoint/strabon-endpoint-3.3.2-SNAPSHOT/.";
//    public static String endpointFolder = "/opt/tomcat/endpoint/strabon-endpoint-3.2.11-temporals/.";

    private boolean startupFlag = false;

    /**************************************** Does the startup work... ************************************************/
    public void startup_jobs() throws IOException, InterruptedException {

        /* Deploy defaults Strabon-Endpoint and Sextant applications */
        String warPath = context.getRealPath("/WEB-INF/classes/strabon-endpoint-3.3.2-SNAPSHOT.war");
//        String warPath = context.getRealPath("/WEB-INF/classes/strabon-endpoint-3.2.11-temporals.war");
        String sextantPath = context.getRealPath("/WEB-INF/classes/SextantOL3.war");

        String webappsPath = tomcatPath.concat("/webapps/");

        /* Loads Strabon Endpoint warfile to Tomcat */
        StartupService.loadApplication(webappsPath, warPath);
        /* Loads Sextant warfile to Tomcat */
        StartupService.loadApplication(webappsPath, sextantPath);

        EndpointModel defEndpoint = new EndpointModel();
        defEndpoint.setHostname("localhost");
        defEndpoint.setPort("5432");
        defEndpoint.setDbengine("postgis");
        defEndpoint.setDbname("endpoint");
        defEndpoint.setUsername("postgres");
        defEndpoint.setPassword("postgres");
        defEndpoint.setCp_username("endpoint");
        defEndpoint.setCp_password("3ndpo1nt");
        defEndpoint.setTomcat_location(tomcatPath);
        defEndpoint.setEndpointname("strabon-endpoint-3.3.2-SNAPSHOT");
//        defEndpoint.setEndpointname("strabon-endpoint-3.2.11-temporals");

        context.setAttribute("defEndpoint", defEndpoint);

        /* Now, creates a folder named "endpoint" inside tomcat's base which contains the Strabon-Endpoint.war
        * in order to be used later, as the base app which is been deployed to tomcat/webapps */
        File dir = new File(System.getProperty("catalina.home") + File.separator + "endpoint" + File.separator + "strabon-endpoint-3.3.2-SNAPSHOT");
//        File dir = new File(System.getProperty("catalina.home") + File.separator + "endpoint" + File.separator + "strabon-endpoint-3.2.11-temporals");
        if (!dir.exists()) dir.mkdirs();

        ProcessBuilder pb = new ProcessBuilder("unzip", "-qq", "-o", warPath, "-d", dir.getAbsolutePath());
        Process p = null;
        p = pb.start();
        p.waitFor();

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

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info() {
        return "info";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "error";
    }

    /********************************** Testing methods for Exception Handling ****************************************/
//    @ExceptionHandler(ResourceNotFoundException.class)
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