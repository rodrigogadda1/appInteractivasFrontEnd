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

public class RecuperaContrasenia2 extends AppCompatActivity {

    TextView txtTitulo ,txtUsuario, txtPassword, txtPassword2;
    EditText editUsuario, editPassword, editPassword2;
    Button btGuardar;
    ImageView btnBack;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera_contrasenia2);

        btnBack = (ImageView) findViewById(R.id.btnBack);

        btGuardar = (Button) findViewById(R.id.btGuardar);

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtUsuario = (TextView) findViewById(R.id.txtUsuario);
        txtPassword = (TextView) findViewById(R.id.txtPassword);
        txtPassword2 = (TextView) findViewById(R.id.txtPassword2);

        editUsuario = (EditText) findViewById(R.id.editUsuario);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editPassword2 = (EditText) findViewById(R.id.editPassword2);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        editUsuario.setText(user.getUsername());
        editUsuario.setEnabled(false);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( (editPassword.getText().toString().equals(editPassword2.getText().toString())) && (!editPassword.getText().toString().equals(""))){
                    guardarPassword(editPassword.getText().toString());
                } else {
                    mostrarDialogo("Error", "Las contraseñas ingresadas no son validas al estar vacias o no coinciden.");
                }
            }
        });

    }

    private void guardarPassword(String password){
        user.setPassword(password);
        Retrofit retrofit = Controller.ConfiguracionIP();
        UserService us = retrofit.create(UserService.class);
        Call<User> call = us.updateUser(user.getId(), user);
        call.enqueue(new Callback<User>() {

             @Override
             public void onResponse(Call<User> call, Response<User> response) {
                 goMain();
             }

             @Override
             public void onFailure(Call<User> call, Throwable t) {
                 mostrarDialogo("Error", "Error en la ejecucion "+t.getMessage());
             }
         });
    }

    private void goMain(){
        Intent intent = new Intent(this, MainActivityLogin.class);
        mostrarDialogo("Guardado con exito","Se ha guardado con exito la contraseña.");
        startActivity(intent);
    }

    private void goBack(){
        Intent intent = new Intent(this, RecuperaContrasenia1.class);
        startActivity(intent);
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