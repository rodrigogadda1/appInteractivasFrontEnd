package com.tp0.appintercativas.gestorreclamos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tp0.appintercativas.gestorreclamos.ResponseURIs.ResponseLogin;
import com.tp0.appintercativas.gestorreclamos.UserManagement.Controller.Controller;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivityLogin extends AppCompatActivity {

    private Button btnLogin;
    private TextView txtEstado;
    private EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loginn();
            }
        });
    }


    private void getUserById (long id) {
        Retrofit retrofit = Controller.ConfiguracionIP();
        UserService us = retrofit.create(UserService.class);
        Call<User> call = us.findBYId(id);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    mostrarDialogo("Error", "Resultado no correcto ");
                    return;
                } else {
                    if (response.body().getFirstTime().toLowerCase() == "false") {
                        paso_a_pantallaprincipal((User) response.body());
                    }else{
                        paso_a_carga_datos((User) response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mostrarDialogo("Error", "Error en la ejecucion "+t.getMessage());
            }
        });
    }

    private void loginn(){
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        User user_a_validar = new User(username, password);

        Retrofit retrofit = Controller.ConfiguracionIP();
        UserService us = retrofit.create(UserService.class);

        Call<ResponseLogin> call = us.login(username,password);


        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                ResponseLogin resp = response.body();
                if ( (long) resp.getNroUser() != -1 ) {
                    getUserById(resp.getNroUser());
                }else{
                    mostrarDialogo("Error", "Combinacion de usuario/contraseña invalida.");
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                mostrarDialogo("Error", "Error en la ejecucion "+t.getMessage());
            }
        });

    }

    private void paso_a_pantallaprincipal (User user){
        Intent intent = new Intent(this, PantallaPrincipal.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    private void paso_a_carga_datos (User user){
        Intent intent = new Intent(this, ConsentisacionCargaDatos.class);
        intent.putExtra("user",user);
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
                    /*.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //no le ponemos nada , si queres hacer un log es Log.d("tag", "mensaje")
                        }
                    })*/
                    .show();
    }

   /* private void getUsers () {
            Retrofit retrofit = UserController.ConfiguracionIP();
            UserService us = retrofit.create(UserService.class);
            Call<List<User>> call = us.getUsers();

            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    //asi se haria el for
                    String salida =  "nada";
                    int cantidad = 0;
                    if (response.body() != null) {
                        for (User user : response.body()) {
                            salida = salida+" - "+user.getFirstName();
                            cantidad++;
                        }
                    }
                    txtEstado.setText(salida);
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    txtEstado.setText("fallo - " + t.getMessage());
                }
            });
    }

    private void getUserById () {
        Retrofit retrofit = UserController.ConfiguracionIP();
        UserService us = retrofit.create(UserService.class);
        Call<User> call = us.findBYId(4);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    txtEstado.setText("resultado no correcto");
                    return;
                }
                txtEstado.setText(response.body().getFirstName());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                txtEstado.setText("error aca - "+t.getMessage());
            }
        });
    }


    private void createUser(){
        Retrofit retrofit = UserController.ConfiguracionIP();
        UserService us = retrofit.create(UserService.class);

        User newUser = new User("rodrigo", "gadda", "rodrigo@gmail.com", "queTi", "que funcione");

        Call<User> call = us.create(newUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                txtEstado.setText("Creado exitosamente "+response.body().getId());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                txtEstado.setText("fallo por "+t.getMessage());
            }
        });
    }


    private void updateUser(){
        Retrofit retrofit = UserController.ConfiguracionIP();
        UserService us = retrofit.create(UserService.class);

        User newUser = new User("cmabiado", "gadda", "rodrigo@gmail.com", "queTi", "que funcione");

        Call<User> call = us.updateUser(7, newUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                txtEstado.setText("Creado exitosamente "+response.body().getFirstName());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                txtEstado.setText("fallo por "+t.getMessage());
            }
        });
    }*/


}