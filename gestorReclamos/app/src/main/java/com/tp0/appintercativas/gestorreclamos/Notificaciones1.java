package com.tp0.appintercativas.gestorreclamos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.tp0.appintercativas.gestorreclamos.UserManagement.data.User;

public class Notificaciones1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{
    User user;
    ImageView principal_img,btnExitNotif;
    TextView txtNotificacionesPpal,principaltexto2;
    ScrollView listanotificaciones;
    Button btnNotifica1,btnNotifica2,btnNotifica3,btnNotifica4,btnNotifica5,btnNotifica6,btnNotifica7,btnBorrarNotif;
    //para la slide bar
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones1);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        principal_img = (ImageView) findViewById(R.id.principal_img);
        btnExitNotif = (ImageView) findViewById(R.id.btnExitNotif);

        txtNotificacionesPpal = (TextView) findViewById(R.id.txtNotificacionesPpal);
        principaltexto2 = (TextView) findViewById(R.id.principaltexto2);

        listanotificaciones = (ScrollView) findViewById(R.id.listanotificaciones);

        btnNotifica1 = (Button) findViewById(R.id.btnNotifica1);
        btnNotifica2 = (Button) findViewById(R.id.btnNotifica2);
        btnNotifica3 = (Button) findViewById(R.id.btnNotifica3);
        btnNotifica4 = (Button) findViewById(R.id.btnNotifica4);
        btnNotifica5 = (Button) findViewById(R.id.btnNotifica5);
        btnNotifica6 = (Button) findViewById(R.id.btnNotifica6);
        btnNotifica7 = (Button) findViewById(R.id.btnNotifica7);
        btnBorrarNotif = (Button) findViewById(R.id.btnBorrarNotif);

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


        btnNotifica1.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                             //aca se escribe que hacer
                                                 GoToNotificaDetalle();
                                             }
                                         }
        );
        btnExitNotif.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                //aca se escribe que hacer
                                                GoPantallaPrincipal();
                                            }
                                        }
        );

     /*   btnNotifica2.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 //aca se escribe que hacer
                                             }
                                         }
        );

        btnNotifica3.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 //aca se escribe que hacer
                                             }
                                         }
        );

        btnNotifica4.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    //aca se escribe que hacer
                                                }
                                            }
        );

        btnNotifica5.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {
                                                  //aca se escribe que hacer
                                              }
                                          }
        );
        btnNotifica6.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                //aca se escribe que hacer
                                            }
                                        }
        );

        btnNotifica7.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                //aca se escribe que hacer
                                            }
                                        }
        );

        btnBorrarNotif.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                //aca se escribe que hacer
                                            }
                                        }
        );*/

    }
    //para la slide bar

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
                Toast.makeText(this, "Ya estas en esa pantalla!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.reclamoactivo:
                Toast.makeText(this, "Reclamos Activos selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.reclamohistorial:
                Toast.makeText(this, "Historial Reclamos selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.notificaciones:
                Toast.makeText(this, "Notificaciones selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, Notificaciones1.class);
                intent.putExtra("user",user);
                startActivity(intent);
                break;
            case R.id.configuracion:
                Toast.makeText(this, "Configuraciones selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ConfiguracionesUser.class);
                intent.putExtra("user",user);
                startActivity(intent);
                break;
            case R.id.acercaapp:
                Toast.makeText(this, "Acerca de la App selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, InfoAppActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                break;
            case R.id.cerrarsesion:
                Toast.makeText(this, "Cerrar Sesión selected", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, MainActivityLogin.class);
                startActivity(intent);
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
    private void GoPantallaPrincipal(){
        Intent intent = new Intent(this, PantallaPrincipal.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}
