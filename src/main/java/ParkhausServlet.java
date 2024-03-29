import Fahrzeuge.FahrzeugTyp;
import kunde.*;
import preis.PreisVerwaltungControllerIF;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @author Team
 */
@WebServlet("/ParkhausServlet")
public class ParkhausServlet extends HttpServlet {

    public static final String REGEX_POSTBODY_REQUEST_FORM = "^(?:(?:[^&]+)&)+[^&]+$";
    public static final String REGEX_POSTBODY_REQUEST_CSV = "^(?:(?:[^,]+),)+[^,]+$";

    private ParkhausIF parkhaus;
    private KundenDatenProcessorIF kundenDatenProcessor;
    private ParkhausChartProcessorIF parkhausChartProcessor;
    private PreisVerwaltungControllerIF preisVerwaltungController;
    private EinnahmenControllerIF einnahmenController;

    public void init() {
        parkhaus = getParkhaus();
        kundenDatenProcessor = getKundenDatenProcessor();
        parkhausChartProcessor = getParkhausChartProcessor();
        preisVerwaltungController = parkhaus.getBezahlAutomat().getPreisVerwaltungController();
        einnahmenController = getEinnahmenController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String postBody = getPostBody(request);
        HashMap<String, String> postMap = new HashMap<>();

        if (postBody.matches(REGEX_POSTBODY_REQUEST_FORM)) {
            postMap = getPostHashMapForm(postBody);

            handlePostPreiseSpeichern(response, postMap);

        } else if (postBody.matches(REGEX_POSTBODY_REQUEST_CSV)) {
            postMap = getPostHashMapCsv(postBody);

            switch (postMap.get("cmd")) {
                case "enter":
                    handlePostEnter(response, postMap);
                    break;

                case "leave":
                    handlePostLeave(response, postMap);
                    break;

                default:
                    System.out.println("Invalid POST-command: " + request.getQueryString());
            }

        }

        System.out.println("POST-command: " + postMap.get("cmd"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<String, String> queryMap = getQueryHashMap(request);

        switch (queryMap.get("cmd")) {
            case "config":
                handleGetConfig(response);
                break;

            case "Durchschnitt":
                sendResponse(response,
                        "Durchschnittseinahme: " + KundenDatenUtils.floatToEuro(getKundenDatenProcessor().getDurschnittsPreis())
                                + " Durschnittsparkdauer: " + KundenDatenUtils.formatDauer(getKundenDatenProcessor().getDurchschnittsDauer()));
                break;

            case "Summe":
                sendResponse(response, KundenDatenUtils.floatToEuro(getKundenDatenProcessor().getSumme()));
                break;

            case "Umsatzsteuer":
                sendResponse(response, KundenDatenUtils.floatToEuro(getKundenDatenProcessor().getUmsatzSteuer()));
                break;

            case "Parkdauer%20Diagramm":
                sendResponse(response, parkhausChartProcessor.getKundenBarChart());
                break;

            case "Gruppen%20Diagramm":
                sendResponse(response, parkhausChartProcessor.getKundenPieChart());
                break;

            case "PreiseZeigen":
                sendResponse(response, preisVerwaltungController.getView().view());
                break;

            case "ManagersichtJahresEinnahmen":
                sendResponse(response, einnahmenController.getJahresEinnahmenView().view());
                break;

            case "ManagersichtTagesEinnahmen":
                sendResponse(response, einnahmenController.getTagesEinnahmenView().view());
                break;

            case "SteuerdatenUebermitteln":
                TaxReturn taxReturn = new TaxReturn();
                taxReturn.addCommand(
                        () -> TaxReturn.taxReturnCommand(parkhaus.getParkhausStatistics())
                );

                sendResponse(response, "Die Steuerberechung wurde erfolgreich beauftragt und wird nun im Hintergrund durchgeführt. " +
                        "Anschließend werden die Daten automatisch an das Finanzamt übermittelt.");

                taxReturn.executeCommands();
                break;

            default:
                System.out.println("Invalid GET-command: " + request.getQueryString());
        }
    }

    private void handleGetConfig(HttpServletResponse response) throws IOException {
        // see https://kaul.inf.h-brs.de/se/#app-content-4-0&03_Technologien=page-53
        sendResponse(response, "100,6,24,100,10");
    }

    private void handlePostEnter(HttpServletResponse response, HashMap<String, String> postMap) {
        FahrzeugTyp fahrzeugTyp = FahrzeugTyp.randomFahrzeugTyp();
        KundenDatenIF kundenDaten = new KundenDaten(postMap.get("csv").split(","), fahrzeugTyp);
        KundeIF kunde = new Kunde(kundenDaten);

        ParkticketIF ticket = getParkhaus().einfahren(kunde);
        getParkhaus().addParkticket(kundenDaten.getTickethash(), ticket);
    }

    private void handlePostLeave(HttpServletResponse response, HashMap<String, String> postMap) throws IOException {
        KundenDatenIF kundenDaten = new KundenDaten(postMap.get("csv").split(","));

        BezahlAutomatIF automat = new BezahlAutomat(preisVerwaltungController);
        ParkticketIF ticket = getParkhaus().getParkticket(kundenDaten.getTickethash());

        automat.bezahlen(ticket, Optional.empty());
        parkhaus.ausfahren(ticket, kundenDaten);

        sendResponse(response, postMap.get("csv"));
    }

    private void handlePostPreiseSpeichern(HttpServletResponse response, HashMap<String, String> postMap) throws IOException {
        preisVerwaltungController.setPreiseAlsStringMap(postMap);

        sendResponse(response, preisVerwaltungController.getView().view());
    }

    private static HashMap<String, String> getQueryHashMap(HttpServletRequest request) {
        HashMap<String, String> map = new HashMap<>();

        for (String splitByAnd : request.getQueryString().split("&")) {
            String[] splitByEquals = splitByAnd.split("=");
            map.put(splitByEquals[0], splitByEquals[1]);
        }

        return map;
    }

    private static HashMap<String, String> getPostHashMapCsv(String postBody) {
        HashMap<String, String> map = new HashMap<>();
        String[] splitByFirstComma = postBody.split(",", 2);

        map.put("cmd", splitByFirstComma[0]);
        map.put("csv", splitByFirstComma[1]);

        return map;
    }

    private static HashMap<String, String> getPostHashMapForm(String postBody) {
        HashMap<String, String> map = new HashMap<>();
        String[] splitByAnd = postBody.split("&");

        for (String split : splitByAnd) {
            String[] splitByEquals = split.split("=");

            map.put(splitByEquals[0], splitByEquals[1]);
        }

        return map;
    }

    private static String getPostBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return stringBuilder.toString();
    }

    private static void sendResponse(HttpServletResponse response, String htmlContent) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(htmlContent);
    }

    private ServletContext getApplication() {
        return getServletConfig().getServletContext();
    }

    private ParkhausIF getParkhaus() {
        if (null == parkhaus) {
            parkhaus = (Parkhaus) getApplication().getAttribute("parkhaus");

            if (null == parkhaus) {
                parkhaus = new Parkhaus();
                getApplication().setAttribute("parkhaus", parkhaus);
            }
        }

        return parkhaus;
    }

    private KundenDatenProcessorIF getKundenDatenProcessor() {
        if (null == kundenDatenProcessor) {
            kundenDatenProcessor = (KundenDatenProcessor) getApplication().getAttribute("datenProcessor");

            if (null == kundenDatenProcessor) {
                kundenDatenProcessor = new KundenDatenProcessor(getParkhaus());
                getApplication().setAttribute("datenProcessor", kundenDatenProcessor);
            }
        }

        return kundenDatenProcessor;
    }

    private ParkhausChartProcessorIF getParkhausChartProcessor() {
        if (null == parkhausChartProcessor) {
            parkhausChartProcessor = (ParkhausChartProcessor) getApplication().getAttribute("chartProcessor");

            if (null == parkhausChartProcessor) {
                parkhausChartProcessor = new ParkhausChartProcessor(getParkhaus());
                getApplication().setAttribute("chartProcessor", parkhausChartProcessor);
            }
        }

        return parkhausChartProcessor;
    }

    private EinnahmenControllerIF getEinnahmenController() {
        if (null == einnahmenController) {
            einnahmenController = (EinnahmenController) getApplication().getAttribute("einnahmenController");

            if (null == einnahmenController) {
                einnahmenController = new EinnahmenController(parkhaus.getParkhausStatistics());
                getApplication().setAttribute("einnahmenController", parkhausChartProcessor);
            }
        }

        return einnahmenController;
    }
}
