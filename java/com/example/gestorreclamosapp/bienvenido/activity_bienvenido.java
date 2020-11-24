package com.example.gestorreclamosapp.bienvenido;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import com.example.gestorreclamosapp.R;
import com.example.gestorreclamosapp.datos1.activity_datos1;
import com.example.gestorreclamosapp.ui.login.LoginActivity;

public class activity_bienvenido extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
    }

    public void back(View view) {
        Intent intent = new Intent(activity_bienvenido.this, LoginActivity.class);
        startActivity(intent);
    }
    public void exit(View view) {
        Intent intent = new Intent(activity_bienvenido.this, LoginActivity.class);
        startActivity(intent);
    }
    public void next(View view) {
        Intent intent = new Intent(activity_bienvenido.this, activity_datos1.class);
        startActivity(intent);
    }
}