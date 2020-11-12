package com.tp0.appintercativas.gestorreclamos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Reclamo;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

import java.io.Serializable;

public class CreacionReclamo2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{

    User user;
    Reclamo reclamo;
    ScrollView scrvResumen;
    TextView txtReclamoResumen, txtReclamoDetalle;
    Spinner spnEspecialidades;
    EditText editReclamoComentario;
    ImageView imgvCamara, imgvArchivos, btnBack, btnPantallaPrincipal, btnNext;

    //para la slide bar
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_reclamo2);

         try{
             Intent intent = getIntent();
             user = (User) intent.getSerializableExtra("user");
             reclamo = (Reclamo) intent.getSerializableExtra("reclamo");

             scrvResumen = (ScrollView) findViewById(R.id.scrvResumen);

             txtReclamoResumen = (TextView) findViewById(R.id.txtReclamoResumen);
             txtReclamoDetalle = (TextView) findViewById(R.id.txtReclamoDetalle);

             spnEspecialidades = (Spinner) findViewById(R.id.spnEspecialidades);

             editReclamoComentario = (EditText) findViewById(R.id.editReclamoComentario);

             imgvCamara = (ImageView) findViewById(R.id.imgvCamara);
             imgvArchivos = (ImageView) findViewById(R.id.imgvArchivos);
             btnBack = (ImageView) findViewById(R.id.btnBack);
             btnPantallaPrincipal = (ImageView) findViewById(R.id.btnPantallaPrincipal);
             btnNext = (ImageView) findViewById(R.id.btnNext);


             //codigo para slide bar
             Toolbar toolbar = findViewById(R.id.toolbar);
             setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

             drawerLayout = findViewById(R.id.container);
             ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
             drawerLayout.addDrawerListener(toggle);
             toggle.syncState();

             navigationView = findViewById(R.id.navigation_view);
             navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
             navigationView.setItemIconTintList(null);

             drawerLayout.addDrawerListener(this);
             //fin codigo para slide bar
        } catch (Exception e){
            mostrarDialogo("error",e.getMessage());
        }

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        reclamo = (Reclamo) intent.getSerializableExtra("reclamo");

        scrvResumen = (ScrollView) findViewById(R.id.scrvResumen);

        txtReclamoResumen = (TextView) findViewById(R.id.txtReclamoResumen);
        txtReclamoDetalle = (TextView) findViewById(R.id.txtReclamoDetalle);

        spnEspecialidades = (Spinner) findViewById(R.id.spnEspecialidades);

        editReclamoComentario = (EditText) findViewById(R.id.editReclamoComentario);

        imgvCamara = (ImageView) findViewById(R.id.imgvCamara);
        imgvArchivos = (ImageView) findViewById(R.id.imgvArchivos);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnPantallaPrincipal = (ImageView) findViewById(R.id.btnPantallaPrincipal);
        btnNext = (ImageView) findViewById(R.id.btnNext);


        //codigo para slide bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        drawerLayout = findViewById(R.id.container);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        navigationView.setItemIconTintList(null);

        drawerLayout.addDrawerListener(this);
        //fin codigo para slide bar


        imgvCamara.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                                //aca se escribe que hacer
                                         }
                                     }
        );

        imgvArchivos.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             //aca se escribe que hacer
                                         }
                                     }
        );

        btnBack.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             //aca se escribe que hacer
                                         }
                                     }
        );

        btnPantallaPrincipal.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             //aca se escribe que hacer
                                         }
                                     }
        );

        btnNext.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             //aca se escribe que hacer
                                         }
                                     }
        );
    }

    private void mostrarDialogo(String titulo,String mensaje){
        new AlertDialog.Builder( this)
                .setTitle(titulo)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //va a hacer nada aca, si se quisiera cerrar la app es finish()
                    }
                })
                .setMessage(mensaje)
                .show();
    }

    //para la slide bar

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.reclamonuevo:
                Toast.makeText(this, "Ya estas en esa pantalla!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.reclamoactivo:
                Toast.makeText(this, "Reclamos Activos selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.reclamohistorial:
                Toast.makeText(this, "Historial Reclamos selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.notificaciones:
                Toast.makeText(this, "Notificaciones selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.configuracion:
                Toast.makeText(this, "Configuraciones selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ConfiguracionesUser.class);
                intent.putExtra("user",user);
                startActivity(intent);
                break;
            case R.id.acercaapp:
                Toast.makeText(this, "Acerca de la App selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, InfoAppActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                break;
            case R.id.cerrarsesion:
                Toast.makeText(this, "Cerrar Sesión selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, MainActivityLogin.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }
}