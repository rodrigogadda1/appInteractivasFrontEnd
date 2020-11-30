package com.tp0.appintercativas.gestorreclamos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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

public class adminuserChangePassword extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{
    User user;
    ImageView adminuser3_img,adminuser3_img2,btnBackNewPassword,btnExitNewPassword;
    TextView txtChangePassword,txtUsuarioChangePassword,txtUsuarioNombreChangePassword,txtPasswordChangePassword,txtPasswordewPassword,
    txtPasswordNewVerificacion;
    EditText txtNewPasswordVerficacion;
    Button btnGuardarNewPassword;
    //para la slide bar
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_reclamos1);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        adminuser3_img = findViewById(R.id.adminuser3_img);
        adminuser3_img2 = findViewById(R.id.adminuser3_img2);
        btnBackNewPassword = findViewById(R.id.btnBackNewPassword);
        btnExitNewPassword = findViewById(R.id.btnExitNewPassword);
        txtChangePassword = findViewById(R.id.txtChangePassword);
        txtUsuarioChangePassword = findViewById(R.id.txtUsuarioChangePassword);
        txtUsuarioNombreChangePassword = findViewById(R.id.txtUsuarioNombreChangePassword);
        txtPasswordChangePassword = findViewById(R.id.txtPasswordChangePassword);
        txtPasswordewPassword = findViewById(R.id.txtPasswordewPassword);
        txtPasswordNewVerificacion = findViewById(R.id.txtPasswordNewVerificacion);

        txtNewPasswordVerficacion = findViewById(R.id.txtNewPasswordVerficacion);

        btnGuardarNewPassword = findViewById(R.id.btnGuardarNewPassword);

        //codigo para slide bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        drawerLayout = findViewById(R.id.container);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        drawerLayout.addDrawerListener(this);
        //fin codigo para slide bar
        txtNewPasswordVerficacion.setOnClickListener(new View.OnClickListener() {
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
                if (user.getTipoUser().toLowerCase().equals("administrado")){
                    GoToNewReclamo();
                }
                else{
                    mostrarToast("Ud no tiene Perfil para Administrar Abrir Reclamos");
                }
                break;
            case R.id.reclamoactivo:
                GoToReclamosActivos ();
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
                //GoToAdministracionUsuarios ();
                mostrarToast("Ya estás en el Menú de Usuarios");
                break;
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
}
