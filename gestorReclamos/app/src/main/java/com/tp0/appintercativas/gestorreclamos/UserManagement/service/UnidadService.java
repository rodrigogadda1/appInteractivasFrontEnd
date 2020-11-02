package com.tp0.appintercativas.gestorreclamos.UserManagement.service;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Unidad;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UnidadService {

    public final String API_ROUTE = "/api/unidades";
    @POST(API_ROUTE)
    Call<Unidad> create(@Body Unidad unidad);

    @GET(API_ROUTE)
    Call<List<Unidad>> getUnidades();

    public final String API_ROUTE_BYID = "/api/unidades/{id}";
    @GET(API_ROUTE_BYID)
    Call<Unidad> findBYId(@Path("id") long id);

    @DELETE(API_ROUTE_BYID)
    Call<ResponseBody> deleteById(@Path("id") long id);

}
