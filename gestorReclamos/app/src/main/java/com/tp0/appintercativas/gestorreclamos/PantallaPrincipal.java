package com.tp0.appintercativas.gestorreclamos;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.tp0.appintercativas.gestorreclamos.UserManagement.Controller.Controller;
import com.tp0.appintercativas.gestorreclamos.UserManagement.SQLite.Reclamo_SQLLite;
import com.tp0.appintercativas.gestorreclamos.UserManagement.SQLite.ReclamosHelper;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Administrado;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Edificio;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.EspacioComun;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Especialidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Estado;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Foto;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Inspector;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.InspectorEdificio;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.InspectorEspecialidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Reclamo;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Unidad;
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

public class PantallaPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{

    NotificationManager notificationManagerAux;
    private NotificationManagerCompat notificationManager;

    ReclamosHelper reclamosHelper;
    Administrado administrado;

    private ImageView principal_img;
    private TextView txtNotificacionesPpal,principaltexto2;
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
        principal_img = (ImageView) findViewById(R.id.imgvPpal);

        txtNotificacionesPpal = (TextView) findViewById(R.id.txtNotificacionesPpal);
        principaltexto2 = (TextView) findViewById(R.id.txtListaUsuarios);

        ScrollViewReclamos = (ScrollView) findViewById(R.id.ScrollViewReclamos);

        //pongo los ultimos 5 reclamos a la vista
        getReclamosFilteredByUserIdStatus();


        /* FIN CODIGO PARA SCROLLVIEW RECLAMOS  */

        btnNotificaciones = (Button) findViewById(R.id.btnNotificaciones);
        btnHistorialReclamos = (Button) findViewById(R.id.btnHistorialReclamos);
        btnReclamosActivos = (Button) findViewById(R.id.btnReclamosActivos);
        btnReclamoNuevo = (Button) findViewById(R.id.btnReclamoNuevo);

        //para las notifications
        String message = "Nueva notificacion, dirigite a notificacion para ver mas detalle.";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                PantallaPrincipal.this
        )
            .setSmallIcon(R.drawable.ic_message)
            .setContentTitle("Nueva notificacion")
            .setContentText(message)
            .setAutoCancel(true);
        Intent intentNotification = new Intent(this, NotificationActivity.class);
        intentNotification.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intentNotification.putExtra("message", message);
        PendingIntent pendingIntent = PendingIntent.getActivity(PantallaPrincipal.this,0, intentNotification,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());

        //se revisa si hay reclamos pendientes para subir
        if (user.getTipoUser().toLowerCase().equals("administrado")) {
            //pruebo que tenga la conexion para poder subir los reclamos
            if (    testearConnection().equals("DataWifi") ||  ( testearConnection().equals("DataMobile") && (user.isDatos_moviles()) )   ) {
                getAdministradoId();
            }
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
                GoToNotificaciones();
            }
        });

        btnHistorialReclamos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //aca va que hace
                GoToViewReclamosHist();
            }
        });

        btnReclamosActivos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //aca va que hace
                GoToReclamosActivos ();
            }
        });

        btnReclamoNuevo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GoToNewReclamo();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //se revisa si hay reclamos pendientes para subir
        if (user.getTipoUser().toLowerCase().equals("administrado")) {
            //pruebo que tenga la conexion para poder subir los reclamos
            if (    testearConnection().equals("DataWifi") ||  ( testearConnection().equals("DataMobile") && (user.isDatos_moviles()) )   ) {
                getAdministradoId();
            }

        } else if (user.getTipoUser().toLowerCase().equals("inspector")){

        }
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


    public void buscarReclamosAdministrado (Administrado administrado){
        reclamosHelper = new ReclamosHelper(this);
        List<Reclamo_SQLLite> reclamitos = reclamosHelper.getReclamosSQLiteByAdminitradoId(administrado.getId_administrado());
        if (reclamitos.size() > 0) {
            CrearReclamosIterativo(reclamitos,reclamosHelper);
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
    private void CrearReclamosIterativo(final List<Reclamo_SQLLite> reclamitos, final ReclamosHelper reclamosHelper){
        try {
            for (int i = 0; i < reclamitos.size(); i++) {
                final Reclamo_SQLLite reclamitoPos = reclamitos.get(i);
                Reclamo reclamo = pasarDeReclamitoAReclamo(reclamitoPos);

                Retrofit retrofit = Controller.ConfiguracionIP();
                ReclamoService rs = retrofit.create(ReclamoService.class);
                Call<Reclamo> call= rs.createReclamo(reclamo);

                call.enqueue(new Callback<Reclamo>() {
                    @Override
                    public void onResponse(Call<Reclamo> call, Response<Reclamo> response) {
                        if (response.isSuccessful()){
                            reclamosHelper.deleteRowById(reclamitoPos.getId_reclamo());
                        }
                    }

                    @Override
                    public void onFailure(Call<Reclamo> call, Throwable t) {
                        mostrarDialogo("error", t.getMessage());
                    }
                });
            }

        } catch (Exception e){
            mostrarDialogo("error en linea 263 pantalla principal",e.getMessage());
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
        if ( (reclamitoPos.getFotos() != null) && (reclamitoPos.getFotos().size() > 0)){
            List<Foto> fotos = new ArrayList<Foto>();
            for (int i = 0; i < reclamitoPos.getFotos().size(); i++) {
                Foto foto = new Foto();
                foto.setFoto(reclamitoPos.getFotos().get(i));
                fotos.add(foto);
            }
            salida.setFotos(fotos);
        }

        return salida;
    }

    private void getReclamosFilteredByUserIdStatus(){
        Retrofit retrofit = Controller.ConfiguracionIP();
        ReclamoService rs = retrofit.create(ReclamoService.class);
        Call<List<Reclamo>> call = null;

        if (user.getTipoUser().toLowerCase().equals("administrado")) {
            call = rs.getReclamosByUserIdAndStatusId(String.valueOf(user.getId()),"1","","");
        } else if (user.getTipoUser().toLowerCase().equals("inspector")){
            getInspector();
        } else {
            //caso administrador
            call = rs.getReclamosByUserIdAndStatusId("","1","","");
        }
        if (!user.getTipoUser().toLowerCase().equals("inspector")){
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
        Call<List<Reclamo>> call = rs.getReclamosByUserIdAndStatusId("","1",lista_edificios,lista_especialidades);

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
                    //aca se tiene que pasar al detalle
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
    private void GoToAdministracionUsuarios () {
        Toast.makeText(this, "Administracion de Usuarios selected", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, adminuserPrincipal.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

}