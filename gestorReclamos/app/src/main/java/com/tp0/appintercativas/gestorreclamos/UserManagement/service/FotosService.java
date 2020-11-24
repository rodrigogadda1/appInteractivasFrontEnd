package com.tp0.appintercativas.gestorreclamos.UserManagement.service;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Especialidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Foto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FotosService {

    public final String API_ROUTE = "/api/fotos";
    @POST(API_ROUTE)
    Call<Foto> create(@Body Foto foto);

    @GET(API_ROUTE)
    Call<List<Foto>> getFotos();

    public final String API_ROUTE_BYID = "/api/fotos/{id}";
    @GET(API_ROUTE_BYID)
    Call<Foto> findBYId(@Path("id") long id);

    @DELETE(API_ROUTE_BYID)
    Call<ResponseBody> deleteById(@Path("id") long id);

}
