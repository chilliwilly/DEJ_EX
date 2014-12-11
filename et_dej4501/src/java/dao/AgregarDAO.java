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
        ArrayList<Raza> listado = new ArrayList<Raza>();
        
        String sql = "SELECT * FROM RAZA ORDER BY ID_RAZA ASC;";
        try (PreparedStatement stmt = cnx.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                int num = rs.getInt("ID_RAZA");
                String nom = rs.getString("NOMBRE_RAZA");                
                
                Raza raza = new Raza(num, nom);
                listado.add(raza);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al Buscar Raza", ex);
        }
        return listado;
    }
}
