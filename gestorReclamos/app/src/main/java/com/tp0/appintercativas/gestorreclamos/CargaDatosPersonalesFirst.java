package com.tp0.appintercativas.gestorreclamos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

public class CargaDatosPersonalesFirst extends AppCompatActivity {

    TextView txtTitulo, txtTipo ,txtNumero, txtNombres, txtApellidos, txtSexo, txtCelular,txtEmail;
    Spinner spnTipo,spnSexo;
    EditText editNumero ,editNombres, editApellidos, editCelular, editEmail;
    Button btnCancelar, btnSiguiente;
    String tipo;
    String numero;
    String nombres;
    String apellidos;
    String sexo;
    String celular;
    String email;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_datos_personales_first);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

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

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
             }
        );

        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               sexo=spnSexo.getSelectedItem().toString();
                                               user.setSexo(sexo);
                                               tipo=spnTipo.getSelectedItem().toString();
                                               user.setTipo_identificacion(tipo);
                                               numero = String.valueOf(editNumero.getText());
                                               user.setNumero_identificacion(numero);
                                               celular = String.valueOf(editCelular.getText());
                                               user.setCelular(celular);
                                               nombres= String.valueOf(editNombres.getText());
                                               user.setFirstName(nombres);
                                               apellidos= String.valueOf(editApellidos.getText());
                                               user.setLastName(apellidos);
                                               email= String.valueOf(editEmail.getText());
                                               user.setEmail(email);
                                               mostrarDialogo("Probando", user.toString());
                                           }
                                       }
        );

    }


    private void mostrarDialogo(String titulo,String mensaje){
        new android.app.AlertDialog.Builder( this)
                .setTitle(titulo)
                .setMessage(mensaje)
                .show();
    }
}