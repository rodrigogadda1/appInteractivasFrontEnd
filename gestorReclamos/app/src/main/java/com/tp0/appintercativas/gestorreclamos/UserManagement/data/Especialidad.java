package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.io.Serializable;

public class Especialidad implements Serializable {

    private long id_especialidad;
    private String nombre;
    private String descripcion;

    @Override
    public String toString() {
        return "Especialidad{" +
                "id_especialidad=" + id_especialidad +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public Especialidad(){

    }

    public Especialidad(long id_especialidad, String nombre, String descripcion) {
        this.id_especialidad = id_especialidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public long getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(long id_especialidad) {
        this.id_especialidad = id_especialidad;
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
