package com.tp0.appintercativas.gestorreclamos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.tp0.appintercativas.gestorreclamos.UserManagement.Controller.Controller;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Administrado;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.AdministradoUnidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Edificio;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.EspacioComun;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Reclamo;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Unidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.AdministradoService;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.EdificioService;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.EspacioComunService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreacionReclamo1 extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{

    ImageView exit, back, next;
    Spinner listaedificiouser, listaespacios;
    User user;
    Administrado administrado;

    ArrayList<Edificio> edificios;
    ArrayList<Unidad> unidades;
    ArrayList<Long> id_espacios_comunes;

    //para la slide bar
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_reclamo1);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        exit = (ImageView) findViewById(R.id.btnExitRec1);
        back = (ImageView) findViewById(R.id.btnBackRec1);
        next = (ImageView) findViewById(R.id.btnNextRec1);

        listaedificiouser = (Spinner) findViewById(R.id.spnLstEdificioUser);
        returnAdministrado("11edificios11");

        listaespacios = (Spinner) findViewById(R.id.spnLstEspacios);
        listaespacios.setEnabled(false);

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

        listaedificiouser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mostrarToast(listaedificiouser.getSelectedItem().toString());
                if(!listaedificiouser.getSelectedItem().toString().equals("Seleccionar edificio") ){
                    returnAdministrado(listaedificiouser.getSelectedItem().toString());
                } else {
                    seleccionarSoloUnidadEspacio();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoPantallaPrincipal();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoPantallaPrincipal();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasar_a_creacion_reclamo_2();
            }
        });
    }

    private void pasar_a_creacion_reclamo_2(){
        String opcionEdificio = listaedificiouser.getSelectedItem().toString();
        String opcionListaEdificio = listaespacios.getSelectedItem().toString();
        if (!( (opcionEdificio.equals("Seleccionar edificio")) || (opcionListaEdificio.equals("Seleccionar")) || (opcionListaEdificio.equals("Seleccionar edificio first")) )) {
            Reclamo reclamo = new Reclamo();

            //listaedificiouser.getSelectedItemPosition()        -->   getSelectedItemPosition()    arranca a 0
            reclamo.setEdificio(edificios.get(listaedificiouser.getSelectedItemPosition()-1));


            //"Piso:"+unidad.getPiso()+"-Unidad:"+unidad.getUnidad()
            if (opcionListaEdificio.contains("Piso")){


                reclamo.setUnidad(unidades.get(listaespacios.getSelectedItemPosition()-1));

                /*try{
                    mostrarDialogo("probando esta linea",reclamo.toStringPersonalizado());
                } catch (Exception e){
                    mostrarDialogo("error",e.getMessage());
                }*/

                pasar_a_reclamo_2(reclamo);
            } else {
                //mostrarDialogo("probando 157", String.valueOf(id_espacios_comunes.get(listaespacios.getSelectedItemPosition()-unidades.size()-1)));
                pasar_a_creacion_reclamo_2_espacio_comun(reclamo,id_espacios_comunes.get(listaespacios.getSelectedItemPosition()-unidades.size()-1));
            }

        }
    }

    private void pasar_a_creacion_reclamo_2_espacio_comun (final Reclamo reclamo, long id){
        Retrofit retrofit = Controller.ConfiguracionIP();
        EspacioComunService es = retrofit.create(EspacioComunService.class);
        Call<EspacioComun> call = es.findBYId(id);

        call.enqueue(new Callback<EspacioComun>() {
            @Override
            public void onResponse(Call<EspacioComun> call, Response<EspacioComun> response) {
                if (response.body().getId_espaciocomun() != 0) {
                    Reclamo reclamo2 = reclamo;
                    reclamo2.setEspacioComun(response.body());

                    /*try{
                        mostrarDialogo("probando", reclamo2.toString());
                    } catch (Exception e){
                        mostrarDialogo("error",e.getMessage());
                    }*/

                    pasar_a_reclamo_2(reclamo2);

                } else {
                    mostrarDialogo("Error", "No se encontro al espacio comun." );
                }
            }

            @Override
            public void onFailure(Call<EspacioComun> call, Throwable t) {
                mostrarDialogo("Error", t.getMessage() );
            }
        });
    }

    private Edificio getEdificioPorNombre(String opcionEdificio){
        Edificio edificioSalida = null;
        for (int i = 0; i < edificios.size(); i++){
            Edificio edificio = edificios.get(i);
            if (edificio.getNombre().equals(opcionEdificio)) {
                edificioSalida = edificio;
            }
        }
        return edificioSalida;
    }

    private void pasar_a_reclamo_2 (Reclamo reclamo){
        Intent intent = new Intent(this, CreacionReclamo2.class);
        intent.putExtra("user",user);
        intent.putExtra("reclamo", reclamo);
        intent.putExtra("administrado",administrado);
        startActivity(intent);
    }

    private void seleccionarSoloUnidadEspacio (){
        String []  soloSeleccionar={"Seleccionar edificio first"};
        ArrayAdapter<String> adapterSoloSeleccionar = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, soloSeleccionar);
        listaespacios.setAdapter(adapterSoloSeleccionar);
        listaespacios.setEnabled(false);
    }

    private void returnAdministrado(final String opcion){

        Retrofit retrofit = Controller.ConfiguracionIP();
        AdministradoService as = retrofit.create(AdministradoService.class);
        Call<Administrado> call = as.getAdministradoId((long) user.getId());

        call.enqueue(new Callback<Administrado>() {

            @Override
            public void onResponse(Call<Administrado> call, Response<Administrado> response) {
                administrado = response.body();
                if ( administrado.getAdministradoUnidades().size() > 0 ) {
                    if (opcion.toLowerCase().equals("11edificios11")) {
                        getEdificiosFromAdministrado(administrado);
                    } else {
                        getUnidadesEspaciosComunes(administrado, opcion);
                    }
                } else {
                    mostrarDialogo("Error", "No se encontro al administrado." );
                }
            }

            @Override
            public void onFailure(Call<Administrado> call, Throwable t) {
                mostrarDialogo("Error", "Error en la ejecucion " + t.getMessage());
            }
        });
    }

    private void getUnidadesEspaciosComunes (Administrado administrado2, String edificioParam){
        ArrayList<String> listaUnidadesEspaciosComunes = new ArrayList<>();
        listaUnidadesEspaciosComunes.add("Seleccionar");

        //mostrarDialogo("probando", edificioParam); este lo muestra bien
        long id_edificio = getEdificioPorNombre(edificioParam).getId_edificio();

        unidades = new ArrayList<Unidad>();

        for (int i = 0; i < administrado2.getAdministradoUnidades().size(); i++) {
            AdministradoUnidad adminUnidad = administrado2.getAdministradoUnidades().get(i);
            Unidad unidad = adminUnidad.getUnidad();
            Edificio edificio = unidad.getEdificio();
            if ( edificio.getId_edificio() ==  id_edificio) {
                unidades.add(unidad);
                listaUnidadesEspaciosComunes.add("Piso:"+unidad.getPiso()+"-Unidad:"+unidad.getUnidad());
            }
        }

        //mostrarDialogo("probando", "llega hasta aca::"+listaUnidadesEspaciosComunes.toString());
        agregarEspaciosComunes(listaUnidadesEspaciosComunes, id_edificio);

    }

    private void agregarEspaciosComunes (final ArrayList<String> listaEspacioComunUnidades, long id_edificio){

        Retrofit retrofit = Controller.ConfiguracionIP();
        EdificioService es = retrofit.create(EdificioService.class);
        Call<Edificio> call = es.findBYId(id_edificio);
        //mostrarDialogo("probando", "llega hasta aca 1");

        call.enqueue(new Callback<Edificio>() {
            @Override
            public void onResponse(Call<Edificio> call, Response<Edificio> response) {
                id_espacios_comunes = new ArrayList<Long>();
                //mostrarDialogo("probando", "llega hasta aca exito");
                ArrayList<String> listaEspacioComunUnidades2 = listaEspacioComunUnidades;

                Edificio edificio = response.body();
                if ( edificio.getNombre() != null ) {
                    List<EspacioComun> espacioComunes = edificio.getEspaciosComunes();

                    for (int i = 0; i < espacioComunes.size(); i++){
                        EspacioComun espacioComun= espacioComunes.get(i);
                        listaEspacioComunUnidades2.add(espacioComun.getNombre());
                        id_espacios_comunes.add(espacioComun.getId_espaciocomun());
                    }

                    /*try {
                        mostrarDialogo("probando linea 300", id_espacios_comunes.toString());
                    } catch (Exception e) {
                        mostrarDialogo("error linea 300",e.getMessage());
                    }*/


                } else {
                    mostrarDialogo("Error", "No se encontro al edificio." );
                }


                listaespacios.setEnabled(true);
                if (listaEspacioComunUnidades2.size() > 1){
                    String[] salida = new String[listaEspacioComunUnidades2.size()];
                    salida = listaEspacioComunUnidades2.toArray(salida);
                    ActualizarSpinnerUnidadesEspacioComunes(salida);
                }
            }

            @Override
            public void onFailure(Call<Edificio> call, Throwable t) {
                //mostrarDialogo("probando", "llega hasta aca fracaso");
                mostrarDialogo("Error", "Error en la ejecucion " + t.getMessage());
            }
        });

    }

    private void ActualizarSpinnerUnidadesEspacioComunes(String[] salida){
        ArrayAdapter<String> adapterEspacioComunUnidades = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, salida);
        listaespacios.setAdapter(adapterEspacioComunUnidades);
    }

    private void getEdificiosFromAdministrado (Administrado administrado2){
        ArrayList<String> listEdificios = new ArrayList<>();
        listEdificios.add("Seleccionar edificio");
        edificios =  new ArrayList<Edificio>();

        for (int i = 0; i < administrado2.getAdministradoUnidades().size(); i++) {
            AdministradoUnidad adminUnidad = administrado2.getAdministradoUnidades().get(i);
            Edificio edificio = adminUnidad.getUnidad().getEdificio();
            edificios.add(edificio);
            listEdificios.add(edificio.getNombre());
        }

        if (listEdificios.size() > 1){
            String[] salida = new String[listEdificios.size()];
            salida = listEdificios.toArray(salida);
            ArrayAdapter<String> adapterEdificiosUser = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, salida);
            listaedificiouser.setAdapter(adapterEdificiosUser);
        }

    }

    private void GoPantallaPrincipal(){
        Intent intent = new Intent(this, PantallaPrincipal.class);
        intent.putExtra("user",user);
        startActivity(intent);
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