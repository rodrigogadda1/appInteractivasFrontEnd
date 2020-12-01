package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.io.Serializable;

public class Notificacion implements Serializable {

    private long id_notificacion;
    private int id_administrdo;
    private int id_reclamo;
    private String descripcion;
    private boolean leido;

    public Notificacion(long id_notificacion, int id_administrdo, int id_reclamo, String descripcion, boolean leido) {
        this.id_notificacion = id_notificacion;
        this.id_administrdo = id_administrdo;
        this.id_reclamo = id_reclamo;
        this.descripcion = descripcion;
        this.leido = leido;
    }

    public Notificacion() {
        super();
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "id_notificacion=" + id_notificacion +
                ", id_administrdo=" + id_administrdo +
                ", id_reclamo=" + id_reclamo +
                ", descripcion='" + descripcion + '\'' +
                ", leido=" + leido +
                '}';
    }

    public long getId_notificacion() {
        return id_notificacion;
    }

    public void setId_notificacion(long id_notificacion) {
        this.id_notificacion = id_notificacion;
    }

    public int getId_administrdo() {
        return id_administrdo;
    }

    public void setId_administrdo(int id_administrdo) {
        this.id_administrdo = id_administrdo;
    }

    public int getId_reclamo() {
        return id_reclamo;
    }

    public void setId_reclamo(int id_reclamo) {
        this.id_reclamo = id_reclamo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

}
