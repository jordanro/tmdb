package com.tikal.themoviedb.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.tikal.themoviedb.R;
import com.tikal.themoviedb.api.MdbApi;
import com.tikal.themoviedb.app.AppData;
import com.tikal.themoviedb.models.Configuration;
import com.tikal.themoviedb.util.OSUtil;

import java.util.Timer;
import java.util.TimerTask;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public class IntroActivity extends Activity {

    private boolean isTablet = OSUtil.isTablet();

    Timer splashTimer = new Timer();
    boolean splashTimerEnded, configurationDataLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityOrientation();
        setContentView(R.layout.activity_intro);

        loadConfiguration();

        splashTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                splashTimerEnded = true;
                continueSplash();
            }
        }, 2000);
    }

    private void setActivityOrientation(){

        if(isTablet){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }



    private void loadConfiguration(){
        MdbApi.configurationService().configuration(new Callback<Configuration>() {
            @Override
            public void success(Configuration configuration, Response response) {
                AppData.setConfiguration(configuration);
                configurationDataLoaded = true;
                continueSplash();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    private void continueSplash() {
        if(splashTimerEnded && configurationDataLoaded){
            MainActivity.launchActivity(this);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
