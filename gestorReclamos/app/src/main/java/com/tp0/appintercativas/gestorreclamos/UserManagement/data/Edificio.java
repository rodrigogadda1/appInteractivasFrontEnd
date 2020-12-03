package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.io.Serializable;
import java.util.List;

public class Edificio implements Serializable {

    private long id_edificio;
    private String nombre;
    private String direccion;
    private String telefono;
    private long cantUnidades;
    private List<Unidad> unidades;
    private List<EspacioComun> espaciosComunes;
    private List<Inspector> inspectores ;

    public Edificio(long id_edificio, String nombre, String direccion, String telefono, long cantUnidades, List<Unidad> unidades, List<EspacioComun> espaciosComunes
            , List<Inspector> inspectores) {
        this.id_edificio = id_edificio;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cantUnidades = cantUnidades;
        this.unidades = unidades;
        this.espaciosComunes = espaciosComunes;
        this.inspectores = inspectores;
    }

    @Override
    public String toString() {
        return "Edificio{" +
                "id_edificio=" + id_edificio +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", cantUnidades=" + cantUnidades +
                ", unidades=" + unidades +
                ", espaciosComunes=" + espaciosComunes +
                ", inspectores=" + inspectores +
                '}';
    }

    public Edificio(){
        super();
    }

    public long getId_edificio() {
        return id_edificio;
    }

    public void setId_edificio(long id_edificio) {
        this.id_edificio = id_edificio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public long getCantUnidades() {
        return cantUnidades;
    }

    public void setCantUnidades(long cantUnidades) {
        this.cantUnidades = cantUnidades;
    }

    public List<Unidad> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidad> unidades) {
        this.unidades = unidades;
    }

    public List<EspacioComun> getEspaciosComunes() {
        return espaciosComunes;
    }

    public void setEspaciosComunes(List<EspacioComun> espaciosComunes) {
        this.espaciosComunes = espaciosComunes;
    }

    public List<Inspector> getInspectores() {
        return inspectores;
    }

    public void setInspectores(List<Inspector> inspectores) {
        this.inspectores = inspectores;
    }
}
