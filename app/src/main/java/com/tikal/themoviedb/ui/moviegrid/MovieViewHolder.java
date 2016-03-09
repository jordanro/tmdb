package com.tikal.themoviedb.ui.moviegrid;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tikal.themoviedb.R;

/**
 * Created by Yarden Rosenberg on 07/03/2016.
 */
public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView title;
    public ImageView image;
    private  MoviesGridAdapter mMoviesGridAdapter;

    public MovieViewHolder(View itemView, MoviesGridAdapter moviesGridAdapter) {
        super(itemView);
        itemView.setOnClickListener(this);
        title = (TextView) itemView.findViewById(R.id.title);
        image = (ImageView) itemView.findViewById(R.id.image);
        mMoviesGridAdapter = moviesGridAdapter;
    }

    @Override
    public void onClick(View view) {
        mMoviesGridAdapter.onMovieClicked(getAdapterPosition());
    }

}
