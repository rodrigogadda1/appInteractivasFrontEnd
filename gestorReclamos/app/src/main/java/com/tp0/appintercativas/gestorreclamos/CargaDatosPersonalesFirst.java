package com.tp0.appintercativas.gestorreclamos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.tp0.appintercativas.gestorreclamos.UserManagement.Auxiliares.MetodosDeVerificacion;
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

public class CargaDatosPersonalesFirst extends AppCompatActivity {

    TextView txtTitulo, txtTipo ,txtNumero, txtNombres, txtApellidos, txtSexo, txtCelular,txtEmail;
    Spinner spnTipo,spnSexo;
    EditText editNumero ,editNombres, editApellidos, editCelular, editEmail;
    ImageView btnCancelar, btnSiguiente;
    String tipo;
    String numero;
    String nombres;
    String apellidos;
    String sexo;
    String celular;
    String email;
    User user;
    String ponerDatosDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_datos_personales_first);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        ponerDatosDefault = intent.getStringExtra("ponerDatosDefault");

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtTipo = (TextView) findViewById(R.id.txtTipo);
        txtNumero = (TextView) findViewById(R.id.txtNumero);
        txtNombres = (TextView) findViewById(R.id.txtNombres);
        txtApellidos = (TextView) findViewById(R.id.txtApellidos);
        txtSexo = (TextView) findViewById(R.id.txtSexo);
        txtCelular = (TextView) findViewById(R.id.txtCelular);
        txtEmail = (TextView) findViewById(R.id.txtEmail);

        editNumero = (EditText) findViewById(R.id.editNumero);
        editNombres = (EditText) findViewById(R.id.editNombres);
        editApellidos = (EditText) findViewById(R.id.editApellidos);
        editCelular = (EditText) findViewById(R.id.editCelular);
        editEmail = (EditText) findViewById(R.id.editEmail);

        spnTipo = (Spinner) findViewById(R.id.spnTipo);
        String []opcionesTipo={"DNI","PASAPORTE"};
        ArrayAdapter<String> adapterTipo = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opcionesTipo);
        spnTipo.setAdapter(adapterTipo);

        spnSexo = (Spinner) findViewById(R.id.spnSexo);
        String []opcionesSexo={"MASCULINO","FEMENINO"};
        ArrayAdapter<String> adapterSexo = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opcionesSexo);
        spnSexo.setAdapter(adapterSexo);

        if (ponerDatosDefault.toLowerCase().equals("true")){
            editNumero.setText(user.getNumero_identificacion().toString());
            editNombres.setText(user.getFirstName().toString());
            editApellidos.setText(user.getLastName().toString());
            editCelular.setText(user.getCelular().toString());
            editEmail.setText(user.getEmail().toString());
            spnSexo.setSelection(adapterSexo.getPosition(user.getSexo().toString()));
            spnTipo.setSelection(adapterTipo.getPosition(user.getTipo_identificacion().toString()));
        }

        btnCancelar = (ImageView) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cancelar_parseScreen();
                    }
             }
        );

        btnSiguiente = (ImageView) findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Boolean ValidAll = true;

                                               sexo=spnSexo.getSelectedItem().toString();
                                               if (!sexo.equals("")){
                                                   user.setSexo(sexo);
                                               } else {
                                                   ValidAll = false;
                                                   mostrarDialogo("Validacion fallida","Seleccione un sexo");
                                               }

                                               tipo=spnTipo.getSelectedItem().toString();
                                               if (!tipo.equals("")){
                                                   user.setTipo_identificacion(tipo);
                                               } else {
                                                   if(ValidAll) {
                                                       ValidAll = false;
                                                       mostrarDialogo("Validacion fallida", "Seleccione un tipo de documento");
                                                   }
                                               }

                                               numero = String.valueOf(editNumero.getText());
                                               if ( (ValidAll ) && (!numero.equals("")) && (MetodosDeVerificacion.isNumeric(numero))){
                                                   user.setNumero_identificacion(numero);
                                               } else {
                                                   if(ValidAll) {
                                                       ValidAll = false;
                                                       mostrarDialogo("Validacion fallida", "Ingrese un numero de documento valido (solo numeros son aceptados)");
                                                   }
                                               }

                                               celular = String.valueOf(editCelular.getText());
                                               if ( (ValidAll ) && (!celular.equals("")) && (MetodosDeVerificacion.isNumeric(celular))){
                                                   user.setCelular(celular);
                                               } else {
                                                   if(ValidAll) {
                                                       ValidAll = false;
                                                       mostrarDialogo("Validacion fallida", "Ingrese un numero de celular valido (solo numeros son aceptados)");
                                                   }
                                               }

                                               nombres= String.valueOf(editNombres.getText());
                                               if ( (ValidAll ) && (!nombres.equals("")) && (MetodosDeVerificacion.isJustLetterAndSpaces(nombres))){
                                                   user.setFirstName(nombres);
                                               } else {
                                                   if(ValidAll) {
                                                       ValidAll = false;
                                                       mostrarDialogo("Validacion fallida", "Complete el campo nombre de manera valida (solo letras y espacios)");
                                                   }
                                               }

                                               apellidos= String.valueOf(editApellidos.getText());
                                               if ( (ValidAll ) && (!apellidos.equals("")) && (MetodosDeVerificacion.isJustLetterAndSpaces(apellidos)) ){
                                                   user.setLastName(apellidos);
                                               } else {
                                                   if(ValidAll) {
                                                       ValidAll = false;
                                                       mostrarDialogo("Validacion fallida", "Complete el campo apellido de manera valida (solo letras y espacios)");
                                                   }
                                               }

                                               email= String.valueOf(editEmail.getText());
                                               if ( (ValidAll ) && (!email.equals("")) && (MetodosDeVerificacion.isMail(email)) ){
                                                   user.setEmail(email);
                                               } else {
                                                   if(ValidAll) {
                                                       ValidAll = false;
                                                       mostrarDialogo("Validacion fallida", "Mail invalido");
                                                   }
                                               }

                                               if(ValidAll){
                                                   goNextItem(user, ponerDatosDefault);
                                               }

                                           }
                                       }
        );

    }


    private void mostrarDialogo(String titulo,String mensaje){
        new android.app.AlertDialog.Builder( this)
                .setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //va a hacer nada aca, si se quisiera cerrar la app es finish()
                    }
                })
                .show();
    }

    private void goNextItem(User user, String ponerDatosDefault){
        Intent intent = new Intent(this, CargaDatosPreguntaFirst.class);
        intent.putExtra("user",this.user);
        intent.putExtra("ponerDatosDefault" , ponerDatosDefault);
        startActivity(intent);
    }

    private void cancelar_parseScreen(){
        Intent intent = new Intent(this, MainActivityLogin.class);
        startActivity(intent);
    }

}