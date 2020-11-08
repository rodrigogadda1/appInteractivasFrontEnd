package com.tp0.appintercativas.gestorreclamos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

public class PantallaPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{

    private ScrollView ScrollViewReclamos;
    private Button btnNotificaciones, btnHistorialReclamos, btnReclamosActivos, btnReclamoNuevo;
    User user;

    //para la slide bar
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        ScrollViewReclamos = (ScrollView) findViewById(R.id.ScrollViewReclamos);

        /*  CODIGO PARA SCROLLVIEW RECLAMOS  */

        //hay que verificar  si el user es administrado o inspector o administrador

        /* FIN CODIGO PARA SCROLLVIEW RECLAMOS  */

        btnNotificaciones = (Button) findViewById(R.id.btnNotificaciones);
        btnHistorialReclamos = (Button) findViewById(R.id.btnHistorialReclamos);
        btnReclamosActivos = (Button) findViewById(R.id.btnReclamosActivos);
        btnReclamoNuevo = (Button) findViewById(R.id.btnReclamoNuevo);

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

        btnNotificaciones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //aca va que hace
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
                GoToNewReclamo();
            }
        });


    }

    private void GoToNewReclamo (){
        Intent intent = new Intent(this, CreacionReclamo1.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    //metodos de slideBar desde ahora

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.reclamonuevo:
                Toast.makeText(this, "Nuevo Reclamo selected", Toast.LENGTH_SHORT).show();
                GoToNewReclamo();
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