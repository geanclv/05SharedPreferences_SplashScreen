package com.example.a05sharedpreferences_splashscreen.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a05sharedpreferences_splashscreen.R;
import com.example.a05sharedpreferences_splashscreen.utils.Constantes;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSharedPreferences();
        initComponents();
        definirTituloActivity();
    }

    //Inicio Menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuCerrarSesion:
                cerrarSesion();
                return true;
            case R.id.mnuOlvidarDatos:
                borrarSharedPreferences();
                cerrarSesion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //Fin Menu de opciones

    private void cerrarSesion(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void borrarSharedPreferences(){
        preferences.edit().clear().apply();
    }

    private void definirTituloActivity(){
        setTitle("Página principal");
    }

    private void initComponents(){

    }

    private void initSharedPreferences(){
        preferences = getSharedPreferences(Constantes.KEY_PREFERENCES, Context.MODE_PRIVATE);
    }
}
