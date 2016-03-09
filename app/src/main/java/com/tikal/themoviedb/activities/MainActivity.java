package com.tikal.themoviedb.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tikal.themoviedb.R;
import com.tikal.themoviedb.fragments.MovieDetailsFragment;
import com.tikal.themoviedb.models.Movie;
import com.tikal.themoviedb.util.OSUtil;
import com.tikal.themoviedb.util.MovieGridController;

public class MainActivity extends AppCompatActivity implements MovieGridController {

    private boolean isTablet = OSUtil.isTablet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityOrientation();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    private void setActivityOrientation(){

        if(isTablet){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMovieClick(Movie movie) {
        if(isTablet){
            loadDetailFragment(movie);
        }
        else {
            MovieDetailsActivity.launchActivity(this, movie.title, movie.id);
        }
    }

    @Override
    public void onMovieListLoaded(Movie firstMovie) {
        if(isTablet){
            loadDetailFragment(firstMovie);
        }
    }

    public void loadDetailFragment(Movie movie){
        Bundle arguments = new Bundle();
        arguments.putInt(MovieDetailsFragment.MOVIE_ID_KEY, movie.id);
        arguments.putString(MovieDetailsFragment.MOVIE_TITLE_KEY, movie.title);
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(arguments);
        getFragmentManager().beginTransaction()
                .replace(R.id.movieDetailsFragment, fragment).commit();
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }
}
