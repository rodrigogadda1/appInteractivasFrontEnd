package com.tp0.appintercativas.gestorreclamos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

public class CreacionReclamo4 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{
    User user;
    ImageView imgReclamoConfirm,imgvAddReclamo,imgvBackRecConf,imgvExitRecConf,imgvNextRecConf;
    TextView txtReclConfTitulo,txtDetalleReclamo;

    //para la slide bar
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_reclamos4);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        imgReclamoConfirm = (ImageView) findViewById(R.id.imgReclamoConfirm);
        imgvAddReclamo = (ImageView) findViewById(R.id.imgvAddReclamo);
        imgvBackRecConf = (ImageView) findViewById(R.id.imgvBackRecConf);
        imgvExitRecConf = (ImageView) findViewById(R.id.imgvExitRecConf);
        imgvNextRecConf = (ImageView) findViewById(R.id.imgvNextRecConf);

        txtReclConfTitulo = (TextView) findViewById(R.id.txtReclConfTitulo);
        txtDetalleReclamo = (TextView) findViewById(R.id.txtDetalleReclamo);

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

        imgReclamoConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //aca va que hace
            }
        });
        imgvAddReclamo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //aca va que hace
            }
        });
        imgvBackRecConf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //aca va que hace
            }
        });
        imgvExitRecConf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //aca va que hace
            }
        });
        imgvNextRecConf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //aca va que hace
            }
        });
        txtReclConfTitulo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //aca va que hace
            }
        });
        txtDetalleReclamo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //aca va que hace
            }
        });


    }
    //metodos de slideBar desde ahora
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
                GoToNewReclamo ();
                break;
            case R.id.reclamoactivo:
                GoToReclamosActivos ();
                break;
            case R.id.reclamohistorial:
                GoToViewReclamosHist ();
                break;
            case R.id.notificaciones:
                GoToNotificaciones ();
                break;
            case R.id.configuracion:
                GoToConfiguraciones();
                break;
            case R.id.acercaapp:
                GoToAcercaApp();
                break;
            case R.id.cerrarsesion:
                GoToCerrarSesion ();
                break;
            case R.id.usuarios:
                GoToAdministracionUsuarios ();
                break;
            default:
                break;
        }
        return true;

    }
    private void GoToNotificaDetalle (){
        Toast.makeText(this, "DEscripcion de Notificacion", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Notificaciones2.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
    private void GoToNewReclamo (){
        Toast.makeText(this, "Nuevo Reclamo selected", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CreacionReclamo1.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
    private void GoToViewReclamosHist () {
        Toast.makeText(this, "Historial Reclamos selected", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this, HistorialReclamos1.class);
        intent.putExtra("user", user);
        startActivity(intent);

    }
    private void GoToNotificaciones () {
        Toast.makeText(this, "Notificaciones selected", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this, Notificaciones1.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    private void GoToConfiguraciones(){
        Intent intent = new Intent(this, ConfiguracionesUser.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    private void GoToReclamosActivos () {
        Toast.makeText(this, "Reclamos Activos selected", Toast.LENGTH_SHORT).show();
        //Intent intent= new Intent(this, Notificaciones1.class);
        //intent.putExtra("user", user);
        //startActivity(intent);
        Intent intent = new Intent(this, CreacionReclamo4.class);
        intent.putExtra("user",user);
        startActivity(intent);

    }
    private void GoToCerrarSesion () {
        Toast.makeText(this, "Cerrar Sesión selected", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivityLogin.class);
        startActivity(intent);
    }
    private void GoToAcercaApp () {
        Toast.makeText(this, "Acerca de la App selected", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, InfoAppActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
    private void GoToAdministracionUsuarios () {
        Toast.makeText(this, "Administracion de Usuarios selected", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, adminuserPrincipal.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}
