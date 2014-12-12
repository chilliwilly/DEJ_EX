package negocio;

import dao.*;
import estructura.*;
import java.sql.Connection;
import java.util.ArrayList;

public class ConcursoService {
    private Connection cnx;

    public ConcursoService(Connection cnx) {
        this.cnx = cnx;
    }
    
    public ArrayList<Raza> lsRaza()
    {
        AgregarDAO dao = new AgregarDAO(cnx);
        return dao.listaRaza();
    }   
    
    public ArrayList<Participante> lsParticipante(String cod)
    {
        ListarDAO dao = new ListarDAO(cnx);
        String cod_r = "";
        if(cod.equals("0"))
        {
            cod_r = "%";
        }
        else
        {
            cod_r = cod;
        }
        return dao.lsParticipantes(cod_r);
    }
    
    public void eliminar(int cod)
    {
        ListarDAO dao = new ListarDAO(cnx);
        dao.eliminarParticipante(cod);
    }
    
    public void agregarConcursante(Participante p)
    {
        AgregarDAO dao = new AgregarDAO(cnx);
        dao.agregarParicipante(p);
    }
}
