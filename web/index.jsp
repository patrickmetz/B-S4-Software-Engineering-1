<%--
author: Patrick Metz
--%>

<%@ page import="kunde.KundenTyp" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Parkhaus-Simulator</title>
    <script src='https://ccmjs.github.io/mkaul-components/parkhaus/versions/ccm.parkhaus-8.0.0.js'></script>
    <ccm-parkhaus-8-0-0
            key='{"name":"parkhaus","server_url":"http://localhost:8888/ParkhausServlet",
            "client_categories": <% out.print(KundenTyp.alsJsonArray()); %>,
            "extra_buttons":[{"extra_class":"Umsatzsteuer","extra_inner":"Umsatzsteuer","extra_popup_title":"Umsatzsteuer"},
            {"extra_class":"Summe","extra_inner":"Summe","extra_popup_title":"Summe"},{"extra_class":"Durchschnitt","extra_inner":"Durchschnitt","extra_popup_title":"Durchschnitt"}],
            "extra_charts":[{"extra_class":"Parkdauer Diagramm","extra_inner":"Parkdauer Diagramm","extra_popup_title":"Parkdauer Diagramm"},{"extra_class":"Gruppen Diagramm","extra_inner":"Gruppen Diagramm","extra_popup_title":"Gruppen Diagramm"}]}'></ccm-parkhaus-8-0-0>
</head>
<body>

<h1>Preise</h1>

    <form id="PreisFormular" action="ParkhausServlet?cmd=PreiseSpeichern" method="post" enctype="application/x-www-form-urlencoded">
        <table>
        <% for (KundenTyp kundenTyp : KundenTyp.values()) {
            out.println(
                    "<tr>"+
                            "<td>" + kundenTyp.getBezeichnung() + "</td>" +
                            "<td><input type=\"text\" id=\"preis" + kundenTyp.toString() + "\"  name=\"" + kundenTyp.toString() + "\" value=\"" + kundenTyp.getInitialPreis() + "\"></td>" +
                    "</tr>"
            );
        }%>
        </table>

        <br /> <br />
        <input type="submit" value="Speichern">
    </form>

</body>
</html>
