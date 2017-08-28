<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="bootstrap.jsp"/>
    <link href="<c:url value="/resources/css/header.css" />" rel="stylesheet">
    <title>MerGeo</title>
</head>
<body>
    <section class="container-fluid">

        <header class="row">
            <%--<h2 align="center">Welcome to MerGeo</h2>--%>
            <div class="com-md-2 col-md-offset-5">
                <a href="/"><img src="./../../resources/img/logo.png" alt="merGeo" width="213" height="94"></a>
            </div>
        </header>
        <header class="row">
            <div class="col-md-12">
                <h4 align="center">The web-app which combines GeoSpatial tools</h4>
            </div>
        </header>

        <nav class="navbar navbar-default">
            <nav class="navbar-header">
                <%--<a class="navbar-brand active" href="/">MerGe<span class="glyphicon glyphicon-globe"></span></a>--%>
                <a class="navbar-left active" href="/"><img src="./../../resources/img/logo.png" alt="merGeo" width="112" height="47"></a>
            </nav>
            <ul class="nav navbar-nav">
                <li><a href="/geotriples"><span class="glyphicon glyphicon-transfer"></span> 1.Transformation</a></li>
                <li><a href="/endpoint"><span class="glyphicon glyphicon-import"></span> 2.Endpoint</a></li>
                <li><a href="/sextant"><span class="glyphicon glyphicon-map-marker"></span> 3.Sextant</a></li>
            </ul>
        </nav>
    </section>
</body>
</html>
