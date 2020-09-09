package bitzgutierrezmaglianesi.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import bitzgutierrezmaglianesi.sendmeal.model.CuentaBancaria;
import bitzgutierrezmaglianesi.sendmeal.model.Tarjeta;
import bitzgutierrezmaglianesi.sendmeal.model.Usuario;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
    private SeekBar sb_Credito;
    private Switch switch_CargaInicial;
    private TextView textVw_CargaInicial;
    private CheckBox checkBox_terminosYCondiciones;
    private Button button_Registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_nombre = (EditText) findViewById(R.id.plainText_nombre_apellido);
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
        button_Registrar.setOnClickListener(this);
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


    public void Registrar(View view){
            String nombre = txt_nombre.getText().toString();
            String clave = txt_clave.getText().toString();
            String email = txt_email.getText().toString();
            String alias_cbu = txt_alias_cbu.getText().toString();
            String cbu = txt_cbu.getText().toString();
            String numero_tarjeta = txt_numero_tarjeta.getText().toString();
            String ccv = txt_ccv.getText().toString();

        //Double credito;
        // Validaciones
         //   if (txt_validar_clave.equals(txt_clave)) {

           // }


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

    @Override
    public void onClick(View view) {

    }
}