import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/adaugaProdus")
public class adaugaProdus extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        dbOperations db = new dbOperations();
        String id = req.getParameter("id");
        try {
            int d = Integer.parseInt(id);
            db.adaugareProdus(d);
            PrintWriter pw = resp.getWriter();
            resp.setContentType("text/html");
//            pw.println("<a href = index.jsp> Inapoi la meniu</a>");
//            pw.println("<p>Produs adaugat cu succes.</p>");
            resp.sendRedirect("/MagazinOnline/afisareProduse");

        }
        catch (Exception e) {
            e.printStackTrace();
        }




    }
        }

