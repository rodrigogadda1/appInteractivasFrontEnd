package com.tp0.appintercativas.gestorreclamos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tp0.appintercativas.gestorreclamos.UserManagement.Controller.UserController;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView txtEstado;
    private EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtEstado = (TextView) findViewById(R.id.txtEstado);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getUsers();
            }
        });
    }

    public void getUsers () {
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


}