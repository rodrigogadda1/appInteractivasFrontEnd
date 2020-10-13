package com.example.gestorreclamosapp.datos2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gestorreclamosapp.R;
import com.example.gestorreclamosapp.datos1.activity_datos1;
import com.example.gestorreclamosapp.datos3.activity_datos3;
import com.example.gestorreclamosapp.ui.login.LoginActivity;

public class activity_datos2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos2);
    }

    public void logout_1(View view) {
        Intent intent = new Intent(activity_datos2.this, LoginActivity.class);
        startActivity(intent);
    }

    public void login_1(View view) {
        Intent intent = new Intent(activity_datos2.this, activity_datos3.class);
        startActivity(intent);
    }

    public void back_1(View view) {
        Intent intent = new Intent(activity_datos2.this, activity_datos1.class);
        startActivity(intent);
    }
}
