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
    <title>mG|GeoTriples</title>

    <script>
        function make_changes() {
            document.getElementById("out-map").readOnly = false;
            document.getElementById("save-btn").style.visibility = "visible";
        }
    </script>

</head>

<body>
<div class="container">
    <div class="row">
        <div style="text-align: center;" class="col-md-8 col-md-push-2">
            <h3><strong>GeoTriples</strong> Section</h3>
            <p><strong>Step 2/3: </strong>Now, check if everything is alright, fill out the form and dump Data to RDF.</p>
        </div>
    </div><hr>
    <c:if test="${not empty type}">
        <div class="row">
            <c:choose>
                <c:when test="${changed == true}">
                    <div class="col-md-6 col-md-offset-3">
                        <div style="text-align: center;" class="alert alert-dismissible alert-success" role="alert">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>[ <i class="fa fa-exclamation-circle" aria-hidden="true"></i> ] Changes saved!</strong> Currently working on: ${name}
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="col-md-6 col-md-offset-3">
                        <div style="text-align: center;" class="alert alert-dismissible alert-info" role="alert">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>[ <i class="fa fa-exclamation-circle" aria-hidden="true"></i> ] Input OK!</strong> Currently working on: ${name}
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
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
                        <form:form class="form-horizontal" method="POST" action="/geotriples_rdf_rdb">
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
                                        <form:input path="rdb_driver" type="text" class="form-control input-sm" id="driver" placeholder="Driver (optional)" />
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
                                    <form:label class="control-label col-sm-3" path="rdb_mapfullpath" for="inputmap_fullpath">Mapping file <strong style="color: darkred">*</strong></form:label>
                                    <div class="col-sm-9 input-group">
                                        <span class="input-group-addon input-sm"><i class="fa fa-folder-open fa-fw"></i></span>
                                        <form:input path="rdb_mapfullpath" type="text" class="form-control input-sm" readonly="true" id="rdb_mapfullpath" required="required" value="${outmap_fullpath}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label class="control-label col-sm-3" path="rdb_format">Format type <strong style="color: darkred">*</strong></form:label>
                                    <div class="col-sm-9 input-group">
                                        <form:radiobutton cssStyle="margin-right: 3px;" path="rdb_format" value="NTRIPLES" label="N-Triples" checked="checked"/>
                                        <form:radiobutton cssStyle="margin-left: 20px; margin-right: 3px;" path="rdb_format" value="RDFXML" label="RDF/XML"/>
                                        <form:radiobutton cssStyle="margin-left: 20px; margin-right: 3px;" path="rdb_format" value="TURTLE" label="TURTLE"/>
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
                                        <button type="submit" class="btn btn-primary btn-block"><i class="fa fa-bolt fa-fw" aria-hidden="true"></i> Dump to RDF</button>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </c:when>
                    <c:when test="${ type == 'shp'}">
                        <%--Shapefile form--%>
                        <form:form class="form-horizontal" method="POST" action="/geotriples_rdf_shp">
                            <div class="col-md-12">
                                <legend>Fill out the form:</legend>
                                <div class="form-group">
                                    <form:label class="control-label col-sm-3" path="shp_sourcefile" for="shp_sourcefile">Shapefile <strong style="color: darkred">*</strong></form:label>
                                    <div class="col-sm-9 input-group">
                                        <span class="input-group-addon input-sm"><i class="fa fa-file fa-fw"></i></span>
                                        <form:input path="shp_sourcefile" type="text" class="form-control input-sm" id="shp_sourcefile" value="${name}" readonly="true"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label class="control-label col-sm-3" path="shp_baseuri" for="rdbbaseuri">Base URI <strong style="color: darkred">*</strong></form:label>
                                    <div class="col-sm-9 input-group">
                                        <span class="input-group-addon input-sm"><i class="fa fa-database fa-fw"></i></span>
                                        <form:input path="shp_baseuri" type="text" class="form-control input-sm" id="shp_baseuri" value="http://example.com" required="required" placeholder="Base URI (required)"/>
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
                                    <form:label class="control-label col-sm-3" path="shp_mapfullpath" for="shp_mapfullpath">Mapping file <strong style="color: darkred">*</strong></form:label>
                                    <div class="col-sm-9 input-group">
                                        <span class="input-group-addon input-sm"><i class="fa fa-folder-open fa-fw"></i></span>
                                        <form:input path="shp_mapfullpath" type="text" class="form-control input-sm" readonly="true" id="shp_mapfullpath" required="required" value="${outmap_fullpath}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label class="control-label col-sm-3" path="shp_format">Format type <strong style="color: darkred">*</strong></form:label>
                                    <div class="col-sm-9 input-group">
                                        <form:radiobutton cssStyle="margin-right: 3px;" path="shp_format" value="NTRIPLES" label="N-Triples  " checked="checked"/>
                                        <form:radiobutton cssStyle="margin-left: 20px; margin-right: 3px;" path="shp_format" value="TURTLE" label="Turtle  "/>
                                        <form:radiobutton cssStyle="margin-left: 20px; margin-right: 3px;" path="shp_format" value="RDFXML" label="RDF/XML  "/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label class="control-label col-sm-3" path="shp_rml">RML Format </form:label>
                                    <div class="col-sm-1 input-group">
                                        <form:checkbox class="form-control input-sm" path="shp_rml" value="-rml" />
                                    </div>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <div class="col-md-6 col-md-offset-3">
                                        <button type="submit" class="btn btn-primary btn-block"><i class="fa fa-bolt fa-fw" aria-hidden="true"></i> Dump to RDF</button>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </c:when>
                    <c:when test="${ type == 'xml'}">
                        <%--XML/JSON form--%>
                        <form:form class="form-horizontal" method="POST" action="/geotriples_rdf_xml">
                            <div class="col-md-12">
                                <legend>Fill out the form:</legend>
                                <div class="form-group">
                                    <form:label class="control-label col-sm-3" path="xml_baseuri" for="xml_baseuri">Base URI <strong style="color: darkred">*</strong></form:label>
                                    <div class="col-sm-9 input-group">
                                        <span class="input-group-addon input-sm"><i class="fa fa-database fa-fw"></i></span>
                                        <form:input path="xml_baseuri" type="text" class="form-control input-sm" id="xml_baseuri" value="http://example.com" required="required" placeholder="Base URI (required)"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label class="control-label col-sm-3" path="xml_epsgcode" for="xml_epsgcode">EPSG Code </form:label>
                                    <div class="col-sm-9 input-group">
                                        <span class="input-group-addon input-sm"><i class="fa fa-terminal fa-fw"></i></span>
                                        <form:input path="xml_epsgcode" type="text" class="form-control input-sm" id="xml_epsgcode" placeholder="EPSG Code (optional)"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label class="control-label col-sm-3" path="xml_mapfullpath" for="xml_mapfullpath">Mapping file <strong style="color: darkred">*</strong></form:label>
                                    <div class="col-sm-9 input-group">
                                        <span class="input-group-addon input-sm"><i class="fa fa-folder-open fa-fw"></i></span>
                                        <form:input path="xml_mapfullpath" type="text" class="form-control input-sm" readonly="true" id="xml_mapfullpath" required="required" value="${outmap_fullpath}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label class="radio-inline control-label col-sm-3" path="xml_format">Format type <strong style="color: darkred">*</strong></form:label>
                                    <div class="col-sm-9 input-group">
                                        <form:radiobutton cssStyle="margin-right: 3px;" path="xml_format" value="NTRIPLES" label="N-Triples" checked="checked"/>
                                        <form:radiobutton cssStyle="margin-left: 20px; margin-right: 3px;" path="xml_format" value="RDFXML" label="RDF/XML"/>
                                        <form:radiobutton cssStyle="margin-left: 20px; margin-right: 3px;" path="xml_format" value="TURTLE" label="TURTLE"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label class="control-label col-sm-3" path="xml_rml">RML Format </form:label>
                                    <div class="col-sm-1 input-group">
                                        <form:checkbox class="form-control input-sm" path="xml_rml" value="-rml" checked="checked" readonly="true"/>
                                    </div>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <div class="col-md-6 col-md-offset-3">
                                        <button type="submit" class="btn btn-primary btn-block"><i class="fa fa-bolt fa-fw" aria-hidden="true"></i> Dump to RDF</button>
                                    </div>
                                </div>
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
                            <legend for="out-map">Produced mapping:</legend>
                            <textarea form="save_form" name="map_data" readonly style="resize: none" class="form-control" rows="20" id="out-map"><%
                                String outmap_file = (String) request.getAttribute("outmap_fullpath");
                                if( outmap_file != null && !outmap_file.isEmpty()){
                                    FileInputStream outmap_code = new FileInputStream(outmap_file);
                                    String outmap_code_str= org.apache.commons.io.IOUtils.toString(outmap_code);
                                    request.setAttribute("outmap_display", outmap_code_str);
                                }
                            %><c:out value="${outmap_display}"/>
                            </textarea>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <a href="/download?type=map&filename=${newfilename}" class="btn btn-primary btn-block btn-sm"><i class="fa fa-download fa-fw" aria-hidden="true"></i> Download file</a>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-md-6">
                                <button onclick="make_changes()" class="btn btn-block btn-sm btn-warning"><i class="fa fa-pencil fa-fw" aria-hidden="true"></i> Edit generated mapping</button>
                            </div>
                            <div class="col-md-6">
                                <form action="/geotriples_map_save" method="post" id="save_form">
                                    <input type="hidden" name="name" value="${name}">
                                    <input type="hidden" name="outmap_fullpath" value="${outmap_fullpath}">
                                    <input type="hidden" name="type" value="${type}">
                                    <button type="submit" style="visibility: hidden;" id="save-btn" class="btn btn-block btn-sm btn-success"><i class="fa fa-floppy-o fa-fw" aria-hidden="true"></i> Save changes</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>