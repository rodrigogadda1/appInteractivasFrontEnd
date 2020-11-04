package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.util.List;

public class Inspector {

    private long id_inspector;
    private long id_user;
    private List<InspectorEdificio> inspectoredificio;
    private List<InspectorEspecialidad> inspectorespecialidad;

    @Override
    public String toString() {
        return "Inspector{" +
                "id_inspector=" + id_inspector +
                ", id_user=" + id_user +
                ", inspectoredificio=" + inspectoredificio +
                ", inspectorespecialidad=" + inspectorespecialidad +
                '}';
    }

    public Inspector(long id_inspector, long id_user, List<InspectorEdificio> inspectoredificio, List<InspectorEspecialidad> inspectorespecialidad) {
        this.id_inspector = id_inspector;
        this.id_user = id_user;
        this.inspectoredificio = inspectoredificio;
        this.inspectorespecialidad = inspectorespecialidad;
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

    public List<InspectorEdificio> getInspectoredificio() {
        return inspectoredificio;
    }

    public void setInspectoredificio(List<InspectorEdificio> inspectoredificio) {
        this.inspectoredificio = inspectoredificio;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public List<InspectorEspecialidad> getInspectorespecialidad() {
        return inspectorespecialidad;
    }

    public void setInspectorespecialidad(List<InspectorEspecialidad> inspectorespecialidad) {
        this.inspectorespecialidad = inspectorespecialidad;
    }

}
