package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.io.Serializable;

public class Especialidad implements Serializable {

    private long id;
    private String nombre;
    private String descripcion;

    @Override
    public String toString() {
        return "Especialidad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public Especialidad(){

    }

    public Especialidad(long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public long getId_especialidad() {
        return id;
    }

    public void setId_especialidad(long id) {
        this.id = id;
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
}
