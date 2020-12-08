package ar.com.leventalgarcia.sendmeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ar.com.leventalgarcia.sendmeal.Dominio.Plato;

public class CrearItemActivity extends AppCompatActivity {

    public static final String actividad = "CrearItemActivity"; //Log.d(HomeActivity.TAG_CICLO_VIDA,actividad+".OnCreate()");
    Button btnGuardar,btnVolver;
    EditText tituloPlato, descripcionPlato,precioPlato, caloriasPlato;
    Boolean hacerValidaciones = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(HomeActivity.TAG_CICLO_VIDA,actividad+".OnCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_crear_item);

        tituloPlato = (EditText) findViewById(R.id.editTextTitulo);
        descripcionPlato = (EditText) findViewById(R.id.editTextDescripcion);
        precioPlato = (EditText) findViewById(R.id.editTextPrecio);
        caloriasPlato = (EditText) findViewById(R.id.editTextCalorias);

        btnGuardar = (Button) findViewById(R.id.buttonGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validarInput()) {
                    String titulo = tituloPlato.getText().toString();
                    String descripcion = descripcionPlato.getText().toString();
                    Double precio = new Double(precioPlato.getText().toString());
                    Double calorias = new Double(caloriasPlato.getText().toString());


                    Plato nuevoPlato = new Plato(titulo, descripcion, precio, calorias);
                    HomeActivity.listaPlatos.add(nuevoPlato);
                    //Toast.makeText(CrearItemActivity.this, "plato2:"+nuevoPlato.toString(), Toast.LENGTH_SHORT).show();

                    Intent i = new Intent();
                    i.putExtra("ItemCreado",nuevoPlato.toString());
                    setResult(Activity.RESULT_OK,i);
                    finish();
                }
                else{
                    Toast.makeText(CrearItemActivity.this, "No se pudo agregar el Plato...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //if(hacerValidaciones){btnGuardar.setEnabled(false);} //deshabilitamos el boton de guardar para que no se pueda hasta que no esten los inputs validados

        btnVolver = findViewById(R.id.BTN_Volver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        
    }

    private Boolean validarInput(){
        if(hacerValidaciones){
            if(tituloPlato.getText().toString().isEmpty()){
                Toast.makeText(this, "Debe ingresar un titulo para el plato...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(descripcionPlato.getText().toString().isEmpty()){
                Toast.makeText(this, "Debe ingresar una descripcion para el plato...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(precioPlato.getText().toString().isEmpty()){
                Toast.makeText(this, "Debe ingresar un precio para el plato...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(Double.parseDouble(precioPlato.getText().toString())<0){
                Toast.makeText(this, "El precio debe ser un valor positivo...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(caloriasPlato.getText().toString().isEmpty()){
                Toast.makeText(this, "Debe ingresar un valor de calorias para el plato...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(Double.parseDouble(caloriasPlato.getText().toString())<0){
                Toast.makeText(this, "Las calorias deben ser un valor positivo...", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        return true;

    }



}
