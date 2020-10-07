package com.tp0.appintercativas.gestorreclamos.UserManagement.service;


import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    //una entrada asi para cada ruta del servicio rest

    public final String API_ROUTE = "/api/users";
    @GET(API_ROUTE)
    Call<List<User>> getUsers();

}
