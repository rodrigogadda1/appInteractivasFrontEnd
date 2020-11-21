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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.tp0.appintercativas.gestorreclamos.UserManagement.Controller.Controller;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConfiguracionesUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{

    CheckBox chk_uso_movil,chk_uso_notificaciones;
    Button guardar;
    ImageView exit;
    User user;

    //para la slideBar
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones_user);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        guardar = (Button) findViewById(R.id.btnGuardarHisto1);
        exit = (ImageView) findViewById(R.id.btnExitRec1);

        chk_uso_movil = (CheckBox) findViewById(R.id.chk_uso_movil);
        chk_uso_notificaciones = (CheckBox) findViewById(R.id.chk_uso_notificaciones);

        //para la slideBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        drawerLayout = findViewById(R.id.container);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        drawerLayout.addDrawerListener(this);
        //fin para la slideBar


        guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                user.setDatos_moviles(chk_uso_movil.isChecked());
                user.setRecibir_notificaciones(chk_uso_notificaciones.isChecked());
                guardarPreferencias(user);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goBackToMainMenu();
            }
        });

    }

    private void goBackToMainMenu(){
        Intent intent = new Intent(this, PantallaPrincipal.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    private void guardarPreferencias(User user2){
        Retrofit retrofit = Controller.ConfiguracionIP();
        UserService us = retrofit.create(UserService.class);
        Call<User> call = us.updateUser(user2.getId(),user2);


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    mostrarDialogo("Error", "Resultado no correcto ");
                    return;
                } else {
                    mostrarToast("Configuraciones guardadas.");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mostrarDialogo("Error", "Error en la ejecucion "+t.getMessage());
            }
        });


    }

    private void mostrarDialogo(String titulo,String mensaje){
        new AlertDialog.Builder( this)
                .setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //va a hacer nada aca, si se quisiera cerrar la app es finish()
                    }
                })
                .show();
    }


    //metodos de slideBar

    private void mostrarToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

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
                Toast.makeText(this, "Nuevo Reclamo selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, CreacionReclamo1.class);
                intent.putExtra("user",user);
                startActivity(intent);
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
                Toast.makeText(this, "Ya estas en esa seccion!", Toast.LENGTH_SHORT).show();
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