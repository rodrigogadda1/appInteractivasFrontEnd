package com.tp0.appintercativas.gestorreclamos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

public class CargaDatosPreguntaFirst extends AppCompatActivity {

    TextView txtPregunta, txtRespuesta, txtContraseña, txtRepeatContraseña;
    Button btnNext, btnCancel;
    Spinner spnPregunta;
    EditText editReponse, editContraseña, editRepeatContraseña;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_datos_pregunta_first);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        txtPregunta = (TextView) findViewById(R.id.txtPregunta);
        txtRespuesta = (TextView) findViewById(R.id.txtRespuesta);
        txtContraseña = (TextView) findViewById(R.id.txtContraseña);
        txtRepeatContraseña = (TextView) findViewById(R.id.txtRepeatContraseña);

        editReponse = (EditText) findViewById(R.id.editReponse);
        editContraseña = (EditText) findViewById(R.id.editContraseña);
        editRepeatContraseña = (EditText) findViewById(R.id.editRepeatContraseña);

        spnPregunta = (Spinner)  findViewById(R.id.spnPregunta);
        String []preguntasPosibles={"Cual es tu color favorito?","Como se llama tu perro?"};
        ArrayAdapter<String> adapterPreguntasPosibles = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, preguntasPosibles);
        spnPregunta.setAdapter(adapterPreguntasPosibles);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnNext.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                                user.setPreguntaSeguridad(spnPregunta.getSelectedItem().toString());
                                                user.setRespuestaSeguridad(editReponse.getText().toString());
                                                if(editContraseña.getText().toString() == editRepeatContraseña.getText().toString()){
                                                    user.setPassword(editContraseña.getText().toString());
                                                } else {
                                                    mostrarDialogo("Validacion incorrecta","Passwords no matchean");
                                                }
                                               mostrarDialogo("Muestra de datos",user.toString());
                                                goNextItem(user);
                                           }
                                       }
        );
        btnCancel.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {

                                           }
                                       }
        );

    }

    private void goNextItem(User user){
        Intent intent = new Intent(this, ConfirmacionDatosFirst.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    private void mostrarDialogo(String titulo,String mensaje){
        new android.app.AlertDialog.Builder( this)
                .setTitle(titulo)
                .setMessage(mensaje)
                .show();
    }
}