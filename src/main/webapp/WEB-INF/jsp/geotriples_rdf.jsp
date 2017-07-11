<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <%@ page import="java.io.*"%>
    <%@ page import="java.lang.String"%>
    <link href="<s:url value="/resources/css/geotriples.css"/>" rel="stylesheet">
    <title>MerGEO|Transform</title>

    <script>
        function make_changes() {
            document.getElementById("out-map").readOnly = false;
            document.getElementById("save-btn").style.visibility = "visible";
        }
    </script>

</head>

<body>
<div class="container">
    <c:if test="${not empty type}">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="alert alert-info" role="alert">
                    <strong>Input OK!</strong> Currently working on: ${name}
                </div>
            </div>
        </div>
    </c:if>

    <div class="row">
        <%--Left Panel - Options when an input has already been given --%>
        <div class="col-sm-6 col-md-6">
            <div class="panel panel-primary">
            <div class="panel-heading">Dump to RDF Section</div>
            <div class="panel-body">
                <c:choose>
                    <c:when test="${ type == 'rdb'}">
                        <%--Relational Database form--%>
                        <form:form method="POST" action="/geotriples_rdf_rdb">
                            <div class="form-group">
                                <form:label path="rdb_baseuri" for="rdbbaseuri">BaseURI</form:label>
                                <form:input path="rdb_baseuri" type="text" class="form-control" id="rdbbaseuri" value="http://example.org/" required="required" />
                            </div>
                            <div class="form-group">
                                <form:label path="rdb_user" for="user">Username</form:label>
                                <form:input path="rdb_user" type="text" class="form-control" id="user" />
                            </div>
                            <div class="form-group">
                                <form:label path="rdb_password" for="password">Password</form:label>
                                <form:input path="rdb_password" type="password" class="form-control" id="password" />
                            </div>
                            <div class="form-group">
                                <form:label path="rdb_driver" for="driver">Driver</form:label>
                                <form:input path="rdb_driver" type="text" class="form-control" id="driver" />
                            </div>
                            <div class="form-group">
                                <form:label path="rdb_jdbcurl" for="jdbcurl">JDBC URL</form:label>
                                <form:input path="rdb_jdbcurl" type="text" class="form-control" id="jdbcurl" required="required" />
                            </div>
                            <div class="form-group">
                                <form:label path="rdb_mapfullpath" for="inputmap_fullpath">Mapping file path</form:label>
                                <form:input path="rdb_mapfullpath" type="text" class="form-control" readonly="true" id="rdb_mapfullpath" required="required" value="${outmap_fullpath}"/>
                            </div>
                            <div class="form-group">
                                <form:label path="rdb_format">Choose format type</form:label><br>
                                <form:radiobutton path="rdb_format" value="N3" label="N3" checked="checked"/>
                                <form:radiobutton path="rdb_format" value="RDF" label="RDF/XML"/>
                                <form:radiobutton path="rdb_format" value="TURTLE" label="TURTLE"/>
                            </div>
                            <div class="form-group">
                                <form:label path="rdb_rml"><form:checkbox path="rdb_rml" value="-rml" /> RML</form:label>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success">Dump to RDF</button>
                            </div>
                        </form:form>
                    </c:when>
                    <c:when test="${ type == 'shp'}">
                        <%--Shapefile form--%>
                        <form:form method="POST" action="/geotriples_rdf_shp">
                            <div class="form-group">
                                <form:label path="shp_sourcefile" for="shp_sourcefile">Source Shapefile</form:label>
                                <form:input path="shp_sourcefile" type="text" class="form-control" id="shp_sourcefile" value="${name}" readonly="true"/>
                            </div>
                            <div class="form-group">
                                <form:label path="shp_baseuri" for="rdbbaseuri">BaseURI</form:label>
                                <form:input path="shp_baseuri" type="text" class="form-control" id="shp_baseuri" value="http://example.org/" required="required" />
                            </div>
                            <div class="form-group">
                                <form:label path="shp_epsgcode" for="shp_epsgcode">EPSG Code</form:label>
                                <form:input path="shp_epsgcode" type="text" class="form-control" id="shp_epsgcode" />
                            </div>
                            <div class="form-group">
                                <form:label path="shp_mapfullpath" for="shp_mapfullpath">Mapping file path</form:label>
                                <form:input path="shp_mapfullpath" type="text" class="form-control" readonly="true" id="shp_mapfullpath" required="required" value="${outmap_fullpath}"/>
                            </div>
                            <div class="form-group">
                                <form:label path="shp_format">Choose format type</form:label><br>
                                <form:radiobutton path="shp_format" value="N3" label="N3" checked="checked"/>
                                <form:radiobutton path="shp_format" value="RDF" label="RDF/XML"/>
                                <form:radiobutton path="shp_format" value="TURTLE" label="TURTLE"/>
                            </div>
                            <div class="form-group">
                                <form:label path="shp_rml"><form:checkbox path="shp_rml" value="-rml" /> RML</form:label>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success">Dump to RDF</button>
                            </div>
                        </form:form>
                    </c:when>
                    <c:when test="${ type == 'xml'}">
                        <%--XML/JSON form--%>
                        <form:form method="POST" action="/geotriples_rdf_xml">
                            <div class="form-group">
                                <form:label path="xml_baseuri" for="xml_baseuri">BaseURI</form:label>
                                <form:input path="xml_baseuri" type="text" class="form-control" id="xml_baseuri" value="http://example.org/" required="required" />
                            </div>
                            <div class="form-group">
                                <form:label path="xml_epsgcode" for="xml_epsgcode">EPSG Code</form:label>
                                <form:input path="xml_epsgcode" type="text" class="form-control" id="xml_epsgcode" />
                            </div>
                            <div class="form-group">
                                <form:label path="xml_mapfullpath" for="xml_mapfullpath">Mapping file path</form:label>
                                <form:input path="xml_mapfullpath" type="text" class="form-control" readonly="true" id="xml_mapfullpath" required="required" value="${outmap_fullpath}"/>
                            </div>
                            <div class="form-group">
                                <form:label path="xml_format">Choose format type</form:label><br>
                                <form:radiobutton path="xml_format" value="N3" label="N3" checked="checked"/>
                                <form:radiobutton path="xml_format" value="RDF" label="RDF/XML"/>
                                <form:radiobutton path="xml_format" value="TURTLE" label="TURTLE"/>
                            </div>
                            <div class="form-group">
                                <form:label path="xml_rml"><form:checkbox path="xml_rml" value="-rml" checked="checked" readonly="true"/> RML</form:label>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success">Dump to RDF</button>
                            </div>
                        </form:form>
                    </c:when>
                </c:choose>
            </div>
            </div>
        </div>

        <%--Right Panel - Mapping output--%>
        <div class="col-sm-6 col-md-6">
            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading">Generated mapping</div>
                    <div class="panel-body">

                        <div class="form-group">
                            <label for="out-map">Output code:</label>
                            <textarea readonly style="resize: none" class="form-control" rows="15" id="out-map"><%
                                String outmap_file = (String) request.getAttribute("outmap_fullpath");
                                if( outmap_file != null && !outmap_file.isEmpty()){
                                    FileInputStream outmap_code = new FileInputStream(outmap_file);
                                    String outmap_code_str= org.apache.commons.io.IOUtils.toString(outmap_code);
                                    request.setAttribute("outmap_display", outmap_code_str);
                                }
                            %><c:out value="${outmap_display}"/>
                            </textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <button onclick="make_changes()" class="btn btn-warning">Hmm, something seems wrong. Let's edit!</button>
                </div>
                <div class="col-md-offset-4 col-md-4">
                    <button style="visibility: hidden;" id="save-btn" class="btn btn-success">Save changes</button>
                </div>
            </div>
        </div>

    </div>

</div>
</body>