package com.tp0.appintercativas.gestorreclamos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tp0.appintercativas.gestorreclamos.ResponseURIs.ResponseLogin;
import com.tp0.appintercativas.gestorreclamos.UserManagement.Controller.Controller;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecuperaContrasenia1 extends AppCompatActivity {

       TextView txtTitulo, txtUsuario, txtPregunta, txtPreguntaTitulo,txtRespuesta;
       EditText editUsuario, editRespuesta;
       ImageView btnBack, btnLogin;
       Button btnValidarUsuario;
       User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera_contrasenia1);

        btnValidarUsuario = (Button) findViewById(R.id.btnValidarUsuario);

        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnLogin = (ImageView) findViewById(R.id.btnLogin);

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtUsuario = (TextView) findViewById(R.id.txtUsuario);
        txtPregunta = (TextView) findViewById(R.id.txtPregunta);
        txtPreguntaTitulo = (TextView) findViewById(R.id.txtPreguntaTitulo);
        txtRespuesta = (TextView) findViewById(R.id.txtRespuesta);

        editUsuario = (EditText) findViewById(R.id.editUsuario);
        editRespuesta = (EditText) findViewById(R.id.editRespuesta);

        txtPregunta.setEnabled(false);
        txtPregunta.setVisibility(View.INVISIBLE);
        txtPreguntaTitulo.setEnabled(false);
        txtPreguntaTitulo.setVisibility(View.INVISIBLE);
        txtRespuesta.setEnabled(false);
        txtRespuesta.setVisibility(View.INVISIBLE);
        editRespuesta.setEnabled(false);
        editRespuesta.setVisibility(View.INVISIBLE);


        btnValidarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editUsuario.getText().toString().equals("")) {
                    validarUsuario();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String respuesta = editRespuesta.getText().toString();
                if (respuesta.toLowerCase().equals(user.getRespuestaSeguridad().toLowerCase())) {
                    goNext();
                }else{
                    mostrarDialogo("Error","Respuesta equivocada");
                }
            }
        });

    }

    private void goBack(){
        Intent intent = new Intent(this, MainActivityLogin.class);
        startActivity(intent);
    }

    private void goNext(){
        Intent intent = new Intent(this, RecuperaContrasenia2.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    private void validarUsuario(){
        String username = editUsuario.getText().toString();

        Retrofit retrofit = Controller.ConfiguracionIP();
        UserService us = retrofit.create(UserService.class);

        Call<User> call = us.getUserByUsername(username);


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                 User resp = response.body();
                if ( (resp.getUsername().equals(editUsuario.getText().toString()))  ) {
                    if ((!resp.getPreguntaSeguridad().equals("")) && (!resp.getRespuestaSeguridad().equals(""))){
                        user = resp;
                        editUsuario.setEnabled(false);
                        txtPregunta.setEnabled(true);
                        txtPregunta.setVisibility(View.VISIBLE);
                        txtPregunta.setText(resp.getPreguntaSeguridad());
                        txtPreguntaTitulo.setEnabled(true);
                        txtPreguntaTitulo.setVisibility(View.VISIBLE);
                        txtRespuesta.setEnabled(true);
                        txtRespuesta.setVisibility(View.VISIBLE);
                        editRespuesta.setEnabled(true);
                        editRespuesta.setVisibility(View.VISIBLE);
                    }
                }else{
                    mostrarDialogo("Error", "Usuario no encontrado");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mostrarDialogo("Error", "Error en la ejecucion "+t.getMessage());
            }
        });

    }

    private void mostrarDialogo(String titulo,String mensaje){
        new AlertDialog.Builder( this)
                .setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //va a hacer nada aca, si se quisiera cerrar la app es finish()
                    }
                })
                .show();
    }
}