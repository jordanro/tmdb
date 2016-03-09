package com.tikal.themoviedb;

import android.app.Application;
import android.content.Context;

/**
 * Created by Yarden Rosenberg on 07/03/2016.
 */
public class MovieDBApplication extends Application{

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public static Context getAppContext() {
        return appContext;
    }
}
