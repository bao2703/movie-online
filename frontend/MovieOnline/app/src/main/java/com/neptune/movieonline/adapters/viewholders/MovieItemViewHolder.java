package com.neptune.movieonline.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.neptune.movieonline.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neptune on 3/25/2018.
 */

public class MovieItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textViewName) TextView textViewName;
    @BindView(R.id.textViewViews) TextView textViewViews;
    @BindView(R.id.imageViewPoster) ImageView imageViewPoster;

    public MovieItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public TextView getTextViewName() {
        return textViewName;
    }

    public TextView getTextViewViews() {
        return textViewViews;
    }

    public ImageView getImageViewPoster() {
        return imageViewPoster;
    }
}
