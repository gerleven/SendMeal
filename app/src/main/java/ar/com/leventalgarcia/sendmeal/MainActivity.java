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
import static ar.com.leventalgarcia.sendmeal.R.id.TV_CreditoInicial;
import static ar.com.leventalgarcia.sendmeal.R.id.gone;
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
    float creditoInicial;
    String creditoInicialText = "Credito Inicial: ";
    Boolean tipoCredito = false;
    Boolean tipoDebito = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //define mediante que layout se va a mostrar


        //LOGICA:




        //Captura de widgets
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
        sbCreditoInicial.setVisibility(View.GONE); //new
        tvCreditoInicial.setVisibility(View.GONE); //new
        btnRregistrar.setEnabled(false); //new
        spinnerMesVencimiento.setEnabled(false);
        spinnerAnioVencimiento.setEnabled(false);

        //Purulaio
        //Setup values:
        creditoInicial=0; //el credito inicial comienza en 0 hasta que se setee un valor en la seekbar
        tvCreditoInicial.setText(creditoInicialText+creditoInicial); //se actualiza el text view para que muestre el credito inicial actual
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




        //-------------- OYENTES: -------------------

        //Listener del Radio Group:
        RadioGroup.OnCheckedChangeListener rgListener =
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch(rgTipoTarjeta.getCheckedRadioButtonId()) {
                            case (R.id.RB_Credito):
                                tipoCredito = true;
                                tipoDebito = false;
                                break;
                            case (R.id.RB_Debito):
                                tipoDebito = true;
                                tipoCredito = false;
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "Error en el Listener del radio group...", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(MainActivity.this, "rgListener: tipo: "+(tipoDebito?"Debito":"Credito"), Toast.LENGTH_SHORT).show();
                    }
                };
        rgTipoTarjeta.setOnCheckedChangeListener(rgListener);

        //Listener de los Views: Botonoes, etc
        View.OnClickListener viewsListener = new View.OnClickListener() { //new
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "viewsListener:", Toast.LENGTH_SHORT).show();
                switch (view.getId()){
                    case R.id.BTN_Registrar:
                        if(validacionesLogIn()){
                            Toast.makeText(MainActivity.this, "Registrando...", Toast.LENGTH_SHORT).show();
                            //intent que lleve a la pantalla siguiente
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Debes completar correctamente todos los campos", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Ningun id encontrado en el switch{}...", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        btnRregistrar.setOnClickListener(viewsListener);

        //Listener de los Compund Buttons:
        CompoundButton.OnCheckedChangeListener compoundButtonListener = new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(MainActivity.this, "compoundButtonListener:", Toast.LENGTH_SHORT).show();
                switch (compoundButton.getId()){ //new

                    case (R.id.SW_CargaInicial): //Si toco el SwitchButton:
                        if(b) {
                            Toast.makeText(MainActivity.this, "Activando carga inicial", Toast.LENGTH_SHORT).show();
                            sbCreditoInicial.setVisibility(View.VISIBLE);
                            sbCreditoInicial.setEnabled(true);
                            tvCreditoInicial.setVisibility(View.VISIBLE);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Desactivando carga inicial", Toast.LENGTH_SHORT).show();
                            sbCreditoInicial.setVisibility(View.GONE);
                            tvCreditoInicial.setVisibility(View.GONE);
                        }
                        break;
                    case (R.id.CB_AcetaTerminos):
                        if(b){
                            terminosAceptados=true;
                            btnRregistrar.setEnabled(true);
                        }
                        else{
                            terminosAceptados=false;
                            btnRregistrar.setEnabled(false);
                        }
                        Toast.makeText(MainActivity.this, terminosAceptados?"Terminos aceptados!":"Terminos no aceptados!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Ningun id encontrado en el switch{}...", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };
        cbAceptoTerm.setOnCheckedChangeListener(compoundButtonListener);
        swCargaInicial.setOnCheckedChangeListener(compoundButtonListener);

        //Listener del SeekBar
        SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Toast.makeText(MainActivity.this, "onProgressChanged. i: "+Integer.toString(i)+". b: "+Boolean.toString(b)+"Prgress: "+seekBar.getProgress(), Toast.LENGTH_SHORT).show();
                creditoInicial=i;
                tvCreditoInicial.setText(creditoInicialText+Float.toString(creditoInicial)+"("+seekBar.getProgress()+")"); //actualiza el text view que esta arriba de la seek bar para mostrar cuanto credito seteo
                //seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "onStartTrackingTouch:", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { //new
                //set monto inicial
                Toast.makeText(MainActivity.this, "onStopTrackingTouch, Credito asignado: "+Float.toString(creditoInicial), Toast.LENGTH_SHORT).show();

            }
        };
        sbCreditoInicial.setOnSeekBarChangeListener(seekBarListener);

        final Spinner.OnClickListener spinerListener = new Spinner.OnClickListener(){

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

        //FALTA HACER:
        //seekbar (mariano)
        //switch (Ger)
        //checkBox (cuelquiera)
        //pasar los detos leidos a variables (si es credito o debito, si acepto los terminos, el monto inicial, etc) //new
        //boton registrar: //validar paswords, etc (cuando esten los otros 3)
        //models (cualqueira)
        //persistir (cualqueira)







    }

    private boolean validacionesLogIn() { //new
        return true;
    }




}