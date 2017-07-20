package com.di.mergeo.service;

import java.io.IOException;

public class EndpointService {

    public static void strabonDeploy(){

        String warPath = "/home/chrispi/mergeo/src/main/resources/strabon-endpoint-3.2.11-temporals.war";
//        String warPath = "/home/chrispi/Desktop/Strabon-ready";
        String newPath = "/opt/tomcat/webapps/newstrabon.war";

        System.out.println( System.getProperty("catalina.base"));

        ProcessBuilder pb = new ProcessBuilder("cp", "-a", warPath, newPath);

        Process p = null;
        try {
            p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }
}
