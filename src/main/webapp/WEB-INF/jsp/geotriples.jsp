<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="<s:url value="/resources/css/geotriples.css"/>" rel="stylesheet">
    <title>mG|GeoTriples</title>
</head>

<body>
<div class="container">
    <div class="row">
        <div style="text-align: center;" class="col-md-8 col-md-push-2">
            <h3><strong>GeoTriples</strong> Section</h3>
            <p>Here you can transform your data into RDF. Fill out the form, follow the steps and finally store your
            data into a Strabon Endpoint. Firstly, you have to generate a mapping.</p>
        </div>
    </div>
    <hr>
    <div class="row">

        <%--Left Panel - Input Options when there is no input given--%>
        <div class="col-sm-6 col-md-6">

            <div class="panel panel-primary">
                <div class="panel-heading">Choose input method</div>
                <div class="panel-body">

                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#rdatabase" data-toggle="tab"><strong>Relational Database</strong></a></li>
                        <li><a href="#shapefile" data-toggle="tab"><strong>Shapefile</strong></a></li>
                        <li><a href="#xmlfile" data-toggle="tab"><strong>XML File</strong></a></li>
                    </ul>

                    <div class="tab-content">

                        <%--Relational Database form--%>
                        <div class="tab-pane active" id="rdatabase">
                            <form:form class="form-horizontal" method="POST" action="/geotriples_rdb">
                                <div class="col-md-12">
                                    <legend>Fill out the form:</legend>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="rdb_baseuri" for="rdbbaseuri">Base URI <strong style="color: darkred">*</strong></form:label>
                                        <div class="col-sm-9 input-group">
                                            <span class="input-group-addon input-sm"><i class="fa fa-database fa-fw"></i></span>
                                            <form:input path="rdb_baseuri" type="text" class="form-control input-sm" id="rdbbaseuri" value="http://example.com" required="required" placeholder="Base URI (required)"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="rdb_user" for="user">Username </form:label>
                                        <div class="col-sm-9 input-group">
                                            <span class="input-group-addon input-sm"><i class="fa fa-user fa-fw"></i></span>
                                            <form:input path="rdb_user" type="text" class="form-control input-sm" id="user" placeholder="Username (optional)"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="rdb_password" for="password">Password </form:label>
                                        <div class="col-sm-9 input-group">
                                            <span class="input-group-addon input-sm"><i class="fa fa-lock fa-fw"></i></span>
                                            <form:input path="rdb_password" type="password" class="form-control input-sm" id="password" placeholder="Password (optional)"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="rdb_driver" for="driver">Driver </form:label>
                                        <div class="col-sm-9 input-group">
                                            <span class="input-group-addon input-sm"><i class="fa fa-hdd-o fa-fw"></i></span>
                                            <form:input path="rdb_driver" type="text" class="form-control input-sm" id="driver" placeholder="Driver (optional)"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="rdb_jdbcurl" for="jdbcurl">JDBC URL <strong style="color: darkred">*</strong></form:label>
                                        <div class="col-sm-9 input-group">
                                            <span class="input-group-addon input-sm"><i class="fa fa-link fa-fw"></i></span>
                                            <form:input path="rdb_jdbcurl" type="text" class="form-control input-sm" id="jdbcurl" required="required" placeholder="JDBC URL (required)"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="rdb_rml">RML Format </form:label>
                                        <div class="col-sm-1 input-group">
                                            <form:checkbox class="form-control input-sm" path="rdb_rml" value="-rml" />
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <div class="col-md-6 col-md-offset-3">
                                            <button type="submit" class="btn btn-primary btn-block">Generate mapping</button>
                                        </div>
                                    </div>

                                </div>
                            </form:form>
                        </div>

                        <%--Shapefile form--%>
                        <div class="tab-pane fade" id="shapefile">
                            <form:form class="form-horizontal" method="POST" action="/geotriples_shape" enctype="multipart/form-data"><br>
                                <div class="col-md-12">
                                    <legend>Fill out the form:</legend>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="shp_baseuri" for="shapebaseuri">Base URI <strong style="color: darkred">*</strong></form:label>
                                        <div class="col-sm-9 input-group">
                                            <span class="input-group-addon input-sm"><i class="fa fa-database fa-fw"></i></span>
                                            <form:input path="shp_baseuri" type="text" class="form-control input-sm" id="shapebaseuri" value="http://example.com" required="required" placeholder="Base URI (required)"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="shp_epsgcode" for="shp_epsgcode">EPSG Code </form:label>
                                        <div class="col-sm-9 input-group">
                                            <span class="input-group-addon input-sm"><i class="fa fa-terminal fa-fw"></i></span>
                                            <form:input path="shp_epsgcode" type="text" class="form-control input-sm" id="shp_epsgcode" placeholder="EPSG Code (optional)"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="shp_inputfile" for="shapeinput">File input <strong style="color: darkred">*</strong></form:label>
                                        <div class="col-sm-9 input-group">
                                            <form:input path="shp_inputfile" type="file" id="shapeinput" class="form-control input-sm" required="required" />
                                            <span class="help-block">Choose a file to convert with GeoTriples</span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="shp_rml">RML Format </form:label>
                                        <div class="col-sm-1 input-group">
                                            <form:checkbox class="form-control input-sm" path="shp_rml" value="-rml" />
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group ">
                                        <div class="col-md-6 col-md-offset-3">
                                            <button type="submit" class="btn btn-primary btn-block">Generate mapping</button>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>

                        <%--XML file form--%>
                        <div class="tab-pane fade" id="xmlfile">
                            <form:form class="form-horizontal" method="POST" action="/geotriples_xml" enctype="multipart/form-data"><br>

                                <div class="col-md-12">
                                <legend>Fill out the form:</legend>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="xml_baseuri" for="xmlbaseuri">Base URI <strong style="color: darkred">*</strong></form:label>
                                        <div class="col-sm-9 input-group">
                                            <span class="input-group-addon input-sm"><i class="fa fa-database fa-fw"></i></span>
                                            <form:input path="xml_baseuri" type="text" class="form-control input-sm" id="xmlbaseuri" value="http://example.com" required="required" placeholder="Base URI (required)" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="xml_rootpath" for="rootpath">Rootpath </form:label>
                                        <div class="col-sm-9 input-group">
                                            <span class="input-group-addon input-sm"><i class="fa fa-folder-open fa-fw"></i></span>
                                            <form:input path="xml_rootpath" type="text" class="form-control input-sm" id="rootpath" placeholder="Rootpath (optional)"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="xml_rootelement" for="rootelement">Rootelement </form:label>
                                        <div class="col-sm-9 input-group">
                                            <span class="input-group-addon input-sm"><i class="fa fa-info fa-fw"></i></span>
                                            <form:input path="xml_rootelement" type="text" class="form-control input-sm" id="rootelement" placeholder="Rootelement (optional)"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="xml_namespace" for="namespace">Namespace </form:label>
                                        <div class="col-sm-9 input-group">
                                            <span class="input-group-addon input-sm"><i class="fa fa-tag fa-fw"></i></span>
                                            <form:input path="xml_namespace" type="text" class="form-control input-sm" id="namespace" placeholder="Namespace (optional)"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="xml_namespaces" for="namespaces">Namespaces </form:label>
                                        <div class="col-sm-9 input-group">
                                            <span class="input-group-addon input-sm"><i class="fa fa-tags fa-fw"></i></span>
                                            <form:input path="xml_namespaces" type="text" class="form-control input-sm" id="namespaces" placeholder="Namespaces (optional)"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="xml_inputfile" for="xmlinput">File input <strong style="color: darkred">*</strong></form:label>
                                        <div class="col-sm-9 input-group">
                                            <form:input path="xml_inputfile" type="file" id="xmlinput" class="form-control input-sm" required="required" />
                                            <span class="help-block">Choose a file to convert with GeoTriples</span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <form:label class="control-label col-sm-3" path="xml_xsdfile" for="xsdfile">XSD File Input </form:label>
                                        <div class="col-sm-9 input-group">
                                            <form:input path="xml_xsdfile" type="file" class="form-control input-sm" id="xsdfile" />
                                            <span class="help-block">Choose XSD file (Required for XMLs)</span>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <div class="col-md-6 col-md-offset-3">
                                            <button type="submit" class="btn btn-primary btn-block">Generate mapping</button>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%--Right Panel - Mapping output--%>
        <div class="col-sm-6 col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading">Generated mapping</div>
                <div class="panel-body">
                    <div class="form-group">
                        <legend for="out-map">Produced mapping:</legend>
                        <textarea readonly style="resize: none" class="form-control" rows="15" id="out-map"></textarea>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>