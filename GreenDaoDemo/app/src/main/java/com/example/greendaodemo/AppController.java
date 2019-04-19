package com.example.greendaodemo;

import android.app.Application;
import android.content.Context;

public class AppController extends Application {

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance= this;
    }

    public static AppController getInstance() {
        return mInstance;
    }

    public static void setmInstance(AppController mInstance) {
        AppController.mInstance = mInstance;
    }
}
