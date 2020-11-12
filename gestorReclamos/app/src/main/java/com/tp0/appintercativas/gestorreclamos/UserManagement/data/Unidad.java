package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.io.Serializable;
import java.util.List;

public class Unidad implements Serializable {

    private long id_unidad;
    private String piso;
    private String unidad;
    private Edificio edificio;
    private List<AdministradoUnidad> administradoUnidades;
    private List<Reclamo> reclamos;

    @Override
    public String toString() {
        return "Unidad{" +
                "id_unidad=" + id_unidad +
                ", piso='" + piso + '\'' +
                ", unidad='" + unidad + '\'' +
                ", edificio=" + edificio +
                ", administradoUnidades=" + administradoUnidades +
                ", reclamos=" + reclamos +
                '}';
    }

    public Unidad(long id_unidad, String piso, String unidad, Edificio edificio, List<AdministradoUnidad> administradoUnidades,
                  List<Reclamo> reclamos) {
        super();
        this.id_unidad = id_unidad;
        this.piso = piso;
        this.unidad = unidad;
        this.edificio = edificio;
        this.administradoUnidades = administradoUnidades;
        this.reclamos = reclamos;
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

    public List<Reclamo> getReclamos() {
        return reclamos;
    }

    public void setReclamos(List<Reclamo> reclamos) {
        this.reclamos = reclamos;
    }

}
