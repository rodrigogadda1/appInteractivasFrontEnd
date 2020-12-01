package com.tp0.appintercativas.gestorreclamos;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class ReclamoActivo3 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{
    User user;
    Button btnEditRecActGuardar;
    ImageView imgPpalRecAct3,imgEditRecAct1,imgEditRecAct2,imgEditRecAct3,imgEditRecAct4,imgEditRecActBack,imgEditRecActExit;
    TextView txtPpalRecAct3,txtEditRecAct,txtEditRecActEspecialidad,txtEditRecActAgrupado,txtEditRecActEstados;
    ScrollView scvEditReclamoTexto,scvEditRecActListImagenes;
    Spinner spnEditRecActEspecialidades,spnEditRecActReclamosAg,spnEditRecActEstados;
    EditText edtTxtEditRecActivoAgrupadosTexto;
    //para la slide bar
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamo_activo3);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        btnEditRecActGuardar = (Button) findViewById(R.id.btnEditRecActGuardar);

        imgPpalRecAct3 = (ImageView) findViewById(R.id.imgPpalRecAct3);
        imgEditRecAct1 = (ImageView) findViewById(R.id.imgEditRecAct1);
        imgEditRecAct2 = (ImageView) findViewById(R.id.imgEditRecAct2);
        imgEditRecAct3 = (ImageView) findViewById(R.id.imgEditRecAct3);
        imgEditRecAct4 = (ImageView) findViewById(R.id.imgEditRecAct4);
        imgEditRecActBack = (ImageView) findViewById(R.id.imgEditRecActBack);
        imgEditRecActExit = (ImageView) findViewById(R.id.imgEditRecActExit);

        txtPpalRecAct3 = (TextView) findViewById(R.id.txtPpalRecAct3);
        txtEditRecAct = (TextView) findViewById(R.id.txtEditRecAct);
        txtEditRecActEspecialidad = (TextView) findViewById(R.id.txtEditRecActEspecialidad);
        txtEditRecActEstados = (TextView) findViewById(R.id.txtEditRecActEstados);

        scvEditReclamoTexto = (ScrollView) findViewById(R.id.scvEditReclamoTexto);

        spnEditRecActEspecialidades = (Spinner) findViewById(R.id.spnEditRecActEspecialidades);
        spnEditRecActEstados = (Spinner) findViewById(R.id.spnEditRecActEstados);

        edtTxtEditRecActivoAgrupadosTexto = (EditText) findViewById(R.id.edtTxtEditRecActivoAgrupadosTexto);

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

        imgEditRecActBack.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                //aca se escribe que hacer
                                                GoToREclamoActivo2();
                                            }
                                        }
        );
        imgEditRecActExit.setOnClickListener(new View.OnClickListener() {
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
}
