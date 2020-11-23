package com.tp0.appintercativas.gestorreclamos;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.tp0.appintercativas.gestorreclamos.ResponseURIs.ResponseLogin;
import com.tp0.appintercativas.gestorreclamos.UserManagement.Controller.Controller;
import com.tp0.appintercativas.gestorreclamos.UserManagement.SQLite.Reclamo_SQLLite;
import com.tp0.appintercativas.gestorreclamos.UserManagement.SQLite.ReclamosHelper;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Administrado;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Edificio;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.EspacioComun;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Especialidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Estado;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Reclamo;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Unidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.AdministradoService;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.EspecialidadService;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.ReclamoService;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PantallaPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{

    NotificationManager notificationManagerAux;
    private NotificationManagerCompat notificationManager;

    ReclamosHelper reclamosHelper;
    Administrado administrado;

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

        /*  CODIGO PARA SCROLLVIEW RECLAMOS  */
        ScrollViewReclamos = (ScrollView) findViewById(R.id.ScrollViewReclamos);


        getReclamosFilteredByUserIdStatus();

        /* FIN CODIGO PARA SCROLLVIEW RECLAMOS  */

        btnNotificaciones = (Button) findViewById(R.id.btnNotificaciones);
        btnHistorialReclamos = (Button) findViewById(R.id.btnHistorialReclamos);
        btnReclamosActivos = (Button) findViewById(R.id.btnReclamosActivos);
        btnReclamoNuevo = (Button) findViewById(R.id.btnReclamoNuevo);

        //para las notifications
        notificationManager =  NotificationManagerCompat.from(this);

        //se revisa si hay reclamos pendientes para subir
        if (user.getTipoUser().toLowerCase().equals("administrado")) {
            //pruebo que tenga la conexion para poder subir los reclamos
            if (    testearConnection().equals("DataWifi") ||  ( testearConnection().equals("DataMobile") && (user.isDatos_moviles()) )   ) {
                getAdministradoId();
            }
            generarNotificacion();
        } else if (user.getTipoUser().toLowerCase().equals("inspector")){

        }


        //CLIPBOARD
        //ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        //ClipData clip = ClipData.newPlainText("label",reclamitos.toString());
        //clipboard.setPrimaryClip(clip);
        /*
        [Reclamo_SQLLite{id_reclamo=0, Nombre='null', username='user2', id_edificio=2, id_especialidad=2, id_estado=1, id_agrupador=0, descripcion='asdad asdasdsa', id_administrado=2, id_unidad=0, id_espacioComun=5, fotos=null}]
         */
        //CLIPBOARD

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

    private void generarNotificacion(){
        Notification notification = new NotificationCompat.Builder(this, com.tp0.appintercativas.gestorreclamos.UserManagement.Auxiliares.NotificationManager.CHENNEL_1_ID)
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle("titulo")
                    .setContentText("contenido")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .build();
        notificationManager.notify(1, notification);
    }

    private void getAdministradoId(){

        Retrofit retrofit = Controller.ConfiguracionIP();
        AdministradoService as = retrofit.create(AdministradoService.class);
        Call<Administrado> call = as.getAdministradoId((long) user.getId());

        call.enqueue(new Callback<Administrado>() {

            @Override
            public void onResponse(Call<Administrado> call, Response<Administrado> response) {
                if (response.isSuccessful()){
                    administrado = response.body();
                    buscarReclamosAdministrado(administrado);
                }

            }

            @Override
            public void onFailure(Call<Administrado> call, Throwable t) {
                mostrarDialogo("Error", "Error en la ejecucion " + t.getMessage());
            }
        });
    }

    private void GoToNewReclamo (){
        Intent intent = new Intent(this, CreacionReclamo1.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void buscarReclamosAdministrado (Administrado administrado){
        reclamosHelper = new ReclamosHelper(this);
        List<Reclamo_SQLLite> reclamitos = reclamosHelper.getReclamosSQLiteByAdminitradoId(administrado.getId_administrado());
        if (reclamitos.size() > 0) {
            mostrarDialogo("probando PantallaPrincipal 179",String.valueOf(reclamitos.size()));
            CrearReclamosRecursivo(reclamitos,0,reclamosHelper);
        }
    }

    private String testearConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        String salida = null;
        if (ni != null && ni.isConnected()) {
            switch (ni.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    salida = "DataWifi";
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    salida = "DataMobile";
                    break;
            }
        } else {
            salida = "NotConnected";
        }
        return salida;
    }

    //iniciarlo en cero
    private void CrearReclamosRecursivo(final List<Reclamo_SQLLite> reclamitos, final int posicion, final ReclamosHelper reclamosHelper){
        try {
            if (posicion < reclamitos.size()){

                Reclamo_SQLLite reclamitoPos = reclamitos.get(posicion);
                Reclamo reclamo = pasarDeReclamitoAReclamo(reclamitoPos);

                Retrofit retrofit = Controller.ConfiguracionIP();
                ReclamoService rs = retrofit.create(ReclamoService.class);
                Call<Reclamo> call= rs.createReclamo(reclamo);

                call.enqueue(new Callback<Reclamo>() {
                    @Override
                    public void onResponse(Call<Reclamo> call, Response<Reclamo> response) {
                        if (response.isSuccessful()){
                            CrearReclamosRecursivo(reclamitos,posicion+1,reclamosHelper);
                        }
                    }

                    @Override
                    public void onFailure(Call<Reclamo> call, Throwable t) {
                        mostrarDialogo("error", t.getMessage());
                    }
                });
            } else {
                reclamosHelper.deleteRowsOfAdministrado((int) administrado.getId_administrado());
            }

        } catch (Exception e){
            mostrarDialogo("error",e.getMessage());
        }

    }

    private Reclamo pasarDeReclamitoAReclamo(Reclamo_SQLLite reclamitoPos) {
        Reclamo salida = new Reclamo();

        salida.setDescripcion(reclamitoPos.getDescripcion());

        Administrado administrado = new Administrado();
        administrado.setId_administrado(reclamitoPos.getId_administrado());
        salida.setAdministrado(administrado);

        Edificio edificio = new Edificio();
        edificio.setId_edificio(reclamitoPos.getId_edificio());
        salida.setEdificio(edificio);

        Estado estado = new Estado();
        estado.setId_estado(reclamitoPos.getId_estado());
        salida.setEstado(estado);

        salida.setUsername(reclamitoPos.getUsername());

        Especialidad especialidad = new Especialidad();
        especialidad.setId_especialidad(reclamitoPos.getId_especialidad());
        salida.setEspecialidad(especialidad);

        if (reclamitoPos.getId_agrupador() != 0){
            salida.setId_agrupador(reclamitoPos.getId_agrupador());
        }

        if (reclamitoPos.getId_espacioComun() != 0){
            EspacioComun espacioComun = new EspacioComun();
            espacioComun.setId_espaciocomun(reclamitoPos.getId_espacioComun());
            salida.setEspacioComun(espacioComun);
        }

        if (reclamitoPos.getNombre() != ""){
            salida.setNombre(reclamitoPos.getNombre());
        }

        if (reclamitoPos.getId_unidad() != 0){
            Unidad unidad = new Unidad();
            unidad.setId_unidad(reclamitoPos.getId_unidad());
            salida.setUnidad(unidad);
        }

        //aca iria fotos

        return salida;
    }


    private void getReclamosFilteredByUserIdStatus(){
        Retrofit retrofit = Controller.ConfiguracionIP();
        ReclamoService rs = retrofit.create(ReclamoService.class);
        Call<List<Reclamo>> call = null;

        if (user.getTipoUser().toLowerCase().equals("administrado")) {
            call = rs.getReclamosByUserIdAndStatusId(String.valueOf(user.getId()),"1","","");
        } else if (user.getTipoUser().toLowerCase().equals("inspector")){
            call = rs.getReclamosByUserIdAndStatusId("","1","1,2","");
        } else {
            //caso administrador
            call = rs.getReclamosByUserIdAndStatusId("","1","","");
        }

        call.enqueue(new Callback<List<Reclamo>>() {
            @Override
            public void onResponse(Call<List<Reclamo>> call, Response<List<Reclamo>> response) {
                if (response.isSuccessful()){
                    List<Reclamo> reclamos = response.body();
                    ArrayList<String> strings = new ArrayList<>();

                    for (int i = 0; i < reclamos.size(); i++) {
                        if ( i < 5 ) {
                            Reclamo reclamo = reclamos.get(i);
                            strings.add(reclamoToString(reclamo));
                        }
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


    protected void makeCenterView(String[] items) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
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
            btnReclamo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mostrarToast(item);
                }
            });
            line.addView(btnReclamo);
            linearLayout.addView(line);
        }
        ScrollViewReclamos.addView(linearLayout);
    }

    private String reclamoToString(Reclamo reclamo) {
        return reclamo.getId_reclamo()+"-"+reclamo.getEspecialidad().getDescripcion()+"-"+reclamo.getDescripcion();
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
                intent = new Intent(this, HistorialReclamos1.class);
                intent.putExtra("user",user);
                startActivity(intent);
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