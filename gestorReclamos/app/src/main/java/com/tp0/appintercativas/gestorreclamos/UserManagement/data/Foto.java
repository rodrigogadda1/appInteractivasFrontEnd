package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.io.Serializable;

public class Foto implements Serializable {

    private long id_foto;
    private String foto;

    public Foto(long id_foto, String foto) {
        super();
        this.id_foto = id_foto;
        this.foto = foto;
    }

    public Foto(){
        super();
    }

    @Override
    public String toString() {
        return "Foto{" +
                "id_foto=" + id_foto +
                ", foto='" + foto + '\'' +
                '}';
    }

    public long getId_foto() {
        return id_foto;
    }

    public void setId_foto(long id_foto) {
        this.id_foto = id_foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}
