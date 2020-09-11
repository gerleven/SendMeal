package ar.com.leventalgarcia.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity { //implements View.OnClickListener

    CheckBox cb1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb1 = findViewById(R.id.checkBox);

        //Validar contraseñas iguales

        //El numero CCV de la tarjeta es un input numerico el cual estara visible pero deshabilitado si el numero de la tarjeta esta vacio

        //Habilitar sleek bar segun estado del switch:
        if(cb1.isEnabled()){

        }


    }
}