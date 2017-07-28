<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="<s:url value="/resources/css/endpoint.css"/>" rel="stylesheet">
    <title>MerGEO|Endpoint</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <label class="col-sm-offset-4">Endpoint Creation Form:</label>
            <form:form class="form-horizontal" method="POST" action="/endpoint_create">
                <div class="form-group">
                    <form:label class="control-label col-sm-2" path="hostname" for="hostname">Hostname</form:label>
                    <div class="col-sm-10">
                        <form:input path="hostname" type="text" class="form-control" id="hostname" value="localhost" required="required" />
                    </div>
                </div>
                <div class="form-group">
                    <form:label class="control-label col-sm-2" path="port" for="port">Port</form:label>
                    <div class="col-sm-10">
                        <form:input path="port" type="text" class="form-control" id="port" required="required" />
                    </div>
                </div>
                <div class="form-group">
                    <form:label class="control-label col-sm-2" path="dbengine" for="dbengine">Database Engine</form:label>
                    <div class="col-sm-10">
                        <form:input path="dbengine" type="text" class="form-control" id="dbengine" value="postgis" required="required" />
                    </div>
                </div>
                <div class="form-group">
                    <form:label class="control-label col-sm-2" path="dbname" for="dbname">Database Name</form:label>
                    <div class="col-sm-10">
                        <form:input path="dbname" type="text" class="form-control" id="dbname" required="required" />
                    </div>
                </div>
                <div class="form-group">
                    <form:label class="control-label col-sm-2" path="username" for="username">Username</form:label>
                    <div class="col-sm-10">
                        <form:input path="username" type="text" class="form-control" id="username" required="required" />
                    </div>
                </div>
                <div class="form-group">
                    <form:label class="control-label col-sm-2" path="password" for="password">Password</form:label>
                    <div class="col-sm-10">
                        <form:input path="password" type="password" class="form-control" id="password" required="required" />
                    </div>
                </div>

                <hr>

                <div class="form-group">
                    <form:label class="control-label col-sm-2" path="cp_username" for="cp_username">Credentials: Username</form:label>
                    <div class="col-sm-10">
                        <form:input path="cp_username" type="text" class="form-control" id="cp_username" required="required" />
                    </div>
                </div>
                <div class="form-group">
                    <form:label class="control-label col-sm-2" path="cp_password" for="cp_password">Credentials: Password</form:label>
                    <div class="col-sm-10">
                        <form:input path="cp_password" type="password" class="form-control" id="cp_password" required="required" />
                    </div>
                </div>

                <hr>

                <div class="form-group">
                    <form:label class="control-label col-sm-2" path="endpointname" for="endpointname">Endpoint's Name</form:label>
                    <div class="col-sm-10">
                        <form:input path="endpointname" type="text" class="form-control" id="endpointname" required="required" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-8">
                        <button type="submit" class="btn btn-success">Create Endpoint!</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
