<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="bootstrap.jsp"/>
    <link href="<c:url value="/resources/css/header.css" />" rel="stylesheet">
    <title>MerGeo</title>
</head>
<body>
    <section class="container-fullwidth">
        <div class="jumbotron">
            <header class="row">
                <%--<div class="col-md-2">--%>
                    <%--<a href="http://en.uoa.gr/"><img src="./../../resources/img/uoa_logo.png" alt="uoa"></a>--%>
                <%--</div>--%>
                <div class="com-md-2 col-md-offset-5">
                    <a href="/"><img src="./../../resources/img/logo-main.png" alt="merGeo" width="303" height="92"></a>
                </div>
            </header>
            <%--<header class="row">--%>
                <%--<div class="col-md-6">--%>
                    <%--<h4 align="center">GeoSpatial tools application</h4>--%>
                <%--</div>--%>
            <%--</header>--%>
        </div>

        <nav class="navbar navbar-default navbar-static-top">
            <%--<div class="navbar-header">--%>
                <%--&lt;%&ndash;<a class="navbar-brand active" href="/">MerGe<span class="glyphicon glyphicon-globe"></span></a>&ndash;%&gt;--%>
                <%--<a class="navbar-left active" href="/"><img src="./../../resources/img/logo-light.png" alt="merGeo" width="112" height="47"></a>--%>
            <%--</div>--%>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li style="border-right: 1px solid #18BC9C;"><a href="/index">[ <span class="glyphicon glyphicon-home"></span> ] Home</a></li>
                    <li><a href="/geotriples">[ <span class="glyphicon glyphicon-transfer"></span> ] GeoTriples</a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">[ <span class="glyphicon glyphicon-import"></span> ] Endpoint
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/endpoint">[ <span class="glyphicon glyphicon-plus"></span> ] Create new</a></li>
                            <li><a href="/endpoint_run">[ <span class="glyphicon glyphicon-saved"></span> ] Work on current</a></li>
                            <li class="divider"></li>
                            <li><a href="/endpoint_default">[ <span class="glyphicon glyphicon-saved"></span> ] Work on default</a></li>
                        </ul>
                    </li>
                    <%--<li><a href="/endpoint"><span class="glyphicon glyphicon-import"></span> 2.Endpoint</a></li>--%>
                    <li><a href="/sextant">[ <span class="glyphicon glyphicon-map-marker"></span> ] Sextant</a></li>
                    <li style="border-left: 1px solid #18BC9C;"><a href="#">[ <span class="glyphicon glyphicon-info-sign"></span> ] Tutorials</a></li>
                </ul>
            </div>
        </nav>
    </section>
</body>
</html>
