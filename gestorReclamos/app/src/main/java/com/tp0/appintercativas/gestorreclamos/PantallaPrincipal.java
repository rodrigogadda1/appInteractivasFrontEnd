package com.tp0.appintercativas.gestorreclamos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

public class PantallaPrincipal extends AppCompatActivity {

    private ScrollView ScrollViewReclamos;
    private Button btnNotificaciones, btnHistorialReclamos, btnReclamosActivos, btnReclamoNuevo;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        ScrollViewReclamos = (ScrollView) findViewById(R.id.ScrollViewReclamos);

        btnNotificaciones = (Button) findViewById(R.id.btnNotificaciones);
        btnHistorialReclamos = (Button) findViewById(R.id.btnHistorialReclamos);
        btnReclamosActivos = (Button) findViewById(R.id.btnReclamosActivos);
        btnReclamoNuevo = (Button) findViewById(R.id.btnReclamoNuevo);

        //esto es como prueba ya que no esta la barra
        btnNotificaciones.setText("InfoApp");
        //fin prueba

        btnNotificaciones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                goToInfoApp();
            }
        });

        btnHistorialReclamos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //aca va que hace
            }
        });

        btnReclamosActivos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //aca va que hace
            }
        });

        btnReclamoNuevo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //aca va que hace
            }
        });


    }

    private void goToInfoApp(){
        Intent intent = new Intent(this, InfoAppActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}