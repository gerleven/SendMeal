    package ar.com.leventalgarcia.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static ar.com.leventalgarcia.sendmeal.R.id.ET_AliasCBU;
import static ar.com.leventalgarcia.sendmeal.R.id.ET_CBU;
import static ar.com.leventalgarcia.sendmeal.R.id.ET_CCVTarj;
import static ar.com.leventalgarcia.sendmeal.R.id.ET_Mail;
import static ar.com.leventalgarcia.sendmeal.R.id.ET_NumTarjeta;
import static ar.com.leventalgarcia.sendmeal.R.id.ET_UserName;
import static ar.com.leventalgarcia.sendmeal.R.id.titleDividerNoCustom;

public class MainActivity extends AppCompatActivity{ //implements View.OnClickListener
//public class MainActivity extends AppCompatActivity implements  View.OnClickListener{ //Si lo hubiesemos hecho sin clases anonimas e implementando View.OnClickListener 1/3


    //Variables widgets:
    EditText userName, userPass1, userPass2,userMail,numTarj,ccv,cbu,aliasCBU;
    TextView tvTipoTarjeta,tvFechaVto,tvCreditoInicial;
    RadioGroup rgTipoTarjeta;
    RadioButton rbDebito,rbCredito;
    Switch swCargaInicial;
    SeekBar sbCreditoInicial;
    CheckBox cbAceptoTerm;
    Button btnRregistrar;
    Spinner spinnerMesVencimiento,spinnerAnioVencimiento;

    Boolean terminosAceptados,esDebito,esCredito,conCargaInicial;
    Float creditoInicial;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //define mediante que layout se va a mostrar


        //LOGICA:




        //Captura de widgets
        /*
        String userNameValue = ((EditText) findViewById(R.id.ET_UserName)).getText().toString();
        String userPass1Value= ((EditText) findViewById(R.id.ET_Pass1)).getText().toString();
        String userPass2Value= ((EditText) findViewById(R.id.ET_Pass2)).getText().toString();
        String userMailValue= ((EditText) findViewById(R.id.ET_Mail)).getText().toString();
        Integer numTarjetaValue = Integer.parseInt(((EditText) findViewById(R.id.ET_NumTarjeta)).getText().toString());
        Integer ccvValue = Integer.parseInt(((EditText) findViewById(R.id.ET_CCVTarj)).getText().toString());
        Integer cbuValue = Integer.parseInt(((EditText) findViewById(R.id.ET_CBU)).getText().toString());
        String aliasCbuValue = ((EditText) findViewById(R.id.ET_AliasCBU)).getText().toString();
        */






        userName = (EditText) findViewById(R.id.ET_UserName);
        userPass1 = (EditText) findViewById(R.id.ET_Pass1);
        userPass2 = (EditText) findViewById(R.id.ET_Pass2);
        userMail = (EditText) findViewById(R.id.ET_Mail);
        cbu = (EditText) findViewById(R.id.ET_CBU);
        aliasCBU = (EditText) findViewById(R.id.ET_AliasCBU);
        tvTipoTarjeta = (TextView) findViewById(R.id.TV_TipoTarjeta);
        tvFechaVto = (TextView) findViewById(R.id.TV_FechaVto);
        tvCreditoInicial = (TextView) findViewById(R.id.TV_CreditoInicial);
        rgTipoTarjeta = (RadioGroup) findViewById(R.id.RG_TipoTarj);
        rbDebito = (RadioButton) findViewById(R.id.RB_Debito);
        rbCredito = (RadioButton) findViewById(R.id.RB_Credito);
        swCargaInicial = (Switch) findViewById(R.id.SW_CargaInicial);
        sbCreditoInicial = (SeekBar) findViewById(R.id.SB_CreditoInicial);
        cbAceptoTerm = (CheckBox) findViewById(R.id.CB_AcetaTerminos);
        btnRregistrar = (Button) findViewById(R.id.BTN_Registrar);
        ccv = (EditText) findViewById(R.id.ET_CCVTarj);
        numTarj = (EditText) findViewById(ET_NumTarjeta);
        spinnerMesVencimiento = (Spinner) findViewById(R.id.spinnerMesVencimiento);
        spinnerAnioVencimiento = (Spinner) findViewById(R.id.spinnerAnioVencimiento);


        //Deshabilitar cosas
        //ccv ya esta en el XML
        spinnerMesVencimiento.setEnabled(false);
        spinnerAnioVencimiento.setEnabled(false);

        //Aca le estamos seteando al boton un escuchador de eventos, es decir, quien va a prestar atencion a cuando ese vvento suceda para hacer alguna accion.
        //tipoTarjeta.getCheckedRadioButtonId();
        //Oyente de radio group forma mas compacta:
        rgTipoTarjeta.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    }
                }
        );

        //-------------- OYENTES: -------------------
        //Radio Group:
        RadioGroup.OnCheckedChangeListener rgListener =
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        Toast.makeText(MainActivity.this, "rgListener:", Toast.LENGTH_SHORT).show();

                    }
                };
        rgTipoTarjeta.setOnCheckedChangeListener(rgListener);

        //Views
        View.OnClickListener viewsListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "viewsListener:", Toast.LENGTH_SHORT).show();
            }
        };
        btnRregistrar.setOnClickListener(viewsListener);

        //Oyente Compund Buttons:
        CompoundButton.OnCheckedChangeListener compoundButtonListener = new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(MainActivity.this, "compoundButtonListener:", Toast.LENGTH_SHORT).show();
            }
        };
        cbAceptoTerm.setOnCheckedChangeListener(compoundButtonListener);
        swCargaInicial.setOnCheckedChangeListener(compoundButtonListener);

        SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Toast.makeText(MainActivity.this, "onProgressChanged:", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "onStartTrackingTouch:", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "onStopTrackingTouch:", Toast.LENGTH_SHORT).show();
            }
        };
        sbCreditoInicial.setOnSeekBarChangeListener(seekBarListener);

        Spinner.OnClickListener spinerListener = new Spinner.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "spinerListener:", Toast.LENGTH_SHORT).show();
            }
        };


        //Listener de los textviews:
        TextWatcher textwatcherListener = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(MainActivity.this, "onTextChanged", Toast.LENGTH_SHORT).show();
            }

           @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
               Toast.makeText(MainActivity.this, "beforeTextChanged", Toast.LENGTH_SHORT).show();
           }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(MainActivity.this, "afterTextChanged", Toast.LENGTH_SHORT).show();
                //Cantidad de digitos en numero de tarjeta valido

                if (s == numTarj.getEditableText()) {
                    if(s.length()==16) {
                        ccv.setEnabled(true);
                    }
                    else {
                        ccv.setEnabled(false);
                    }
                }
                if (s == ccv.getEditableText()) {
                    if(s.length()==3) {
                        spinnerAnioVencimiento.setEnabled(true);
                        spinnerMesVencimiento.setEnabled(true);
                    }
                    else {
                        spinnerAnioVencimiento.setEnabled(false);
                        spinnerMesVencimiento.setEnabled(false);
                    }
                }
            }
        };

        numTarj.addTextChangedListener(textwatcherListener);
        ccv.addTextChangedListener(textwatcherListener);









    }

}