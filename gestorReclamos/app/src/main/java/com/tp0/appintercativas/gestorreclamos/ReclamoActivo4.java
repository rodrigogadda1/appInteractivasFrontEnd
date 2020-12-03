package com.tp0.appintercativas.gestorreclamos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Reclamo;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

public class ReclamoActivo4 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{
    User user;
    Reclamo reclamo;
    Button btnGuardarRecActEstado;
    ImageView imgPpalRecActEstado,btnBackRecActEstado,btnExitREcActEstado;
    TextView txtRecActEstado,txtRecActEstadoReclamo;
    ScrollView scvRecActEstado;
    EditText edtxtRecActEstadoDetalle;

    //para la slide bar
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamo_activo4);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        btnGuardarRecActEstado = (Button) findViewById(R.id.btnGuardarRecActEstado);

        imgPpalRecActEstado = (ImageView) findViewById(R.id.imgPpalRecActEstado);
        btnBackRecActEstado = (ImageView) findViewById(R.id.btnBackRecActEstado);
        btnExitREcActEstado = (ImageView) findViewById(R.id.btnExitREcActEstado);

        txtRecActEstado = (TextView) findViewById(R.id.txtRecActEstado);
        txtRecActEstadoReclamo = (TextView) findViewById(R.id.txtRecActEstadoReclamo);

        scvRecActEstado = (ScrollView) findViewById(R.id.scvRecActEstado);

        edtxtRecActEstadoDetalle = (EditText) findViewById(R.id.edtxtRecActEstadoDetalle);

        //para el estado
        txtRecActEstadoReclamo.setText(reclamo.getEstado().getDescripcion());
        //para descripcion de todo lo demas
        String texto = "";

        txtRecActEstado.setText(texto);
        //para completar el mensaje de administrador
        //edtxtRecActEstadoDetalle






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

        btnBackRecActEstado.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {
                                                  //aca se escribe que hacer

                                              }
                                          }
        );
        btnExitREcActEstado.setOnClickListener(new View.OnClickListener() {
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
                .setMessage(mensaje)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //va a hacer nada aca, si se quisiera cerrar la app es finish()
                    }
                })
                .show();
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
                if (user.getTipoUser().toLowerCase().equals("administrado")){
                    GoToNewReclamo();
                }
                else{
                    mostrarToast("Ud no tiene Perfil para Administrar Abrir Reclamos");
                }
                break;
            case R.id.reclamoactivo:
                //GoToReclamosActivos ();
                mostrarToast("Ya estas en Reclamos activos");
                break;
            case R.id.reclamohistorial:
                GoToViewReclamosHist ();
                break;
            case R.id.notificaciones:
                if (user.getTipoUser().toLowerCase().equals("administrado")){
                    GoToNotificaciones ();
                    break;
                }
                else{
                    mostrarToast("Ud no tiene Perfil para Administrar Notificaciones");
                    break;
                }
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
                if (user.getTipoUser().toLowerCase().equals("administrador")){
                    GoToAdministracionUsuarios ();
                    break;
                }
                else{
                    mostrarToast("Ud no tiene Perfil para Administrar Usuarios");
                    break;
                }
            default:
                break;
        }
        return true;

    }

    private void mostrarToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void GoToNotificaDetalle (){
        Intent intent = new Intent(this, Notificaciones2.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
    private void GoToNewReclamo (){
        Intent intent = new Intent(this, CreacionReclamo1.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
    private void GoToViewReclamosHist () {
        Intent intent= new Intent(this, HistorialReclamos1.class);
        intent.putExtra("user", user);
        startActivity(intent);

    }
    private void GoToNotificaciones () {
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
        //Intent intent= new Intent(this, Notificaciones1.class);
        //intent.putExtra("user", user);
        //startActivity(intent);
        Intent intent = new Intent(this, ReclamoActivo1.class);
        intent.putExtra("user",user);
        startActivity(intent);

    }
    private void GoToCerrarSesion () {
        Intent intent = new Intent(this, MainActivityLogin.class);
        startActivity(intent);
    }
    private void GoToAcercaApp () {
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
        Intent intent = new Intent(this, adminuserPrincipal.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
    private void GoToREclamoActivo2() {
        Intent intent = new Intent(this, ReclamoActivo2.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
    private void GoToREclamoActivo3() {
        Intent intent = new Intent(this, ReclamoActivo3.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}
