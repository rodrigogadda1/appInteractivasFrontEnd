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
    private String respuesta_inspector;
    private String respuesta_administrador;
    private long id_administrado;
    private long id_unidad;
    private long id_espacioComun;
    private List<String> fotos;

    @Override
    public String toString() {
        return "Reclamo_SQLLite{" +
                "id_reclamo=" + id_reclamo +
                ", Nombre='" + Nombre + '\'' +
                ", username='" + username + '\'' +
                ", id_edificio=" + id_edificio +
                ", id_especialidad=" + id_especialidad +
                ", id_estado=" + id_estado +
                ", id_agrupador=" + id_agrupador +
                ", descripcion='" + descripcion + '\'' +
                ", respuesta_inspector='" + respuesta_inspector + '\'' +
                ", respuesta_administrador='" + respuesta_administrador + '\'' +
                ", id_administrado=" + id_administrado +
                ", id_unidad=" + id_unidad +
                ", id_espacioComun=" + id_espacioComun +
                ", fotos=" + fotos +
                '}';
    }

    public Reclamo_SQLLite(long id_reclamo, String nombre, String username, long id_edificio, long id_especialidad, long id_estado, long id_agrupador, String descripcion,
                           String respuesta_inspector, String respuesta_administrador, long id_administrado, long id_unidad, long id_espacioComun, List<String> fotos) {
        this.id_reclamo = id_reclamo;
        Nombre = nombre;
        this.username = username;
        this.id_edificio = id_edificio;
        this.id_especialidad = id_especialidad;
        this.id_estado = id_estado;
        this.id_agrupador = id_agrupador;
        this.descripcion = descripcion;
        this.respuesta_inspector = respuesta_inspector;
        this.respuesta_administrador = respuesta_administrador;
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

    public String getRespuesta_administrador() {
        return respuesta_administrador;
    }

    public void setRespuesta_administrador(String respuesta_administrador) {
        this.respuesta_administrador = respuesta_administrador;
    }

    /**
     * Convierte el estado en pares clave valor para hacer el insert
     * */
    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(ReclamoContract.ReclamosEntry.ID, id_reclamo);
        if (Nombre != "") {
            cv.put(ReclamoContract.ReclamosEntry.NOMBRE, Nombre);
        }
        if (username != "") {
            cv.put(ReclamoContract.ReclamosEntry.USERNAME, username);
        }
        if (id_edificio != 0) {
            cv.put(ReclamoContract.ReclamosEntry.ID_EDIFICIO, id_edificio);
        }
        if (id_especialidad != 0) {
            cv.put(ReclamoContract.ReclamosEntry.ID_ESPECIALIDAD, id_especialidad);
        }
        if (id_estado != 0) {
            cv.put(ReclamoContract.ReclamosEntry.ID_ESTADO, id_estado);
        }
        if (id_agrupador != 0) {
            cv.put(ReclamoContract.ReclamosEntry.ID_AGRUPADOR, id_agrupador);
        }
        if (descripcion != "") {
            cv.put(ReclamoContract.ReclamosEntry.DESCRIPCION, descripcion);
        }
        if (respuesta_inspector != "") {
            cv.put(ReclamoContract.ReclamosEntry.RESPUESTA_INSPECTOR, respuesta_inspector);
        }
        if (respuesta_administrador != "") {
            cv.put(ReclamoContract.ReclamosEntry.RESPUESTA_INSPECTOR, respuesta_administrador);
        }
        if (id_administrado != 0) {
            cv.put(ReclamoContract.ReclamosEntry.ID_ADMINISTRADO, id_administrado);
        }
        if (id_unidad != 0) {
            cv.put(ReclamoContract.ReclamosEntry.ID_UNIDAD, id_unidad);
        }
        if (id_espacioComun != 0) {
            cv.put(ReclamoContract.ReclamosEntry.ID_ESPACIO_COMUN, id_espacioComun);
        }

        if (fotos != null) {
            if (fotos.size() > 0) {
                cv.put(ReclamoContract.ReclamosEntry.FOTO_1, fotos.get(0));
            }
            if (fotos.size() > 1) {
                cv.put(ReclamoContract.ReclamosEntry.FOTO_2, fotos.get(1));
            }
            if (fotos.size() > 2) {
                cv.put(ReclamoContract.ReclamosEntry.FOTO_3, fotos.get(2));
            }
            if (fotos.size() > 3) {
                cv.put(ReclamoContract.ReclamosEntry.FOTO_4, fotos.get(3));
            }
            if (fotos.size() > 4) {
                cv.put(ReclamoContract.ReclamosEntry.FOTO_5, fotos.get(4));
            }
            if (fotos.size() > 5) {
                cv.put(ReclamoContract.ReclamosEntry.FOTO_6, fotos.get(5));
            }
            if (fotos.size() > 6) {
                cv.put(ReclamoContract.ReclamosEntry.FOTO_7, fotos.get(6));
            }
        }
        return cv;
    }

    public String getRespuesta_inspector() {
        return respuesta_inspector;
    }

    public void setRespuesta_inspector(String respuesta_inspector) {
        this.respuesta_inspector = respuesta_inspector;
    }

}
