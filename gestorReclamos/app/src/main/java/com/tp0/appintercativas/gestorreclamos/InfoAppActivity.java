package com.tp0.appintercativas.gestorreclamos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

public class InfoAppActivity extends AppCompatActivity {

    ImageView btnExit;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_app);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        btnExit = (ImageView) findViewById(R.id.btnExit);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoPantallaPrincipal();
            }
        });
    }

    private void GoPantallaPrincipal(){
        Intent intent = new Intent(this, PantallaPrincipal.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}