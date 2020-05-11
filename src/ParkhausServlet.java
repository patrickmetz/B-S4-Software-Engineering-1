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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> postMap = getPostHashMap(request);

        System.out.println("POST-command: " + postMap.get("cmd"));
        System.out.println("POST-csv: " + postMap.get("csv"));

        switch (postMap.get("cmd")) {
            case "enter":
                break;
            case "leave":
                // see https://kaul.inf.h-brs.de/se/#app-content-4-0&03_Technologien=page-61
                String[] params = postMap.get("csv").split(",");
                int nr = Integer.parseInt(params[0]);
                Double beginn = Double.parseDouble(params[1]);
                Double dauer = Double.parseDouble(params[2]);
                int preis = Integer.parseInt(params[3]);
                String tickethash = params[4];
                String farbe = params[5];
                int slot = Integer.parseInt(params[6]);

                einnahmen.add(preis);

                sendResponse(response, postMap.get("csv"));
                break;

            default:
                System.out.println("Invalid POST-command: " + request.getQueryString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> queryMap = getQueryHashMap(request);

        switch(queryMap.get("cmd")) {
            case "config":
                // see https://kaul.inf.h-brs.de/se/#app-content-4-0&03_Technologien=page-53
                sendResponse(response, "100,6,24,100,10");
                break;

            case "Durchschnitt":
                int total = 0;

                for (int einnahme : einnahmen) {
                    total += einnahme;
                }

                sendResponse(response, "" + formatCentAsEuro(total/einnahmen.size()));
                break;

            case "Summe":
                int sum = 0;
                for (int einnahme: einnahmen) {
                    sum += einnahme;
                }

                sendResponse(response, "" + formatCentAsEuro(sum));
                break;

            default:
                System.out.println("Invalid GET-command: " + request.getQueryString());
        }
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
        return "" + cent/100 + "," + cent%100 + "€";
    }
}
