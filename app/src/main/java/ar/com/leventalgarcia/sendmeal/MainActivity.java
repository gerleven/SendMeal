    package ar.com.leventalgarcia.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
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

import java.util.Date;

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
    EditText userName, userPass1, userPass2,userMail,numTarj,ccv,cbu,aliasCBU,vtoMes,vtoAnio;
    TextView tvTipoTarjeta,tvFechaVto,tvCreditoInicial;
    RadioGroup rgTipoTarjeta;
    RadioButton rbDebito,rbCredito;
    Switch swCargaInicial;
    SeekBar sbCreditoInicial;
    CheckBox cbAceptoTerm;
    Button btnRregistrar;
    Spinner spinnerMesVencimiento,spinnerAnioVencimiento;

    //Variables datos:
    String userNameValue;
    String userPass1Value;
    String userPass2Value;
    String userMailValue;
    String numTarjetaValue; 
    Integer ccvValue;
    String cbuValue;
    String aliasCbuValue;
    float creditoInicial;
    String creditoInicialText = "Credito Inicial: ";
    Boolean terminosAceptados=false;
    Boolean conCargaInicial=false;
    Boolean tipoCredito = false;
    Boolean tipoDebito = false;
    Boolean hacerValidaciones=true;
    Integer vtoMesValue;
    Integer vtoAnioValue;

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
        spinnerMesVencimiento = (Spinner) findViewById(R.id.spinnerMes);
        spinnerAnioVencimiento = (Spinner) findViewById(R.id.spinnerAnio);
        vtoMes= (EditText) findViewById(R.id.ET_fechaVtoMes);
        vtoAnio= (EditText) findViewById(R.id.ET_fechaVtoAnio);
        final String[] meses = {"Enero","Febrero","Marzo"};

        //Adapters
        ArrayAdapter<String> mesesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,meses);
        mesesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item); //este layout te los muestra uno arriba del otro sin interlineado, se ve medio feo
        mesesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //este layout te lo muestra mas lindo, se ve con interlineado entre medio

        spinnerMesVencimiento.setAdapter(mesesAdapter);

        //Deshabilitar cosas
        //ccv ya esta en el XML
        sbCreditoInicial.setVisibility(View.GONE); //new
        tvCreditoInicial.setVisibility(View.GONE); //new
        btnRregistrar.setEnabled(false); //new
        //spinnerMesVencimiento.setEnabled(false);
        //spinnerAnioVencimiento.setEnabled(false);
        vtoMes.setEnabled(false);
        vtoAnio.setEnabled(false);

        //Setup values:
        creditoInicial=0; //el credito inicial comienza en 0 hasta que se setee un valor en la seekbar
        tvCreditoInicial.setText(creditoInicialText+creditoInicial); //se actualiza el text view para que muestre el credito inicial actual



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
                            if(!hacerValidaciones){
                                Toast.makeText(MainActivity.this, "Registrando... (No se hicieron las validaciones...)", Toast.LENGTH_LONG).show();
                            }
                            else{
                                //Toast.makeText(MainActivity.this, "Registrando...", Toast.LENGTH_SHORT).show();
                            }
                            //intent que lleve a la pantalla siguiente
                        }
                        else{
                            //Toast.makeText(MainActivity.this, "Debes completar correctamente todos los campos", Toast.LENGTH_SHORT).show();
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
                            conCargaInicial=true;
                            Toast.makeText(MainActivity.this, "Activando carga inicial", Toast.LENGTH_SHORT).show();
                            sbCreditoInicial.setVisibility(View.VISIBLE);
                            sbCreditoInicial.setEnabled(true);
                            tvCreditoInicial.setVisibility(View.VISIBLE);
                        }
                        else{
                            conCargaInicial=false;
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
                if(i>0 && i<=1500) {
                    creditoInicial = i;
                tvCreditoInicial.setText(creditoInicialText+Float.toString(creditoInicial)+"("+seekBar.getProgress()+")"); //actualiza el text view que esta arriba de la seek bar para mostrar cuanto credito seteo
                }
                else{
                    Toast.makeText(MainActivity.this, "Seleccione un Credito inicial entre 0 y 1500", Toast.LENGTH_SHORT).show();
                }
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


        //Listener de los editText:
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
                        vtoMes.setEnabled(true);
                        vtoAnio.setEnabled(true);
                       // spinnerAnioVencimiento.setEnabled(true);
                        //spinnerMesVencimiento.setEnabled(true);
                    }
                    else {
                        vtoMes.setEnabled(false);
                        vtoAnio.setEnabled(false);
                        //spinnerAnioVencimiento.setEnabled(false);
                        //spinnerMesVencimiento.setEnabled(false);
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
        if(hacerValidaciones) {
            if (userName.getEditableText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Debe completar el nombre de usuario...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (userPass1.getEditableText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Debe completar la contraseña...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (userPass2.getEditableText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Debe repetir la contraseña...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!userPass1.getEditableText().toString().equals(userPass2.getEditableText().toString())) {
                Toast.makeText(MainActivity.this, "Las contraseñas deben ser iguales...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (userMail.getEditableText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Debe completar el mail...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!userMail.getEditableText().toString().matches("([a-zA-Z0-9]+)@([a-zA-Z0-9]{3}).com")){
                //"([a-zA-Z0-9]*)@([a-zA-Z0-9]{3})")) {
                //"^[_\\.0-9a-zA-Z-]+@([0-9a-zA-Z][0-9a-zA-Z-]+\\.)+[a-zA-Z]{2,6}$"
                Toast.makeText(MainActivity.this, "Mail invalido...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if ((!tipoCredito && !tipoDebito) || (tipoCredito && tipoDebito)) {
                Toast.makeText(MainActivity.this, "Seleccione el tipo de tarjeta: Credito o Debito", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (numTarj.getEditableText().toString().length() != 16) {
                Toast.makeText(MainActivity.this, "El numero de tarjeta debe tener 16 digitos...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (ccv.getEditableText().toString().length() != 3) {
                Toast.makeText(MainActivity.this, "El numero CCV debe tener 3 digitos...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (vtoMes.getEditableText().toString().length() != 2) {
                Toast.makeText(MainActivity.this, "El Mes debe tener 2 digitos...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (vtoAnio.getEditableText().toString().length() != 2) {
                Toast.makeText(MainActivity.this, "El Anio debe tener 2 digitos...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (cbu.getEditableText().toString().length() != 22) {
                Toast.makeText(MainActivity.this, "El CBU debe tener 22 digitos...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (aliasCBU.getEditableText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Debe completar el Alias del CBU...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (conCargaInicial) {
                if (creditoInicial == 0) {
                    Toast.makeText(MainActivity.this, "Asigne un credito inicial deslizando la barra...", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (creditoInicial < 0 || creditoInicial > 1500) {
                    Toast.makeText(MainActivity.this, "El credito inicial debe ser mayor a 0 y menor o igual a 1500", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        else{
            Toast.makeText(MainActivity.this, "Cuidado! las validaciones estan desactivadas!", Toast.LENGTH_LONG).show();
        }


        //Fin de las validaciones...

        //Persistir
        if(persistirDatos()) {
            Toast.makeText(MainActivity.this, "Login correcto...", Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(MainActivity.this, "Algo salio mal...", Toast.LENGTH_SHORT).show();
            return false;
        }
        
    }

    private boolean persistirDatos() {

        try {
            //Credito inicial ya esta hecho en el oyente del seekbar
            userNameValue = userName.getText().toString();
            userPass1Value = userPass1.getText().toString();
            userPass2Value = userPass2.getText().toString();
            userMailValue = userMail.getText().toString();
            numTarjetaValue = numTarj.getText().toString();
            //numTarjetaValue = Integer.parseInt(numTarj.getText().toString()); //es muy largo para un integer!
            ccvValue = Integer.parseInt(ccv.getText().toString());
            vtoMesValue = Integer.parseInt(vtoMes.getText().toString());
            vtoAnioValue = Integer.parseInt(vtoAnio.getText().toString());
            cbuValue = cbu.getText().toString();
            //cbuValue = Integer.parseInt(cbu.getText().toString()); //es muy largo para un integer!
            aliasCbuValue = aliasCBU.getText().toString();
            Toast.makeText(MainActivity.this, "Datos persistidos!", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Error :"+e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }

    }



}

/*//Falta:
    validaciones String para el mail
    validaciones
    agregar un maxleng a cbu y aliasCbu
*/