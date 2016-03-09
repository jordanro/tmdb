package com.tikal.themoviedb.ui.moviedetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public class BaseViewHolder  extends RecyclerView.ViewHolder  {

    protected Context mContext;

    public BaseViewHolder(Context context,View itemView) {
        super(itemView);
        mContext = context;
    }
}
