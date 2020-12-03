package com.tp0.appintercativas.gestorreclamos;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Edificio;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Especialidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Inspector;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Reclamo;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.AdministradoService;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.InspectorService;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.ReclamoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReclamoActivo1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{
        List<Reclamo> reclamos;
        Administrado administrado;
        User user;
        Button btnFiltroRec,btnAgruparReclamo;
        ImageView btnExitReclamo;
        TextView txtPpal1,txtPpal2;
        ScrollView svwlistareclamos;
    //para la slide bar
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamo_activo1);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        btnFiltroRec = (Button) findViewById(R.id.btnFiltroRec);
        btnAgruparReclamo = (Button) findViewById(R.id.btnAgruparReclamo);
        btnExitReclamo = (ImageView) findViewById(R.id.btnExitReclamo);

        txtPpal1 = (TextView) findViewById(R.id.txtPpal1);
        txtPpal2 = (TextView) findViewById(R.id.txtPpal2);

        svwlistareclamos = (ScrollView) findViewById(R.id.svwlistareclamos);

        reclamos = new ArrayList<Reclamo>();
        getReclamosFilteredByUserIdStatus();

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

        btnFiltroRec.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                        mostrarToast("funcionalidad no implementada");
                                            }
                                        }
        );
        btnAgruparReclamo.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                mostrarToast("funcionalidad no implementada");
                                            }
                                        }
        );
        btnExitReclamo.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    GoPantallaPrincipal();
                                                }
                                            }
        );

    }

    private void getReclamosFilteredByUserIdStatus() {
        Retrofit retrofit = Controller.ConfiguracionIP();
        ReclamoService rs = retrofit.create(ReclamoService.class);
        Call<List<Reclamo>> call = null;

        if (user.getTipoUser().toLowerCase().equals("administrado")) {
            call = rs.getReclamosByUserIdAndStatusId(String.valueOf(user.getId()),"1,3,4,5","","");
        } else if (user.getTipoUser().toLowerCase().equals("inspector")){
            getInspector();
        } else {
            //caso administrador
            call = rs.getReclamosByUserIdAndStatusId("","4,5","","");
        }

        if (!user.getTipoUser().toLowerCase().equals("inspector")) {
            call.enqueue(new Callback<List<Reclamo>>() {
                @Override
                public void onResponse(Call<List<Reclamo>> call, Response<List<Reclamo>> response) {
                    if (response.isSuccessful()) {
                        reclamos = response.body();

                    ArrayList<String> strings = new ArrayList<>();

                    for (int i = 0; i < reclamos.size(); i++) {
                            Reclamo reclamo = reclamos.get(i);
                            strings.add(reclamoToString(reclamo));
                    }

                    if (strings.size() > 0) {
                        String[] reclamosToAdd = new String[strings.size()];
                        reclamosToAdd = strings.toArray(reclamosToAdd);
                        makeCenterView(reclamosToAdd);
                    }
                    }
                }

                @Override
                public void onFailure(Call<List<Reclamo>> call, Throwable t) {
                    mostrarDialogo("Error", "Error en la ejecucion " + t.getMessage());
                }
            });
        }

    }

    private void getInspector (){
        Retrofit retrofit = Controller.ConfiguracionIP();
        InspectorService is = retrofit.create(InspectorService.class);
        Call<Inspector> call = is.getInspectorId((long) user.getId());
        call.enqueue(new Callback<Inspector>() {
            @Override
            public void onResponse(Call<Inspector> call, Response<Inspector> response) {

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label",response.body().toString());
                clipboard.setPrimaryClip(clip);

                if (response.isSuccessful()){
                    Inspector inspector = response.body();
                    if ( (inspector.getEdificios().size() > 0) && (inspector.getEspecialidades().size() > 0)){
                        String lista_edificios = "", lista_especialidades = "";

                        for (int i = 0; i < inspector.getEdificios().size(); i++) {
                            Edificio edificio = inspector.getEdificios().get(i);
                            if (i==0){
                                lista_edificios=String.valueOf(edificio.getId_edificio());
                            } else {
                                lista_edificios+=","+String.valueOf(edificio.getId_edificio());
                            }
                        }

                        for (int i = 0; i < inspector.getEspecialidades().size(); i++) {
                            Especialidad especialidad = inspector.getEspecialidades().get(i);
                            if (i == 0){
                                lista_especialidades=String.valueOf(especialidad.getId_especialidad());
                            } else {
                                lista_especialidades+=","+String.valueOf(especialidad.getId_especialidad());
                            }
                        }

                        getReclamosInspector(lista_edificios,lista_especialidades);

                    }
                }
            }

            @Override
            public void onFailure(Call<Inspector> call, Throwable t) {
                mostrarDialogo("Error", "Error en la ejecucion "+t.getMessage());
            }
        });
    }

    private void getReclamosInspector(String lista_edificios,String lista_especialidades){
        Retrofit retrofit = Controller.ConfiguracionIP();
        ReclamoService rs = retrofit.create(ReclamoService.class);
        Call<List<Reclamo>> call = rs.getReclamosByUserIdAndStatusId("","1,3",lista_edificios,lista_especialidades);

        call.enqueue(new Callback<List<Reclamo>>() {
            @Override
            public void onResponse(Call<List<Reclamo>> call, Response<List<Reclamo>> response) {

                if (response.isSuccessful()){
                    reclamos = response.body();
                    ArrayList<String> strings = new ArrayList<>();

                    for (int i = 0; i < reclamos.size(); i++) {
                            Reclamo reclamo = reclamos.get(i);
                            strings.add(reclamoToString(reclamo));
                    }

                    if (strings.size() > 0) {
                        String[] reclamosToAdd = new String[strings.size()];
                        reclamosToAdd = strings.toArray(reclamosToAdd);
                        makeCenterView(reclamosToAdd);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Reclamo>> call, Throwable t) {
                mostrarDialogo("Error", "Error en la ejecucion "+t.getMessage());
            }
        });
    }

    private String reclamoToString(Reclamo reclamo) {
        return reclamo.getId_reclamo()+"-"+reclamo.getEspecialidad().getDescripcion()+"-"+reclamo.getDescripcion();
    }

    protected void makeCenterView(String[] items) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        int nro = 0;
        for (final String item : items) {
            LinearLayout line = new LinearLayout(this);
            line.setOrientation(LinearLayout.HORIZONTAL);
            line.setGravity(Gravity.CENTER);
            Button btnReclamo = new Button(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            lp.gravity = Gravity.CENTER;
            btnReclamo.setLayoutParams(lp);
            btnReclamo.setText(item); //aca iria el texto
            btnReclamo.setGravity(Gravity.CENTER);
            final int finalNro = nro;
            btnReclamo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mostrarToast(item);
                    if (user.getTipoUser().toLowerCase().equals("administrado")){
                        GoToREclamoActivo2(reclamos.get(finalNro));
                    } else if (user.getTipoUser().toLowerCase().equals("inspector")) {
                        GoToREclamoActivo3(reclamos.get(finalNro));
                    } else {
                        GoToREclamoActivo4(reclamos.get(finalNro));
                    }

                    //var nro para usar en la List<Reclamo>
                    //aca se tiene que pasar al detalle
                }
            });
            line.addView(btnReclamo);
            linearLayout.addView(line);
            nro++;
        }
        svwlistareclamos.addView(linearLayout);
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
    private void GoToREclamoActivo2(Reclamo reclamo) {
        Intent intent = new Intent(this, ReclamoActivo2.class);
        intent.putExtra("user",user);
        intent.putExtra("reclamo",reclamo);
        startActivity(intent);
    }
    private void GoToREclamoActivo3(Reclamo reclamo) {
        Intent intent = new Intent(this, ReclamoActivo3.class);
        intent.putExtra("user",user);
        intent.putExtra("reclamo",reclamo);
        startActivity(intent);
    }
    private void GoToREclamoActivo4(Reclamo reclamo) {
        Intent intent = new Intent(this, ReclamoActivo4.class);
        intent.putExtra("user",user);
        intent.putExtra("reclamo",reclamo);
        startActivity(intent);
    }
}