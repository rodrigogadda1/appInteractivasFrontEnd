package com.tp0.appintercativas.gestorreclamos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

public class ConfirmacionDatosFirst extends AppCompatActivity {

    TextView txtTitulo, txtDatos;
    ImageView btnBack, btnCancel , btnOk;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_datos_first);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnCancel = (ImageView) findViewById(R.id.btnCancel);
        btnOk = (ImageView) findViewById(R.id.btnOk);

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtDatos = (TextView) findViewById(R.id.txtDatos);

        txtDatos.setText(user.toString());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void confirmar_parseScreen(User user){
        Intent intent = new Intent(this, ConfirmacionDatosFirst.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    private void cancelar_parseScreen(){
        Intent intent = new Intent(this, ConfirmacionDatosFirst.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    private void goback_parseScreen(User user){
        Intent intent = new Intent(this, ConfirmacionDatosFirst.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}