import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/stergeCos")
public class stergeCos extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        dbOperations db = new dbOperations();
        String id = req.getParameter("id");
        try {
            int d = Integer.parseInt(id);
            db.stergeCos(d);
            PrintWriter pw = resp.getWriter();
            resp.setContentType("text/html");
//            pw.println("<a href = index.jsp> Inapoi la meniu</a>");
//            pw.println("<p>Stergere facuta cu succes.</p>");
            resp.sendRedirect("/MagazinOnline/afisareCos");

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    }
