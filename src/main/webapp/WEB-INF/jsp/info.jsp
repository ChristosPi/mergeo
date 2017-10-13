<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <title>mG|Info</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div style="text-align: center;" class="col-md-8 col-md-push-2">
            <h3><strong>Information</strong> Section</h3>
            <p>Learn about the app & find useful instructions</p>
        </div>
    </div><hr>

    <div class="row">
        <div class="col-md-12">
            <legend align="center">merGeo::<strong>instructions</strong></legend>
            <p>In merGeo, you have the ability to work either on a geospatial-tool exclusively or use them in a combination.</p>
            <p>Starting with <strong>GeoTriples</strong>, you can transform your Data into Linked Data through 3 full-guided and user-friendly
            steps. Firstly, you have to choose a input method which can be:
            <ul style="list-style-type:square">
                <li>A Relational-Database</li>
                <li>A Shapefile</li>
                <li>A XML file</li>
            </ul>
            Then, fill out the shown form and generate a mapping. After that, the produced mapping is been displayed and
            you can Download it or even Edit it if something seems incorrect. In case everything is alright, you fill out
            the new form and proceed into dumping the Data into RDF Linked Data. Done! The final RDF Linked Data is been displayed
            and you are able to Download/Edit it or choose to move forward and store it into an available SPARQL Endpoint. (note:
            at this point, the user also has the option to create a new Endpoint through a pop-up form.)</p>
            <p>With <strong>Strabon</strong>, you can create your own Endpoint Client, based on a local PostgreSQL database, by following the "Strabon"
            section from our navigation panel. After the new Endpoint Client is created, you can have full access on
            Strabon's services like "Store" (directly or via a URI) and "Query/Update". In case you want to avoid
            the creation process, a default Endpoint Client is already running on merGeo in order to store/explore your data.
            If you choose to start with GeoTriples and store the transformed Linked Data into a Strabon Endpoint, the whole
            Strabon interface will be shown to you after the storing process (in GeoTriples final step) has been completed.</p>
            <p>Finally, <strong>Sextant</strong> is the ideal geospatial tool to visualize the stored RDF Data. Either you
            choose to work on Sextant independently by following "Sextant" from navbar or from its integration with Strabon,
            the visualized results and the available configuration options will surely satisfy your needs.</p>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-6">
            <legend align="center">merGeo::<strong>contribution</strong></legend>
            <p>MerGeo is an attempt to provide a user-friendly application that allows both domain experts
                and non-experts to take advantage of semantic web tools and technologies and also convince them to adopt
                these technologies by presenting the benefits and the convenience of using together different
                applications that are based on linked geospatial data, through the use of merGeo.</p>
        </div>
        <div class="col-md-6">
            <legend align="center">merGeo::<strong>credits</strong></legend>
            <p>MerGeo web-application was developed within the dissertation named:
                "merGeo: Engineering the geospatial-tools application" by <strong>Christos Papaloukas</strong>, under the supervision
            of Professor <strong>Manolis Koubarakis</strong> and PhD Candidate <strong>George Stamoulis</strong>.</p>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>