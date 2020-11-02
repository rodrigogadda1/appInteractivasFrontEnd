package com.example.gestorreclamosapp.datos3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.gestorreclamosapp.R;
import com.example.gestorreclamosapp.ui.login.LoginActivity;
import com.example.gestorreclamosapp.datos2.activity_datos2;

public class activity_datos3 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos3);
    }

    public void back(View view) {
        Intent intent = new Intent(activity_datos3.this, activity_datos2.class);
        startActivity(intent);
    }

    public void exit(View view) {
        Intent intent = new Intent(activity_datos3.this, LoginActivity.class);
        startActivity(intent);
    }

    public void next(View view) {
        Intent intent = new Intent(activity_datos3.this, LoginActivity.class);
        startActivity(intent);
    }
}
