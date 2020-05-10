import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/ParkhausServlet")
public class ParkhausServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> query = getQueryHashMap(request);

        if ("config".equals(query.get("cmd"))) {
            // see https://kaul.inf.h-brs.de/se/#app-content-4-0&03_Technologien=page-53
            sendResponse(response, "100,6,24,100,10");
        } else {
            System.out.println("Invalid Command: " + request.getQueryString());
        }
    }

    private void sendResponse(HttpServletResponse response, String config) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(config);
    }

    private HashMap<String, String> getQueryHashMap(HttpServletRequest request) {
        HashMap<String, String> query = new HashMap<>();

        for (String splitByAnd : request.getQueryString().split("&")) {
            String[] splitByEquals = splitByAnd.split("=");
            query.put(splitByEquals[0], splitByEquals[1]);
        }

        return query;
    }
}
