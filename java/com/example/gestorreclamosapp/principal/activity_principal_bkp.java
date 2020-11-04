package com.example.gestorreclamosapp.principal;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.gestorreclamosapp.R;
import com.example.gestorreclamosapp.configuraciones.activity_configuraciones;
import com.example.gestorreclamosapp.infoaplicacion.activity_infoaplicacion;
import com.example.gestorreclamosapp.ui.login.LoginActivity;

public class activity_principal_bkp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        setTitle("Gestor de Reclamos Menu");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.reclamonuevo:
                Toast.makeText(this, "Nuevo Reclamo selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.reclamoactivo:
                Toast.makeText(this, "Reclamos Activos selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.reclamohistorial:
                Toast.makeText(this, "Historial Reclamos selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.notificaciones:
                Toast.makeText(this, "Notificaciones selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.configuracion:
                Toast.makeText(this, "Configuraciones selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(activity_principal_bkp.this, activity_configuraciones.class);
                startActivity(intent);
                break;
            case R.id.cerrarsesion:
                Toast.makeText(this, "Cerrar Sesion selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(activity_principal_bkp.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.acercaapp:
                Toast.makeText(this, "Acerca de la App selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(activity_principal_bkp.this, activity_infoaplicacion.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    public void reclamonuevo(View view) {
    }

    public void reclamoactivo(View view) {
    }

    public void reclamohistorial(View view) {
    }

    public void notificaciones(View view) {
    }
}
