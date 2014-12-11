package web.servlet;

import estructura.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.*;

@WebServlet(name = "AgregarServlet", urlPatterns = {"/AgregarServlet"})
public class AgregarServlet extends HttpServlet {

    @javax.annotation.Resource(name = "jdbc/et_dej4501")
    private javax.sql.DataSource ds;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection cnx = ds.getConnection()){            
            ConcursoService service = new ConcursoService(cnx);
            
            ArrayList<Raza> r = service.lsRaza();
            request.setAttribute("lista",r);
            request.getRequestDispatcher("/ingreso.jsp").forward(request, response);
            
        } catch (Exception ex) {
            throw new RuntimeException("Error Get", ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("ingreso.jsp").forward(request, response);
    }
}
