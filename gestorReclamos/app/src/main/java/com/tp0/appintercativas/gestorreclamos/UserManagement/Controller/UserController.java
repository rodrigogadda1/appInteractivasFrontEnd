package com.tp0.appintercativas.gestorreclamos.UserManagement.Controller;


import android.app.AlertDialog;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserController {

        public static Retrofit ConfiguracionIP() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080")   //url base
                    .addConverterFactory(GsonConverterFactory.create()) //quien se va a encargar de convertir de json a objeto y arrevez
                    .build();

            //UserService us = retrofit.create(UserService.class); //que retrofit se encarge de implementar una clase que utilize esa interfaz, esta clase ya tiene resuelto todo
            return retrofit;
        }
        //spring no libera el puerto 8080 cuando terminate
}
