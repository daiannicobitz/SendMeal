package bitzgutierrezmaglianesi.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.Touch;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.*;

import bitzgutierrezmaglianesi.sendmeal.model.CuentaBancaria;
import bitzgutierrezmaglianesi.sendmeal.model.Tarjeta;
import bitzgutierrezmaglianesi.sendmeal.model.Usuario;

public class MainActivity extends AppCompatActivity {

    private EditText txt_nombre;
    private EditText txt_clave;
    private EditText txt_validar_clave;
    private EditText txt_email;
    private EditText txt_numero_tarjeta;
    private EditText txt_ccv;
    private EditText txt_cbu;
    private EditText txt_alias_cbu;
    private EditText txt_mes;
    private EditText txt_anio;
    private RadioButton rb_debito;
    private RadioButton rb_credito;
    private RadioGroup radioGroup;
    private SeekBar sb_Credito;
    private Switch switch_CargaInicial;
    private TextView textVw_CargaInicial;
    private CheckBox checkBox_terminosYCondiciones;
    private Button button_Registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtener referencias a los widgets
        radioGroup = (RadioGroup)  findViewById(R.id.radioGroup);
        rb_debito = (RadioButton) findViewById(R.id.radioButton_debito);
        rb_credito = (RadioButton) findViewById(R.id.radioButton_credito);
        txt_nombre = (EditText) findViewById(R.id.plainTextNombreApellido);
        txt_clave = (EditText) findViewById(R.id.password_contraseña);
        txt_validar_clave = (EditText) findViewById(R.id.password_confirmar_contrasenia);
        txt_email = (EditText) findViewById(R.id.e_mail);
        txt_numero_tarjeta = (EditText) findViewById(R.id.number_numero_tarjeta);
        txt_ccv = (EditText) findViewById(R.id.number_CCV);
        txt_ccv.setEnabled(false);
        txt_mes = (EditText) findViewById(R.id.number_mes);
        txt_mes.setEnabled(false);
        txt_anio = (EditText) findViewById(R.id.number_anio);
        txt_anio.setEnabled(false);
        txt_cbu = (EditText) findViewById(R.id.plainText_CBU);
        txt_alias_cbu = (EditText) findViewById(R.id.plainText_AliasCBU);
        sb_Credito = (SeekBar) findViewById(R.id.seekBar_CreditoInicial);
        switch_CargaInicial = (Switch) findViewById(R.id.switchCargaInicial);
        textVw_CargaInicial = (TextView) findViewById(R.id.textView_ValorCargaInicial);
        checkBox_terminosYCondiciones = (CheckBox) findViewById(R.id.checkBox_TerminosYCondiciones);

        button_Registrar = (Button) findViewById(R.id.button_Registrar);
        button_Registrar.setOnClickListener(buttonRegistrar());
        button_Registrar.setEnabled(false);

        //verifica si el switch esta activado o no y hace visible o invisible el seekbar
        switch_CargaInicial.setOnCheckedChangeListener(returnOnCheckedChangeListenerCargaInicial());
        //verifica si el campo de numero de tarjeta esta vacio
        txt_numero_tarjeta.addTextChangedListener(returnTextWatcher());
        //verifica si terminos y condiciones esta activado o no y habilita o deshabilita el boton registrar
        checkBox_terminosYCondiciones.setOnCheckedChangeListener(returnOnCheckedChangeListenerTerminosYCondiciones());
        //setear el valor que se selecciona con el seekbar en el textvw_cargainicial
        sb_Credito.setOnSeekBarChangeListener(returnOnSeekBarChangeListenerCargaInicial());


    }

    public View.OnClickListener buttonRegistrar(){

            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    validarDatosRegistro();

                }
            };
    }


    public void validarDatosRegistro(){

        String nombre = txt_nombre.getText().toString();
        String clave = txt_clave.getText().toString();
        String claveConfirmada = txt_validar_clave.getText().toString();
        String email = txt_email.getText().toString();
        String alias_cbu = txt_alias_cbu.getText().toString();
        String cbu = txt_cbu.getText().toString();
        String numero_tarjeta = txt_numero_tarjeta.getText().toString();
        String ccv = txt_ccv.getText().toString();
        String mes = txt_mes.getText().toString();
        String anio = txt_anio.getText().toString();

        validarComponentes(clave,claveConfirmada);

    }

    public boolean validarContrasenias(String clave,String claveConfirmada){
        return clave.equals(claveConfirmada);
    }

    public void validarSlider(){

            if(textVw_CargaInicial.getText().toString().equals("Carga Inicial")){
                Toast.makeText(this, "Debe determinar un valor de carga.", Toast.LENGTH_LONG).show();
            }else if (Integer.parseInt(textVw_CargaInicial.getText().toString()) == 0) {
                Toast.makeText(this, "¡La carga debe ser mayor a 0!", Toast.LENGTH_LONG).show();
            }else Toast.makeText(this, "El usuario fue registrado con éxito!", Toast.LENGTH_LONG).show();
    }

    public void validarComponentes(String clave,String claveConfirmada){

        if(txt_email.getText().toString().isEmpty()){
            txt_email.setError(mensajeCampoIncompleto());
        }
        else if(!correoValido(txt_email.getText().toString())){
            txt_email.setError("Formato inválido");
        }
        else if(txt_clave.getText().toString().isEmpty()){
            txt_clave.setError(mensajeCampoIncompleto());
        }else if(!validarContrasenias(clave,claveConfirmada)){
            txt_validar_clave.setError("Las claves no coinciden.");
        }else if (txt_numero_tarjeta.getText().toString().isEmpty()){
            txt_numero_tarjeta.setError(mensajeCampoIncompleto());
        }else if(txt_ccv.getText().toString().isEmpty()){
            txt_ccv.setError(mensajeCampoIncompleto());
        }else if(txt_mes.getText().toString().isEmpty()){
            txt_mes.setError(mensajeCampoIncompleto());
        }else if(txt_anio.getText().toString().isEmpty()){
            txt_anio.setError(mensajeCampoIncompleto());
        }
        else if (rb_credito.isChecked() && !tarjetaVigente(txt_mes.getText().toString(),txt_anio.getText().toString())){
            txt_mes.setError("Tarjeta proxima a vencer");
        }else if(radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "¡Debe seleccionar un tipo de tarjeta!", Toast.LENGTH_LONG).show();
        }else if(switch_CargaInicial.isChecked()) {
            validarSlider();
        }else{
            Toast.makeText(this, "El usuario fue registrado con éxito!", Toast.LENGTH_LONG).show();
        }
    }



    public CompoundButton.OnCheckedChangeListener returnOnCheckedChangeListenerCargaInicial(){

        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    sb_Credito.setVisibility(View.VISIBLE);
                    textVw_CargaInicial.setVisibility(View.VISIBLE);
                }
                else {
                    sb_Credito.setVisibility(View.GONE);
                    textVw_CargaInicial.setVisibility(View.GONE);
                }
            }
        };

    }

    public CompoundButton.OnCheckedChangeListener returnOnCheckedChangeListenerTerminosYCondiciones(){

        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) button_Registrar.setEnabled(true);
                else button_Registrar.setEnabled(false);
            }
        };

    }

    public SeekBar.OnSeekBarChangeListener returnOnSeekBarChangeListenerCargaInicial(){

        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                textVw_CargaInicial.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textVw_CargaInicial.setText("Carga Inicial");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

    }

    public TextWatcher returnTextWatcher(){
        TextWatcher retorno = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    txt_ccv.setEnabled(false);
                    txt_mes.setEnabled(false);
                    txt_anio.setEnabled(false);
                } else {
                    txt_ccv.setEnabled(true);
                    txt_mes.setEnabled(true);
                    txt_anio.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("")) {
                    txt_ccv.setEnabled(false);
                    txt_mes.setEnabled(false);
                    txt_anio.setEnabled(false);
                } else {
                    txt_ccv.setEnabled(true);
                    txt_mes.setEnabled(true);
                    txt_anio.setEnabled(true);
                }
            }
        };
        return retorno;

    }

    public String mensajeCampoIncompleto(){
        return "Este campo debe estar completo.";
    }

    public boolean correoValido(String correo){
        //creo la expresion regular para el correo
        Pattern pat = Pattern.compile("([a-zA-Z0-9./-_])*@[a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9]*$");
        // comparo con la expresion escrita
        Matcher match = pat.matcher(correo);
        //devuelvo si cumple el patron o no;
        return match.find();
    }

    public boolean tarjetaVigente(String mesTarjeta, String anioTarjeta){
        Date fecha = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(fecha);
        int mesActual = c.get(Calendar.MONTH);
        int anioActual = c.get(Calendar.YEAR);
        int diferencia = (Integer.parseInt(anioTarjeta)*12+Integer.parseInt(mesTarjeta)) - (anioActual*12+mesActual+1);
        return diferencia>3;
    }

}