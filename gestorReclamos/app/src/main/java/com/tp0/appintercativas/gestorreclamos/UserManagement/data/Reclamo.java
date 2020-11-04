package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.util.Date;
import java.util.List;

public class Reclamo {

    private long id_reclamo;
    private String Nombre;
    private String username;
    private long id_edificio;
    private long id_especialidad;
    private Date fecha;
    private Estado estado;
    private long id_agrupador;
    private String descripcion;
    private Administrado administrado;
    private Unidad unidad;
    private List<Foto> fotos;

    @Override
    public String toString() {
        return "Reclamo{" +
                "id_reclamo=" + id_reclamo +
                ", Nombre='" + Nombre + '\'' +
                ", username='" + username + '\'' +
                ", id_edificio=" + id_edificio +
                ", id_especialidad=" + id_especialidad +
                ", fecha=" + fecha +
                ", estado=" + estado +
                ", id_agrupador=" + id_agrupador +
                ", descripcion='" + descripcion + '\'' +
                ", administrado=" + administrado +
                ", unidad=" + unidad +
                ", fotos=" + fotos +
                '}';
    }

    public Reclamo(long id_reclamo, String nombre, String username, long id_edificio, long id_especialidad, Date fecha, Estado estado, long id_agrupador,
                   String descripcion, Administrado administrado, Unidad unidad, List<Foto> fotos) {
        super();
        this.id_reclamo = id_reclamo;
        Nombre = nombre;
        this.username = username;
        this.id_edificio = id_edificio;
        this.id_especialidad = id_especialidad;
        this.fecha = fecha;
        this.estado = estado;
        this.id_agrupador = id_agrupador;
        this.descripcion = descripcion;
        this.administrado = administrado;
        this.unidad = unidad;
        this.fotos = fotos;
    }

    public Reclamo(){
        super();
    }

    public long getId_reclamo() {
        return id_reclamo;
    }

    public void setId_reclamo(long id_reclamo) {
        this.id_reclamo = id_reclamo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId_edificio() {
        return id_edificio;
    }

    public void setId_edificio(long id_edificio) {
        this.id_edificio = id_edificio;
    }

    public long getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(long id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public long getId_agrupador() {
        return id_agrupador;
    }

    public void setId_agrupador(long id_agrupador) {
        this.id_agrupador = id_agrupador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Administrado getAdministrado() {
        return administrado;
    }

    public void setAdministrado(Administrado administrado) {
        this.administrado = administrado;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

}
