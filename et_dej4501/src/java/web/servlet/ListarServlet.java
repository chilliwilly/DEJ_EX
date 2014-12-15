package web.servlet;

import estructura.*;
import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.ConcursoService;

@WebServlet(name = "ListarServlet", urlPatterns = {"/ListarServlet"})
public class ListarServlet extends HttpServlet {

    @javax.annotation.Resource(name = "jdbc/et_dej4501")
    private javax.sql.DataSource ds;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection cnx = ds.getConnection()){   
            String razaid = request.getParameter("cboRaza");
            ConcursoService service = new ConcursoService(cnx);
            
            if(razaid != null && !razaid.isEmpty())
            {
                ArrayList<Participante> lsp = service.lsParticipante(razaid);
                request.setAttribute("listap",lsp);
            }
            
            ArrayList<Raza> r = service.lsRaza();
            request.setAttribute("mensaje", request.getParameter("mensaje"));
            request.setAttribute("lista",r);
            request.getRequestDispatcher("/listado.jsp").forward(request, response);
            
        } catch (Exception ex) {
            throw new RuntimeException("Error Get Listar Servlet", ex);
        }
    }
}
