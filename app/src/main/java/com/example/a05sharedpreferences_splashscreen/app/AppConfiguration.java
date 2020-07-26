package com.example.a05sharedpreferences_splashscreen.app;

import android.app.Application;
import android.os.SystemClock;

public class AppConfiguration extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Durmiendo al sistema 3seg para ver el splash
        SystemClock.sleep(3000);
    }
}
