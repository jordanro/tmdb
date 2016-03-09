package com.tikal.themoviedb.ui.moviedetails;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.tikal.themoviedb.R;
import com.tikal.themoviedb.models.Video;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public class TrailerViewHolder extends BaseViewHolder implements View.OnClickListener{

    TextView title;
    View mItemView;
    public TrailerViewHolder(Context context,View itemView) {
        super(context,itemView);
        mItemView = itemView;
        title = (TextView) itemView.findViewById(R.id.title);
    }

    @Override
    public void onClick(View v) {
        Video video = (Video) v.getTag();
    }

    public void bindData(Video video) {
        title.setText(video.name);
        mItemView.setTag(video);

    }
}
