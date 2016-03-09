package com.tikal.themoviedb.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.tikal.themoviedb.MovieDBApplication;
import com.tikal.themoviedb.R;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public class OSUtil {

    public static boolean isTablet(){
        return MovieDBApplication.getAppContext().getResources().getBoolean(R.bool.isTablet);
    }

    public static boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) MovieDBApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
