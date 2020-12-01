package com.tp0.appintercativas.gestorreclamos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.tp0.appintercativas.gestorreclamos.UserManagement.Controller.Controller;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Administrado;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Notificacion;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.AdministradoService;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.NotificacionService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Notificaciones1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{
    User user;
    Administrado administrado;
    List<Notificacion> notificacionsNoLeidas;
    ImageView principal_img,btnExitNotif;
    TextView txtNotificacionesPpal,principaltexto2;
    ScrollView listanotificaciones;
    Button btnBorrarNotif;
    //para la slide bar
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones1);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        principal_img = (ImageView) findViewById(R.id.imgvPpal);
        btnExitNotif = (ImageView) findViewById(R.id.btnExitNotif);

        txtNotificacionesPpal = (TextView) findViewById(R.id.txtNotificacionesPpal);
        principaltexto2 = (TextView) findViewById(R.id.txtListaUsuarios);

        listanotificaciones = (ScrollView) findViewById(R.id.listanotificaciones);

        revisarNotificaciones();

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

        btnExitNotif.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                //aca se escribe que hacer
                                                GoPantallaPrincipal();
                                            }
                                        }
        );



        btnBorrarNotif.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                //aca se escribe que hacer
                                            }
                                        }
        );

    }

    private Notificacion getNotificacionByIdDescr (String busqueda){
        Notificacion salida = null;
        for (int i = 0; i < notificacionsNoLeidas.size(); i++) {
            Notificacion notificacionActual = notificacionsNoLeidas.get(i);
            String newComp = notificacionActual.getId_notificacion()+"-"+notificacionActual.getDescripcion();
            if (newComp.startsWith(busqueda)){
                salida = notificacionActual;
            }
        }
        return salida;
    }

    private void revisarNotificaciones(){
        Retrofit retrofit = Controller.ConfiguracionIP();
        AdministradoService as = retrofit.create(AdministradoService.class);
        Call<Administrado> call = as.getAdministradoId((long) user.getId());

        call.enqueue(new Callback<Administrado>() {

            @Override
            public void onResponse(Call<Administrado> call, Response<Administrado> response) {
                administrado = response.body();
                mostrarDialogo("revisarNotificaciones ",administrado.toString());
                if (response.isSuccessful()){
                    administrado = response.body();
                    buscarNotificaciones(administrado);
                }
            }

            @Override
            public void onFailure(Call<Administrado> call, Throwable t) {
                mostrarDialogo("Error", "Error en la ejecucion " + t.getMessage());
            }
        });
    }

    private void buscarNotificaciones(Administrado administrado){
        Retrofit retrofit = Controller.ConfiguracionIP();
        NotificacionService ns = retrofit.create(NotificacionService.class);
        Call<List<Notificacion>> call = ns.getNotificacionId(administrado.getId_administrado());

        call.enqueue(new Callback<List<Notificacion>>() {
            @Override
            public void onResponse(Call<List<Notificacion>> call, Response<List<Notificacion>> response) {
                mostrarDialogo("probando buscarNotificaciones",response.body().toString());
                if (response.isSuccessful()){
                    List<Notificacion> notificaciones = response.body();
                    notificacionsNoLeidas = new ArrayList<Notificacion>();
                    for (int i = 0; i < notificaciones.size(); i++) {
                        Notificacion notificacion = notificaciones.get(i);
                        if (!notificacion.isLeido()) {
                            notificacionsNoLeidas.add(notificacion);
                            actualizarALeido(notificacion);
                        }
                    }

                    if (notificacionsNoLeidas.size() > 0){
                        List<String> notificacionesString = new ArrayList<String>();
                        for (int i = 0; i < notificacionsNoLeidas.size(); i++) {
                            Notificacion notificacion = notificacionsNoLeidas.get(i);
                            if (notificacion.getDescripcion().length() < 10){
                                notificacionesString.add(notificacion.getId_notificacion()+"-"+notificacion.getDescripcion());
                            } else {
                                notificacionesString.add(notificacion.getId_notificacion()+"-"+notificacion.getDescripcion().substring(0,9));
                            }

                        }
                        String[] strings = new String[notificacionesString.size()];
                        strings = notificacionesString.toArray(strings);
                        makeCenterView(strings);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Notificacion>> call, Throwable t) {
                mostrarDialogo("Error", "Error en la ejecucion " + t.getMessage());
            }
        });
    }

    private void actualizarALeido(Notificacion notificacion){
        Retrofit retrofit = Controller.ConfiguracionIP();
        NotificacionService ns = retrofit.create(NotificacionService.class);
        Call<Notificacion> call = ns.updateNotificacion(notificacion.getId_notificacion(),notificacion);

        call.enqueue(new Callback<Notificacion>() {
            @Override
            public void onResponse(Call<Notificacion> call, Response<Notificacion> response) {

            }

            @Override
            public void onFailure(Call<Notificacion> call, Throwable t) {
                mostrarDialogo("Error", "Error en la ejecucion " + t.getMessage());
            }
        });
    }

    protected void makeCenterView(String[] items) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for (final String item : items) {
            LinearLayout line = new LinearLayout(this);
            line.setOrientation(LinearLayout.HORIZONTAL);
            line.setGravity(Gravity.CENTER);
            Button btnNotificacion = new Button(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            lp.gravity = Gravity.CENTER;
            btnNotificacion.setLayoutParams(lp);
            btnNotificacion.setText(item); //aca iria el texto
            btnNotificacion.setGravity(Gravity.CENTER);
            btnNotificacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mostrarToast(item);
                    GoToNotificaDetalle(getNotificacionByIdDescr(item));
                }
            });
            line.addView(btnNotificacion);
            linearLayout.addView(line);
        }
        listanotificaciones.addView(linearLayout);
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

    private void mostrarToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
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
                //GoToNotificaciones ();
                mostrarToast("Ya estás en el Menú de Notificaciones");
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


    private void GoToNotificaDetalle (Notificacion notificacionSend){
        Toast.makeText(this, "DEscripcion de Notificacion", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Notificaciones2.class);
        intent.putExtra("user",user);
        intent.putExtra("notificacion", notificacionSend);
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
