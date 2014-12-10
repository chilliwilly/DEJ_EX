package web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "IndexServlet", urlPatterns = {"/IndexServlet"})
public class IndexController extends HttpServlet {

    @javax.annotation.Resource(name = "jdbc/et_dej4501")
    private javax.sql.DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection cnx = ds.getConnection()) {
            request.setAttribute("estadoConexion", cnx.toString());
        } catch (SQLException ex) {
            request.setAttribute("estadoConexion", ex.getMessage());
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }


}
