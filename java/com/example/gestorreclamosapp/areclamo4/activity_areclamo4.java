package com.example.gestorreclamosapp.areclamo4;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.gestorreclamosapp.R;
import com.example.gestorreclamosapp.adminuser1.activity_adminuser1;
import com.example.gestorreclamosapp.areclamo1.activity_areclamo1;
import com.example.gestorreclamosapp.configuraciones.activity_configuraciones;
import com.example.gestorreclamosapp.datospers.activity_datospers;
import com.example.gestorreclamosapp.hreclamo.activity_hreclamo;
import com.example.gestorreclamosapp.infoaplicacion.activity_infoaplicacion;
import com.example.gestorreclamosapp.notificaciones1.activity_notificaciones1;
import com.example.gestorreclamosapp.nreclamo1.activity_nreclamo1;
import com.example.gestorreclamosapp.principal.activity_principal;
import com.example.gestorreclamosapp.ui.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;

public class activity_areclamo4 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areclamo4);

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

        setTitle("Gestor de Reclamos Menu");
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.reclamonuevo:
                Toast.makeText(this, "Nuevo Reclamo selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(activity_areclamo4.this, activity_nreclamo1.class);
                startActivity(intent);
                break;
            case R.id.reclamoactivo:
                Toast.makeText(this, "Reclamos Activos selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.reclamohistorial:
                Toast.makeText(this, "Historial Reclamos selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(activity_areclamo4.this, activity_hreclamo.class);
                startActivity(intent);
                break;
            case R.id.notificaciones:
                Toast.makeText(this, "Notificaciones selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(activity_areclamo4.this, activity_notificaciones1.class);
                startActivity(intent);
                break;
            case R.id.usuarios:
                Toast.makeText(this, "Usuarios selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(activity_areclamo4.this, activity_adminuser1.class);
                startActivity(intent);
                break;
            case R.id.datospersonales:
                Toast.makeText(this, "Datos Personales selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(activity_areclamo4.this, activity_datospers.class);
                startActivity(intent);
                break;
            case R.id.configuracion:
                Toast.makeText(this, "Configuraciones selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(activity_areclamo4.this, activity_configuraciones.class);
                startActivity(intent);
                break;
            case R.id.acercaapp:
                Toast.makeText(this, "Acerca de la App selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(activity_areclamo4.this, activity_infoaplicacion.class);
                startActivity(intent);
                break;
            case R.id.cerrarsesion:
                Toast.makeText(this, "Cerrar Sesión selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(activity_areclamo4.this, LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    public void camara(View view) {
        Toast.makeText(this, "Camara selected", Toast.LENGTH_SHORT).show();
    }

    public void archivos(View view) {
        Toast.makeText(this, "Archivos selected", Toast.LENGTH_SHORT).show();
    }

    public void guardar(View view) {
        Toast.makeText(this, "Guardar selected", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(activity_areclamo4.this, activity_areclamo1.class);
        startActivity(intent);
    }

    public void reclamo_1(View view) {
    }
    public void reclamo_2(View view) {
    }
    public void reclamo_3(View view) {
    }
    public void reclamo_4(View view) {
    }
    public void reclamo_5(View view) {
    }

    public void back(View view) {
        Intent intent = new Intent(activity_areclamo4.this, activity_areclamo1.class);
        startActivity(intent);
    }
    public void exit(View view) {
        Intent intent = new Intent(activity_areclamo4.this, activity_principal.class);
        startActivity(intent);
    }

    @Override
    public void onDrawerSlide( View view, float v) {
        //cambio en la posición del drawer
    }

    @Override
    public void onDrawerOpened( View view) {
        //el drawer se ha abierto completamente
        Toast.makeText(this, getString(R.string.navigation_open), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrawerClosed( View view) {
        //el drawer se ha cerrado completamente
        Toast.makeText(this, getString(R.string.navigation_close), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrawerStateChanged(int i) {
        //cambio de estado, puede ser STATE_IDLE, STATE_DRAGGING or STATE_SETTLING
    }
}