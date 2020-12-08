package ar.com.leventalgarcia.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.com.leventalgarcia.sendmeal.Dominio.Usuario;

import static ar.com.leventalgarcia.sendmeal.R.id.ET_NumTarjeta;

public class RegistrarActivity extends AppCompatActivity{ //implements View.OnClickListener
//public class MainActivity extends AppCompatActivity implements  View.OnClickListener{ //Si lo hubiesemos hecho sin clases anonimas e implementando View.OnClickListener 1/3

    public static final String actividad = "RegistrarActivity"; //Log.d(HomeActivity.TAG_CICLO_VIDA,actividad+".OnCreate()");


    //Variables widgets:
    EditText userName, userPass1, userPass2,userMail,numTarj,ccv,cbu,aliasCBU;
    TextView tvTipoTarjeta,tvFechaVto,tvCreditoInicial;
    RadioGroup rgTipoTarjeta;
    RadioButton rbDebito,rbCredito;
    Switch swCargaInicial;
    SeekBar sbCreditoInicial;
    CheckBox cbAceptoTerm;
    Button btnRegistrar, btnVolver;
    Spinner spinnerMesVencimiento,spinnerAnioVencimiento;
    private List<String> listaMeses;
    private List<String> listaAnios;



    //Variables datos:
    String userNameValue;
    String userPass1Value;
    String userPass2Value;
    String userMailValue;
    String numTarjetaValue;
    Integer ccvValue;
    String cbuValue;
    String aliasCbuValue;
    Integer creditoInicial;
    String creditoInicialText = "Credito Inicial: ";
    Boolean terminosAceptados=false;
    Boolean conCargaInicial=false;
    Boolean tipoCredito = false;
    Boolean tipoDebito = false;
    Boolean hacerValidaciones=true;
    String vtoMesValue;
    String vtoAnioValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(HomeActivity.TAG_CICLO_VIDA,actividad+".OnCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registrarme); //define mediante que layout se va a mostrar



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
        btnRegistrar = (Button) findViewById(R.id.BTN_Registrar);
        ccv = (EditText) findViewById(R.id.ET_CCVTarj);
        numTarj = (EditText) findViewById(ET_NumTarjeta);
        spinnerMesVencimiento = (Spinner) findViewById(R.id.spinnerMes);
        spinnerAnioVencimiento = (Spinner) findViewById(R.id.spinnerAnio);
        btnVolver = (Button) findViewById(R.id.BTN_Volver);


        listaMeses=new ArrayList<String>(Arrays.asList("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"));
        listaAnios = new ArrayList<String>(Arrays.asList("2020","2021","2022","2023","2024","2025","2026"));


        //Adapters
        ArrayAdapter<String> mesesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listaMeses);
        mesesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item); //este layout te los muestra uno arriba del otro sin interlineado, se ve medio feo
        mesesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //este layout te lo muestra mas lindo, se ve con interlineado entre medio
        spinnerMesVencimiento.setAdapter(mesesAdapter);

        ArrayAdapter<String> aniosAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listaAnios);
        spinnerAnioVencimiento.setAdapter(aniosAdapter);

        //Deshabilitar cosas
        //ccv ya esta en el XML
        sbCreditoInicial.setVisibility(View.GONE); //new
        tvCreditoInicial.setVisibility(View.GONE); //new
        btnRegistrar.setEnabled(false); //new
        spinnerMesVencimiento.setEnabled(false);
        spinnerAnioVencimiento.setEnabled(false);


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
                                Toast.makeText(RegistrarActivity.this, "Error en el Listener del radio group...", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(RegistrarActivity.this, "rgListener: tipo: "+(tipoDebito?"Debito":"Credito"), Toast.LENGTH_SHORT).show();
                    }
                };
        rgTipoTarjeta.setOnCheckedChangeListener(rgListener);

        //Listener de los Views: Botonoes, etc
        View.OnClickListener viewsListener = new View.OnClickListener() { //new
            @Override
            public void onClick(View view) {
                Toast.makeText(RegistrarActivity.this, "viewsListener:", Toast.LENGTH_SHORT).show();
                switch (view.getId()){
                    case R.id.BTN_Registrar:
                        if(validacionesLogIn()){
                            if(hacerValidaciones){
                                //Toast.makeText(MainActivity.this, "Registrando...", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(RegistrarActivity.this, "Registrando... (No se hicieron las validaciones...)", Toast.LENGTH_LONG).show();
                            }
                            //Agregar nuevo usuario a la lista de usuarios registrados:

                            Usuario nuevoUsuario = new Usuario(userNameValue, userPass1Value, userMailValue, rbCredito.isChecked(),numTarjetaValue,ccvValue,vtoMesValue,vtoAnioValue,cbuValue,aliasCbuValue,creditoInicial);
                            //Usuario(String nombre, String contrasenia, String mail, Boolean deCredito, String numeroTarjeta, Integer ccv, String vencimientoMes, String vencimientoAnio, String cbu, String aliasCbu, Integer creditoTarjeta){
                            HomeActivity.listaUsuarios.add(nuevoUsuario);

                            //intent que lleve a la pantalla siguiente
                            Intent i = new Intent(RegistrarActivity.this,HomeActivity.class);
                            i.putExtra("userName",userName.getText().toString());
                            setResult(Activity.RESULT_OK,i);
                            finish();
                        }

                        
                        else{
                            //Toast.makeText(MainActivity.this, "Debes completar correctamente todos los campos", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case R.id.BTN_Volver:
                        finish();
                        break;
                    default:
                        Toast.makeText(RegistrarActivity.this, "Ningun id encontrado en el switch{}...", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        btnRegistrar.setOnClickListener(viewsListener);
        btnVolver.setOnClickListener(viewsListener);

        //Listener de los Compund Buttons:
        CompoundButton.OnCheckedChangeListener compoundButtonListener = new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(RegistrarActivity.this, "compoundButtonListener:", Toast.LENGTH_SHORT).show();
                switch (compoundButton.getId()){ //new

                    case (R.id.SW_CargaInicial): //Si toco el SwitchButton:
                        if(b) {
                            conCargaInicial=true;
                            Toast.makeText(RegistrarActivity.this, "Activando carga inicial", Toast.LENGTH_SHORT).show();
                            sbCreditoInicial.setVisibility(View.VISIBLE);
                            sbCreditoInicial.setEnabled(true);
                            tvCreditoInicial.setVisibility(View.VISIBLE);
                        }
                        else{
                            conCargaInicial=false;
                            Toast.makeText(RegistrarActivity.this, "Desactivando carga inicial", Toast.LENGTH_SHORT).show();
                            sbCreditoInicial.setVisibility(View.GONE);
                            tvCreditoInicial.setVisibility(View.GONE);
                        }
                        break;
                    case (R.id.CB_AcetaTerminos):
                        if(b){
                            terminosAceptados=true;
                            btnRegistrar.setEnabled(true);
                        }
                        else{
                            terminosAceptados=false;
                            btnRegistrar.setEnabled(false);
                        }
                        Toast.makeText(RegistrarActivity.this, terminosAceptados?"Terminos aceptados!":"Terminos no aceptados!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(RegistrarActivity.this, "Ningun id encontrado en el switch{}...", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(RegistrarActivity.this, "onProgressChanged. i: "+Integer.toString(i)+". b: "+Boolean.toString(b)+"Prgress: "+seekBar.getProgress(), Toast.LENGTH_SHORT).show();
                if(i>0 && i<=1500) {
                    creditoInicial = i;
                    tvCreditoInicial.setText(creditoInicialText+Float.toString(creditoInicial)+"("+seekBar.getProgress()+")"); //actualiza el text view que esta arriba de la seek bar para mostrar cuanto credito seteo
                }
                else{
                    Toast.makeText(RegistrarActivity.this, "Seleccione un Credito inicial entre 0 y 1500", Toast.LENGTH_SHORT).show();
                }
                //seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(RegistrarActivity.this, "onStartTrackingTouch:", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { //new
                //set monto inicial
                Toast.makeText(RegistrarActivity.this, "onStopTrackingTouch, Credito asignado: "+Float.toString(creditoInicial), Toast.LENGTH_SHORT).show();

            }
        };
        sbCreditoInicial.setOnSeekBarChangeListener(seekBarListener);

        final Spinner.OnClickListener spinerListener = new Spinner.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(RegistrarActivity.this, "spinerListener:", Toast.LENGTH_SHORT).show();
            }
        };


        //Listener de los editText:
        TextWatcher textwatcherListener = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(RegistrarActivity.this, "onTextChanged", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
                Toast.makeText(RegistrarActivity.this, "beforeTextChanged", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(RegistrarActivity.this, "afterTextChanged", Toast.LENGTH_SHORT).show();

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
        if(hacerValidaciones) {
            if (userName.getEditableText().toString().isEmpty()) {
                Toast.makeText(RegistrarActivity.this, "Debe completar el nombre de usuario...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (userPass1.getEditableText().toString().isEmpty()) {
                Toast.makeText(RegistrarActivity.this, "Debe completar la contraseña...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (userPass2.getEditableText().toString().isEmpty()) {
                Toast.makeText(RegistrarActivity.this, "Debe repetir la contraseña...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!userPass1.getEditableText().toString().equals(userPass2.getEditableText().toString())) {
                Toast.makeText(RegistrarActivity.this, "Las contraseñas deben ser iguales...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (userMail.getEditableText().toString().isEmpty()) {
                Toast.makeText(RegistrarActivity.this, "Debe completar el mail...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!userMail.getEditableText().toString().matches("([a-zA-Z0-9]+)@([a-zA-Z0-9]{3}).com")){
                //"([a-zA-Z0-9]*)@([a-zA-Z0-9]{3})")) {
                //"^[_\\.0-9a-zA-Z-]+@([0-9a-zA-Z][0-9a-zA-Z-]+\\.)+[a-zA-Z]{2,6}$"
                Toast.makeText(RegistrarActivity.this, "Mail invalido...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if ((!tipoCredito && !tipoDebito) || (tipoCredito && tipoDebito)) {
                Toast.makeText(RegistrarActivity.this, "Seleccione el tipo de tarjeta: Credito o Debito", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (numTarj.getEditableText().toString().length() != 16) {
                Toast.makeText(RegistrarActivity.this, "El numero de tarjeta debe tener 16 digitos...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (ccv.getEditableText().toString().length() != 3) {
                Toast.makeText(RegistrarActivity.this, "El numero CCV debe tener 3 digitos...", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (cbu.getEditableText().toString().length() != 22) {
                Toast.makeText(RegistrarActivity.this, "El CBU debe tener 22 digitos...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (aliasCBU.getEditableText().toString().isEmpty()) {
                Toast.makeText(RegistrarActivity.this, "Debe completar el Alias del CBU...", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (conCargaInicial) {
                if (creditoInicial == 0) {
                    Toast.makeText(RegistrarActivity.this, "Asigne un credito inicial deslizando la barra...", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (creditoInicial < 0 || creditoInicial > 1500) {
                    Toast.makeText(RegistrarActivity.this, "El credito inicial debe ser mayor a 0 y menor o igual a 1500", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }

            //Fin de las validaciones...

            //Persistir
            if(!persistirDatos()) {
                Toast.makeText(RegistrarActivity.this, "Algo salio mal al persistir los datos...", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        Toast.makeText(RegistrarActivity.this, "Login correcto...", Toast.LENGTH_SHORT).show();
        return true;


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
            vtoMesValue = spinnerMesVencimiento.getSelectedItem().toString();
            vtoAnioValue = spinnerAnioVencimiento.getSelectedItem().toString();
            //vtoMesValue = Integer.parseInt(vtoMes.getText().toString());
            //vtoAnioValue = Integer.parseInt(vtoAnio.getText().toString());
            cbuValue = cbu.getText().toString();
            //cbuValue = Integer.parseInt(cbu.getText().toString()); //es muy largo para un integer!
            aliasCbuValue = aliasCBU.getText().toString();
            creditoInicial += sbCreditoInicial.getProgress();
            Toast.makeText(RegistrarActivity.this, "Datos persistidos!", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e) {
            Toast.makeText(RegistrarActivity.this, "Error :"+e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }

    }




}
