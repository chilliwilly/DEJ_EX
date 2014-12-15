package dao;

import estructura.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class AgregarDAO {
    private Connection cnx;

    public AgregarDAO(Connection cnx) {
        this.cnx = cnx;
    }
    
    public ArrayList<Raza> listaRaza()
    {
        ArrayList<Raza> listado = new ArrayList<>();
        
        String sql = "SELECT * FROM RAZA ORDER BY ID_RAZA ASC;";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            try(ResultSet rs = stmt.executeQuery())
            {
                while(rs.next())
                {
                    int num = rs.getInt("ID_RAZA");
                    String nom = rs.getString("NOMBRE_RAZA");                

                    Raza raza = new Raza(num, nom);
                    listado.add(raza);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al Buscar Raza", ex);
        }
        return listado;
    }
    
    public void agregarParicipante(Participante p)
    {
        String sql = "INSERT INTO PARTICIPANTE VALUES (NULL,?,?,NOW(),?);";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            stmt.setString(1, p.getNombre_participante());
            stmt.setInt(2, p.getId_resgistro());
            stmt.setInt(3, p.getRaza().getId_raza());
            stmt.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException("Error al Insertar Participante", ex);
        }
    }
    
    public int selectLastId()
    {
        ResultSet rs = null;
        int idp = 0;
        String sql = "SELECT LAST_INSERT_ID()";
        try(PreparedStatement stmt = cnx.prepareStatement(sql))
        {
            rs = stmt.executeQuery();

            if (rs.next()) {
                idp = rs.getInt(1);
            }
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Error al Recuperar ID ", ex);
        }
        return idp;
    }
    
    public ArrayList<Integer> listaRegParticipante()
    {
        ArrayList<Integer> lsIdReg = new ArrayList<>();
        
        String sql = "SELECT ID_PARTICIPANTE FROM PARTICIPANTE;";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            try(ResultSet rs = stmt.executeQuery())
            {
                while(rs.next())
                {
                    int num = rs.getInt("ID_PARTICIPANTE");

                    lsIdReg.add(num);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al Buscar Reg Participante", ex);
        }
        
        return lsIdReg;
    }
}
