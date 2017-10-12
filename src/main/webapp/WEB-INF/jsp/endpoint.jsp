<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="<s:url value="/resources/css/endpoint.css"/>" rel="stylesheet">
    <title>mG|Strabon</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div style="text-align: center;" class="col-md-8 col-md-push-2">
            <h3><strong>Strabon</strong> Section</h3>
            <p>Here you can create a Stranon Endpoint and store your previously created RDF-data (or even upload and
            store your data). Fill out the form to summon your own Strabon Endpoint.</p>
        </div>
    </div><hr>
    <c:if test="${not empty no_end}">
        <c:if test="${no_end == true}">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div style="text-align: center;" class="alert alert-dismissible alert-warning" role="alert" style="text-align: center;">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>[ <i class="fa fa-exclamation-circle" aria-hidden="true"></i> ] No endpoint found!</strong> Let's create a new one
                    </div>
                </div>
            </div>
        </c:if>
    </c:if>
    <c:if test="${not empty formError}">
        <c:if test="${formError == true}">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div style="text-align: center;" class="alert alert-dismissible alert-danger" role="alert" style="text-align: center;">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>[ <i class="fa fa-exclamation-circle" aria-hidden="true"></i> ] Form error!</strong> Try again
                    </div>
                </div>
            </div>
        </c:if>
    </c:if>
    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-heading">Endpoint Creation Form</div>
            <div class="panel-body">
                <form:form class="form-horizontal" method="POST" action="/endpoint_create">
                    <div class="col-md-6">

                        <legend>Database Properties</legend>

                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="dbname" for="dbname">Database Name</form:label>
                            <div class="col-sm-8 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-database fa-fw"></i></span>
                                <form:input path="dbname" type="text" class="form-control input-sm" id="dbname" required="required" placeholder="Database name"/>
                                <form:errors path="dbname" cssStyle="color: #ff0000;" />
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="username" for="username">Username</form:label>
                            <div class="col-sm-8 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-user fa-fw"></i></span>
                                <form:input path="username" type="text" class="form-control input-sm" id="username" required="required" placeholder="Username"/>
                                <form:errors path="username" cssStyle="color: #ff0000;" />
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="password" for="password">Password</form:label>
                            <div class="col-sm-8 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-lock fa-fw"></i></span>
                                <form:input path="password" type="password" class="form-control input-sm" id="password" required="required" placeholder="Password"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="port" for="port">Port</form:label>
                            <div class="col-sm-8 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-external-link-square fa-fw"></i></span>
                                <form:input path="port" type="text" class="form-control input-sm" id="port" value="5432" required="required" placeholder="Port (e.g.: 5432)"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="hostname" for="hostname">Hostname</form:label>
                            <div class="col-sm-8 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-desktop fa-fw"></i></span>
                                <form:input path="hostname" type="text" class="form-control input-sm" id="hostname" value="localhost" required="required" placeholder="Hostname (e.g.: localhost)"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="dbengine" for="dbengine">Database Engine</form:label>
                            <div class="col-sm-8 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-database fa-fw"></i></span>
                                <form:input path="dbengine" type="text" class="form-control input-sm" id="dbengine" value="postgis" required="required" placeholder="Database Engine (e.g.: postgis)"/>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <legend>Endpoint's Credentials</legend>
                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="cp_username" for="cp_username">Credentials Username</form:label>
                            <div class="col-sm-8 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-user-circle-o fa-fw"></i></span>
                                <form:input path="cp_username" type="text" class="form-control input-sm" id="cp_username" required="required" placeholder="credentials.properties: Username"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="cp_password" for="cp_password">Credentials Password</form:label>
                            <div class="col-sm-8 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-lock fa-fw"></i></span>
                                <form:input path="cp_password" type="password" class="form-control input-sm" id="cp_password" required="required" placeholder="credentials.properties: Password"/>
                            </div>
                        </div>

                        <hr>

                        <div class="form-group">
                            <form:label class="control-label col-sm-4" path="endpointname" for="endpointname">Endpoint's Name</form:label>
                            <div class="col-sm-8 input-group">
                                <span class="input-group-addon input-sm"><i class="fa fa-id-badge fa-fw"></i></span>
                                <form:input path="endpointname" type="text" class="form-control input-sm" id="endpointname" required="required" placeholder="Endpoint's Name"/>
                            </div>
                        </div>

                        <hr><br><br>

                        <div class="form-group">
                            <div class="col-sm-6 col-sm-offset-6">
                                <button type="submit" class="btn btn-success btn-block"><i class="fa fa-plus-square fa-fw" aria-hidden="true"></i> Create Endpoint</button>
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
