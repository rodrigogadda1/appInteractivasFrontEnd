package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.io.Serializable;

public class EspacioComun implements Serializable {

    private long id_espaciocomun;
    private String nombre;
    private String descripcion;
    private Edificio edificio;

    public EspacioComun(long id_espaciocomun, String nombre, String descripcion, Edificio edificio) {
        super();
        this.id_espaciocomun = id_espaciocomun;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.edificio = edificio;
    }

    public EspacioComun (){
        super();
    }

    @Override
    public String toString() {
        return "EspacioComun{" +
                "id_espaciocomun=" + id_espaciocomun +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", edificio=" + edificio +
                '}';
    }

    public long getId_espaciocomun() {
        return id_espaciocomun;
    }

    public void setId_espaciocomun(long id_espaciocomun) {
        this.id_espaciocomun = id_espaciocomun;
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

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }
}
