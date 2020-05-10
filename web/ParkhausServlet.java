import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/ParkhausServlet")
public class ParkhausServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> query = new HashMap<>();

        //todo: put into static tool class and write a test for it
        for (String splitByAnd : request.getQueryString().split("&")) {
            String[] splitByEquals = splitByAnd.split("=");
            query.put(splitByEquals[0], splitByEquals[1]);
        }

        if ("config".equals(query.get("cmd"))) {
            System.out.println("Known Command: " + request.getQueryString());

        } else {
            System.out.println("Invalid Command: " + request.getQueryString());
        }
    }
}
