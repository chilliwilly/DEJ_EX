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
        int codraza = Integer.parseInt(request.getParameter("razaid"));
        try (Connection cnx = ds.getConnection()){               
            ConcursoService service = new ConcursoService(cnx);
            service.eliminar(cod);
            String msg = "Participante Nro " + cod + " eliminado";
            System.out.println("MENSAJE PROGRAMA: Participante Nro " + cod + " eliminado");
            Thread.sleep(2000);            
            String url = request.getContextPath()+"/ListarServlet?mensaje="+msg+"&cboRaza="+codraza;
            response.sendRedirect(url);            
        } catch (Exception ex) {
            throw new RuntimeException("Error Get Borrar Servlet", ex);
        }
    }
}
