package com.example.a05sharedpreferences_splashscreen.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.a05sharedpreferences_splashscreen.R;
import com.example.a05sharedpreferences_splashscreen.utils.Constantes;

public class LoginActivity extends AppCompatActivity {

    //Manipulando las SharedPreferences
    private SharedPreferences preferences;

    private EditText txtEmail;
    private EditText txtClave;
    private Switch swcRecordar;
    private Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initSharedPreferences();
        initComponents();
        definirTituloActivity();

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String clave = txtClave.getText().toString();
                if(login(email, clave)) {
                    grabarEnSharedPreferences(email, clave);
                    accederAlSistema();
                }
            }
        });

        llenarCredencialesSiExisten();
    }

    private void llenarCredencialesSiExisten(){
        String email = obtenerDatosDeSharedPreferences(Constantes.KEY_LOGIN_EMAIL);
        String clave = obtenerDatosDeSharedPreferences(Constantes.KEY_LOGIN_CLAVE);

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(clave)){
            txtEmail.setText(email);
            txtClave.setText(clave);
            swcRecordar.setChecked(true);
        } else {
            swcRecordar.setChecked(false);
        }
    }

    private String obtenerDatosDeSharedPreferences(String key){
        return preferences.getString(key, "");
    }

    private void accederAlSistema(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        //evitando que cuando entre al sistema pueda presionar atras y regresar
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void grabarEnSharedPreferences(String email, String clave) {
        //Edición o grabado de datos en SharedPreferences
        if(swcRecordar.isChecked()){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(Constantes.KEY_LOGIN_EMAIL, email);
            editor.putString(Constantes.KEY_LOGIN_CLAVE, clave);
            //editor.commit(); // devuelve un boolean, es síncrono, puede bloquear la ejecución del programa
            editor.apply(); //devuelve un void, es asíncrono, puede requerirse un valor que aún no se ha guardado
        } else {
            borrarSharedPreferences();
        }
    }

    private void borrarSharedPreferences(){
        preferences.edit().clear().apply();
    }

    private boolean login (String email, String clave){
        if(!validarEmail(email)){
            mostrarAlerta("Email incorrecto");
            return false;
        } else if(!validarClave(clave)) {
            mostrarAlerta("Clave incorrecta");
            return false;
        } else {
            return true;
        }
    }

    private void mostrarAlerta(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    private boolean validarEmail(String email){
        if(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return true;
        else
            return false;
    }

    private boolean validarClave(String clave){
        return clave.length() > 4;
    }

    private void definirTituloActivity(){
        setTitle("Bienvenido");
    }

    private void initComponents(){
        txtEmail = findViewById(R.id.txtEmail);
        txtClave = findViewById(R.id.txtClave);
        swcRecordar = findViewById(R.id.swcRecordar);
        btnAceptar = findViewById(R.id.btnAceptar);
    }

    private void initSharedPreferences(){
        preferences = getSharedPreferences(Constantes.KEY_PREFERENCES, Context.MODE_PRIVATE);
    }
}
