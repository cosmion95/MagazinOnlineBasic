import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/afisareCos")
public class afisareCos extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        dbOperations db = new dbOperations();

        try {
            double total = 0.0;
            PrintWriter pw = resp.getWriter();
            resp.setContentType("text/html");
            pw.println("<a href = index.jsp> Inapoi la meniu</a>");
            for (produs prod : db.afisareCos()) {
                pw.print("<p>" + prod.getNume());
                pw.print(" -- " + prod.getPret());
                pw.print("  buc: " + prod.getCantitate());
                pw.println("<a href = stergeCos?id=" + prod.getId() + ">   Sterge</a></p>");
                total = total + (prod.getPret() * prod.getCantitate());
            }
            total = (int)(total * 100);
            total = total / 100;
            pw.println("<h2><p> Total: " + total + " lei </h2></p>");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
