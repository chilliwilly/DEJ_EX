package dao;

import estructura.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;

public class ListarDAO {
    private Connection cnx;

    public ListarDAO(Connection cnx) {
        this.cnx = cnx;
    }
    
    public ArrayList<Participante> lsParticipantes(String cod_raza)
    {        
        ArrayList<Participante> lsp = new ArrayList<>();
        String sql = "SELECT * FROM PARTICIPANTE WHERE ID_RAZA LIKE ? ORDER BY ID_PARTICIPANTE ASC;";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setString(1, cod_raza);            
            try(ResultSet rs = stmt.executeQuery())
            {
                while(rs.next())
                {
                    int id_p = rs.getInt("ID_PARTICIPANTE");
                    String nom = rs.getString("NOMBRE_PARTICIPANTE");
                    int id_reg = rs.getInt("ID_REGISTRO");
                    Timestamp f_reg = rs.getTimestamp("FECHA_REGISTRO");
                    Raza r = razaCod(rs.getInt("ID_RAZA"));             

                    Participante participante = new Participante(id_p, nom, id_reg, f_reg, r);
                    lsp.add(participante);
                }
            }            
        } catch (Exception ex) {
            throw new RuntimeException("Error al Buscar Participante", ex);
        }
        return lsp;
    }
    
    public Raza razaCod(int cod_raza)
    {
        Raza raza = null;
        String sql = "SELECT * FROM RAZA WHERE ID_RAZA = ?;";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setInt(1, cod_raza);
            try(ResultSet rs = stmt.executeQuery())
            {
                while(rs.next())
                {                
                    int num = rs.getInt("ID_RAZA");
                    String nom = rs.getString("NOMBRE_RAZA");                

                    raza = new Raza(num, nom);

                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al Buscar Raza", ex);
        }
        return raza;
    }
    
    public void eliminarParticipante(int cod)
    {        
        String sql = "DELETE FROM PARTICIPANTE WHERE ID_PARTICIPANTE = ?;";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setInt(1, cod);
            stmt.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException("Error al Eliminar", ex);
        }
    }
}