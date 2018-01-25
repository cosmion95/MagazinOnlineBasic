import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/afisareProduse")
public class afisareProduse extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        dbOperations db = new dbOperations();

        try {
            PrintWriter pw = resp.getWriter();
            resp.setContentType("text/html");
            pw.println("<a href = index.jsp> Inapoi la meniu</a>");
            for (produs prod : db.afisareProduse()) {
                pw.println("<p>" + prod.getNume());
                pw.println(" -- " + prod.getPret());
                pw.println("<a href = adaugaProdus?id=" + prod.getId() + ">   Adauga in cos</a></p>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
