package com.tp0.appintercativas.gestorreclamos.UserManagement.service;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Administrado;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.AdministradoUnidad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AdministradoUnidadService {

    public final String API_ROUTE = "/api/administrados/unidades";
    @GET(API_ROUTE)
    Call<List<AdministradoUnidad>> getAdministradoUnidades();

}
