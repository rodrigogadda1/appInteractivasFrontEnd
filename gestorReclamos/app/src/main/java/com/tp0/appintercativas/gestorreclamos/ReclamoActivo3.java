package com.tp0.appintercativas.gestorreclamos;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.tp0.appintercativas.gestorreclamos.UserManagement.Auxiliares.GeneradorEstadosObjects;
import com.tp0.appintercativas.gestorreclamos.UserManagement.Auxiliares.MetodosDeVerificacion;
import com.tp0.appintercativas.gestorreclamos.UserManagement.Controller.Controller;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Especialidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Estado;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Foto;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Reclamo;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.EspecialidadService;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.ReclamoService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReclamoActivo3 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{
    Boolean seAgreganFotos;
    User user;
    Reclamo reclamo;
    Button btnEditRecActGuardar;
    ImageView imgPpalRecAct3,imgEditRecAct1,imgEditRecAct2,imgEditRecAct3,imgEditRecAct4,imgEditRecActBack,imgEditRecActExit,imvRecFiles;
    TextView txtPpalRecAct3,txtEditRecAct,txtEditRecActEspecialidad,txtEditRecActAgrupado,txtEditRecActEstados;
    ScrollView scvEditReclamoTexto,scvEditRecActListImagenes;
    Spinner spnEditRecActEspecialidades,spnEditRecActReclamosAg,spnEditRecActEstados;
    EditText edtTxtEditRecActivoAgrupadosTexto;
    ArrayList<Especialidad> especialidades;
    private static final int GALLERY_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 200;
    //para la slide bar
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamo_activo3);



        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        reclamo = (Reclamo) intent.getSerializableExtra("reclamo");
        seAgreganFotos = false;

        btnEditRecActGuardar = (Button) findViewById(R.id.btnEditRecActGuardar);

        imgPpalRecAct3 = (ImageView) findViewById(R.id.imgPpalRecAct3);
        imgEditRecAct1 = (ImageView) findViewById(R.id.imgEditRecAct1);
        imgEditRecAct2 = (ImageView) findViewById(R.id.imgEditRecAct2);
        imgEditRecAct3 = (ImageView) findViewById(R.id.imgEditRecAct3);
        imgEditRecAct4 = (ImageView) findViewById(R.id.imgEditRecAct4);
        imgEditRecActBack = (ImageView) findViewById(R.id.imgEditRecActBack);
        imgEditRecActExit = (ImageView) findViewById(R.id.imgEditRecActExit);
        imvRecFiles =  (ImageView) findViewById(R.id.imvRecFiles);

        txtPpalRecAct3 = (TextView) findViewById(R.id.txtPpalRecAct3);
        txtEditRecAct = (TextView) findViewById(R.id.txtEditRecAct);
        txtEditRecActEspecialidad = (TextView) findViewById(R.id.txtEditRecActEspecialidad);
        txtEditRecActEstados = (TextView) findViewById(R.id.txtEditRecActEstados);

        scvEditReclamoTexto = (ScrollView) findViewById(R.id.scvEditReclamoTexto);

        spnEditRecActEspecialidades = (Spinner) findViewById(R.id.spnEditRecActEspecialidades);
        spnEditRecActEstados = (Spinner) findViewById(R.id.spnEditRecActEstados);

        edtTxtEditRecActivoAgrupadosTexto = (EditText) findViewById(R.id.edtTxtEditRecActivoAgrupadosTexto);

        agregarEstadosPosibles();
        agregarEspecialidades();

        //text de administrado reclamo
        String texto = "";
        if (reclamo.getUsername() != ""){
            texto+="Usuario: "+reclamo.getUsername()+ "\n";
        }
        texto+="Edificio: "+reclamo.getEdificio().getNombre()+ "\n";
        if (reclamo.getEspacioComun() != null) {
            texto+="Espacio comun: "+reclamo.getEspacioComun().getNombre()+ "\n";
        }
        if (reclamo.getUnidad() != null) {
            texto+="Piso: "+reclamo.getUnidad().getPiso()+" Unidad: "+reclamo.getUnidad().getUnidad()+ "\n";
        }
        if (reclamo.getDescripcion() != null){
            texto+="Descripcion reclamo: "+reclamo.getDescripcion();
        }
        txtEditRecAct.setText(texto);


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
                                                GoBack();
                                            }
                                        }
        );
        imgEditRecActExit.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {
                                                     //aca se escribe que hacer
                                                     GoPantallaPrincipal();
                                                 }
                                             }
        );

        imvRecFiles.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               //aca se escribe que hacer
                                               OpenGallery();
                                           }
                                       }
        );

        btnEditRecActGuardar.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        //aca se escribe que hacer
                                                        int position_especialidad = spnEditRecActEspecialidades.getSelectedItemPosition();
                                                        Especialidad newEspecialidad = especialidades.get(position_especialidad);
                                                        newEspecialidad.setInspectores(null);
                                                        if (newEspecialidad.getId_especialidad() != reclamo.getEspecialidad().getId_especialidad()) {
                                                            reclamo.setEspecialidad(newEspecialidad);
                                                            seAgreganFotos = true;
                                                        }

                                                        int position_estado = spnEditRecActEstados.getSelectedItemPosition();
                                                        /*
                                                        if (reclamo.getEstado().getDescripcion().toLowerCase().equals("abierto")) {
                                                            spinners.add("Abierto");
                                                        }
                                                        spinners.add("En reparacion");
                                                        spinners.add("Cancelado");
                                                        spinners.add("Inspeccionando");
                                                         */

                                                        Estado newEstado = GeneradorEstadosObjects.getEstadoObject(spnEditRecActEstados.getSelectedItem().toString());

                                                        if (reclamo.getEstado().getDescripcion().toLowerCase().equals("abierto")) {
                                                            if (position_estado != 0) {
                                                                reclamo.setEstado(newEstado);
                                                                seAgreganFotos = true;
                                                            }
                                                        } else {
                                                            if (position_estado != 2) {
                                                                reclamo.setEstado(newEstado);
                                                                seAgreganFotos = true;
                                                            }
                                                        }

                                                        if (  (seAgreganFotos)  && (edtTxtEditRecActivoAgrupadosTexto.getText().toString().equals("")) ) {
                                                            seAgreganFotos = false;
                                                        } else {
                                                            reclamo.setRespuesta_inspector(edtTxtEditRecActivoAgrupadosTexto.getText().toString());
                                                        }

                                                        //seAgreganFotos se utiliza para saber si se guarda o no un reclamo
                                                        if ( seAgreganFotos) {
                                                            reclamo.setFecha(null);
                                                            guardarReclamo();
                                                            GoBack();
                                                        }

                                                    }
                                                }
        );


    }

    private void GoBack(){
        Intent intent = new Intent(this, ReclamoActivo1.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    private void guardarReclamo(){
        Retrofit retrofit = Controller.ConfiguracionIP();
        ReclamoService rs = retrofit.create(ReclamoService.class);
        Call<Reclamo> call= rs.updateReclamo(reclamo.getId_reclamo(),reclamo);

        call.enqueue(new Callback<Reclamo>() {
            @Override
            public void onResponse(Call<Reclamo> call, Response<Reclamo> response) {
                mostrarToast("Reclamo actualizado.");
            }

            @Override
            public void onFailure(Call<Reclamo> call, Throwable t) {
                mostrarDialogo("error en la ejecucion lina 232",t.getMessage());
            }
        });
    }

    private void OpenGallery () {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select picture"), GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            try {
                //mostrarDialogo("probando 230 creacion reclamo2","funciona");
                Uri selectedImage = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                List<Foto> reclamoFotos = reclamo.getFotos();
                if (reclamoFotos == null) {
                    reclamoFotos = new ArrayList<Foto>();
                }
                Foto fotoNew = new Foto();
                if (  (selectedImage!=null) ) {
                    String[] projection = {MediaStore.Images.Media.DATA};
                    Cursor cursor = managedQuery(selectedImage,projection,null,null,null);
                    if (cursor!=null){
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        cursor.moveToFirst();
                        cursor.getString(column_index);
                    }
                    fotoNew.setFoto(MetodosDeVerificacion.getEncoded64ImageStringFromBitmap(BitmapFactory.decodeStream(imageStream)));
                    //selectedImage.getPath()
                }
                reclamoFotos.add(fotoNew);
                seAgreganFotos = true;
                //BitmapFactory.decodeStream(imageStream)  --> imagen
                //ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                //ClipData clip = ClipData.newPlainText("label",String.valueOf(reclamo.getFotos().size()));
                //clipboard.setPrimaryClip(clip);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

    }

    private void agregarEstadosPosibles(){
        ArrayList<String> spinners = new ArrayList<String>();
        if (reclamo.getEstado().getDescripcion().toLowerCase().equals("abierto")) {
            spinners.add("Abierto");
        }
        spinners.add("En Reparacion");
        spinners.add("Cancelado");
        spinners.add("Inspeccionando");
        String[] stringsEstados = new String[spinners.size()];
        stringsEstados = spinners.toArray(stringsEstados);
        ArrayAdapter<String> adapterEspecialidades = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, stringsEstados);
        spnEditRecActEstados.setAdapter(adapterEspecialidades);
        if (reclamo.getEstado().getDescripcion().toLowerCase().equals("abierto")) {
            spnEditRecActEstados.setSelection(0);
        } else {
            spnEditRecActEstados.setSelection(2);
        }
    }


    private void agregarEspecialidades(){
        Retrofit retrofit = Controller.ConfiguracionIP();
        EspecialidadService es = retrofit.create(EspecialidadService.class);
        Call<List<Especialidad>> call= es.getEspecialidades();

        especialidades = new ArrayList<Especialidad>();


        call.enqueue(new Callback<List<Especialidad>>() {
            @Override
            public void onResponse(Call<List<Especialidad>> call, Response<List<Especialidad>> response) {
                if ( (response.isSuccessful()) && (response.body().size() > 0) ) {
                    int seleccionado = -1;
                    ArrayList<String> spinners = new ArrayList<String>();
                    for (int i = 0; i < response.body().size(); i++){
                        Especialidad especialidad = response.body().get(i);
                        if (especialidad.getId_especialidad() == reclamo.getEspecialidad().getId_especialidad()){
                            seleccionado = i;
                        }
                        especialidades.add(especialidad);
                        spinners.add(especialidad.getNombre());
                    }
                    spinnerEspecialidades(spinners);
                    spnEditRecActEspecialidades.setSelection(seleccionado);
                }
            }

            @Override
            public void onFailure(Call<List<Especialidad>> call, Throwable t) {
                mostrarDialogo("error", t.getMessage());
            }
        });

    }

    private void spinnerEspecialidades (ArrayList<String> especialidades){
        String[] stringsEspecialidades = new String[especialidades.size()];
        stringsEspecialidades = especialidades.toArray(stringsEspecialidades);
        ArrayAdapter<String> adapterEspecialidades = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, stringsEspecialidades);
        spnEditRecActEspecialidades.setAdapter(adapterEspecialidades);
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
