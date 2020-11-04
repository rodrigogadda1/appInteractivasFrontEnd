package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

public class Estado {

    private long id_estado;
    private String descripcion;

    public Estado(long id_estado, String descripcion) {
        super();
        this.id_estado = id_estado;
        this.descripcion = descripcion;
    }

    public Estado(){
        super();
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id_estado=" + id_estado +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public long getId_estado() {
        return id_estado;
    }

    public void setId_estado(long id_estado) {
        this.id_estado = id_estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
