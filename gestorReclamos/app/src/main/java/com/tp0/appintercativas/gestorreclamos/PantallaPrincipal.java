package com.tp0.appintercativas.gestorreclamos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

public class PantallaPrincipal extends AppCompatActivity {

    private TextView txtDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        txtDatos = (TextView) findViewById(R.id.txtDatos);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

        txtDatos.setText(user.toString());
    }
}