package ar.com.leventalgarcia.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static ar.com.leventalgarcia.sendmeal.R.id.editTextTextEmailAddress;

public class MainActivity extends AppCompatActivity { //implements View.OnClickListener

    CheckBox cb1;
    EditText numTarj,ccv;
    Integer numTarjetaValue;
    String value;
    Integer finalValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb1 = findViewById(R.id.checkBox);
        numTarj = findViewById(R.id.editTextTextEmailAddress);
        ccv = findViewById(R.id.editTextTextPersonName5);


        value= numTarj.getText().toString();
        //finalValue=Integer.parseInt(value);
        //Validar contraseñas iguales

        if(value.isEmpty()){
            ccv.setEnabled(false);
        }
        else{
            ccv.setEnabled(true);
        }

        //El numero CCV de la tarjeta es un input numerico el cual estara visible pero deshabilitado si el numero de la tarjeta esta vacio

        //Habilitar sleek bar segun estado del switch:
        if(cb1.isEnabled()){

        }


    }
}