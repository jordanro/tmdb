package com.tikal.themoviedb.ui.moviegrid;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.tikal.themoviedb.R;
import com.tikal.themoviedb.app.AppData;
import com.tikal.themoviedb.models.Configuration;
import com.tikal.themoviedb.models.Movie;
import com.tikal.themoviedb.util.MovieGridController;

import java.util.List;

/**
 * Created by Yarden Rosenberg on 07/03/2016.
 */
public class MoviesGridAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    public static final String TAG = MoviesGridAdapter.class.getSimpleName();

    private List<Movie> mItemList;
    private Context mContext;

    public MoviesGridAdapter(Context context, List<Movie> itemList) {
        this.mItemList = itemList;
        this.mContext = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(mContext).inflate(R.layout.grid_movie_item, null);
//        layoutView.getLayoutParams().width = parent.getWidth()/2;
        MovieViewHolder rcv = new MovieViewHolder(layoutView,MoviesGridAdapter.this);
        return rcv;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        final Configuration configuration = AppData.getConfiguration();
        final Movie movie = mItemList.get(position);
        holder.title.setText(movie.title);
        String path = configuration.images.base_url + "/" + configuration.getLargestPosterSize() +  movie.poster_path;
        Log.d(TAG,"Image path " + path);
        Picasso.with(mContext).load(Uri.parse(path)).placeholder(R.drawable.grid_placeholder).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return this.mItemList.size();
    }

    public void onMovieClicked(int position) {
        ((MovieGridController)mContext).onMovieClick(mItemList.get(position));
    }
}

