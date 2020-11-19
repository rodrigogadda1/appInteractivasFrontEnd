package com.tp0.appintercativas.gestorreclamos.UserManagement.SQLite;

import android.content.ContentValues;

import java.util.List;

public class Reclamo_SQLLite {

    private long id_reclamo;
    private String Nombre;
    private String username;
    private long id_edificio;
    private long id_especialidad;
    private long id_estado;
    private long id_agrupador;
    private String descripcion;
    private long id_administrado;
    private long id_unidad;
    private long id_espacioComun;
    private List<String> fotos;

    public Reclamo_SQLLite(long id_reclamo, String nombre, String username, long id_edificio, long id_especialidad, long id_estado, long id_agrupador, String descripcion, long id_administrado,
                           long id_unidad, long id_espacioComun, List<String> fotos) {
        super();
        this.id_reclamo = id_reclamo;
        Nombre = nombre;
        this.username = username;
        this.id_edificio = id_edificio;
        this.id_especialidad = id_especialidad;
        this.id_estado = id_estado;
        this.id_agrupador = id_agrupador;
        this.descripcion = descripcion;
        this.id_administrado = id_administrado;
        this.id_unidad = id_unidad;
        this.id_espacioComun = id_espacioComun;
        this.fotos = fotos;
    }

    public Reclamo_SQLLite(){
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

    public long getId_estado() {
        return id_estado;
    }

    public void setId_estado(long id_estado) {
        this.id_estado = id_estado;
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

    public long getId_administrado() {
        return id_administrado;
    }

    public void setId_administrado(long id_administrado) {
        this.id_administrado = id_administrado;
    }

    public long getId_unidad() {
        return id_unidad;
    }

    public void setId_unidad(long id_unidad) {
        this.id_unidad = id_unidad;
    }

    public long getId_espacioComun() {
        return id_espacioComun;
    }

    public void setId_espacioComun(long id_espacioComun) {
        this.id_espacioComun = id_espacioComun;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    /**
     * Convierte el estado en pares clave valor para hacer el insert
     * */
    public ContentValues toContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(ReclamoContract.ClubesEntry.ID,idClub);
        cv.put(ReclamoContract.ClubesEntry.NOMBRE, nombre);
        cv.put(ReclamoContract.ClubesEntry.NROZONA, nroZona);
        return cv;
    }

    private long id_reclamo;
    private String Nombre;
    private String username;
    private long id_edificio;
    private long id_especialidad;
    private long id_estado;
    private long id_agrupador;
    private String descripcion;
    private long id_administrado;
    private long id_unidad;
    private long id_espacioComun;
    private List<String> fotos;
}
