package web.servlet;

import estructura.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;
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
            throw new RuntimeException("Error Get Agregar Servlet", ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("txtNombre");
        int idraza = Integer.parseInt(request.getParameter("cboRaza"));
        int idreg = Integer.parseInt(request.getParameter("txtReg"));        
        String txt = "";
        
        try (Connection cnx = ds.getConnection()){            
            ConcursoService service = new ConcursoService(cnx);
            Participante p = new Participante();
            p.setNombre_participante(nom);
            p.setRaza(new Raza(idraza, null));
            p.setId_resgistro(idreg);            
            
            if(service.validaParticipante(idreg))
            {
                request.setAttribute("nombre", nom);
                request.setAttribute("razaid", idraza);
                request.setAttribute("registro", idreg);
                txt = "ID Registro Participante Ya Existe";
                System.out.println("MENSAJE PROGRAMA: ID Registro Participante Ya Existe");
            }
            else if(idreg <= 0)
            {
                request.setAttribute("nombre", nom);
                request.setAttribute("razaid", idraza);
                request.setAttribute("registro", idreg);
                txt = "ID Registro Participante No Puede Ser Igual o Menor Que 0";
                System.out.println("MENSAJE PROGRAMA: ID Registro Participante No Puede Ser Igual o Menor Que 0");
            }
            else
            {
                service.agregarConcursante(p);
                int id = service.getIdConcursante();
                txt = "Participante Ingresado Exitosamente Con ID Nro: " + id;
                System.out.println("MENSAJE PROGRAMA: Participante Ingresado Exitosamente Con ID Nro: " + id);
            }
            
            ArrayList<Raza> r = service.lsRaza();
            request.setAttribute("lista",r);
            
            request.setAttribute("mensaje", txt);
            request.getRequestDispatcher("/ingreso.jsp").forward(request, response);
            
        } catch (Exception ex) {
            throw new RuntimeException("Error Post Agregar Servlet", ex);
        }
    }
}
