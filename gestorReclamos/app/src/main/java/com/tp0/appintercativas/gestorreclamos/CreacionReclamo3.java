package com.tp0.appintercativas.gestorreclamos;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Reclamo;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.ReclamoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreacionReclamo3 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{

    User user;
    Administrado administrado;
    Reclamo reclamo;

    ImageView back, exit,next;
    TextView txtReclamoConfirmado,txtVisualizaReclamo;
    //para la slide bar
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_reclamo3);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        reclamo = (Reclamo) intent.getSerializableExtra("reclamo");
        administrado = (Administrado)  intent.getSerializableExtra("administrado");

        back = (ImageView) findViewById(R.id.btnBackHisto1);
        exit = (ImageView) findViewById(R.id.btnExitHisto1);
        next = (ImageView) findViewById(R.id.btnNextHisto1);

        txtReclamoConfirmado = (TextView) findViewById(R.id.txtReclamoConfirmado);
        txtVisualizaReclamo = (TextView) findViewById(R.id.txtVisualizaReclamo);

        String detalle = null;
        detalle = "Especialidad :"+reclamo.getEspecialidad().getNombre()+"\n";
        detalle+= "Edificio :"+reclamo.getEdificio().getNombre()+"\n";
        if (reclamo.getUnidad() != null) {
            detalle+= "Unidad: Piso "+reclamo.getUnidad().getPiso()+" Unidad "+reclamo.getUnidad().getUnidad()+"\n";
        } else if (reclamo.getEspacioComun() != null) {
            detalle+= "Espacio Comun: "+reclamo.getEspacioComun().getNombre()+"\n";
        }
        detalle+= "\n"+"Descripcion: "+reclamo.getDescripcion()+"\n";
        txtVisualizaReclamo.setText(detalle);

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

        back.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           GoBack();
                                       }
                                   }
        );

        exit.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        GoPantallaPrincipal();
                                                    }
                                                }
        );

        next.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                        if (testearConnection().equals("NotConnected")){
                            mostrarToast("No hay conexion");
                            //aca se manda a insertar hasta tener wifi
                        } else if (testearConnection().equals("DataMobile")){
                            if (user.isDatos_moviles()) {
                                //mostrarToast("se manda a crear");
                                CrearReclamo();
                            } else {
                                mostrarToast("No tenes habilitado usar datos moviles.");
                                //aca se manda a insertar hasta tener wifi
                            }
                        } else {
                            CrearReclamo();
                            //mostrarToast("se manda a crear");
                        }
                   }
           }
        );
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

    private void CrearReclamo(){
        try {
            Retrofit retrofit = Controller.ConfiguracionIP();
            ReclamoService rs = retrofit.create(ReclamoService.class);
            Call<Reclamo> call= rs.createReclamo(reclamo);

            //METODO PARA COPY TO CLIPBOARD
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label",reclamo.toString());
            clipboard.setPrimaryClip(clip);
            //FIN METODO COPY TO CLIPBOARD

            mostrarDialogo("probando 123",reclamo.toString());
            call.enqueue(new Callback<Reclamo>() {
                @Override
                public void onResponse(Call<Reclamo> call, Response<Reclamo> response) {
                    //mostrarDialogo("probando",response.body().toString());
                    //if (  response.body() != null ) {
                        //mostrarDialogo("probando",response.body().toString());
                    //}
                }

                @Override
                public void onFailure(Call<Reclamo> call, Throwable t) {
                    mostrarDialogo("error", t.getMessage());
                }
            });
        } catch (Exception e){
            mostrarDialogo("error",e.getMessage());
        }

    }

    private void GoPantallaPrincipal(){
        Intent intent = new Intent(this, PantallaPrincipal.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    private void GoBack(){
        Intent intent = new Intent(this, CreacionReclamo2.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    private void mostrarToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void mostrarDialogo(String titulo,String mensaje){
        new AlertDialog.Builder( this)
                .setTitle(titulo)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setMessage(mensaje)
                .show();
    }

    //para la slide bar

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
                Toast.makeText(this, "Ya estas en esa pantalla!", Toast.LENGTH_SHORT).show();
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
