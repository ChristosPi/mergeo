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
</head>

<body>
<div class="container-fluid">

    <div class="row">
        <%--Left Panel - Input Options when there is no input given--%>
        <div class="col-sm-6 col-md-6">

            <div class="panel panel-default">
                <div class="panel-heading">Choose input method</div>
                <div class="panel-body">

                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#rdatabase" data-toggle="tab">Relational Database</a></li>
                        <li><a href="#shapefile" data-toggle="tab">Shapefile</a></li>
                        <li><a href="#xmlfile" data-toggle="tab">XML files (RML only)</a></li>
                    </ul>

                    <div class="tab-content">

                        <%--Relational Database form--%>
                        <div class="tab-pane active" id="rdatabase">
                            <form:form method="POST" action="/geotriples_rdb">
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
                                    <form:label path="rdb_rml"><form:checkbox path="rdb_rml" value="-rml" /> RML</form:label>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-success">Connect</button>
                                </div>
                            </form:form>
                        </div>

                        <%--Shapefile form--%>
                        <div class="tab-pane fade" id="shapefile">
                            <form:form method="POST" action="/geotriples_shape" enctype="multipart/form-data"><br>
                                <div class="form-group">
                                    <form:label path="shp_inputfile" for="shapeinput">File input</form:label>
                                    <form:input path="shp_inputfile" type="file" id="shapeinput" required="required" />
                                    <p class="help-block">Choose a file to convert with GeoTriples</p>
                                </div>
                                <div class="form-group">
                                    <form:label path="shp_baseuri" for="shapebaseuri">BaseURI</form:label>
                                    <form:input path="shp_baseuri" type="text" class="form-control" id="shapebaseuri" value="http://example.org/" required="required" />
                                </div>
                                <div class="form-group">
                                    <form:label path="shp_rml"><form:checkbox path="shp_rml" value="-rml" /> RML</form:label>
                                </div>
                                <div class="form-group  ">
                                    <button type="submit" class="btn btn-success">Upload</button>
                                </div>
                            </form:form>
                        </div>

                        <%--XML file form--%>
                        <div class="tab-pane fade" id="xmlfile">
                            <form:form method="POST" action="/geotriples_xml" enctype="multipart/form-data"><br>
                                <div class="form-group">
                                    <form:label path="xml_inputfile" for="xmlinput">File input</form:label>
                                    <form:input path="xml_inputfile" type="file" id="xmlinput" required="required" />
                                    <p class="help-block">Choose a file to convert with GeoTriples</p>
                                </div>
                                <div class="form-group">
                                    <form:label path="xml_baseuri" for="xmlbaseuri">BaseURI</form:label>
                                    <form:input path="xml_baseuri" type="text" class="form-control" id="xmlbaseuri" value="http://example.org/" required="required" />
                                </div>
                                <div class="form-group">
                                    <form:label path="xml_rootpath" for="rootpath">Rootpath</form:label>
                                    <form:input path="xml_rootpath" type="text" class="form-control" id="rootpath" />
                                </div>
                                <div class="form-group">
                                    <form:label path="xml_rootelement" for="rootelement">Rootelement</form:label>
                                    <form:input path="xml_rootelement" type="text" class="form-control" id="rootelement" />
                                </div>
                                <div class="form-group">
                                    <form:label path="xml_namespace" for="namespace">Namespace</form:label>
                                    <form:input path="xml_namespace" type="text" class="form-control" id="namespace" />
                                </div>
                                <div class="form-group">
                                    <form:label path="xml_namespaces" for="namespaces">Namespaces</form:label>
                                    <form:input path="xml_namespaces" type="text" class="form-control" id="namespaces" />
                                </div>
                                <div class="form-group">
                                    <form:label path="xml_xsdfile" for="xsdfile">XSD File Input</form:label>
                                    <form:input path="xml_xsdfile" type="file" id="xsdfile" />
                                    <p class="help-block">Choose XSD file (Required for XMLs)</p>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-success">Upload</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%--Right Panel - Mapping output--%>
        <div class="col-sm-6 col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">Generated mapping</div>
                <div class="panel-body">
                    <div class="form-group">
                        <label for="out-map">Output code:</label>
                        <textarea readonly style="resize: none" class="form-control" rows="15" id="out-map"></textarea>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>