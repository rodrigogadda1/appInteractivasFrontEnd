package com.tp0.appintercativas.gestorreclamos.UserManagement.service;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Especialidad;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EspecialidadService {

    public final String API_ROUTE = "/api/especialidades";
    @POST(API_ROUTE)
    Call<Especialidad> create(@Body Especialidad especialidad);

    @GET(API_ROUTE)
    Call<List<Especialidad>> getEspecialidades();

    public final String API_ROUTE_BYID = "/api/especialidades/{id}";
    @GET(API_ROUTE_BYID)
    Call<Especialidad> findBYId(@Path("id") long id);

    @DELETE(API_ROUTE_BYID)
    Call<ResponseBody> deleteById(@Path("id") long id);
}
