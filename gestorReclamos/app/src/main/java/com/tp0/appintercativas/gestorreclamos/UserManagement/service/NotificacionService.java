package com.tp0.appintercativas.gestorreclamos.UserManagement.service;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Inspector;

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

public interface NotificacionService {

    public final String API_ROUTE = "/api/notificaciones";
    @POST(API_ROUTE)
    Call<Inspector> create(@Body Inspector inspector);

    @GET(API_ROUTE)
    Call<List<Inspector>> getInspectores();

    public final String API_ROUTE_BYID = "/api/notificaciones/{id}";
    @GET(API_ROUTE_BYID)
    Call<Inspector> findBYId(@Path("id") long id);

    public final String API_ROUTE_GETBYID = "/api/notificaciones/getId";
    @Headers({
            "Content-Type:application/json"
    })
    @GET(API_ROUTE_GETBYID)
    Call<Inspector> getInspectorId(@Query("id_user") long id_user);

    @DELETE(API_ROUTE_BYID)
    Call<ResponseBody> deleteById(@Path("id") long id);

}
