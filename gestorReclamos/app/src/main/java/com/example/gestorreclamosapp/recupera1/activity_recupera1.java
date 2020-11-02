package com.example.gestorreclamosapp.recupera1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.gestorreclamosapp.recupera2.activity_recupera2;
import com.example.gestorreclamosapp.ui.login.LoginActivity;
import com.example.gestorreclamosapp.R;

public class activity_recupera1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera1);
    }

    public void next(View view) {
        Intent intent = new Intent(activity_recupera1.this, activity_recupera2.class);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(activity_recupera1.this, LoginActivity.class);
        startActivity(intent);
    }

    public void validar_Usuario(View view) {
    }
}
