package com.tp0.appintercativas.gestorreclamos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.tp0.appintercativas.gestorreclamos.UserManagement.Auxiliares.GeneradorEstadosObjects;
import com.tp0.appintercativas.gestorreclamos.UserManagement.Auxiliares.MetodosDeVerificacion;
import com.tp0.appintercativas.gestorreclamos.UserManagement.Controller.Controller;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Administrado;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.AdministradoUnidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Especialidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Foto;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Reclamo;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Unidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.EspecialidadService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreacionReclamo2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{

    //for image view
    List<String> url_fotos;
    private static final int GALLERY_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 200;

    User user;
    Reclamo reclamo;
    Administrado administrado;

    TextView txtReclamoResumen, txtReclamoDetalle;
    Spinner spnEspecialidades;
    EditText editReclamoComentario;
    ImageView imgvCamara, imgvArchivos, btnBack, btnPantallaPrincipal, btnNext;

    ArrayList<Especialidad> especialidades;

    //para la slide bar
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_reclamo2);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        reclamo = (Reclamo) intent.getSerializableExtra("reclamo");
        administrado = (Administrado)  intent.getSerializableExtra("administrado");

        txtReclamoResumen = (TextView) findViewById(R.id.txtReclamoResumen);
        txtReclamoDetalle = (TextView) findViewById(R.id.txtReclamoDetalle);

        //aca va el codigo para poner resumen de la pantalla anterior --> txtReclamoResumen
        //Reclamo Número XXX\nSobre Edificio X\nEspacio/Unidad X\nDueño/Inquilino --> string a tener en cuenta
        String reclamoResumen = ""+"Edificio "+reclamo.getEdificio().getNombre()+"\n";

        if ( (reclamo.getUnidad() != null ) && (reclamo.getUnidad().getId_unidad() != 0) ) {
            try {
                reclamoResumen += "Unidad " + " Piso:" +reclamo.getUnidad().getPiso()+ " Unidad: "+ reclamo.getUnidad().getUnidad() + "\n";
                reclamoResumen += " Relacion " + getRelacion() ;
            } catch (Exception e){
                mostrarDialogo("error", e.getMessage());
            }
        } else {
            reclamoResumen+=" Espacio Comun "+reclamo.getEspacioComun().getNombre();
        }

        txtReclamoResumen.setText(reclamoResumen);
        //fin codigo resumen de la pantalla anterior

        spnEspecialidades = (Spinner) findViewById(R.id.spnEspecialidades);
        agregarEspecialidades();

        editReclamoComentario = (EditText) findViewById(R.id.editReclamoComentario);

        imgvCamara = (ImageView) findViewById(R.id.imgvCamara);
        imgvArchivos = (ImageView) findViewById(R.id.imgvArchivos);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnPantallaPrincipal = (ImageView) findViewById(R.id.btnPantallaPrincipal);
        btnNext = (ImageView) findViewById(R.id.btnNext);

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

        //para captura de fotos
        url_fotos = new ArrayList<String>();
        //para captura de fotos

        imgvCamara.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              if (!tengoCamara()){
                                                  mostrarToast("No tenes camara disponible");
                                              } else {
                                                  OpenCamara();
                                              }
                                          }
                                      }
        );

        imgvArchivos.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                //aca se escribe que hacer
                                                if (  (reclamo.getFotos() == null) ||  (reclamo.getFotos().size() < 7)  ) {
                                                    OpenGallery();
                                                } else  {
                                                    mostrarToast("No se pueden cargar mas imagenes.");
                                                }
                                            }
                                        }
        );

        btnBack.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           GoBack();
                                       }
                                   }
        );

        btnPantallaPrincipal.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        GoPantallaPrincipal();
                                                    }
                                                }
        );

        btnNext.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           boolean valido = true;

                                           if (!spnEspecialidades.getSelectedItem().toString().equals("Seleccione una especialidad")){
                                               reclamo.setEspecialidad(especialidades.get(spnEspecialidades.getSelectedItemPosition()-1));
                                           } else {
                                               valido = false;
                                               mostrarToast("Es necesario seleccionar una categoria valida.");
                                           }

                                           if (!editReclamoComentario.getText().toString().equals("")){
                                               reclamo.setDescripcion(editReclamoComentario.getText().toString());
                                           } else {
                                               valido = false;
                                               mostrarToast("Es necesario completar el detalle del reclamo para continuar.");
                                           }

                                           if ( valido ) {
                                               //mostrarDialogo("probando", reclamo.toString());
                                               reclamo.setEstado(GeneradorEstadosObjects.getEstadoObject("Abierto"));
                                               Administrado administrado2 = new Administrado();
                                               administrado2.setId_administrado(administrado.getId_administrado());
                                               administrado2.setId_user(administrado.getId_user());
                                               reclamo.setAdministrado(administrado2);
                                               reclamo.setUsername(user.getUsername());

                                               ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                               ClipData clip = ClipData.newPlainText("label",String.valueOf(reclamo.toString()));
                                               clipboard.setPrimaryClip(clip);

                                               pasar_a_pantalla_reclamos_3(reclamo);
                                           }
                                       }
                                   }
        );
    }

    private void OpenGallery () {
        if (url_fotos.size() < 7) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select picture"), GALLERY_REQUEST_CODE);
        } else {
            mostrarToast("No se puden cargar mas imagenes. (Max. 7)");
        }
    }

    private void OpenCamara () {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        }
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
                    fotoNew.setUri_foto(MetodosDeVerificacion.getEncoded64ImageStringFromBitmap(BitmapFactory.decodeStream(imageStream)));
                    //selectedImage.getPath()
                }
                reclamoFotos.add(fotoNew);
                reclamo.setFotos(reclamoFotos);
                //BitmapFactory.decodeStream(imageStream)  --> imagen
                //ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                //ClipData clip = ClipData.newPlainText("label",String.valueOf(reclamo.getFotos().size()));
                //clipboard.setPrimaryClip(clip);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //selectedImageView.setImageBitmap(imageBitmap);
        }

    }

    private boolean tengoCamara(){
        return getBaseContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    private void pasar_a_pantalla_reclamos_3 (Reclamo reclamo2){
        Intent intent = new Intent(this, CreacionReclamo3.class);
        intent.putExtra("user",user);
        intent.putExtra("reclamo", reclamo2);
        intent.putExtra("administrado",administrado);
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label",String.valueOf(reclamo.toString()));
        clipboard.setPrimaryClip(clip);
        startActivity(intent);
    }

    /*private Especialidad getEspecialidadByName (String nombre){
        Especialidad especialidadResult = null;
        for(int i = 0; i < especialidades.size(); i++){
            Especialidad especialidad = especialidades.get(i);
            if ( especialidad.getNombre().equals(nombre) ){
                especialidadResult = especialidad;
            }
        }
        return especialidadResult;
    }  */

    private void GoPantallaPrincipal(){
        Intent intent = new Intent(this, PantallaPrincipal.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    private void GoBack(){
        Intent intent = new Intent(this, CreacionReclamo1.class);
        intent.putExtra("user",user);
        startActivity(intent);
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
                    ArrayList<String> spinners = new ArrayList<String>();
                    spinners.add("Seleccione una especialidad");
                    for (int i = 0; i < response.body().size(); i++){
                        Especialidad especialidad = response.body().get(i);
                        //especialidad.setId_especialidad(i+1);
                        especialidades.add(especialidad);
                        spinners.add(especialidad.getNombre());
                    }
                    spinnerEspecialidades(spinners);
                }
                //CLIPBOARD
                //ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                //ClipData clip = ClipData.newPlainText("label",response.body().toString());
                //clipboard.setPrimaryClip(clip);
                //mostrarToast("se copio");
                //CLIPBOARD
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
        spnEspecialidades.setAdapter(adapterEspecialidades);
    }

    private String getRelacion(){
        String relacion = null;
        List<AdministradoUnidad> administradoUnidades = administrado.getAdministradoUnidades();
        if (administradoUnidades.size() > 0) {
            for (int i = 0; i < administradoUnidades.size(); i++) {
                AdministradoUnidad adminUnidad = administradoUnidades.get(i);
                Unidad unidad = adminUnidad.getUnidad();
                if (unidad.getId_unidad() == reclamo.getUnidad().getId_unidad()){
                    relacion =  adminUnidad.getRelacion();
                }
            }
        }
        return relacion;
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
                mostrarToast("Ya estás Creando Nuevo Reclamo");
                //GoToNewReclamo ();
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