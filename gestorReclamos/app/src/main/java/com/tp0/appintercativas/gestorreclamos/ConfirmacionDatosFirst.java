package com.tp0.appintercativas.gestorreclamos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tp0.appintercativas.gestorreclamos.UserManagement.Controller.Controller;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Administrado;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.AdministradoUnidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Inspector;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.InspectorEdificio;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Unidad;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.AdministradoService;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.InspectorService;
import com.tp0.appintercativas.gestorreclamos.UserManagement.service.UserService;

import java.io.Serializable;
import java.util.List;

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
        user.setFirstTime("false");

        btnBack = (ImageView) findViewById(R.id.btnBackRec2);
        btnCancel = (ImageView) findViewById(R.id.btnCancel);
        btnOk = (ImageView) findViewById(R.id.btnNextRec);

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtDatos = (TextView) findViewById(R.id.txtDatos);

        salidaDatos = "Tipo: "+user.getTipo_identificacion()+ "  Nro: "+user.getNumero_identificacion()+"\n"
                    +"Nombres : "+user.getFirstName()+"\n"
                    +"Apellidos: "+user.getLastName()+"\n"
                    +"Sexo: "+user.getSexo()+"\n"
                    +"Pregunta: "+user.getPreguntaSeguridad()+"\n"
                    +"Respuesta: "+user.getRespuestaSeguridad()+"\n"
                    +"Email: "+user.getEmail()+"\n"
                    +"Celular: "+user.getCelular()+"\n";

        Retrofit retrofit = Controller.ConfiguracionIP();

        if (user.getTipoUser().toString().toLowerCase().equals("administrado")){

            AdministradoService as = retrofit.create(AdministradoService.class);
            Call<Administrado> call = as.getAdministradoId((long) user.getId());

            call.enqueue(new Callback<Administrado>() {

                @Override
                public void onResponse(Call<Administrado> call, Response<Administrado> response) {
                    administrado = response.body();
                    if ((response.isSuccessful()) && (response.body().getAdministradoUnidades().size() > 0)) {
                        for (int i = 0; i < response.body().getAdministradoUnidades().size(); i++) {
                            AdministradoUnidad adminUnidad = response.body().getAdministradoUnidades().get(i);
                            Unidad unidad = adminUnidad.getUnidad();
                            salidaDatos += "Edificio: " + unidad.getEdificio().getNombre() + " - " + unidad.getEdificio().getDireccion()
                                    + " Piso: " + unidad.getPiso() + "\n"
                                    + " Unidad: " + unidad.getUnidad() + "\n"
                                    + " Tipo: " + adminUnidad.getRelacion() ;
                        }
                    }
                    txtDatos.setText(salidaDatos);
                }

                @Override
                public void onFailure(Call<Administrado> call, Throwable t) {
                    mostrarDialogo("Error", "Error en la ejecucion " + t.getMessage());
                }
            });

        } else if (user.getTipoUser().toString().toLowerCase().equals("inspector")) {
            InspectorService is = retrofit.create(InspectorService.class);
            Call<Inspector> call = is.getInspectorId(user.getId());

            call.enqueue(new Callback<Inspector>() {
                     @Override
                     public void onResponse(Call<Inspector> call, Response<Inspector> response) {
                        if ( response.body().getEdificios().size() > 0) {

                            for (int i = 0; i < response.body().getEdificios().size(); i++){
                                salidaDatos+= "Edificio: " + response.body().getEdificios().get(i).getNombre()
                                        + " Direccion: "+response.body().getEdificios().get(i).getDireccion()+ "\n";
                            }

                            if(response.body().getEspecialidads().size() > 0) {
                                salidaDatos += "Especialidades:\n";
                            }

                            for (int i = 0; i < response.body().getEspecialidads().size(); i++){
                                salidaDatos+= response.body().getEspecialidads().get(i).getNombre()+" "
                                                    +response.body().getEspecialidads().get(i).getDescripcion()+"\n";
                            }

                        }
                         txtDatos.setText(salidaDatos);
                     }

                     @Override
                     public void onFailure(Call<Inspector> call, Throwable t) {
                         mostrarDialogo("Error", "Error en la ejecucion " + t.getMessage());
                     }
                 }
            );

        }

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
                confirmar_parseScreen(user);
            }
        });
    }

    private void confirmar_parseScreen(final User user){
        Retrofit retrofit = Controller.ConfiguracionIP();
        UserService us = retrofit.create(UserService.class);
        Call<User> call = us.updateUser(user.getId(),user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    mostrarDialogo("Error", "Resultado no correcto ");
                    return;
                } else {
                    if (response.body().getFirstTime() != "") {
                        pasar_a_menu_principal(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mostrarDialogo("Error", "Error en la ejecucion "+t.getMessage());
            }
        });


    }

    private void pasar_a_menu_principal(User user) {
        Intent intent = new Intent(this, PantallaPrincipal.class);
        intent.putExtra("user",user);
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
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //va a hacer nada aca, si se quisiera cerrar la app es finish()
                    }
                })
                .setMessage(mensaje)
                .show();
    }
}