package com.di.mergeo.service;

import com.di.mergeo.model.EndpointModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EndpointService {

    public static void strabonDeploy(EndpointModel endmodel) throws IOException, InterruptedException {

        String warPath = "/home/chrispi/mergeo/src/main/resources/strabon-endpoint-3.2.11-temporals.war";

        /* Set location to tomcat's webapps*/
        endmodel.setTomcat_location("/opt/tomcat/webapps/");
        String newPath = endmodel.getTomcat_location() + endmodel.getEndpointname()+ ".war";

        System.out.println( System.getProperty("catalina.base"));

        ProcessBuilder pb = new ProcessBuilder("cp", "-a", warPath, newPath);

        Process p = null;
        p = pb.start();
        p.waitFor();

        System.out.println("Done!");
    }

    /******************************************************************************************************************/
    public static void strabonSetCredentials(EndpointModel endmodel) throws IOException {

        String target_folder = endmodel.getTomcat_location() + endmodel.getEndpointname() + File.separator + "WEB-INF/";

        File conn_props = new File(target_folder + "connection.properties");
        File cred_props = new File(target_folder + "credentials.properties");

        FileWriter writer1 = new FileWriter(conn_props);
        writer1.write("hostname=" + endmodel.getHostname() + "\n");
        writer1.write("port=" + endmodel.getPort() + "\n");
        writer1.write("dbengine=" + endmodel.getDbengine() + "\n");
        writer1.write("password=" + endmodel.getPassword() + "\n");
        writer1.write("dbname=" + endmodel.getDbname() + "\n");
        writer1.write("username=" + endmodel.getUsername() );

        writer1.flush();
        writer1.close();

        FileWriter writer2 = new FileWriter(cred_props);
        writer2.write("username=" + endmodel.getCp_username() + "\n");
        writer2.write("password=" + endmodel.getCp_password() );

        writer2.flush();
        writer2.close();
    }
}
