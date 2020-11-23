package com.tp0.appintercativas.gestorreclamos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

public class adminuserNewUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{
    User user;
    ImageView imgvAdminUserNewUser,adminuser2_img2,imgvBackNewUser,imgvExitNewUser,imgvNextNeuUser;
    TextView txtNewUser,txtUsuarioNew,txtEdificiosNewUser,txtUnidadesNewUser,txtOcupantesNewUser;
    EditText txtedtUserID;
    Spinner spnListaEdificiosNewUser,spnUnidadesNewUser,spnListaOcupantesNewUser;

    //para la slide bar
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminuser1_principal);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        imgvAdminUserNewUser = (ImageView) findViewById(R.id.imgvAdminUserNewUser);
        adminuser2_img2 = (ImageView) findViewById(R.id.adminuser2_img2);
        imgvBackNewUser = (ImageView) findViewById(R.id.imgvBackNewUser);
        imgvExitNewUser = (ImageView) findViewById(R.id.imgvExitNewUser);
        imgvNextNeuUser = (ImageView) findViewById(R.id.imgvNextNeuUser);

        txtNewUser = (TextView) findViewById(R.id.txtNewUser);
        txtUsuarioNew = (TextView) findViewById(R.id.txtUsuarioNew);
        txtEdificiosNewUser = (TextView) findViewById(R.id.txtEdificiosNewUser);
        txtUnidadesNewUser = (TextView) findViewById(R.id.txtUnidadesNewUser);
        txtOcupantesNewUser = (TextView) findViewById(R.id.txtOcupantesNewUser);

        txtedtUserID = (EditText) findViewById(R.id.txtedtUserID);

        spnListaEdificiosNewUser = (Spinner) findViewById(R.id.spnListaEdificiosNewUser);
        spnUnidadesNewUser = (Spinner) findViewById(R.id.spnUnidadesNewUser);
        spnListaOcupantesNewUser = (Spinner) findViewById(R.id.spnListaOcupantesNewUser);
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
        imgvBackNewUser.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                   //aca se escribe que hacer
                                                   GoToAdministracionUsuarios();
                                               }
                                           }
        );
        imgvExitNewUser.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                   //aca se escribe que hacer
                                                   GoToAdministracionUsuarios();
                                               }
                                           }
        );

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
    private void GoPantallaPrincipal(){
        Intent intent = new Intent(this, PantallaPrincipal.class);
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
