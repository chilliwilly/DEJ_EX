package web.servlet;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.ConcursoService;

@WebServlet(name = "BorrarServlet", urlPatterns = {"/BorrarServlet"})
public class BorrarServlet extends HttpServlet {

    @javax.annotation.Resource(name = "jdbc/et_dej4501")
    private javax.sql.DataSource ds;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cod = Integer.parseInt(request.getParameter("codp"));
        try (Connection cnx = ds.getConnection()){               
            ConcursoService service = new ConcursoService(cnx);
            service.eliminar(cod);
            
            Thread.sleep(2000);            
            String url = request.getContextPath()+"/ListarServlet";
            response.sendRedirect(url);            
        } catch (Exception ex) {
            throw new RuntimeException("Error Get", ex);
        }
    }
}
