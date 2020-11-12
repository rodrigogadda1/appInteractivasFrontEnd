package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.io.Serializable;

public class Foto implements Serializable {

    private long id_foto;
    private String uri_foto;

    public Foto(long id_foto, String uri_foto) {
        super();
        this.id_foto = id_foto;
        this.uri_foto = uri_foto;
    }

    public Foto(){
        super();
    }

    @Override
    public String toString() {
        return "Foto{" +
                "id_foto=" + id_foto +
                ", uri_foto='" + uri_foto + '\'' +
                '}';
    }

    public long getId_foto() {
        return id_foto;
    }

    public void setId_foto(long id_foto) {
        this.id_foto = id_foto;
    }

    public String getUri_foto() {
        return uri_foto;
    }

    public void setUri_foto(String uri_foto) {
        this.uri_foto = uri_foto;
    }

}
