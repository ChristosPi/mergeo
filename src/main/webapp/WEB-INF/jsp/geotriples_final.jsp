<%@ page import="java.io.FileInputStream" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="<s:url value="/resources/css/geotriples.css"/>" rel="stylesheet">
    <title>MerGEO|Transform</title>

    <script>
        function make_changes() {
            document.getElementById("out-rdf").readOnly = false;
            document.getElementById("save-btn2").style.visibility = "visible";
        }
    </script>

</head>
<body>
<div class="container">

    <c:if test="${not empty name}">
        <div class="row">
            <c:choose>
                <c:when test="${changed == true}">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="alert alert-success" role="alert">
                            <strong>Changes saved!</strong> Output file: ${name}
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="col-md-4 col-md-offset-4">
                        <div class="alert alert-info" role="alert">
                            <strong>Dumped to RDF!</strong> Output file: ${name}
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>

    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading">RDF Output file</div>
                <div class="panel-body">
                    <div class="form-group">
                        <label for="out-rdf">RDF file:</label>
                        <textarea form="save_form2" name="rdf_data" readonly style="resize: none" class="form-control" rows="15" id="out-rdf"><%
                            String outrdf_file = (String) request.getAttribute("outrdf_fullpath");
                            if( outrdf_file != null && !outrdf_file.isEmpty()){
                                FileInputStream outrdf_code = new FileInputStream(outrdf_file);
                                String outrdf_code_str= org.apache.commons.io.IOUtils.toString(outrdf_code);
                                request.setAttribute("outrdf_display", outrdf_code_str);
                            }
                            %><c:out value="xaxax"/></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4">
            <%--<button onclick="make_changes()" class="btn btn-warning disabled">Hmm, something seems wrong. Let's edit!</button>--%>
            <button class="btn btn-warning disabled">Edit not supported</button>
        </div>
        <div class="col-md-3">
            <form action="/geotriples_rdf_save" method="post" id="save_form2">
                <input type="hidden" name="name" value="${name}">
                <input type="hidden" name="outrdf_fullpath" value="${outrdf_fullpath}">
                <button type="submit" style="visibility: hidden;" id="save-btn2" class="btn btn-success">Save changes</button>
            </form>
        </div>
        <div class="col-md-3 col-md-push-3">
            <div class="btn-group">
                <a href="#" class="btn btn-success dropdown-toggle" data-toggle="dropdown">Store to Endpoint | <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Store on default</a></li>
                    <li><a href="#">Store on current</a></li>
                    <li class="divider"></li>
                    <li><a href="#" data-toggle="modal" data-target="#modalCreate">Create new endpoint</a></li>
                </ul>
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
                        <form:form class="form-horizontal" method="post" action="/endpoint_create">

                            <label class="col-md-offset-5">Connection properties:</label>
                            <div class="form-group">
                                <form:label class="control-label col-sm-2" path="hostname" for="hostname">Hostname</form:label>
                                <div class="col-sm-10">
                                    <form:input path="hostname" type="text" class="form-control input-sm" id="hostname" value="localhost" required="required" />
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label class="control-label col-sm-2" path="port" for="port">Port</form:label>
                                <div class="col-sm-10">
                                    <form:input path="port" type="text" class="form-control input-sm" id="port" required="required" />
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label class="control-label col-sm-2" path="dbengine" for="dbengine">Database Engine</form:label>
                                <div class="col-sm-10">
                                    <form:input path="dbengine" type="text" class="form-control input-sm" id="dbengine" value="postgis" required="required" />
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label class="control-label col-sm-2" path="dbname" for="dbname">Database Name</form:label>
                                <div class="col-sm-10">
                                    <form:input path="dbname" type="text" class="form-control input-sm" id="dbname" required="required" />
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label class="control-label col-sm-2" path="username" for="username">Username</form:label>
                                <div class="col-sm-10">
                                    <form:input path="username" type="text" class="form-control input-sm" id="username" required="required" />
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label class="control-label col-sm-2" path="password" for="password">Password</form:label>
                                <div class="col-sm-10">
                                    <form:input path="password" type="password" class="form-control input-sm" id="password" required="required" />
                                </div>
                            </div>

                            <label class="col-md-offset-5">Credentials properties:</label>
                            <div class="form-group">
                                <form:label class="control-label col-sm-2" path="cp_username" for="cp_username">Credentials: Username</form:label>
                                <div class="col-sm-10">
                                    <form:input path="cp_username" type="text" class="form-control input-sm" id="cp_username" required="required" />
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label class="control-label col-sm-2" path="cp_password" for="cp_password">Credentials: Password</form:label>
                                <div class="col-sm-10">
                                    <form:input path="cp_password" type="password" class="form-control input-sm" id="cp_password" required="required" />
                                </div>
                            </div>

                            <hr>

                            <div class="form-group">
                                <form:label class="control-label col-sm-2" path="endpointname" for="endpointname">Endpoint's Name</form:label>
                                <div class="col-sm-10">
                                    <form:input path="endpointname" type="text" class="form-control input-sm" id="endpointname" required="required" />
                                </div>
                            </div>
                    </div>
                    <div class="modal-footer">
                        <div class="form-group">
                            <div class="col-sm-offset-9">
                                <button type="submit" class="btn btn-success">Create Endpoint!</button>
                            </div>
                        </div>
                    </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
