package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.util.List;

public class Administrado {

    private long id_administrado;
    private long id_user;
    private String tipo_administriado;
    private List<Unidad> unidades;

    public Administrado(){
        super();
    }

    public Administrado(long id_administrado, long id_user, String tipo_administriado, List<Unidad> unidades) {
        this.id_administrado = id_administrado;
        this.id_user = id_user;
        this.tipo_administriado = tipo_administriado;
        this.unidades = unidades;
    }

    @Override
    public String toString() {
        return "Administrado{" +
                "id_administrado=" + id_administrado +
                ", id_user=" + id_user +
                ", tipo_administriado='" + tipo_administriado + '\'' +
                ", unidades=" + unidades +
                '}';
    }

    public long getId_administrado() {
        return id_administrado;
    }

    public void setId_administrado(long id_administrado) {
        this.id_administrado = id_administrado;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getTipo_administriado() {
        return tipo_administriado;
    }

    public void setTipo_administriado(String tipo_administriado) {
        this.tipo_administriado = tipo_administriado;
    }

    public List<Unidad> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidad> unidades) {
        this.unidades = unidades;
    }
}
