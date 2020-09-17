package ar.com.leventalgarcia.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity { //implements View.OnClickListener

    CheckBox checkBoxTerminosYCondiciones;
    EditText numeroTarjeta,ccv;
    RadioButton radioButtonCredito,radioButtonDebito;
    Spinner spinnerMesVencimiento, spinnerAnioVencimiento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxTerminosYCondiciones = findViewById(R.id.checkBoxTerminosYCondiciones);
        numeroTarjeta = findViewById(R.id.editTextNumeroTarjeta);
        ccv = findViewById(R.id.editTextCCV);
        radioButtonCredito = findViewById(R.id.radioButtonCredito);
        radioButtonDebito = findViewById(R.id.radioButtonDebito);
        spinnerMesVencimiento = findViewById(R.id.spinnerMesVencimiento);
        spinnerAnioVencimiento = findViewById(R.id.spinnerAnioVencimiento);

        //Deshabilitar cosas
        ccv.setEnabled(false);
        spinnerMesVencimiento.setEnabled(false);
        spinnerAnioVencimiento.setEnabled(false);

        //Validar contraseñas iguales




        //El numero CCV de la tarjeta es un input numerico el cual estara visible pero deshabilitado si el numero de la tarjeta esta vacio
        numeroTarjeta.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}

            @Override
            public void afterTextChanged(Editable s) {
                //Cantidad de digitos en numero de tarjeta valido
                if(s.length()==3) {
                    ccv.setEnabled(true);
                }
                else {
                    ccv.setEnabled(false);
                }
            }
        });

        ccv.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Cantidad de digitos en numero de ccv valido
                if(s.length()==3) {
                    spinnerMesVencimiento.setEnabled(true);
                    spinnerAnioVencimiento.setEnabled(true);
                }
                else {
                    spinnerMesVencimiento.setEnabled(false);
                    spinnerAnioVencimiento.setEnabled(false);
                }
            }
        });

        //Habilitar sleek bar segun estado del switch:
        if(checkBoxTerminosYCondiciones.isEnabled()){

        }


    }
}