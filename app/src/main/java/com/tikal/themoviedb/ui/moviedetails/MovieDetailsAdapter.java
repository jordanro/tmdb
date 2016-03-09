package com.tikal.themoviedb.ui.moviedetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tikal.themoviedb.R;
import com.tikal.themoviedb.models.Movie;
import com.tikal.themoviedb.models.Video;

import java.util.List;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public class MovieDetailsAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private Context mContext;
    private Movie mMovie;
    private List<Video> mTrailers;
    private boolean mIsTablet;

    private static int HEADER_HOLDER_TYPE = 0;
    private static int DETAIL_HOLDER_TYPE = 1;
    private static int TRAILER_HOLDER_TYPE = 2;

    public MovieDetailsAdapter(Context context,Movie movie,boolean isTablet){
        mContext = context;
        mMovie = movie;
        mTrailers = movie.getTrailers();
        mIsTablet = isTablet;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        View layoutView = null;
        switch(viewType){
            case 0:
                layoutView = LayoutInflater.from(mContext).inflate(R.layout.movie_header_item, parent,false);
                baseViewHolder = new HeaderViewHolder(mContext,layoutView);
                break;
            case 1:
                layoutView = LayoutInflater.from(mContext).inflate(R.layout.movie_details_item, null);
                baseViewHolder = new DetailsViewHolder(mContext,layoutView);
                break;
            case 2:
                layoutView = LayoutInflater.from(mContext).inflate(R.layout.movie_trailer_item, null);
                baseViewHolder = new TrailerViewHolder(mContext,layoutView);
                break;
        }
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if(holder instanceof HeaderViewHolder){
            ((HeaderViewHolder)holder).bindData(mMovie);
        }
        else if(holder instanceof DetailsViewHolder){
            ((DetailsViewHolder)holder).bindData(mMovie);
        }
        else{
            int offsetPosition = mIsTablet ? position - 2 : position - 1;
            ((TrailerViewHolder)holder).bindData(mTrailers.get(offsetPosition));
        }
    }

    @Override
    public int getItemCount() {
        int count = 1 + mTrailers.size();
        if(mIsTablet){
            count++;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        if(mIsTablet){
            switch (position){
                case 0:
                    type = HEADER_HOLDER_TYPE;
                    break;
                case 1:
                    type = DETAIL_HOLDER_TYPE;
                    break;
                default:
                    type = TRAILER_HOLDER_TYPE;
            }
        }
        else{
            switch (position){
                case 0:
                    type = DETAIL_HOLDER_TYPE;
                    break;
                default:
                    type = TRAILER_HOLDER_TYPE;
            }
        }

        return type;
    }
}
