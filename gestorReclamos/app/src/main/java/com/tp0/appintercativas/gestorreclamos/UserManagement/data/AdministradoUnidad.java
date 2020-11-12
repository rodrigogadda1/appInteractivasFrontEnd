package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.io.Serializable;

public class AdministradoUnidad implements Serializable {

    private long id_administradounidad;
    private Administrado administrado;
    private Unidad unidad;
    private String relacion;

    public AdministradoUnidad(long id_administradounidad, Administrado administrado, Unidad unidad, String relacion) {
        this.id_administradounidad = id_administradounidad;
        this.administrado = administrado;
        this.unidad = unidad;
        this.relacion = relacion;
    }

    public AdministradoUnidad() {
        super();
    }

    @Override
    public String toString() {
        return "AdministradoUnidad{" +
                "id_administradounidad=" + id_administradounidad +
                ", administrado=" + administrado +
                ", unidad=" + unidad +
                ", relacion='" + relacion + '\'' +
                '}';
    }

    public long getId_administradounidad() {
        return id_administradounidad;
    }

    public void setId_administradounidad(long id_administradounidad) {
        this.id_administradounidad = id_administradounidad;
    }

    public Administrado getAdministrado() {
        return administrado;
    }

    public void setAdministrado(Administrado administrado) {
        this.administrado = administrado;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }
}
