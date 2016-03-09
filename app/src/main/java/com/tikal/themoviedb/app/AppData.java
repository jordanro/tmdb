package com.tikal.themoviedb.app;

import com.tikal.themoviedb.models.Configuration;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public class AppData {

    private Configuration mConfiguration;

    private static AppData mInstance;

    public synchronized static AppData getInstance(){
        if(mInstance == null){
            mInstance = new AppData();
        }
        return mInstance;
    }

    private AppData(){}

    public static Configuration getConfiguration(){
        return getInstance().mConfiguration;
    }

    public static void setConfiguration(Configuration configuration){
        getInstance().mConfiguration = configuration;
    }


}
