<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
    <title>merGeo| Home</title>
</head>

<body>
<div class="container">


    <article id="welcome_text" class="row">
        <h2>Welcome to <strong>merGeo</strong> web-application</h2>
        <p>The app which Transforms, Stores and Visualizes RDF Data</p>
    </article>
    <hr>
    <section class="row">
        <article class="col-md-6">
            <h3>merGeo:<strong>about</strong></h3>
            <p><strong style="font-size: 18px;">merGeo</strong> is a web-application which combines the following Geospatial tools together: GeoTriples, Strabon
            and Sextant. Through this full-guided platform you can transform your data, store them into an Endpoint and finally visualize them.</p>
            <hr></ht><p><strong>&#8226; GeoTriples</strong> is a tool for transforming geospatial data from their original formats (e.g., shapefiles or spatially-enabled relational databases) into RDF.
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
            <br><br><br>
            <img src="./../../resources/img/diagram.png" width="500" height="405.3">
        </article>
    </section>

</div>

<jsp:include page="footer.jsp"/>
</body>
</html>