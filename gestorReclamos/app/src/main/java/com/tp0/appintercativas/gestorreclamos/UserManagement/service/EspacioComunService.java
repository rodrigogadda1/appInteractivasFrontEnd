package com.tp0.appintercativas.gestorreclamos.UserManagement.service;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.EspacioComun;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EspacioComunService {

    public final String API_ROUTE = "/api/espacioscomunes";
    @POST(API_ROUTE)
    Call<EspacioComun> create(@Body EspacioComun espacioComun);

    @GET(API_ROUTE)
    Call<List<EspacioComun>> getEspaciosComunes();

    public final String API_ROUTE_BYID = "/api/espacioscomunes/{id}";
    @GET(API_ROUTE_BYID)
    Call<EspacioComun> findBYId(@Path("id") long id);

    @DELETE(API_ROUTE_BYID)
    Call<ResponseBody> deleteById(@Path("id") long id);

}
