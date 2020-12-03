package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.io.Serializable;
import java.util.List;

public class Especialidad implements Serializable {

    private long id_especialidad;
    private String nombre;
    private String descripcion;
    private List<Inspector> inspectores;

    @Override
    public String toString() {
        return "Especialidad{" +
                "id_especialidad=" + id_especialidad +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", inspectores=" + inspectores +
                '}';
    }

    public Especialidad(){
        super();
    }

    public Especialidad(long id_especialidad, String nombre, String descripcion) {
        this.id_especialidad = id_especialidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.inspectores = null;
    }

    public long getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(long id) {
        this.id_especialidad = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Inspector> getInspectores() {
        return inspectores;
    }

    public void setInspectores(List<Inspector> inspectores) {
        this.inspectores = inspectores;
    }
}
