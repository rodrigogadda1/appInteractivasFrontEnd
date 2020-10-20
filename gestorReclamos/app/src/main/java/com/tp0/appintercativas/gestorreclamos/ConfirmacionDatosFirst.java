package com.tp0.appintercativas.gestorreclamos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tp0.appintercativas.gestorreclamos.UserManagement.Controller.Controller;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Administrado;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Unidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.AdministradoService;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConfirmacionDatosFirst extends AppCompatActivity {

    TextView txtTitulo, txtDatos;
    ImageView btnBack, btnCancel , btnOk;
    User user;
    Administrado administrado;
    String salidaDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_datos_first);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnCancel = (ImageView) findViewById(R.id.btnCancel);
        btnOk = (ImageView) findViewById(R.id.btnOk);

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtDatos = (TextView) findViewById(R.id.txtDatos);

        salidaDatos = "Tipo: "+user.getTipo_identificacion()+ "  Nro: "+user.getNumero_identificacion()+"\n"
                    +"Nombres : "+user.getFirstName()+"\n"
                    +"Apellidos: "+user.getLastName()+"\n"
                    +"Sexo: "+user.getSexo()+"\n"
                    +"Pregunta: "+user.getPreguntaSeguridad()+"\n"
                    +"Respuesta: "+user.getRespuestaSeguridad()+"\n"
                    +"Email: "+user.getEmail()+"\n"
                    +"Numero: "+user.getCelular()+"\n";

        Retrofit retrofit = Controller.ConfiguracionIP();
        AdministradoService as= retrofit.create(AdministradoService.class);
        Call<Administrado> call = as.getAdministradoId((long) user.getId());

        call.enqueue(new Callback<Administrado>() {

             @Override
             public void onResponse(Call<Administrado> call, Response<Administrado> response) {
                 String salidaUnidades = "";
                 administrado = response.body();
                 if (response.isSuccessful() && response.body().getUnidades().size() > 0){
                    for (int i = 0; i < response.body().getUnidades().size(); i++){
                        Unidad unidad = response.body().getUnidades().get(i);
                        salidaDatos+= "Edificio: "+unidad.getEdificio().getNombre()+ " - "+unidad.getEdificio().getDireccion()
                                + " Piso: "+ unidad.getPiso() +"\n"
                                + " Unidad: "+unidad.getUnidad() +"\n"
                                + " Tipo: "+response.body().getTipo_administriado()+"\n \n";
                    }
                 }
                 txtDatos.setText(salidaDatos);
             }

             @Override
             public void onFailure(Call<Administrado> call, Throwable t) {
                 mostrarDialogo("Error", "Error en la ejecucion "+t.getMessage());
             }
         });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goback_parseScreen(user);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar_parseScreen();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmar_parseScreen(user, (int) administrado.getId_administrado());
            }
        });
    }

    private void confirmar_parseScreen(User user, int administrado_id){
        Intent intent = new Intent(this, PantallaPrincipal.class);
        intent.putExtra("user",user);
        intent.putExtra("administrado_id", administrado_id);
        startActivity(intent);
    }

    private void cancelar_parseScreen(){
        Intent intent = new Intent(this, MainActivityLogin.class);
        startActivity(intent);
    }

    private void goback_parseScreen(User user){
        Intent intent = new Intent(this, CargaDatosPreguntaFirst.class);
        intent.putExtra("user",user);
        intent.putExtra("ponerDatosDefault" , "true");
        startActivity(intent);
    }

    private void mostrarDialogo(String titulo,String mensaje){
        new AlertDialog.Builder( this)
                .setTitle(titulo)
                .setMessage(mensaje)
                .show();
    }
}