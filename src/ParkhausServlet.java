import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


@WebServlet("/ParkhausServlet")
public class ParkhausServlet extends HttpServlet {

    private List<Integer> einnahmen = new LinkedList<>();
    private List<Integer> parkdauer = new LinkedList<>();
    private Parkhaus parkhaus;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> postMap = getPostHashMap(request);

        System.out.println("POST-command: " + postMap.get("cmd"));
        System.out.println("POST-csv: " + postMap.get("csv"));

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> queryMap = getQueryHashMap(request);

        switch (queryMap.get("cmd")) {
            case "config":
                handleGetConfig(response);
                break;

            case "Durchschnitt":
                handleGetDurchschnitt(response);
                break;

            case "Summe":
                handleGetSumme(response);
                break;

            default:
                System.out.println("Invalid GET-command: " + request.getQueryString());
        }
    }

    private void handleGetConfig(HttpServletResponse response) throws IOException {
        // see https://kaul.inf.h-brs.de/se/#app-content-4-0&03_Technologien=page-53
        sendResponse(response, "100,6,24,100,10");
    }

    private void handleGetDurchschnitt(HttpServletResponse response) throws IOException {
        int totalMoney = 0;
        int totalDuration = 0;

        for (int einnahme : einnahmen) {
            totalMoney += einnahme;
        }

        for (int duration : parkdauer) {
            totalDuration += duration;
        }

        sendResponse(response, "Durchschnittseinahme: " + formatCentAsEuro(totalMoney / einnahmen.size())
                + " Durschnittsparkdauer: " + formatDauer(totalDuration / parkdauer.size()));
    }

    private void handleGetSumme(HttpServletResponse response) throws IOException {
        int sum = 0;
        for (int einnahme : einnahmen) {
            sum += einnahme;
        }

        sendResponse(response, "" + formatCentAsEuro(sum));
    }

    private void handlePostEnter(HttpServletResponse response, HashMap<String, String> postMap) {
        KundenDatenIF kundenDaten = new KundenDaten(postMap.get("csv").split(","));
        KundeIF kunde = new Kunde();
        UhrzeitIF zeit = new Uhrzeit(0, 0);
        getParkhaus().einfahren(kunde, zeit);
        return;
    }

    private void handlePostLeave(HttpServletResponse response, HashMap<String, String> postMap) throws IOException {
        KundenDatenIF kundenDaten = new KundenDaten(postMap.get("csv").split(","));

        einnahmen.add(kundenDaten.getPreis());
        parkdauer.add(kundenDaten.getDauer());

        sendResponse(response, postMap.get("csv"));
    }

    private static HashMap<String, String> getQueryHashMap(HttpServletRequest request) {
        HashMap<String, String> map = new HashMap<>();

        for (String splitByAnd : request.getQueryString().split("&")) {
            String[] splitByEquals = splitByAnd.split("=");
            map.put(splitByEquals[0], splitByEquals[1]);
        }

        return map;
    }

    private static HashMap<String, String> getPostHashMap(HttpServletRequest request) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        String[] splitByFirstComma = getPostBody(request).split(",", 2);

        map.put("cmd", splitByFirstComma[0]);
        map.put("csv", splitByFirstComma[1]);

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
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
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

    private static String formatCentAsEuro(int cent) {
        return "" + cent / 100 + "," + cent % 100 + "€";
    }

    private ServletContext getApplication() {
        return getServletConfig().getServletContext();
    }

    private Parkhaus getParkhaus() {
        if (null == parkhaus) {
            parkhaus = (Parkhaus) getApplication().getAttribute("parkhaus");

            if (null == parkhaus) {
                parkhaus = new Parkhaus();
                getApplication().setAttribute("parkhaus", parkhaus);
            }
        }

        return parkhaus;
    }

    private static String formatDauer(int dauer) {
        String dauerString = String.format("%7s", Integer.toString(dauer)).replace(' ', '0');

        StringBuilder formattedString = new StringBuilder(dauerString);
        formattedString.insert(2, ':');
        formattedString.insert(5, '.');

        return formattedString.toString();
    }
}
