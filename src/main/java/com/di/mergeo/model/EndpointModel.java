package com.di.mergeo.model;

public class EndpointModel {

    /* Connection properties values */
    private String hostname;
    private String port;
    private String dbengine;
    private String password;
    private String dbname;
    private String username;

    /* Credentials properties values */
    private String cp_username;
    private String cp_password;

    private String endpointname;
    private String tomcat_location;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDbengine() {
        return dbengine;
    }

    public void setDbengine(String dbengine) {
        this.dbengine = dbengine;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCp_username() {
        return cp_username;
    }

    public void setCp_username(String cp_username) {
        this.cp_username = cp_username;
    }

    public String getCp_password() {
        return cp_password;
    }

    public void setCp_password(String cp_password) {
        this.cp_password = cp_password;
    }

    public String getEndpointname() {
        return endpointname;
    }

    public void setEndpointname(String endpointname) {
        this.endpointname = endpointname;
    }

    public String getTomcat_location() {
        return tomcat_location;
    }

    public void setTomcat_location(String tomcat_location) {
        this.tomcat_location = tomcat_location;
    }
}
