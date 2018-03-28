package com.neptune.movieonline.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.neptune.movieonline.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neptune on 3/28/2018.
 */

public class CommentViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textViewName) TextView textViewContent;

    public CommentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public TextView getTextViewContent() {
        return textViewContent;
    }
}
