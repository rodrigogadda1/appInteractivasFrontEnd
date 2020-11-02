package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.util.List;

public class Administrado {

    private long id_administrado;
    private long id_user;
    private List<AdministradoUnidad> administradoUnidades;

    public Administrado(){
        super();
    }

    public Administrado(long id_administrado, long id_user, List<AdministradoUnidad> administradoUnidades) {
        this.id_administrado = id_administrado;
        this.id_user = id_user;
        this.administradoUnidades = administradoUnidades;
    }

    @Override
    public String toString() {
        return "Administrado{" +
                "id_administrado=" + id_administrado +
                ", id_user=" + id_user +
                ", administradoUnidades=" + administradoUnidades +
                '}';
    }

    public long getId_administrado() {
        return id_administrado;
    }

    public void setId_administrado(long id_administrado) {
        this.id_administrado = id_administrado;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public List<AdministradoUnidad> getAdministradoUnidades() {
        return administradoUnidades;
    }

    public void setAdministradoUnidades(List<AdministradoUnidad> administradoUnidades) {
        this.administradoUnidades = administradoUnidades;
    }
}
