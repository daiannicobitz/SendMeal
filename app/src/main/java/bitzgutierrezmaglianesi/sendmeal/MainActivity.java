package bitzgutierrezmaglianesi.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        txt_cbu = (EditText) findViewById(R.id.plainText_CBU);
        txt_alias_cbu = (EditText) findViewById(R.id.plainText_AliasCBU);

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
            if (txt_validar_clave.equals(txt_clave)) {

            }


        }
}