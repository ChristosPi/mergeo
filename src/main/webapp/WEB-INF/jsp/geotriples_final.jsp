<%@ page import="java.io.FileInputStream" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="<s:url value="/resources/css/geotriples.css"/>" rel="stylesheet">
    <title>mG|GeoTriples</title>

    <script>
        function make_changes() {
            document.getElementById("out-rdf").readOnly = false;
            document.getElementById("save-btn2").style.visibility = "visible";
        }
    </script>

</head>
<body>
<div class="container">
    <div class="row">
        <div style="text-align: center;" class="col-md-8 col-md-push-2">
            <h3><strong>GeoTriples</strong> Section</h3>
            <p><strong>Step 3/3: </strong>Done! Your RDF Data is ready. Check if everything is alright and move forward into storing the Linked Data into a SPARQL Endpoint.</p>
        </div>
    </div><hr>
    <c:if test="${not empty name}">
        <div class="row">
            <c:choose>
                <c:when test="${changed == true}">
                    <div class="col-md-6 col-md-offset-3">
                        <div style="text-align: center;" class="alert alert-dismissible alert-success" role="alert">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>[ <i class="fa fa-exclamation-circle" aria-hidden="true"></i> ] Changes saved!</strong> Output file: ${name}
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="col-md-6 col-md-offset-3">
                        <div style="text-align: center;" class="alert alert-dismissible alert-info" role="alert">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>[ <i class="fa fa-exclamation-circle" aria-hidden="true"></i> ] Dumped to RDF!</strong> Output file: ${name}
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>
    <c:if test="${not empty formError}">
        <c:if test="${formError == true}">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div style="text-align: center;" class="alert alert-dismissible alert-danger" role="alert" style="text-align: center;">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>[ <i class="fa fa-exclamation-circle" aria-hidden="true"></i> ] Endpoint form error!</strong> Try again
                    </div>
                </div>
            </div>
        </c:if>
    </c:if>

    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading">Generated RDF data</div>
                <div class="panel-body">
                    <div class="form-group">
                        <legend for="out-map">Produced RDF:</legend>
                        <textarea form="save_form2" name="rdf_data" readonly style="resize: none" class="form-control" rows="15" id="out-rdf"><%
                            String outrdf_file = (String) request.getAttribute("outrdf_fullpath");
                            if( outrdf_file != null && !outrdf_file.isEmpty()){
                                FileInputStream outrdf_code = new FileInputStream(outrdf_file);
                                String outrdf_code_str= org.apache.commons.io.IOUtils.toString(outrdf_code);
                                request.setAttribute("outrdf_display", outrdf_code_str);
                            }
                            %>Due to file size preview is not recommended</textarea>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <a href="/download?type=rdf&filename=${name}" class="btn btn-primary btn-sm btn-block"><i class="fa fa-download fa-fw" aria-hidden="true"></i> Download file</a>
                        </div>
                        <div class="col-md-4">
                            <button id="editbtn" class="btn btn-warning btn-sm btn-block"><i class="fa fa-pencil fa-fw" aria-hidden="true"></i> Edit generated RDF file</button>
                            <%--<button onclick="make_changes()" id="editbtn" class="btn btn-warning">Something seems wrong? Let's edit!</button>--%>
                        </div>
                        <div class="col-md-4">
                            <form action="/geotriples_rdf_save" method="post" id="save_form2">
                                <input type="hidden" name="name" value="${name}">
                                <input type="hidden" name="outrdf_fullpath" value="${outrdf_fullpath}">
                                <button type="submit" style="visibility: hidden;" id="save-btn2" class="btn btn-success btn-sm btn-block"><i class="fa fa-floppy-o fa-fw" aria-hidden="true"></i> Save changes</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="well col-md-8 col-md-offset-2 text-center" style="border:3px solid #2C3E50;">
            <legend><strong>Transformation Completed!</strong> Now, save on Endpoint:</legend>
            <%--<div class="btn-group">--%>
                <%--<a href="#" class="btn btn-success dropdown-toggle" data-toggle="dropdown">Store to Endpoint |  <span class="caret"></span></a>--%>
                <%--<ul class="dropdown-menu">--%>
                    <%--<li><a href="/geotriples_defStore">Store on default</a></li>--%>
                    <%--<c:if test="${not empty endpoint}">--%>
                        <%--<li><a href="/geotriples_curStore">Store on current</a></li>--%>
                    <%--</c:if>--%>
                    <%--<li class="divider"></li>--%>
                    <%--<li><a href="#" data-toggle="modal" data-target="#modalCreate">Create new endpoint</a></li>--%>
                <%--</ul>--%>
            <%--</div>--%>

            <form action="/geotriples_defstore" id="form_defstore" method="post" >
                <input type="hidden" value="${outrdf_fullpath}" name="rdf_input_path"/>
                <input type="hidden" value="${outrdf_format}" name="rdf_input_format"/>
                <input type="hidden" value="${name}" name="rdf_input_name"/>
            </form>
            <form action="/geotriples_curstore" id="form_curstore" method="post" >
                <input type="hidden" value="${outrdf_fullpath}" name="rdf_input_path"/>
                <input type="hidden" value="${outrdf_format}" name="rdf_input_format"/>
                <input type="hidden" value="${name}" name="rdf_input_name"/>
            </form>

            <div id="triplebtn" class="btn-group">
                <button href="#" data-toggle="modal" data-target="#modalCreate" class="btn btn-primary"><i class="fa fa-plus-circle fa-fw"></i> Create new Endpoint</button>
                <button type="submit" form="form_defstore" class="btn btn-success"><i class="fa fa-arrow-circle-right fa-fw"></i> Store on <strong>&lt;default&gt;</strong> Endpoint</button>
                <c:if test="${not empty endpoint}">
                    <button type="submit" form="form_curstore" class="btn btn-success"><i class="fa fa-arrow-circle-right fa-fw"></i> Store on <strong>&lt;created&gt;</strong> Endpoint</button>
                </c:if>
            </div>
        </div>
    </div>

    <div id="modalCreate" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Create a new Endpoint and store RDF data</h4>
                </div>
                <div class="modal-body">
                    <form:form class="form-horizontal" method="post" action="/geotriples_createnstore">
                        <input type="hidden" value="${outrdf_fullpath}" name="rdf_input_path"/>
                        <input type="hidden" value="${outrdf_format}" name="rdf_input_format"/>
                        <input type="hidden" value="${name}" name="rdf_input_name"/>

                        <legend>Database Properties</legend>
                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="dbname" for="dbname">Database Name</form:label>
                            <div class="col-sm-6 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-database fa-fw"></i></span>
                                <form:input path="dbname" type="text" class="form-control input-sm" id="dbname" required="required" placeholder="Database name"/>
                                <form:errors path="dbname" cssStyle="color: #ff0000;" />
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="username" for="username">Username</form:label>
                            <div class="col-sm-6 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-user fa-fw"></i></span>
                                <form:input path="username" type="text" class="form-control input-sm" id="username" required="required" placeholder="Username"/>
                                <form:errors path="username" cssStyle="color: #ff0000;" />
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="password" for="password">Password</form:label>
                            <div class="col-sm-6 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-lock fa-fw"></i></span>
                                <form:input path="password" type="password" class="form-control input-sm" id="password" required="required" placeholder="Password"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="port" for="port">Port</form:label>
                            <div class="col-sm-6 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-external-link-square fa-fw"></i></span>
                                <form:input path="port" type="text" class="form-control input-sm" id="port" required="required" value="5432" placeholder="Port (e.g.: 5432)"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="hostname" for="hostname">Hostname</form:label>
                            <div class="col-sm-6 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-desktop fa-fw"></i></span>
                                <form:input path="hostname" type="text" class="form-control input-sm" id="hostname" value="localhost" required="required" placeholder="Hostname (e.g.: localhost)"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="dbengine" for="dbengine">Database Engine</form:label>
                            <div class="col-sm-6 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-database fa-fw"></i></span>
                                <form:input path="dbengine" type="text" class="form-control input-sm" id="dbengine" value="postgis" required="required" placeholder="Database Engine (e.g.: postgis)"/>
                            </div>
                        </div>
                        <hr>
                        <legend>Endpoint's Credentials</legend>
                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="cp_username" for="cp_username">Credentials Username</form:label>
                            <div class="col-sm-6 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-user-circle-o fa-fw"></i></span>
                                <form:input path="cp_username" type="text" class="form-control input-sm" id="cp_username" required="required" placeholder="credentials.properties Username"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="cp_password" for="cp_password">Credentials Password</form:label>
                            <div class="col-sm-6 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-lock fa-fw"></i></span>
                                <form:input path="cp_password" type="password" class="form-control input-sm" id="cp_password" required="required" placeholder="credentials.properties Password"/>
                            </div>
                        </div>

                        <hr>

                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="endpointname" for="endpointname">Endpoint's Name</form:label>
                            <div class="col-sm-6 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-id-badge fa-fw"></i></span>
                                <form:input path="endpointname" type="text" class="form-control input-sm" id="endpointname" required="required" placeholder="Endpoint's Name"/>
                            </div>
                        </div>
                </div>
                <div class="modal-footer">
                    <div class="form-group">
                        <div class="col-sm-offset-3">
                            <button type="submit" class="btn btn-success">Create Endpoint & Store data!</button>
                        </div>
                    </div>
                </div>
                    </form:form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
