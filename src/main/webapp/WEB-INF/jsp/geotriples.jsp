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
    <c:if test="${not empty filename}">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="alert alert-info" role="alert">
                    <strong>Input OK!</strong> Currently working on: ${filename}
                </div>
            </div>
        </div>
    </c:if>

    <div class="row">
        <c:choose>
            <c:when test="${not empty filename}">
                <%--Left Panel - Options when an input has already been given --%>
                <div class="col-sm-6 col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">Dump to RDF Section</div>
                        <div class="panel-body">

                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
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
                                    <%--<form role="form" method="POST" action="/geotriplesrdb"><br>--%>
                                        <%--<div class="form-group">--%>
                                            <%--<label for="rdbbaseuri">BaseURI</label>--%>
                                            <%--<input name="baseuri" type="text" class="form-control" id="rdbbaseuri" value="http://example.org/" required>--%>
                                        <%--</div>--%>
                                        <%--<div class="form-group">--%>
                                            <%--<label for="user">Username</label>--%>
                                            <%--<input name="user" type="text" class="form-control" id="user">--%>
                                        <%--</div>--%>
                                        <%--<div class="form-group">--%>
                                            <%--<label for="password">Password</label>--%>
                                            <%--<input name="password" type="password" class="form-control" id="password">--%>
                                        <%--</div>--%>
                                        <%--<div class="form-group">--%>
                                            <%--<label for="driver">Driver</label>--%>
                                            <%--<input name="driver" type="text" class="form-control" id="driver">--%>
                                        <%--</div>--%>
                                        <%--<div class="form-group">--%>
                                            <%--<label for="jdbcurl">JDBC URL</label>--%>
                                            <%--<input name="jdbcurl" type="text" class="form-control" id="jdbcurl" required>--%>
                                        <%--</div>--%>
                                        <%--<div class="form-group">--%>
                                            <%--<label><input type="checkbox" name="rml" value="-rml"> RML</label>--%>
                                        <%--</div>--%>
                                        <%--<div class="form-group">--%>
                                            <%--<button type="submit" class="btn btn-success">Connect</button>--%>
                                        <%--</div>--%>
                                    <%--</form>--%>
                                    <form:form method="POST" action="/geotriplesrdb">
                                        <div class="form-group">
                                            <form:label path="baseuri" for="rdbbaseuri">BaseURI</form:label>
                                            <form:input path="baseuri" type="text" class="form-control" id="rdbbaseuri" value="http://example.org/" required="required"/>
                                        </div>
                                        <div class="form-group">
                                            <form:label path="user" for="user">Username</form:label>
                                            <form:input path="user" type="text" class="form-control" id="user" />
                                        </div>
                                        <div class="form-group">
                                            <form:label path="password" for="password">Password</form:label>
                                            <form:input path="password" type="password" class="form-control" id="password" />
                                        </div>
                                        <div class="form-group">
                                            <form:label path="driver" for="driver">Driver</form:label>
                                            <form:input path="driver" type="text" class="form-control" id="driver" />
                                        </div>
                                        <div class="form-group">
                                            <form:label path="jdbcurl" for="jdbcurl">JDBC URL</form:label>
                                            <form:input path="jdbcurl" type="text" class="form-control" id="jdbcurl" required="required" />
                                        </div>
                                        <div class="form-group">
                                            <form:label path="rml"><form:checkbox path="rml" value="-rml" /> RML</form:label>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-success">Connect</button>
                                        </div>
                                    </form:form>
                                </div>

                                    <%--Shapefile form--%>
                                <div class="tab-pane fade" id="shapefile">
                                    <form role="form" method="POST" action="/geotriplesshape" enctype="multipart/form-data"><br>
                                        <div class="form-group">
                                            <label for="shapeinput">File input</label>
                                            <input type="file" name="inputfile" id="shapeinput" required>
                                            <p class="help-block">Choose a file to convert with GeoTriples</p>
                                        </div>
                                        <div class="form-group">
                                            <label for="shapebaseuri">BaseURI</label>
                                            <input name="baseuri" type="text" class="form-control" id="shapebaseuri" value="http://example.org/" required>
                                        </div>
                                        <div class="form-group">
                                            <label><input type="checkbox" name="rml" value="-rml"> RML</label>
                                        </div>
                                        <div class="form-group  ">
                                            <button type="submit" class="btn btn-success">Upload</button>
                                        </div>
                                    </form>
                                </div>

                                    <%--XML file form--%>
                                <div class="tab-pane fade" id="xmlfile">
                                    <form role="form" method="POST" action="/geotriplesxml" enctype="multipart/form-data"><br>
                                        <div class="form-group">
                                            <label for="xmlinput">File input</label>
                                            <input type="file" name="inputfile" id="xmlinput" required>
                                            <p class="help-block">Choose a file to convert with GeoTriples</p>
                                        </div>
                                        <div class="form-group">
                                            <label for="xmlbaseuri">BaseURI</label>
                                            <input name="baseuri" type="text" class="form-control" id="xmlbaseuri" value="http://example.org/" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="rootpath">Rootpath</label>
                                            <input name="rootpath" type="text" class="form-control" id="rootpath" value="rootpath">
                                        </div>
                                        <div class="form-group">
                                            <label for="rootelement">Rootelement</label>
                                            <input name="rootelement" type="text" class="form-control" id="rootelement" value="rootelement">
                                        </div>
                                        <div class="form-group">
                                            <label for="namespace">Namespace</label>
                                            <input name="namespace" type="text" class="form-control" id="namespace" value="namespace">
                                        </div>
                                        <div class="form-group">
                                            <label for="namespaces">Namespaces</label>
                                            <input name="namespaces" type="text" class="form-control" id="namespaces" value="namespaces">
                                        </div>
                                        <div class="form-group">
                                            <label for="xsdfile">XSD File Input</label>
                                            <input type="file" name="xsdfile" id="xsdfile" required>
                                            <p class="help-block">Choose XSD file (Required for XMLs)</p>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-success">Upload</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

        <%--Right Panel - Mapping output--%>
        <div class="col-sm-6 col-md-6">
            <div class="panel panel-default">
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

    </div>
</div>
</body>
</html>