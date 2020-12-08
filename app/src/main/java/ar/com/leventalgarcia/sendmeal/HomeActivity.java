//--Ger 06/12/2020
package ar.com.leventalgarcia.sendmeal;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar; //Importar toolbar

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.leventalgarcia.sendmeal.Dominio.Plato;
import ar.com.leventalgarcia.sendmeal.Dominio.Usuario;


public class HomeActivity extends AppCompatActivity {

    public static final String TAG_CICLO_VIDA ="CICLO_DE_VIDA";
    public static final String actividad = "HomeActivity"; //Log.d(HomeActivity.TAG_CICLO_VIDA,actividad+".OnCreate()");

    public static List<Plato> listaPlatos = new ArrayList<Plato>();
    public static List<Usuario> listaUsuarios = new ArrayList<Usuario>();
    public Context ctx = HomeActivity.this;
    Button btnRegistrarme, btnCrearItem, btnListaItems, btnListaUsuarios, btnCargarPlatosDePrueba, btnCargarUsuariosDePrueba, btnDate;


    public static Date hora= new Date();
    public static Integer contador = 0;

    public static void contar1(){
        HomeActivity.contador++;
        if(contador==3){
            contador=1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Long login= hora.getTime();
        Log.d(this.TAG_CICLO_VIDA,"OnCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);

        btnDate = findViewById(R.id.Test_Datenow);
        btnDate.setOnClickListener(new View.OnClickListener(){
            Long delta = login - hora.getTime();
            @Override
            public void onClick(View view) {
                HomeActivity.contar1();
                switch (HomeActivity.contador){
                    case 1:
                        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                        Date date = new Date();
                        Toast.makeText(ctx, "Hora actual: "+ dateFormat.format(date), Toast.LENGTH_SHORT).show();
                        break;
                    case 2:

                        long tMinTotal = System.currentTimeMillis() / (60 * 1000);
                        int tMinCurrent = (int) (tMinTotal % (24 * 60));
                        int hours = tMinCurrent / 60;
                        int minutes = tMinCurrent % 60;
                        Toast.makeText(ctx, "hora: "+hours+" minutos: "+minutes, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        /*
                        LocalDateTime locaDate = LocalDateTime.now();
                        int hours2  = locaDate.getHour();
                        int minutes = locaDate.getMinute();
                        int seconds = locaDate.getSecond();
                        Toast.makeText( HomeActivity.this, "Hora actual : " + hours  + ":"+ minutes +":"+seconds, Toast.LENGTH_SHORT).show();
                        */
                        break;
                    default:
                        Toast.makeText(HomeActivity.this, "Hora: "+delta, Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnRegistrarme = findViewById(R.id.buttonRegistrarme);
        btnRegistrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Going to Resgistrarme...", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(HomeActivity.this,RegistrarActivity.class);
                startActivityForResult(i,123);
            }
        });

        btnCargarUsuariosDePrueba = findViewById(R.id.buttonCargarUsuariosDePrueba);
        btnCargarUsuariosDePrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario.cargarUsuariosDePrueba(HomeActivity.this);
            }
        });

        btnListaUsuarios = findViewById(R.id.buttonListaUsuarios);
        btnListaUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,ListaUsuariosActivity.class);
                startActivityForResult(i,1);
            }
        });


        btnCrearItem=findViewById(R.id.buttonCrearItem);
        btnCrearItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "CrearItemActivity...", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(HomeActivity.this,CrearItemActivity.class);
                startActivityForResult(i,456);
            }
        });

        btnCargarPlatosDePrueba=findViewById(R.id.buttonCargarPlatosDePrueba);
        btnCargarPlatosDePrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Plato.cargarPlatosDePrueba(HomeActivity.this);
            }
        });



        btnListaItems=findViewById(R.id.buttonListaItems);
        btnListaItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "ListaItemsActivity...", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(HomeActivity.this,ListaItemsActivity.class);
                startActivityForResult(i,789);
            }
        });





        //DesHabilitar cosas:
        //btnListaItems.setEnabled(false);
        //btnCrearItem.setEnabled(false);


    }

    //Metodo que se ejecuta cuando recibimos el resultado del startActivityForResult()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Hacer algo
        if(requestCode==123){
            if(resultCode== RESULT_OK) {
                Toast.makeText(this, "El usuario "+data.getExtras().getString("userName")+" fue registrado con exito", Toast.LENGTH_SHORT).show();
                btnListaItems.setEnabled(true);
                btnCrearItem.setEnabled(true);
            }
            else{
                Toast.makeText(this, "Algo salio mal... resultCode: "+resultCode, Toast.LENGTH_SHORT).show();
            }

        }
        if(requestCode==456){
            if(resultCode== RESULT_OK) {
                Toast.makeText(this, "El Item "+data.getExtras().getString("ItemCreado")+"."+listaPlatos.get(0).toString(), Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Algo salio mal... resultCodee: "+resultCode, Toast.LENGTH_SHORT).show();
            }

        }
        if(requestCode==789){
            if(resultCode== RESULT_OK) {
                //Toast.makeText(this, "Item seleccionado: "+data.getExtras().getString("item")+".", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Algo salio mal... resultCode: "+resultCode, Toast.LENGTH_SHORT).show();
            }

        }

    }

}