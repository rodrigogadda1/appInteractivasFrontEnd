package com.example.gestorreclamosapp.configuraciones;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import com.example.gestorreclamosapp.R;
import com.example.gestorreclamosapp.infoaplicacion.activity_infoaplicacion;
import com.example.gestorreclamosapp.nreclamo1.activity_nreclamo1;
import com.example.gestorreclamosapp.principal.activity_principal;
import com.example.gestorreclamosapp.ui.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;

public class activity_configuraciones extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {
    private DrawerLayout drawerLayout;
    private CheckBox seleccionBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones);

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

        seleccionBox = (CheckBox) findViewById(R.id.chk_uso_movil);
        seleccionBox = (CheckBox) findViewById(R.id.chk_uso_notificaciones);
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
                intent = new Intent(activity_configuraciones.this, activity_nreclamo1.class);
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
                Toast.makeText(this, "Configuraciones selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.acercaapp:
                Toast.makeText(this, "Acerca de la App selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(activity_configuraciones.this, activity_infoaplicacion.class);
                startActivity(intent);
                break;
            case R.id.cerrarsesion:
                Toast.makeText(this, "Cerrar Sesión selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(activity_configuraciones.this, LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    public void exit(View view) {
        Intent intent = new Intent(activity_configuraciones.this, activity_principal.class);
        startActivity(intent);
    }

    public void guardar(View view) {
        String s = "Estado: " + (seleccionBox.isChecked() ? "Marcado" : "No Marcado");
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
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
