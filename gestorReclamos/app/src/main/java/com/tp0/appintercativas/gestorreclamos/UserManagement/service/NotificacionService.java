package com.tp0.appintercativas.gestorreclamos.UserManagement.service;

import android.app.Notification;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Inspector;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Notificacion;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NotificacionService {

    public final String API_ROUTE = "/api/notificaciones";
    @POST(API_ROUTE)
    Call<Notificacion> create(@Body Notificacion notificacion);

    @GET(API_ROUTE)
    Call<List<Notificacion>> getNotifications();

    public final String API_ROUTE_BYID = "/api/notificaciones/{id}";
    @GET(API_ROUTE_BYID)
    Call<Notificacion> findNotificationBYId(@Path("id") long id);

    public final String API_ROUTE_GETBYID = "/api/notificaciones/getByAdministradoId";
    @Headers({
            "Content-Type:application/json"
    })
    @GET(API_ROUTE_GETBYID)
    Call<List<Notificacion>> getNotificacionId(@Query("id") long id_administrado);

    @DELETE(API_ROUTE_BYID)
    Call<ResponseBody> deleteById(@Path("id") long id);

    @PUT(API_ROUTE_BYID)
    Call<Notificacion> updateNotificacion(@Path("id") long id, @Body Notificacion notificacion);

}
