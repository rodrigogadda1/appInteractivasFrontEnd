package com.example.gestorreclamosapp.datos1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.gestorreclamosapp.R;
import com.example.gestorreclamosapp.datos2.activity_datos2;
import com.example.gestorreclamosapp.ui.login.LoginActivity;

public class activity_datos1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos1);
    }

    public void login_1(View view) {
        Intent intent = new Intent(activity_datos1.this, activity_datos2.class);
        startActivity(intent);
    }

    public void logout_1(View view) {
        Intent intent = new Intent(activity_datos1.this, LoginActivity.class);
        startActivity(intent);
    }
}
