package com.di.mergeo.model;

import org.springframework.web.multipart.MultipartFile;

public class RdfInputModel {

    /* Common characteristics for every input form */
    private String type;
    private String outrdf_fullpath;
    private String uploadpath;
    private String name;

    /* Characteristics for RDB input form */
    private String rdb_baseuri;
    private boolean rdb_rml;
    private String rdb_user;
    private String rdb_password;
    private String rdb_driver;
    private String rdb_jdbcurl;
    private String rdb_format;
    private String rdb_mapfullpath;

    /* Characteristics for ShapeFile input form */
    private String shp_baseuri;
    private boolean shp_rml;
    private String shp_format;
    private String shp_epsgcode;
    private String shp_mapfullpath;
    private String shp_sourcefile;

    /* Characteristics for XML file input form */
    private String xml_baseuri;
    private String xml_format;
    private String xml_epsgcode;
    private String xml_mapfullpath;
    private boolean xml_rml;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOutrdf_fullpath() {
        return outrdf_fullpath;
    }

    public void setOutrdf_fullpath(String outrdf_fullpath) {
        this.outrdf_fullpath = outrdf_fullpath;
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

    public String getRdb_format() {
        return rdb_format;
    }

    public void setRdb_format(String rdb_format) {
        this.rdb_format = rdb_format;
    }

    public String getRdb_mapfullpath() {
        return rdb_mapfullpath;
    }

    public void setRdb_mapfullpath(String rdb_mapfullpath) {
        this.rdb_mapfullpath = rdb_mapfullpath;
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

    public String getShp_format() {
        return shp_format;
    }

    public void setShp_format(String shp_format) {
        this.shp_format = shp_format;
    }

    public String getShp_epsgcode() {
        return shp_epsgcode;
    }

    public void setShp_epsgcode(String shp_epsgcode) {
        this.shp_epsgcode = shp_epsgcode;
    }

    public String getShp_mapfullpath() {
        return shp_mapfullpath;
    }

    public void setShp_mapfullpath(String shp_mapfullpath) {
        this.shp_mapfullpath = shp_mapfullpath;
    }

    public String getShp_sourcefile() {
        return shp_sourcefile;
    }

    public void setShp_sourcefile(String shp_sourcefile) {
        this.shp_sourcefile = shp_sourcefile;
    }

    public String getXml_baseuri() {
        return xml_baseuri;
    }

    public void setXml_baseuri(String xml_baseuri) {
        this.xml_baseuri = xml_baseuri;
    }

    public String getXml_format() {
        return xml_format;
    }

    public void setXml_format(String xml_format) {
        this.xml_format = xml_format;
    }

    public String getXml_epsgcode() {
        return xml_epsgcode;
    }

    public void setXml_epsgcode(String xml_epsgcode) {
        this.xml_epsgcode = xml_epsgcode;
    }

    public String getXml_mapfullpath() {
        return xml_mapfullpath;
    }

    public void setXml_mapfullpath(String xml_mapfullpath) {
        this.xml_mapfullpath = xml_mapfullpath;
    }

    public boolean isXml_rml() {
        return xml_rml;
    }

    public void setXml_rml(boolean xml_rml) {
        this.xml_rml = xml_rml;
    }
}
