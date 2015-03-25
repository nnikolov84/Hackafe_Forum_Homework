package com.hackafe.nikola.sunshine;

import android.app.Application;
import android.content.Context;

/**
 * Created by Nikola on 24.3.2015 г..
 */
public class MyApplication extends Application {

    private static Context context;

    public void onCreate(){
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
