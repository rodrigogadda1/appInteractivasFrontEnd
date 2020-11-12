package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.io.Serializable;

public class InspectorEdificio implements Serializable {

    private long id_inspectoredificio;
    private Inspector inspector;
    private Edificio edificio;

    public InspectorEdificio(long id_inspectoredificio, Inspector inspector, Edificio edificio) {
        this.id_inspectoredificio = id_inspectoredificio;
        this.inspector = inspector;
        this.edificio = edificio;
    }

    public InspectorEdificio(){
        super();
    }

    @Override
    public String toString() {
        return "InspectorEdificio{" +
                "id_inspectoredificio=" + id_inspectoredificio +
                ", inspector=" + inspector +
                ", edificio=" + edificio +
                '}';
    }

    public long getId_inspectoredificio() {
        return id_inspectoredificio;
    }

    public void setId_inspectoredificio(long id_inspectoredificio) {
        this.id_inspectoredificio = id_inspectoredificio;
    }

    public Inspector getInspector() {
        return inspector;
    }

    public void setInspector(Inspector inspector) {
        this.inspector = inspector;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }
}
