<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
    <title>mG|Home</title>
</head>

<body>
<div class="container">


    <article id="welcome_text" class="row">
        <h2>Welcome to <strong>merGeo</strong> web-application</h2>
        <p>The app which Transforms, Stores and Visualizes Linked Data</p>
    </article>
    <hr>
    <section class="row">
        <article class="col-md-6">
            <h3 class="text-center">merGeo::<strong>about</strong></h3>
            <p><strong style="font-size: 18px;">merGeo</strong> is an application which focuses on sharing and reusing Linked Data across different applications.
                Although many tools and applications for exploiting this abundance of information have also started to emerge, most of them focalize in a specific processing procedure.
                However, merGeo aims to combine three very essential tasks on Linked data:
                <li>The transformation process from Data to Linked Data (GeoTriples)</li>
                <li>The storing and exploration of Linked Data through SPARQL Endpoints (Strabon)</li>
                <li>The visualization of these Linked Data on a map (Sextant)</li><br>
                All these features were merged together into one user-friendly, step-by-step guided and simple Web Application, offering the final user a unified platform for Linked (especially geospatial) Data manipulation.</p>
            <hr>
            <h3 class="text-center">merGeo::<strong>tools</strong></h3>
            <p><strong>&#8226; GeoTriples</strong> is a tool for transforming geospatial data from their original formats (e.g., shapefiles or spatially-enabled relational databases) into RDF.
                The following input formats are supported: spatially-enabled relational databases (PostGIS and MonetDB), ESRI shapefiles and XML, GML, KML, JSON, GeoJSON
                and CSV documents. &nbsp;<a target="_blank" href="http://geotriples.di.uoa.gr">more info...</a></p>
            <p><strong>&#8226; Strabon</strong> is a spatiotemporal RDF store. You can use it to store linked geospatial data that changes over time and pose queries using two popular extensions of SPARQL.
                Strabon supports spatial datatypes enabling the serialization of geometric objects in OGC standards WKT and GML.
                &nbsp;<a target="_blank" href="http://strabon.di.uoa.gr">more info...</a></p>
            <p><strong>&#8226; Sextant</strong> is a web based and mobile ready platform for visualizing, exploring and interacting with linked geospatial data.
                The core feature of Sextant is the ability to create thematic maps by combining geospatial and temporal information that exists in a number of heterogeneous data sources ranging from standard SPARQL endpoints,
                to SPARQL endpoints following the standard GeoSPARQL defined by the Open Geospatial Consortium (OGC), or well-adopted geospatial file formats, like KML, GML and GeoTIFF.
                &nbsp;<a target="_blank" href="http://sextant.di.uoa.gr">more info...</a></p>
        </article>
        <article class="col-md-6">
            <h3 class="text-center">merGeo::<strong>workflow</strong></h3>
            <p>The following diagram displays the workflow of merGeo. The initial task is to transform Data into Linked
            Data through Geotriples interface, then store and explore them by using Strabon RDF store and finally
            visualize them on a map through Sextant's services.</p>
            <img class="img-responsive " src="./../../resources/img/diagram.png" width="500" height="405.3">
        </article>
    </section>

</div>

<jsp:include page="footer.jsp"/>
</body>
</html>