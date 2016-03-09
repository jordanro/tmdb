package com.tikal.themoviedb.util;

import com.tikal.themoviedb.MovieDBApplication;
import com.tikal.themoviedb.R;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public class OSUtil {

    public static boolean isTablet(){
        return MovieDBApplication.getAppContext().getResources().getBoolean(R.bool.isTablet);
//        boolean xlarge = ((MovieDBApplication.getAppContext().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
//        boolean large = ((MovieDBApplication.getAppContext().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);

//        boolean isTablet = false;
//        WindowManager wm = (WindowManager) MovieDBApplication.getAppContext().getSystemService(Context.WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
//        DisplayMetrics metrics = new DisplayMetrics();
//        display.getMetrics(metrics);
//
//        float widthInches = metrics.widthPixels / metrics.xdpi;
//        float heightInches = metrics.heightPixels / metrics.ydpi;
//        double diagonalInches = Math.sqrt(Math.pow(widthInches, 2) + Math.pow(heightInches, 2));
//        if (diagonalInches >= 7.0) {
//            isTablet = true;
//        }
//
//        return isTablet;
    }

}
