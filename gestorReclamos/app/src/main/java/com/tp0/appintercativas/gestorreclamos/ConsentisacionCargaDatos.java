package com.tp0.appintercativas.gestorreclamos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

public class ConsentisacionCargaDatos extends AppCompatActivity {

    TextView txtConsentimiento;
    ImageView btnCancel, btnOk;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consentisacion_carga_datos);

        btnCancel = (ImageView) findViewById(R.id.btnCancel);
        btnOk = (ImageView) findViewById(R.id.btnOk);
        txtConsentimiento = (TextView) findViewById(R.id.txtConsentimiento);

        //agarro el user
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        btnCancel.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                    goBackButton();
                 }
             }
        );
        btnOk.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goNextItem();
                    }
                }
        );

    }

    private void goNextItem(){
        Intent intent = new Intent(this, CargaDatosPersonalesFirst.class);
        intent.putExtra("user",user);
        intent.putExtra("ponerDatosDefault" , "false");
        startActivity(intent);
    }

    private void goBackButton () {
        Intent intent = new Intent(this, MainActivityLogin.class);
        startActivity(intent);
    }

}