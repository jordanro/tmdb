package com.tikal.themoviedb.ui.moviedetails;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tikal.themoviedb.R;
import com.tikal.themoviedb.app.AppData;
import com.tikal.themoviedb.models.Configuration;
import com.tikal.themoviedb.models.Movie;
import com.tikal.themoviedb.util.DateFormatter;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public class DetailsViewHolder extends BaseViewHolder {

    TextView year,duration,rating,description,trailersTitle;
    ImageView image;
    View markFavoriteBtn;
    public DetailsViewHolder(Context context,View itemView) {
        super(context,itemView);
        year = (TextView) itemView.findViewById(R.id.year);
        duration = (TextView) itemView.findViewById(R.id.duration);
        rating = (TextView) itemView.findViewById(R.id.rating);
        description = (TextView) itemView.findViewById(R.id.description);
        trailersTitle = (TextView) itemView.findViewById(R.id.trailersTitle);
        markFavoriteBtn = itemView.findViewById(R.id.markFavoriteBtn);
        markFavoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        image = (ImageView) itemView.findViewById(R.id.image);
    }

    public void bindData(Movie movie){
        String yearStr = DateFormatter.getYear(movie.release_date);
        year.setText(yearStr);
        duration.setText(movie.runtime + "min");
        rating.setText(movie.vote_average + "/10");
        description.setText(movie.overview);
        trailersTitle.setVisibility(movie.getTrailers().isEmpty() ? View.GONE : View.VISIBLE);
        final Configuration configuration = AppData.getConfiguration();

        String path = configuration.images.base_url + "/w342"  +  movie.poster_path;
        Picasso.with(mContext).load(Uri.parse(path)).placeholder(R.drawable.grid_placeholder).into(image);
    }
}
