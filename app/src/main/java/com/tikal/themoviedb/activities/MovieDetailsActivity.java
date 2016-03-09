package com.tikal.themoviedb.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tikal.themoviedb.R;
import com.tikal.themoviedb.fragments.MovieDetailsFragment;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public class MovieDetailsActivity extends AppCompatActivity {

    private Integer mMovieId;
    private MovieDetailsFragment mMovieDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String movieTitle = getIntent().getStringExtra(MovieDetailsFragment.MOVIE_TITLE_KEY);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(movieTitle);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mMovieId = getIntent().getIntExtra(MovieDetailsFragment.MOVIE_ID_KEY,-1);
        mMovieDetailsFragment = (MovieDetailsFragment) getFragmentManager().findFragmentById(R.id.movieDetailsFragment);
        mMovieDetailsFragment.setMovieId(mMovieId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void launchActivity(Context context,String movieTitle,Integer movieId){
        Intent intent = new Intent(context,MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsFragment.MOVIE_TITLE_KEY,movieTitle);
        intent.putExtra(MovieDetailsFragment.MOVIE_ID_KEY,movieId);
        context.startActivity(intent);
    }
}
