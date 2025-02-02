<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="bootstrap.jsp"/>
    <link href="<c:url value="/resources/css/header.css" />" rel="stylesheet">
</head>
<body>
    <section class="container-fullwidth">
        <div class="jumbotron">
            <header class="row">
                <%--<div class="col-md-2">--%>
                    <%--<a href="http://en.uoa.gr/"><img src="./../../resources/img/uoa_logo.png" alt="uoa"></a>--%>
                <%--</div>--%>
                <div class="col-sm-4 col-lg-4 col-lg-offset-4 col-sm-offset-4">
                    <a href="/"><img class="img-responsive center-block" src="./../../resources/img/logo.png" alt="merGeo" width="400" height="125.17"></a>
                </div>
                <%--<div class="col-md-2 col-md-offset-4">--%>
                    <%--<a target="_blank" href="http://www.di.uoa.gr/eng"><img class="img-responsive center-block" src="./../../resources/img/dit-logo.png"  style="padding-top: 30%;" width="149.5" height="37" alt="DiT"></a>--%>
                <%--</div>--%>
            </header>
        </div>

        <nav class="navbar navbar-default navbar-static-top">
            <%--<div class="navbar-header">--%>
                <%--&lt;%&ndash;<a class="navbar-brand active" href="/">MerGe<span class="glyphicon glyphicon-globe"></span></a>&ndash;%&gt;--%>
                <%--<a class="navbar-left active" href="/"><img src="./../../resources/img/logo-light.png" alt="merGeo" width="112" height="47"></a>--%>
            <%--</div>--%>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li style="border-right: 2px solid #18BC9C;"><a href="/index"><i class="fa fa-home fa-lg fa-fw" aria-hidden="true"></i>&nbsp; Home</a></li>
                    <li><a href="/geotriples"><i class="fa fa-bars fa-lg fa-fw" aria-hidden="true"></i>&nbsp; GeoTriples</a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-download fa-lg fa-fw" aria-hidden="true"></i>&nbsp; Strabon
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/endpoint"><i class="fa fa-plus-circle fa-lg fa-fw" aria-hidden="true"></i>&nbsp; Create new Endpoint</a></li>
                            <li class="divider"></li>
                            <li><a href="/endpoint_run"><i class="fa fa-arrow-circle-right fa-lg fa-fw" aria-hidden="true"></i>&nbsp; Work on <strong>&lt;created&gt;</strong> Endpoint</a></li>
                            <li><a href="/endpoint_default"><i class="fa fa-arrow-circle-right fa-lg fa-fw" aria-hidden="true"></i>&nbsp; Work on <strong>&lt;default&gt;</strong> Endpoint</a></li>
                        </ul>
                    </li>
                    <li><a href="/sextant"><i class="fa fa-map-marker fa-lg fa-fw" aria-hidden="true"></i>&nbsp; Sextant</a></li>
                    <li style="border-left: 2px solid #18BC9C;"><a href="/info"><i class="fa fa-info-circle fa-lg fa-fw" aria-hidden="true"></i>&nbsp; Information</a></li>
                </ul>
            </div>
        </nav>
    </section>
</body>
</html>
