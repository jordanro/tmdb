package com.tikal.themoviedb.ui.moviedetails;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.tikal.themoviedb.R;
import com.tikal.themoviedb.models.Movie;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public class HeaderViewHolder extends BaseViewHolder {

    private TextView title;
    public HeaderViewHolder(Context context,View itemView) {
        super(context,itemView);
        title = (TextView) itemView.findViewById(R.id.title);
    }

    public void bindData(Movie movie) {
        title.setText(movie.title);
    }
}
