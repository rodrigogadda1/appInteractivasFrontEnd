package com.example.gestorreclamosapp.bienvenido;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.gestorreclamosapp.R;
import com.example.gestorreclamosapp.datos1.activity_datos1;
import com.example.gestorreclamosapp.ui.login.LoginActivity;

public class activity_bienvenido extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
    }

    public void login_1(View view) {
        Intent intent = new Intent(activity_bienvenido.this, activity_datos1.class);
        startActivity(intent);
    }

    public void logout_1(View view) {
        Intent intent = new Intent(activity_bienvenido.this, LoginActivity.class);
        startActivity(intent);
    }
}