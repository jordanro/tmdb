package com.tikal.themoviedb.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tikal.themoviedb.R;
import com.tikal.themoviedb.api.MdbApi;
import com.tikal.themoviedb.models.AppendToResponse;
import com.tikal.themoviedb.models.AppendToResponseItem;
import com.tikal.themoviedb.models.Movie;
import com.tikal.themoviedb.ui.moviedetails.MovieDetailsAdapter;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public class MovieDetailsFragment extends Fragment {

    public static final String MOVIE_ID_KEY = "movieId";
    public static final String MOVIE_TITLE_KEY = "movieTitle";

    private Integer mMovieId = null;
    private String mMovieTitle = null;
    private RecyclerView mMovieDetailsList;
    private LinearLayoutManager mLinearLayoutManager;
    private View mProgressIndicator;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            mMovieId = savedInstanceState.getInt(MOVIE_ID_KEY);
            mMovieTitle = savedInstanceState.getString(MOVIE_TITLE_KEY);
        }
        else if(getArguments() != null){
            mMovieId = getArguments().getInt(MOVIE_ID_KEY);
            mMovieTitle =  getArguments().getString(MOVIE_TITLE_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_details_fragment,container,false);
        mMovieDetailsList = (RecyclerView)view.findViewById(R.id.detailsList);
        mLinearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mMovieDetailsList.setLayoutManager(mLinearLayoutManager);
        mProgressIndicator =  view.findViewById(R.id.progressIndicator);
        if(mMovieId != null){
            loadMovie();
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(MOVIE_ID_KEY, mMovieId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void setMovieId(int movieId){
        this.mMovieId = movieId;
        loadMovie();
    }

    private void loadMovie(){
        toggleProgressIndicatorState(true);
        MdbApi.moviesService().details(mMovieId, "en",new AppendToResponse(AppendToResponseItem.VIDEOS), new Callback<Movie>() {
            @Override
            public void success(Movie movie, Response response) {
                toggleProgressIndicatorState(false);
                mMovieDetailsList.setAdapter(new MovieDetailsAdapter(getActivity(),movie,mMovieTitle != null));
            }

            @Override
            public void failure(RetrofitError error) {
                toggleProgressIndicatorState(false);
            }
        });
    }

    private void toggleProgressIndicatorState(boolean turnOn){
        mProgressIndicator.setVisibility(turnOn ? View.VISIBLE : View.GONE);
    }
}
