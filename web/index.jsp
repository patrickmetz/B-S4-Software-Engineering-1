<%--
author: Patrick Metz
--%>

<%@ page import="kunde.KundenTyp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Parkhaus-Simulator</title>

    <link rel="stylesheet" href="styles.css" />

    <%-- Angular: siehe \angular\parkhaus\src\app\preisformular --%>
    <script src="runtime-es2015.js" type="module"></script>
    <script src="runtime-es5.js" nomodule defer></script>
    <script src="polyfills-es5.js" nomodule defer></script>
    <script src="polyfills-es2015.js" type="module"></script>
    <script src="main-es2015.js" type="module"></script>
    <script src="main-es5.js" nomodule defer></script>

    <script src='https://ccmjs.github.io/mkaul-components/parkhaus/versions/ccm.parkhaus-8.0.0.js'></script>
    <ccm-parkhaus-8-0-0
            key='{"name":"parkhaus","server_url":"http://localhost:8080/ParkhausServlet",
            "client_categories": <% out.print(KundenTyp.alsJsonArray()); %>,
            "extra_buttons":[{"extra_class":"Umsatzsteuer","extra_inner":"Umsatzsteuer","extra_popup_title":"Umsatzsteuer"},
            {"extra_class":"Summe","extra_inner":"Summe","extra_popup_title":"Summe"},{"extra_class":"Durchschnitt","extra_inner":"Durchschnitt","extra_popup_title":"Durchschnitt"}],
            "extra_charts":[{"extra_class":"Parkdauer Diagramm","extra_inner":"Parkdauer Diagramm","extra_popup_title":"Parkdauer Diagramm"},{"extra_class":"Gruppen Diagramm","extra_inner":"Gruppen Diagramm","extra_popup_title":"Gruppen Diagramm"}]}'></ccm-parkhaus-8-0-0>
</head>
<body>

<div id="app">
<h2>Preis&shy;ver&shy;wal&shy;tung</h2>
<%-- Angular: siehe \angular\parkhaus\src\app\preisformular --%>
<app-root></app-root>

<h2>Manageransicht</h2>
<p>
    <b>Jahreseinnahmen:</b> <span id="JahresEinnahmen"></span>
</p>
<p>
    <b>Tageseinnahmen:</b> <span id="TagesEinnahmen"></span>
</p>

<br/><br/><br/><br/><br/>
</div>

<script type="text/javascript">
    function holeJahresEinnahmen() {
		fetch('ParkhausServlet?cmd=ManagersichtJahresEinnahmen')
			.then(response => response.text())
            .then(text => document
	            .getElementById("JahresEinnahmen")
	            .innerHTML = text);
	}

    function holeTagesEinnahmen() {
	    fetch('ParkhausServlet?cmd=ManagersichtTagesEinnahmen')
		    .then(response => response.text())
		    .then(text => document
			    .getElementById("TagesEinnahmen")
			    .innerHTML = text);
    }

    const interval1 = setInterval(() => holeJahresEinnahmen(), 1000);
    const interval2 = setInterval(() => holeTagesEinnahmen(), 1000);
</script>

</body>
</html>
