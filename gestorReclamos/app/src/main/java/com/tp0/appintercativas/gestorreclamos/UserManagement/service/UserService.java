package com.tp0.appintercativas.gestorreclamos.UserManagement.service;


import com.tp0.appintercativas.gestorreclamos.ResponseURIs.ResponseLogin;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface UserService {

    //una entrada asi para cada ruta del servicio rest

    public final String API_ROUTE_GET_USERS = "/api/users";
    @GET(API_ROUTE_GET_USERS)
    Call<List<User>> getUsers();

    public final String API_ROUTE_LOGIN = "/api/users/login";
    @Headers({
            "Content-Type:application/json"
    })
    @GET(API_ROUTE_LOGIN)
    Call<ResponseLogin> login(@Body User user);

    public final String API_ROUTE_FINDBYID = "/api/users/{id}";
    @GET(API_ROUTE_FINDBYID)
    Call<User> findBYId(@Path("id") long id);

}
