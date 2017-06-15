package com.di.mergeo.model;

public class InputModel {
    private String type;        // shp or rdb or xml
    private String uploadpath;  // if there is a file
    private String filename;    // filename or rdb name
    private String baseuri;     // rdb uri
    private boolean rml;        // is rml or not
    private String user;        // username for rdb
    private String password;    // password for rdb
    private String driver;      // rdb driver
    private String jdbcurl;     // rdb jdbc

    private String rootpath;    // for xml
    private String rootelement; // for xml
    private String namespace;   // for xml
    private String namespaces;  // for xml
    private String xsdfilepath; // for xml

    private String outmap_fullpath;

    public String getOutmap_fullpath() {
        return outmap_fullpath;
    }

    public void setOutmap_fullpath(String outmap_fullpath) {
        this.outmap_fullpath = outmap_fullpath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUploadpath() {
        return uploadpath;
    }

    public void setUploadpath(String uploadpath) {
        this.uploadpath = uploadpath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getBaseuri() {
        return baseuri;
    }

    public void setBaseuri(String baseuri) {
        this.baseuri = baseuri;
    }

    public boolean isRml() {
        return rml;
    }

    public void setRml(boolean rml) {
        this.rml = rml;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getJdbcurl() {
        return jdbcurl;
    }

    public void setJdbcurl(String jdbcurl) {
        this.jdbcurl = jdbcurl;
    }

    public String getRootpath() {
        return rootpath;
    }

    public void setRootpath(String rootpath) {
        this.rootpath = rootpath;
    }

    public String getRootelement() {
        return rootelement;
    }

    public void setRootelement(String rootelement) {
        this.rootelement = rootelement;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getNamespaces() {
        return namespaces;
    }

    public void setNamespaces(String namespaces) {
        this.namespaces = namespaces;
    }

    public String getXsdfilepath() {
        return xsdfilepath;
    }

    public void setXsdfilepath(String xsdfilepath) {
        this.xsdfilepath = xsdfilepath;
    }

}
