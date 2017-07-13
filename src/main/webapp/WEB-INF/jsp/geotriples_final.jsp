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
            document.getElementById("out-map").readOnly = false;
            document.getElementById("save-btn").style.visibility = "visible";
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
                            <strong>Changes to RDF are saved!</strong> Output file: ${name}
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
        <div class="col-md-8">
            <div class="panel panel-primary">
                <div class="panel-heading">RDF Output file</div>
                <div class="panel-body">
                    <div class="form-group">
                        <label for="out-rdf">RDF file:</label>
                        <textarea form="save_form" name="rdf_data" readonly style="resize: none" class="form-control" rows="15" id="out-rdf"><%
                            String outrdf_file = (String) request.getAttribute("outrdf_fullpath");
                            if( outrdf_file != null && !outrdf_file.isEmpty()){
                                FileInputStream outrdf_code = new FileInputStream(outrdf_file);
                                String outrdf_code_str= org.apache.commons.io.IOUtils.toString(outrdf_code);
                                request.setAttribute("outrdf_display", outrdf_code_str);
                            }
                            %><c:out value="${outrdf_display}"/>
                        </textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="row">
                <div class="col-md-4">
                    <button onclick="make_changes()" class="btn btn-warning">Hmm, something seems wrong. Let's edit!</button>
                </div>
                <div class="col-md-offset-4 col-md-4">
                    <form action="/geotriples_rdf_save" method="post" id="save_form">
                        <input type="hidden" name="name" value=${name}>
                        <input type="hidden" name="outrdf_fullpath" value=${outrdf_fullpath}>
                        <button type="submit" style="visibility: hidden;" id="save-btn" class="btn btn-success">Save changes</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
