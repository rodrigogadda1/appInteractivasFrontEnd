package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

public class Unidad {

    private long id_unidad;
    private String piso;
    private String unidad;
    private Edificio edificio;

    public Unidad(long id_unidad, String piso, String unidad, Edificio edificio) {
        this.id_unidad = id_unidad;
        this.piso = piso;
        this.unidad = unidad;
        this.edificio = edificio;
    }

    public Unidad() {
        super();
    }

    @Override
    public String toString() {
        return "Unidad{" +
                "id_unidad=" + id_unidad +
                ", piso='" + piso + '\'' +
                ", unidad='" + unidad + '\'' +
                ", edificio=" + edificio.toString() +
                '}';
    }

    public long getId_unidad() {
        return id_unidad;
    }

    public void setId_unidad(long id_unidad) {
        this.id_unidad = id_unidad;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }
}
