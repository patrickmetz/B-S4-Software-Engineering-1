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
            key='{"name":"parkhaus","server_url":"http://localhost:8080/ParkhausServlet",
            "client_categories": <% out.print(KundenTyp.alsJsonArray()); %>,
            "extra_buttons":[{"extra_class":"Umsatzsteuer","extra_inner":"Umsatzsteuer","extra_popup_title":"Umsatzsteuer"},
            {"extra_class":"Summe","extra_inner":"Summe","extra_popup_title":"Summe"},{"extra_class":"Durchschnitt","extra_inner":"Durchschnitt","extra_popup_title":"Durchschnitt"}],
            "extra_charts":[{"extra_class":"Parkdauer Diagramm","extra_inner":"Parkdauer Diagramm","extra_popup_title":"Parkdauer Diagramm"},{"extra_class":"Gruppen Diagramm","extra_inner":"Gruppen Diagramm","extra_popup_title":"Gruppen Diagramm"}]}'></ccm-parkhaus-8-0-0>
</head>
<body>

<h1>Preise</h1>

<form id="'preisFormular" action="ParkhausServlet?cmd=PreiseSpeichern" method="post"
      enctype="application/x-www-form-urlencoded">
    <table>
        <% for (KundenTyp kundenTyp : KundenTyp.values()) {
            out.println(
                    "<tr>" +
                            "<td>" + kundenTyp.getBezeichnung() + "</td>" +
                            "<td><input type='number' step='any' class='preisFeld' id='preis" + kundenTyp.toString() + "'  name='" + kundenTyp.toString() + "' value=''></td>" +
                            "</tr>"
            );
        }%>
    </table>

    <br/> <br/>
    <input type="submit" value="Speichern">
</form>

<script type="text/javascript">
    // author: Patrick Metz

    function zeigePreise(data) {
        for (let [kundenTyp, betrag] of Object.entries(data)) {
            document
                .getElementById("preis" + kundenTyp)
                .setAttribute("value", betrag);
        }
    }

    function zeigePreiseUndMeldung(data) {
        zeigePreise(data);
        window.alert("Die Preise wurden gespeichert.")
    }

    function holeAktuellePreise() {
        fetch('ParkhausServlet?cmd=PreiseZeigen')
            .then(response => response.json())
            .then(json => zeigePreise(json));
    }

    function sendeNeuePreise(event) {
        let formular = event.target;

        fetch(formular.action, {
            method: formular.method,
            body: erzeugePostBody(),
        })
            .then(response => response.json())
            .then(json => zeigePreiseUndMeldung(json));
    }

    function erzeugePostBody() {
        var postBody = [];

        var formularFelder = document.getElementsByClassName("preisFeld");

        for (i = 0; i < formularFelder.length; i++) {
            feldWert = formularFelder[i].value;
            feldName = formularFelder[i].getAttribute("name");

            var schluessel = encodeURIComponent(feldName);
            var wert = encodeURIComponent(feldWert);

            postBody.push(schluessel + "=" + wert);
        }

        postBody = postBody.join("&");

        return postBody;
    }

    function setzeSeiteGeladenEreignis() {
        document.addEventListener('DOMContentLoaded', (event) => {
            holeAktuellePreise();
        });
    }

    function setzeFormularSendenEreignis() {
        document.getElementById("'preisFormular")
            .addEventListener('submit', event => {
                event.preventDefault();
                sendeNeuePreise(event);
            });
    }

    setzeSeiteGeladenEreignis();
    setzeFormularSendenEreignis();
</script>
</body>
</html>
