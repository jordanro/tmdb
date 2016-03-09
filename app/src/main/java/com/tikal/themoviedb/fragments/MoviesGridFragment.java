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
        mMovieGrid.addOnScrollListener(onScrollListener);
        mProgressIndicator =  view.findViewById(R.id.progressIndicator);
        loadPage(mCurrentPage);
        return view;
    }

    private void loadPage(int page) {
        MdbApi.moviesService().popular(page, new Callback<MovieResultsPage>() {
            @Override
            public void success(MovieResultsPage movieResultsPage, Response response) {
                onMovieListLoaded(movieResultsPage.results);
                mLoading = false;
            }

            @Override
            public void failure(RetrofitError error) {
                toggleProgressIndicatorState(false);
                mLoading = false;
            }
        });
    }

    private void onMovieListLoaded(List<Movie> movies){
        final Activity activity = getActivity();
        ((MovieGridController)activity).onMovieListLoaded(movies.isEmpty()? null : movies.get(0));
        if(mMoviesGridAdapter == null) {
            mMoviesGridAdapter = new MoviesGridAdapter(activity, movies);
            mMovieGrid.setAdapter(mMoviesGridAdapter);
        }
        else{
            mMoviesGridAdapter.addItems(movies);
        }
        toggleProgressIndicatorState(false);
    }

    private void toggleProgressIndicatorState(boolean turnOn){
        mProgressIndicator.setVisibility(turnOn ? View.VISIBLE : View.GONE);
    }

    private boolean mLoading = false;
    private int mCurrentPage = 1;

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {


        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if(dy > 0) //check for scroll down
            {
                int pastVisiblesItems, visibleItemCount, totalItemCount;

                visibleItemCount = mGridLayoutManager.getChildCount();
                totalItemCount = mGridLayoutManager.getItemCount();
                pastVisiblesItems = mGridLayoutManager.findFirstVisibleItemPosition();

                if (!mLoading)
                {
                    if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                    {
                        mLoading = true;
                        loadPage(++mCurrentPage);
                    }
                }
            }
        }
    };
}
