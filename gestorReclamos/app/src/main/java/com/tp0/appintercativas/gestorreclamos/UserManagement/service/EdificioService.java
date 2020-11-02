package com.tp0.appintercativas.gestorreclamos.UserManagement.service;


import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Edificio;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EdificioService {

    public final String API_ROUTE = "/api/edificios";
    @POST(API_ROUTE)
    Call<Edificio> create(@Body Edificio edificio);

    @GET(API_ROUTE)
    Call<List<Edificio>> getEdificios();

    public final String API_ROUTE_BYID = "/api/edificios/{id}";
    @GET(API_ROUTE_BYID)
    Call<Edificio> findBYId(@Path("id") long id);

    @DELETE(API_ROUTE_BYID)
    Call<ResponseBody> deleteById(@Path("id") long id);

}
