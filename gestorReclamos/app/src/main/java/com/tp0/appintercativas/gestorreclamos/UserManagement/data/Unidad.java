package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.util.List;

public class Unidad {

    private long id_unidad;
    private String piso;
    private String unidad;
    private Edificio edificio;
    private List<AdministradoUnidad> administradoUnidades;

    @Override
    public String toString() {
        return "Unidad{" +
                "id_unidad=" + id_unidad +
                ", piso='" + piso + '\'' +
                ", unidad='" + unidad + '\'' +
                ", edificio=" + edificio +
                ", administradoUnidades=" + administradoUnidades +
                '}';
    }

    public Unidad(long id_unidad, String piso, String unidad, Edificio edificio, List<AdministradoUnidad> administradoUnidades) {
        this.id_unidad = id_unidad;
        this.piso = piso;
        this.unidad = unidad;
        this.edificio = edificio;
        this.administradoUnidades = administradoUnidades;
    }

    public Unidad() {
        super();
    }

    public long getId_unidad() {
        return id_unidad;
    }

    public void setId_unidad(long id_unidad) {
        this.id_unidad = id_unidad;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public List<AdministradoUnidad> getAdministradoUnidades() {
        return administradoUnidades;
    }

    public void setAdministradoUnidades(List<AdministradoUnidad> administradoUnidades) {
        this.administradoUnidades = administradoUnidades;
    }

}
