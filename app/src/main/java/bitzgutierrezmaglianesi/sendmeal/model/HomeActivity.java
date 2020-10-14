package bitzgutierrezmaglianesi.sendmeal.model;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import bitzgutierrezmaglianesi.sendmeal.R;

public class HomeActivity extends AppCompatActivity {
    Toolbar toolBar;
    Toolbar crearItem;
    Toolbar listaItems;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

    }
}