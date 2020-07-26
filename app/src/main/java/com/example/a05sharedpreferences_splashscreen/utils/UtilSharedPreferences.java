package com.example.a05sharedpreferences_splashscreen.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UtilSharedPreferences {

    private static SharedPreferences preferences;

    public static void borrarSharedPreferences() {
        preferences.edit().clear().apply();
    }

    public static String obtenerDatosDeSharedPreferences(String key){
        return preferences.getString(key, "");
    }

    public static void grabarEnSharedPreferences(String email, String clave) {
        //Edición o grabado de datos en SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constantes.KEY_LOGIN_EMAIL, email);
        editor.putString(Constantes.KEY_LOGIN_CLAVE, clave);
        //editor.commit(); // devuelve un boolean, es síncrono, puede bloquear la ejecución del programa
        editor.apply(); //devuelve un void, es asíncrono, puede requerirse un valor que aún no se ha guardado
    }

    public static void initSharedPreferences(Context context) {
        preferences = context.getSharedPreferences(Constantes.KEY_PREFERENCES, Context.MODE_PRIVATE);
    }
}
