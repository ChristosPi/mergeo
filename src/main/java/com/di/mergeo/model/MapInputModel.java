package com.di.mergeo.model;

import org.springframework.web.multipart.MultipartFile;

public class MapInputModel {

    /* Common characteristics for every input form */
    private String type;
    private String outmap_fullpath;
    private String uploadpath;
    private String name;
    private String newfilename;


    /* Characteristics for RDB input form */
    private String rdb_baseuri;
    private boolean rdb_rml;
    private String rdb_user;
    private String rdb_password;
    private String rdb_driver;
    private String rdb_jdbcurl;

    /* Characteristics for ShapeFile input form */
    private MultipartFile shp_inputfile;
    private String shp_baseuri;
    private String shp_epsgcode;
    private boolean shp_rml;

    /* Characteristics for XML file input form */
    private MultipartFile xml_inputfile;
    private String xml_baseuri;
    private String xml_rootpath;
    private String xml_rootelement;
    private String xml_namespace;
    private String xml_namespaces;
    private MultipartFile xml_xsdfile;

    /*** Getters & Setters for class data ***/

    public String getNewfilename() {
        return newfilename;
    }

    public void setNewfilename(String newfilename) {
        this.newfilename = newfilename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOutmap_fullpath() {
        return outmap_fullpath;
    }

    public void setOutmap_fullpath(String outmap_fullpath) {
        this.outmap_fullpath = outmap_fullpath;
    }

    public String getUploadpath() {
        return uploadpath;
    }

    public void setUploadpath(String uploadpath) {
        this.uploadpath = uploadpath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRdb_baseuri() {
        return rdb_baseuri;
    }

    public void setRdb_baseuri(String rdb_baseuri) {
        this.rdb_baseuri = rdb_baseuri;
    }

    public boolean isRdb_rml() {
        return rdb_rml;
    }

    public void setRdb_rml(boolean rdb_rml) {
        this.rdb_rml = rdb_rml;
    }

    public String getRdb_user() {
        return rdb_user;
    }

    public void setRdb_user(String rdb_user) {
        this.rdb_user = rdb_user;
    }

    public String getRdb_password() {
        return rdb_password;
    }

    public void setRdb_password(String rdb_password) {
        this.rdb_password = rdb_password;
    }

    public String getRdb_driver() {
        return rdb_driver;
    }

    public void setRdb_driver(String rdb_driver) {
        this.rdb_driver = rdb_driver;
    }

    public String getRdb_jdbcurl() {
        return rdb_jdbcurl;
    }

    public void setRdb_jdbcurl(String rdb_jdbcurl) {
        this.rdb_jdbcurl = rdb_jdbcurl;
    }


    public MultipartFile getShp_inputfile() {
        return shp_inputfile;
    }

    public void setShp_inputfile(MultipartFile shp_inputfile) {
        this.shp_inputfile = shp_inputfile;
    }

    public String getShp_baseuri() {
        return shp_baseuri;
    }

    public void setShp_baseuri(String shp_baseuri) {
        this.shp_baseuri = shp_baseuri;
    }

    public boolean isShp_rml() {
        return shp_rml;
    }

    public void setShp_rml(boolean shp_rml) {
        this.shp_rml = shp_rml;
    }

    public String getShp_epsgcode() {
        return shp_epsgcode;
    }

    public void setShp_epsgcode(String shp_epsgcode) {
        this.shp_epsgcode = shp_epsgcode;
    }

    public void setXml_xsdfile(MultipartFile xml_xsdfile) {
        this.xml_xsdfile = xml_xsdfile;
    }

    public MultipartFile getXml_inputfile() {
        return xml_inputfile;
    }

    public void setXml_inputfile(MultipartFile xml_inputfile) {
        this.xml_inputfile = xml_inputfile;
    }

    public String getXml_baseuri() {
        return xml_baseuri;
    }

    public void setXml_baseuri(String xml_baseuri) {
        this.xml_baseuri = xml_baseuri;
    }

    public String getXml_rootpath() {
        return xml_rootpath;
    }

    public void setXml_rootpath(String xml_rootpath) {
        this.xml_rootpath = xml_rootpath;
    }

    public String getXml_rootelement() {
        return xml_rootelement;
    }

    public void setXml_rootelement(String xml_rootelement) {
        this.xml_rootelement = xml_rootelement;
    }

    public String getXml_namespace() {
        return xml_namespace;
    }

    public void setXml_namespace(String xml_namespace) {
        this.xml_namespace = xml_namespace;
    }

    public String getXml_namespaces() {
        return xml_namespaces;
    }

    public void setXml_namespaces(String xml_namespaces) {
        this.xml_namespaces = xml_namespaces;
    }

    public MultipartFile getXml_xsdfile() {
        return xml_xsdfile;
    }

    public void setXml_xsdfilepath(String xml_xsdfilepath) {
        this.xml_xsdfile = xml_xsdfile;
    }
}
