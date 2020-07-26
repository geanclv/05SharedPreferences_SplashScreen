package com.example.a05sharedpreferences_splashscreen.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.a05sharedpreferences_splashscreen.utils.Constantes;
import com.example.a05sharedpreferences_splashscreen.utils.UtilSharedPreferences;

public class SplashActivity extends AppCompatActivity {

    /*Para crear el Splash:
    1. Creamos un drawable splash
    2. Creamos un style SplashScreen
    3. Creamos un activity sin layout SplashActivity
    4. Registramos nuestro SplashActivity como principal en el Manifest

    Mejora:
    - SharedPreferences se ha centralizado (menos en Main y Login) en la clase UtilSharedPreferences

    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UtilSharedPreferences.initSharedPreferences(this);

        if (tieneDatosAlmacenados()) {
            irAMain();
        } else {
            irALogin();
        }

        //Matando la instancia del activity para no regresar ni tenerlo en el historial
        finish();
    }

    private boolean tieneDatosAlmacenados() {
        if (!TextUtils.isEmpty(UtilSharedPreferences.obtenerDatosDeSharedPreferences(Constantes.KEY_LOGIN_EMAIL))
                && !TextUtils.isEmpty(UtilSharedPreferences.obtenerDatosDeSharedPreferences(Constantes.KEY_LOGIN_CLAVE))){
            return true;
        } else {
            return false;
        }
    }

    private void irAMain() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void irALogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
