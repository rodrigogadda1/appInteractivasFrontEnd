package com.tp0.appintercativas.gestorreclamos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

public class CargaDatosPreguntaFirst extends AppCompatActivity {

    TextView txtPregunta, txtRespuesta, txtContraseña, txtRepeatContraseña;
    ImageView btnNext, btnCancel, btnBack;
    Spinner spnPregunta;
    EditText editReponse, editContraseña, editRepeatContraseña;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_datos_pregunta_first);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        String ponerDatosDefault = intent.getStringExtra("ponerDatosDefault");
        mostrarDialogo("asd", user.toString());

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

        if (ponerDatosDefault.toLowerCase().equals("true")){
            editReponse.setText(user.getRespuestaSeguridad().toString());
            editContraseña.setText(user.getPassword().toString());
            editRepeatContraseña.setText(user.getPassword().toString());
            spnPregunta.setSelection(adapterPreguntasPosibles.getPosition(user.getPreguntaSeguridad().toString()));
        }

        btnNext = (ImageView) findViewById(R.id.btnNext);
        btnCancel = (ImageView) findViewById(R.id.btnCancel);
        btnBack = (ImageView) findViewById(R.id.btnBack);

        btnNext.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                    user.setPreguntaSeguridad(spnPregunta.getSelectedItem().toString());
                    user.setRespuestaSeguridad(editReponse.getText().toString());
                    if(editContraseña.getText().toString().equals(editRepeatContraseña.getText().toString())){
                        user.setPassword(editContraseña.getText().toString());
                        user.setFirstTime("false");
                        goNextItem(user);
                    } else {
                        mostrarDialogo("Validacion incorrecta","Passwords no matchean");
                    }
               }
           }
        );
        btnCancel.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               cancelar_parseScreen();
                                           }
                                       }
        );

        btnBack.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             goBack(user);
                                         }
                                     }
        );

    }

    private void goNextItem(User user){
        Intent intent = new Intent(this, ConfirmacionDatosFirst.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    private void goBack(User user){
        Intent intent = new Intent(this, CargaDatosPersonalesFirst.class);
        intent.putExtra("user",user);
        intent.putExtra("ponerDatosDefault" , "true");
        startActivity(intent);
    }

    private void cancelar_parseScreen(){
        Intent intent = new Intent(this, MainActivityLogin.class);
        startActivity(intent);
    }

    private void mostrarDialogo(String titulo,String mensaje){
        new android.app.AlertDialog.Builder( this)
                .setTitle(titulo)
                .setMessage(mensaje)
                .show();
    }

}