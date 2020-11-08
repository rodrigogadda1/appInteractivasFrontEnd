package com.tp0.appintercativas.gestorreclamos.UserManagement.service;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Reclamo;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReclamoService {

    public final String API_ROUTE = "/api/reclamos";
    @POST(API_ROUTE)
    Call<Reclamo> createReclamo(@Body Reclamo reclamo);

    @GET(API_ROUTE)
    Call<List<Reclamo>> getReclamos();

    public final String API_ROUTE_ID = "/api/reclamos/{id}";
    @GET(API_ROUTE_ID)
    Call<Reclamo> findBYId(@Path("id") long id);

    @PUT(API_ROUTE_ID)
    Call<Reclamo> updateReclamo(@Path("id") long id, @Body Reclamo reclamo);

    @DELETE(API_ROUTE_ID)
    Call<ResponseBody> deleteById(@Path("id") long id);

    public final String API_ROUTE_BYUSERID = "/api/reclamos/byUserId";
    @GET(API_ROUTE_BYUSERID)
    Call<List<Reclamo>> getReclamosByUserIdAndStatusId(@Query("users_ids") String users_ids, @Query("status_ids") String status_ids);
}
