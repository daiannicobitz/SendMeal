package bitzgutierrezmaglianesi.sendmeal.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bitzgutierrezmaglianesi.sendmeal.R;

public class AltaPlato extends AppCompatActivity {
    private EditText text_titulo;
    private EditText text_descripcion;
    private EditText text_precio;
    private EditText text_calorias;

    private Button button_Guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_plato);
        text_titulo = findViewById(R.id.titulo);
        text_descripcion=findViewById(R.id.descripcion);
        text_precio = findViewById(R.id.precio);
        text_calorias = findViewById(R.id.calorias);

        button_Guardar = findViewById(R.id.button_Guardar);
        button_Guardar.setOnClickListener(buttonGuardar());
        button_Guardar.setEnabled(false);





    }

    public View.OnClickListener buttonGuardar(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validarDatosPlato();

            }
        };
    }



    public void validarDatosPlato(){

        if(text_titulo.getText().toString().isEmpty()){
            text_titulo.setError(mensajeCampoIncompleto());
        }
        else if(text_descripcion.getText().toString().isEmpty()){
            text_descripcion.setError(mensajeCampoIncompleto());
        }
        else if(text_precio.getText().toString().isEmpty()){
            text_precio.setError(mensajeCampoIncompleto());
        }
        else if(text_calorias.getText().toString().isEmpty()){
            text_calorias.setError(mensajeCampoIncompleto());
        }


        darDeAltaPlato();

    }

    public void darDeAltaPlato(){
        String titulo = text_titulo.getText().toString();
        String descripcion = text_descripcion.getText().toString();
        Double precio = Double.parseDouble(text_precio.getText().toString().trim());
        Integer calorias = Integer.parseInt(text_calorias.getText().toString().trim());

        Plato nuevo_plato = new Plato(titulo, descripcion,precio,calorias);

    }

    public String mensajeCampoIncompleto(){
        return "Este campo debe estar completo.";
    }
}