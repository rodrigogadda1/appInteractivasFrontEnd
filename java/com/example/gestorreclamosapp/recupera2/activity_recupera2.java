package com.example.gestorreclamosapp.recupera2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.gestorreclamosapp.R;
import com.example.gestorreclamosapp.datos3.activity_datos3;
import com.example.gestorreclamosapp.recupera1.activity_recupera1;
import com.example.gestorreclamosapp.ui.login.LoginActivity;

public class activity_recupera2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera2);
    }

    public void guardar(View view) {
        Intent intent = new Intent(activity_recupera2.this, LoginActivity.class);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(activity_recupera2.this, activity_recupera1.class);
        startActivity(intent);
    }
    public void exit(View view) {
        Intent intent = new Intent(activity_recupera2.this, LoginActivity.class);
        startActivity(intent);
    }
}
