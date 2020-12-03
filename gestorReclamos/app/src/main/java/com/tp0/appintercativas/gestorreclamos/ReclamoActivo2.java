package com.tp0.appintercativas.gestorreclamos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;



import com.google.android.material.navigation.NavigationView;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Foto;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Reclamo;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

import java.util.List;

public class ReclamoActivo2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {
    User user;
    Reclamo reclamo;
    ImageView areclamo2_img, btnBackRecAct, btnExitRecAct;
    ImageView imgEditRecAct1, imgEditRecAct2, imgEditRecAct3, imgEditRecAct4, imgEditRecAct5, imgEditRecAct6, imgEditRecAct7, imgEditRecAct8,
            imgEditRecAct9, imgEditRecAct10, imgEditRecAct11, imgEditRecAct12, imgEditRecAct13;
    TextView areclamo2_texto1, txtDetallesReclamoAct, txtEspecialidadRecActivo, txtEspecialidadRecActDetalle, txtEstadoRecActivo, txtRecActivoDetalle, txtDetalleDelReclamoRecAct;
    ScrollView scvInfoREclamo2, scvListaImagenesRecActivo;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamo_activo2);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        reclamo = (Reclamo) intent.getSerializableExtra("reclamo");

        areclamo2_img = (ImageView) findViewById(R.id.areclamo2_img);
        btnBackRecAct = (ImageView) findViewById(R.id.btnBackRecAct);
        btnExitRecAct = (ImageView) findViewById(R.id.btnExitRecAct);


        //para las imagenes
        imgEditRecAct1 = (ImageView) findViewById(R.id.imgEditRecAct1);
        imgEditRecAct2 = (ImageView) findViewById(R.id.imgEditRecAct2);
        imgEditRecAct3 = (ImageView) findViewById(R.id.imgEditRecAct3);
        imgEditRecAct4 = (ImageView) findViewById(R.id.imgEditRecAct4);
        imgEditRecAct5 = (ImageView) findViewById(R.id.imgEditRecAct5);
        imgEditRecAct6 = (ImageView) findViewById(R.id.imgEditRecAct6);
        imgEditRecAct7 = (ImageView) findViewById(R.id.imgEditRecAct7);
        imgEditRecAct8 = (ImageView) findViewById(R.id.imgEditRecAct8);
        imgEditRecAct9 = (ImageView) findViewById(R.id.imgEditRecAct9);
        imgEditRecAct10 = (ImageView) findViewById(R.id.imgEditRecAct10);
        imgEditRecAct11 = (ImageView) findViewById(R.id.imgEditRecAct11);
        imgEditRecAct12 = (ImageView) findViewById(R.id.imgEditRecAct12);
        imgEditRecAct13 = (ImageView) findViewById(R.id.imgEditRecAct13);

        if ((reclamo.getFotos() != null) && (reclamo.getFotos().size() > 0)) {
            //se agregan al scrollview
            //mostrarDialogo("probando 95", String.valueOf(reclamo.getFotos().size()));
            rellenarConImagenes(reclamo.getFotos());
            //next.set
            //next.setImageBitmap(MetodosDeVerificacion.resizeBitmap(MetodosDeVerificacion.stringToBitmap(reclamo.getFotos().get(0).getUri_foto())));
        }
        //para las imagenes fin


        areclamo2_texto1 = (TextView) findViewById(R.id.txtRecActEstado);
        txtEspecialidadRecActivo = (TextView) findViewById(R.id.txtEspecialidadRecActivo);
        txtEspecialidadRecActDetalle = (TextView) findViewById(R.id.txtEspecialidadRecActDetalle);
        txtEstadoRecActivo = (TextView) findViewById(R.id.txtEstadoRecActivo);
        txtRecActivoDetalle = (TextView) findViewById(R.id.txtRecActivoDetalle);
        txtDetalleDelReclamoRecAct = (TextView) findViewById(R.id.txtDetalleDelReclamoRecAct);

        txtEspecialidadRecActDetalle.setText(reclamo.getEspecialidad().getNombre());
        txtRecActivoDetalle.setText(reclamo.getEstado().getDescripcion());

        scvInfoREclamo2 = (ScrollView) findViewById(R.id.scvInfoREclamo2);
        scvListaImagenesRecActivo = (ScrollView) findViewById(R.id.scvListaImagenesRecActivo);

        if (user.getTipoUser().toLowerCase().equals("administrado")) {
            if ((reclamo.getRespuesta_administrador() != null) && (reclamo.getRespuesta_administrador() != "")) {
                txtDetalleDelReclamoRecAct.setText(reclamo.getRespuesta_administrador());
            } else {
                txtDetalleDelReclamoRecAct.setText("No hay respuesta del adminsitrador todavia.");
            }
        }

        if (user.getTipoUser().toLowerCase().equals("inspector")) {
            txtDetalleDelReclamoRecAct.setText(reclamo.getDescripcion());
        }

        if (user.getTipoUser().toLowerCase().equals("administrador")) {
            if ((reclamo.getRespuesta_inspector() != null) && (reclamo.getRespuesta_inspector() != "")) {
                txtDetalleDelReclamoRecAct.setText(reclamo.getRespuesta_inspector());
            } else {
                txtDetalleDelReclamoRecAct.setText("No hay respuesta del inspector todavia.");
            }
        }

        String texto = "\n"+"Detalle del Reclamo" + "\n";
        if (reclamo.getUsername() != ""){
            texto+="Usuario: "+reclamo.getUsername()+ "\n";
        }
        texto+="Edificio: "+reclamo.getEdificio().getNombre()+ "\n";
        if (reclamo.getEspacioComun() != null) {
            texto+="Espacio comun: "+reclamo.getEspacioComun().getNombre()+ "\n";
        }
        if (reclamo.getUnidad() != null) {
            texto+="Piso: "+reclamo.getUnidad().getPiso()+" Unidad: "+reclamo.getUnidad().getUnidad();
        }
        areclamo2_texto1.setText(texto);

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

        btnBackRecAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToReclamosActivos();
            }
        });

        btnExitRecAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoPantallaPrincipal();
            }
        });


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


    protected void rellenarConImagenes(List<Foto> fotos) {
        for (int i = 0; i < fotos.size(); i++) {
            Bitmap bitmap =pasar_a_Bitmap(fotos.get(i).getFoto());
            switch(i) {
                case 0:
                    imgEditRecAct1.setImageBitmap(bitmap);
                    break;
                case 1:
                    imgEditRecAct2.setImageBitmap(bitmap);
                    break;
                case 2:
                    imgEditRecAct3.setImageBitmap(bitmap);
                    break;
                case 3:
                    imgEditRecAct4.setImageBitmap(bitmap);
                    break;
                case 4:
                    imgEditRecAct5.setImageBitmap(bitmap);
                    break;
                case 5:
                    imgEditRecAct6.setImageBitmap(bitmap);
                    break;
                case 6:
                    imgEditRecAct7.setImageBitmap(bitmap);
                    break;
                case 7:
                    imgEditRecAct8.setImageBitmap(bitmap);
                    break;
                case 8:
                    imgEditRecAct9.setImageBitmap(bitmap);
                    break;
                case 9:
                    imgEditRecAct10.setImageBitmap(bitmap);
                    break;
                case 10:
                    imgEditRecAct11.setImageBitmap(bitmap);
                    break;
                case 11:
                    imgEditRecAct12.setImageBitmap(bitmap);
                    break;
                case 12:
                    imgEditRecAct13.setImageBitmap(bitmap);
                    break;
            }
        }
    }

    private Bitmap pasar_a_Bitmap (String encodedImage){
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
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
}
