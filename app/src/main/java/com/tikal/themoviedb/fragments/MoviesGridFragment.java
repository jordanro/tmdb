package com.tikal.themoviedb.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tikal.themoviedb.R;
import com.tikal.themoviedb.api.MdbApi;
import com.tikal.themoviedb.models.Movie;
import com.tikal.themoviedb.models.MovieResultsPage;
import com.tikal.themoviedb.ui.moviegrid.MoviesGridAdapter;
import com.tikal.themoviedb.util.MovieGridController;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Yarden Rosenberg on 07/03/2016.
 */
public class MoviesGridFragment extends Fragment {

    private GridLayoutManager mGridLayoutManager;
    private MoviesGridAdapter mMoviesGridAdapter;
    private RecyclerView mMovieGrid;

    private View mProgressIndicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_grid_fragment,container);
        mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mMovieGrid = (RecyclerView)view.findViewById(R.id.movieGrid);
        mMovieGrid.setHasFixedSize(true);
        mMovieGrid.setLayoutManager(mGridLayoutManager);
        mProgressIndicator =  view.findViewById(R.id.progressIndicator);
        MdbApi.moviesService().popular(1, new Callback<MovieResultsPage>() {
            @Override
            public void success(MovieResultsPage movieResultsPage, Response response) {
                onMovieListLoaded(movieResultsPage.results);
            }

            @Override
            public void failure(RetrofitError error) {
                toggleProgressIndicatorState(false);
            }
        });
        return view;
    }

    private void onMovieListLoaded(List<Movie> movies){
        final Activity activity = getActivity();
        ((MovieGridController)activity).onMovieListLoaded(movies.isEmpty()? null : movies.get(0));
        mMoviesGridAdapter = new MoviesGridAdapter(activity,movies);
        mMovieGrid.setAdapter(mMoviesGridAdapter);
        toggleProgressIndicatorState(false);
    }

    private void toggleProgressIndicatorState(boolean turnOn){
        mProgressIndicator.setVisibility(turnOn ? View.VISIBLE : View.GONE);
    }
}
