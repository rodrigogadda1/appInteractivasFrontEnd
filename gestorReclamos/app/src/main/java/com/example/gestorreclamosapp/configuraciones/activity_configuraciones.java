package com.example.gestorreclamosapp.configuraciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.gestorreclamosapp.R;
import com.example.gestorreclamosapp.principal.activity_principal;

public class activity_configuraciones extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones);
    }

    public void exit(View view) {
        Intent intent = new Intent(activity_configuraciones.this, activity_principal.class);
        startActivity(intent);
    }
}
