package estructura;

import java.sql.*;

public class Participante {
    private int id_participante;
    private String nombre_participante;
    private int id_resgistro;
    private Timestamp fecha_registro;
    private Raza raza;

    public Participante() {
    }

    public Participante(int id_participante, String nombre_participante, int id_resgistro, Timestamp fecha_registro, Raza raza) {
        this.id_participante = id_participante;
        this.nombre_participante = nombre_participante;
        this.id_resgistro = id_resgistro;
        this.fecha_registro = fecha_registro;
        this.raza = raza;
    }

    public int getId_participante() {
        return id_participante;
    }

    public void setId_participante(int id_participante) {
        this.id_participante = id_participante;
    }

    public String getNombre_participante() {
        return nombre_participante;
    }

    public void setNombre_participante(String nombre_participante) {
        this.nombre_participante = nombre_participante;
    }

    public int getId_resgistro() {
        return id_resgistro;
    }

    public void setId_resgistro(int id_resgistro) {
        this.id_resgistro = id_resgistro;
    }

    public Timestamp getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Timestamp fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }
}
