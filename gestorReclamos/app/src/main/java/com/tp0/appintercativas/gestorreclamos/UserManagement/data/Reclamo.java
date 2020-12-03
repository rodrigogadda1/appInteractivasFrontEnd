package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Reclamo implements Serializable {

    private long id_reclamo;
    private String Nombre;
    private String username;
    private Edificio edificio;
    private Especialidad especialidad;
    private Date fecha;
    private Estado estado;
    private long id_agrupador;
    private String descripcion;
    private String respuesta_inspector;
    private String respuesta_administrador;
    private Administrado administrado;
    private Unidad unidad;
    private EspacioComun espacioComun;
    private List<Foto> fotos;

    public Reclamo(long id_reclamo, String nombre, String username, Edificio edificio, Especialidad especialidad, Date fecha, Estado estado, long id_agrupador
                    , String descripcion, String respuesta_inspector, String respuesta_administrador, Administrado administrado, Unidad unidad, EspacioComun espacioComun
                    , List<Foto> fotos) {
        this.id_reclamo = id_reclamo;
        Nombre = nombre;
        this.username = username;
        this.edificio = edificio;
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.estado = estado;
        this.id_agrupador = id_agrupador;
        this.descripcion = descripcion;
        this.respuesta_inspector = respuesta_inspector;
        this.respuesta_administrador = respuesta_administrador;
        this.administrado = administrado;
        this.unidad = unidad;
        this.espacioComun = espacioComun;
        this.fotos = fotos;
    }

    public Reclamo(){
        super();
    }

    @Override
    public String toString() {
        return "Reclamo{" +
                "id_reclamo=" + id_reclamo +
                ", Nombre='" + Nombre + '\'' +
                ", username='" + username + '\'' +
                ", edificio=" + edificio +
                ", especialidad=" + especialidad +
                ", fecha=" + fecha +
                ", estado=" + estado +
                ", id_agrupador=" + id_agrupador +
                ", descripcion='" + descripcion + '\'' +
                ", respuesta_inspector='" + respuesta_inspector + '\'' +
                ", respuesta_administrador='" + respuesta_administrador + '\'' +
                ", administrado=" + administrado +
                ", unidad=" + unidad +
                ", espacioComun=" + espacioComun +
                ", fotos=" + fotos +
                '}';
    }

    public String toStringPersonalizado () {
        String salida = "Reclamo{"+
                " -id_reclamo" + id_reclamo;
        if (Nombre != null) {
            salida+=" -Nombre "+Nombre;
        }
        if (username != null){
            salida+=" -username "+username;
        }
        if (edificio != null){
            salida+=" -edificio "+edificio.toString();
        }
        if (especialidad != null){
            salida+=" -especialidad "+especialidad.toString();
        }
        if (fecha != null){
            salida+=" -fecha "+fecha.toString();
        }
        if (estado != null){
            salida+=" -estado "+estado.toString();
        }
        if (id_agrupador != 0){
            salida+=" -id_agrupador "+String.valueOf(id_agrupador);
        }
        if (descripcion != null){
            salida+=" -descripcion "+descripcion;
        }
        if (respuesta_inspector != null){
            salida+= " -respuesta inspector "+respuesta_inspector;
        }
        if (respuesta_administrador != null){
            salida+=" - respuesta administrador "+respuesta_administrador;
        }
        if (administrado != null){
            salida+=" -administrado "+administrado.toString();
        }
        if (unidad != null){
            salida+=" -unidad "+unidad.toString();
        }
        if (espacioComun != null){
            salida+=" -espacio comun "+espacioComun.toString();
        }
        if (fotos != null){
            salida+=" -fotos "+fotos.toString();
        }
        return salida;
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

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Date getFecha() {
        return fecha;
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

    public EspacioComun getEspacioComun() {
        return espacioComun;
    }

    public void setEspacioComun(EspacioComun espacioComun) {
        this.espacioComun = espacioComun;
    }

    public String getRespuesta_inspector() {
        return respuesta_inspector;
    }

    public void setRespuesta_inspector(String respuesta_inspector) {
        this.respuesta_inspector = respuesta_inspector;
    }

    public String getRespuesta_administrador() {
        return respuesta_administrador;
    }

    public void setRespuesta_administrador(String respuesta_administrador) {
        this.respuesta_administrador = respuesta_administrador;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
