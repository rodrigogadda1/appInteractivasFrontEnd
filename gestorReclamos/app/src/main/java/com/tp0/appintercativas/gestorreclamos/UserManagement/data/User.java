package com.tp0.appintercativas.gestorreclamos.UserManagement.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String firstTime;
    private String preguntaSeguridad;
    private String respuestaSeguridad;
    private String sexo;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String celular;
    private String tipoUser;
    private boolean datos_moviles;
    private boolean recibir_notificaciones;

    public User() {
        super();
    }

    public User(long id, String firstName, String lastName, String email, String username, String password, String firstTime, String preguntaSeguridad, String respuestaSeguridad, String sexo,
                String tipoIdentificacion, String numeroIdentificacion, String celular, String tipoUser, boolean datos_moviles, boolean recibir_notificaciones) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstTime = firstTime;
        this.preguntaSeguridad = preguntaSeguridad;
        this.respuestaSeguridad = respuestaSeguridad;
        this.sexo = sexo;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.celular = celular;
        this.tipoUser = tipoUser;
        this.datos_moviles = datos_moviles;
        this.recibir_notificaciones = recibir_notificaciones;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstTime='" + firstTime + '\'' +
                ", preguntaSeguridad='" + preguntaSeguridad + '\'' +
                ", respuestaSeguridad='" + respuestaSeguridad + '\'' +
                ", sexo='" + sexo + '\'' +
                ", tipoIdentificacion='" + tipoIdentificacion + '\'' +
                ", numeroIdentificacion='" + numeroIdentificacion + '\'' +
                ", celular='" + celular + '\'' +
                ", tipoUser='" + tipoUser + '\'' +
                ", datos_moviles=" + datos_moviles +
                ", recibir_notificaciones=" + recibir_notificaciones +
                '}';
    }

    public String isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public String getPreguntaSeguridad() {
        return preguntaSeguridad;
    }

    public void setPreguntaSeguridad(String preguntaSeguridad) {
        this.preguntaSeguridad = preguntaSeguridad;
    }

    public String getRespuestaSeguridad() {
        return respuestaSeguridad;
    }

    public void setRespuestaSeguridad(String respuestaSeguridad) {
        this.respuestaSeguridad = respuestaSeguridad;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipo_identificacion() {
        return tipoIdentificacion;
    }

    public void setTipo_identificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumero_identificacion() {
        return numeroIdentificacion;
    }

    public void setNumero_identificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public boolean isDatos_moviles() {
        return datos_moviles;
    }

    public void setDatos_moviles(boolean datos_moviles) {
        this.datos_moviles = datos_moviles;
    }

    public boolean isRecibir_notificaciones() {
        return recibir_notificaciones;
    }

    public void setRecibir_notificaciones(boolean recibir_notificaciones) {
        this.recibir_notificaciones = recibir_notificaciones;
    }
}
