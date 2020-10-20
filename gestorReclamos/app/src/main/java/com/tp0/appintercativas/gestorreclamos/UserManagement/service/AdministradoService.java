package com.tp0.appintercativas.gestorreclamos.UserManagement.service;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Administrado;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AdministradoService {

    public final String API_ROUTE = "/api/administrados";
    @POST(API_ROUTE)
    Call<Administrado> create(@Body Administrado administrado);

    @GET(API_ROUTE)
    Call<List<Administrado>> getAdministrados();

    public final String API_ROUTE_BYID = "/api/administrados/{id}";
    @GET(API_ROUTE_BYID)
    Call<Administrado> findBYId(@Path("id") long id);

    @DELETE(API_ROUTE_BYID)
    Call<ResponseBody> deleteById(@Path("id") long id);

    public final String API_ROUTE_GETBYID = "/api/administrados/getId";
    @Headers({
            "Content-Type:application/json"
    })
    @GET(API_ROUTE_GETBYID)
    Call<Administrado> getAdministradoId(@Query("id_user") long id_user);

}
