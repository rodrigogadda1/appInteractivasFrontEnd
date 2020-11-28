package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.io.Serializable;
import java.util.List;

public class Inspector implements Serializable {

    private long id_inspector;
    private long id_user;
    private List<Edificio> edificios;
    private List<Especialidad> especialidades;

    @Override
    public String toString() {
        return "Inspector{" +
                "id_inspector=" + id_inspector +
                ", id_user=" + id_user +
                ", edificios =" + edificios +
                ", especialidades=" + especialidades +
                '}';
    }

    public Inspector(long id_inspector, long id_user, List<Edificio> edificios, List<Especialidad> especialidades) {
        this.id_inspector = id_inspector;
        this.id_user = id_user;
        this.edificios = edificios;
        this.especialidades = especialidades;
    }

    public Inspector() {
        super();
    }

    public long getId_inspector() {
        return id_inspector;
    }

    public void setId_inspector(long id_inspector) {
        this.id_inspector = id_inspector;
    }

    public List<Edificio> getEdificios() {
        return edificios;
    }

    public void setEdificios(List<Edificio> edificios) {
        this.edificios = edificios;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

}
