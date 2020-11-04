package com.example.gestorreclamosapp.nreclamo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gestorreclamosapp.R;
import com.example.gestorreclamosapp.nreclamo1.activity_nreclamo1;
import com.example.gestorreclamosapp.principal.activity_principal;

public class activity_nreclamo2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nreclamo2);
    }

    public void exit(View view) {
        Intent intent = new Intent(activity_nreclamo2.this, activity_principal.class);
        startActivity(intent);
    }

    public void next(View view) {
        Intent intent = new Intent(activity_nreclamo2.this, activity_nreclamo2.class);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(activity_nreclamo2.this, activity_nreclamo1.class);
        startActivity(intent);
    }
}
