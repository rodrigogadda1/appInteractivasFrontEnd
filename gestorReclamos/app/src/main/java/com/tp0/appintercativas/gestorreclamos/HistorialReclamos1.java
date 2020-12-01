package com.tp0.appintercativas.gestorreclamos;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.android.material.stateful.ExtendableSavedState;
import com.tp0.appintercativas.gestorreclamos.UserManagement.Controller.Controller;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Administrado;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.AdministradoUnidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Edificio;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.EspacioComun;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Especialidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Estado;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Inspector;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Unidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.AdministradoService;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.EdificioService;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.InspectorService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HistorialReclamos1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{

    //para spinners
    Spinner spnListaedificios,spnListaunidades,spnListaespecialidades,spnListaestado;
    CheckBox chklistaedificios,chklistaunidades,chklistaespecialidades,chklistaestado;
    User user;
    Administrado administrado;
    Inspector inspector;
    String [] estados;
    String [] estadosNotSelected;
    List<Estado> estadosList;
    String [] edificios;
    String [] edificiosNotSelected;
    List<Edificio> edificiosList;
    List<Edificio> AllEdificiosList;
    String [] unidades_espacios_comunes;
    String [] unidades_espacios_comunes_notSelected;
    List<Unidad> unidadesList;
    List<EspacioComun> espacioComunesList;
    String [] especialidades;
    String [] especialidadesNotSelected;
    List<Especialidad> especialidadesList;

    //para el resto
    ImageView imvPrincipal,nreclamo1_img2,btnBackHisto1,btnExitHisto1,btnNextHisto1;
    TextView txtPpal;
    Button  btnGuardarHisto1;

    //para la slide bar
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_reclamos1);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        imvPrincipal = (ImageView) findViewById(R.id.imvPrincipal);
        nreclamo1_img2 = (ImageView) findViewById(R.id.nreclamo1_img2);
        btnBackHisto1 = (ImageView) findViewById(R.id.btnBackRec1);
        btnExitHisto1 = (ImageView) findViewById(R.id.btnExitRec1);
        btnNextHisto1 = (ImageView) findViewById(R.id.btnNextRec1);

        txtPpal = (TextView) findViewById(R.id.txtPpal);

        btnGuardarHisto1 = (Button) findViewById(R.id.btnGuardarHisto1);

        spnListaedificios = (Spinner) findViewById(R.id.spnListaedificios);
        spnListaunidades = (Spinner) findViewById(R.id.spnListaunidades);
        spnListaespecialidades = (Spinner) findViewById(R.id.spnListaespecialidades);
        spnListaestado = (Spinner) findViewById(R.id.spnListaestado);

        chklistaedificios = (CheckBox) findViewById(R.id.chklistaedificios);
        chklistaunidades = (CheckBox) findViewById(R.id.chklistaunidades);
        chklistaespecialidades = (CheckBox) findViewById(R.id.chklistaespecialidades);
        chklistaestado = (CheckBox) findViewById(R.id.chklistaestado);

        //para los spinners
        estadosList = new ArrayList<Estado>();
        edificiosList = new ArrayList<Edificio>();
        AllEdificiosList = new ArrayList<Edificio>();
        unidadesList = new ArrayList<Unidad>();
        espacioComunesList = new ArrayList<EspacioComun>();
        especialidadesList = new ArrayList<Especialidad>();
        especialidadesNotSelected = new String[]{"Habilite la opcion"};
        unidades_espacios_comunes_notSelected = new String[]{"Habilite la opcion"};
        edificiosNotSelected = new String[]{"Habilite la opcion"};
        estadosNotSelected = new String[]{"Habilite la opcion"};

        getEdificios();
        if (user.getTipoUser().toLowerCase().equals("administrado")) {
            returnAdministrado();
            //getUnidadesEspaciosComunesAdministrado();
        }
        //else if (user.getTipoUser().toLowerCase().equals("inspector")) {
        //    getInspector();
        //}
        else {
            getEdificiosAdministrador();
            getUnidadesEspaciosComunesAdministrador();
        }
        //setearEdificiosSpinner(edificios);

            //para los edificios (creacionReclamo1 par administrado, pantallaPrincipal para inspector, y todos los edificios para adminitrador)
            //para las unidades/espacios comunes usar el edificio elegido, logica en CreacionReclamo1
            //especialidades estan en pantalla principal, usar objeto inspector, para administrador y administrado poner todos
            //estados todos para todos los perfiles
        //buscar action de checkbox que si se activa sea enabled true




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
        btnBackHisto1.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                    //aca se escribe que hacer
                    GoPantallaPrincipal();
                 }
        });

        btnExitHisto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aca se escribe que hacer
                GoPantallaPrincipal();
            }
        });

        btnGuardarHisto1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //aca se escribe que hacer
                                    }
                                }
        );

        nreclamo1_img2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //aca se escribe que hacer
                                    }
                                }
        );
    }

    private void getUnidadesEspaciosComunesAdministrador(){
            if (spnListaedificios.getSelectedItemPosition() == 0) {
                Edificio edificioSeleccionado = edificiosList.get(spnListaedificios.getSelectedItemPosition()-1);

                ArrayList<String> listUnidadesEspacioComunes = new ArrayList<>();
                listUnidadesEspacioComunes.add("Seleccionar");

                for (int i = 0; i < edificioSeleccionado.getUnidades().size(); i++) {
                    Unidad unidad = edificioSeleccionado.getUnidades().get(i);
                    unidadesList.add(unidad);
                    listUnidadesEspacioComunes.add(unidad.getPiso()+" "+unidad.getUnidad());
                }

                for (int i = 0; i < edificioSeleccionado.getEspaciosComunes().size(); i++) {
                    EspacioComun espacioComun = edificioSeleccionado.getEspaciosComunes().get(i);
                    espacioComunesList.add(espacioComun);
                    listUnidadesEspacioComunes.add(espacioComun.getNombre());
                }

                if (listUnidadesEspacioComunes.size() > 1){
                    unidades_espacios_comunes = new String[listUnidadesEspacioComunes.size()];
                    unidades_espacios_comunes = listUnidadesEspacioComunes.toArray(unidades_espacios_comunes);
                }

            }

        }

    private void getUnidadesEspaciosComunesAdministrado(){
        if (spnListaedificios.getSelectedItemPosition() != 0) {
            //no se selecciono edificio
            Edificio edificioSeleccionado = edificiosList.get(spnListaedificios.getSelectedItemPosition()-1);
            //spnListaedificios.getSelectedItemPosition()-1
            String ids=",";
            for (int i = 0; i < administrado.getAdministradoUnidades().size(); i++) {
                AdministradoUnidad administradoUnidad = administrado.getAdministradoUnidades().get(i);
                if ( (administradoUnidad.getRelacion().toLowerCase().contains("vividor"))  &&  (administradoUnidad.getUnidad().getEdificio().getId_edificio() == edificioSeleccionado.getId_edificio()) ){
                    ids+=","+String.valueOf(administradoUnidad.getUnidad().getId_unidad());
                }
            }

            ArrayList<String> listUnidadesEspacioComunes = new ArrayList<>();
            listUnidadesEspacioComunes.add("Seleccionar");

            //unidadesList        espacioComunesList      unidades_espacios_comunes
            for (int i = 0; i < edificioSeleccionado.getUnidades().size(); i++) {
                Unidad unidad = edificioSeleccionado.getUnidades().get(i);
                if (ids.contains(","+unidad.getId_unidad()+",")) {
                    listUnidadesEspacioComunes.add(unidad.getPiso()+" "+unidad.getUnidad());
                    unidadesList.add(unidad);
                }
            }

            for (int i = 0; i < edificioSeleccionado.getEspaciosComunes().size(); i++) {
                EspacioComun espacioComun = edificioSeleccionado.getEspaciosComunes().get(i);
                espacioComunesList.add(espacioComun);
                listUnidadesEspacioComunes.add(espacioComun.getNombre());
            }

            if (listUnidadesEspacioComunes.size() > 1){
                unidades_espacios_comunes = new String[listUnidadesEspacioComunes.size()];
                unidades_espacios_comunes = listUnidadesEspacioComunes.toArray(unidades_espacios_comunes);
            }

            setearUnidadesEspaciosComunesSpinner(unidades_espacios_comunes);

        } else {
            //no se selecciono edificio
            setearUnidadesEspaciosComunesSpinner(unidades_espacios_comunes_notSelected);
        }

    }

    private void returnAdministrado(){

        Retrofit retrofit = Controller.ConfiguracionIP();
        AdministradoService as = retrofit.create(AdministradoService.class);
        Call<Administrado> call = as.getAdministradoId((long) user.getId());

        call.enqueue(new Callback<Administrado>() {

            @Override
            public void onResponse(Call<Administrado> call, Response<Administrado> response) {
                administrado = response.body();
                if ( administrado.getAdministradoUnidades().size() > 0 ) {
                    getEdificiosFromAdministrado(administrado);
                }
            }

            @Override
            public void onFailure(Call<Administrado> call, Throwable t) {
                mostrarDialogo("Error", "Error en la ejecucion " + t.getMessage());
            }
        });
    }

    private void getEdificiosFromAdministrado (Administrado administrado2){
        ArrayList<String> listEdificios = new ArrayList<>();
        listEdificios.add("Seleccionar edificio");

        for (int i = 0; i < administrado2.getAdministradoUnidades().size(); i++) {
            AdministradoUnidad adminUnidad = administrado2.getAdministradoUnidades().get(i);
            Edificio edificio = adminUnidad.getUnidad().getEdificio();
            edificiosList.add(edificio);
            listEdificios.add(edificio.getNombre());
        }

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label","probando 301 "+edificiosList.toString());
        clipboard.setPrimaryClip(clip);
        mostrarDialogo("probando 301", "se copio");

        if (listEdificios.size() > 1){
            edificios = new String[listEdificios.size()];
            edificios = listEdificios.toArray(edificios);
        }

    }

    private void getInspector (){
        Retrofit retrofit = Controller.ConfiguracionIP();
        InspectorService is = retrofit.create(InspectorService.class);
        Call<Inspector> call = is.getInspectorId((long) user.getId());
        call.enqueue(new Callback<Inspector>() {
            @Override
            public void onResponse(Call<Inspector> call, Response<Inspector> response) {
                if (response.isSuccessful()){
                    ArrayList<String> listEdificios = new ArrayList<>();
                    ArrayList<String> listEspecialiadades = new ArrayList<>();
                    listEdificios.add("Seleccionar edificio");
                    listEspecialiadades.add("Seleccionar especialidad");

                    inspector = response.body();

                    if ( (inspector.getEdificios().size() > 0) && (inspector.getEspecialidades().size() > 0)){

                        for (int i = 0; i < inspector.getEdificios().size(); i++) {
                            Edificio edificio = inspector.getEdificios().get(i);
                            edificiosList.add(edificio);
                            listEdificios.add(edificio.getNombre());
                        }

                        for (int j = 0; j < inspector.getEspecialidades().size(); j++) {
                            Especialidad especialidad = inspector.getEspecialidades().get(j);
                            especialidadesList.add(especialidad);
                            listEspecialiadades.add(especialidad.getNombre());
                        }

                        if (listEdificios.size() > 1){
                            edificios = new String[listEdificios.size()];
                            edificios = listEdificios.toArray(edificios);
                        }

                        if (listEspecialiadades.size() > 1){
                            especialidades = new String[listEspecialiadades.size()];
                            especialidades = listEspecialiadades.toArray(especialidades);
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<Inspector> call, Throwable t) {
                mostrarDialogo("Error", "Error en la ejecucion "+t.getMessage());
            }
        });
    }

    private void getEdificios (){
        Retrofit retrofit = Controller.ConfiguracionIP();
        EdificioService es = retrofit.create(EdificioService.class);
        Call<List<Edificio>> call = es.getEdificios();

        call.enqueue(new Callback<List<Edificio>>() {
            @Override
            public void onResponse(Call<List<Edificio>> call, Response<List<Edificio>> response) {
                if (response.isSuccessful()){
                    AllEdificiosList = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Edificio>> call, Throwable t) {
                mostrarDialogo("Error", "Error en la ejecucion "+t.getMessage());
            }
        });
    }

    private void getEdificiosAdministrador(){

        ArrayList<String> listEdificios = new ArrayList<>();
        listEdificios.add("Seleccionar edificio");

        for (int i = 0; i < AllEdificiosList.size(); i++) {
            Edificio edificio = AllEdificiosList.get(i);
            listEdificios.add(edificio.getNombre());
            edificiosList.add(edificio);
        }

        if (listEdificios.size() > 1){
            edificios = new String[listEdificios.size()];
            edificios = listEdificios.toArray(edificios);
        }

    }

    private void setearEdificiosSpinner (String[] entrada){
        //CLIPBOARD
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label","probando 400 "+entrada.toString());
        clipboard.setPrimaryClip(clip);

        ArrayAdapter<String> adapterEdificiosUser = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, entrada);
        spnListaedificios.setAdapter(adapterEdificiosUser);
    }

    private void setearUnidadesEspaciosComunesSpinner (String[] entrada){
        ArrayAdapter<String> adapterUnidadesEspaciosComunesUser = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, entrada);
        spnListaedificios.setAdapter(adapterUnidadesEspaciosComunesUser);
    }

    private void setearEspecialidades (String[] entrada){
        ArrayAdapter<String> adapterEdificiosUser = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, entrada);
        spnListaespecialidades.setAdapter(adapterEdificiosUser);
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
                //GoToViewReclamosHist ();
                mostrarToast("Ya estas en el Hostorial de Reclamos");
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
}
