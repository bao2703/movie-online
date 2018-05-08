package com.neptune.movieonline.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.neptune.movieonline.R;
import com.neptune.movieonline.models.Comment;

import java.text.DateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neptune on 3/28/2018.
 */

public class CommentRecyclerViewAdapter extends RecyclerView.Adapter<CommentRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Comment> items;

    public CommentRecyclerViewAdapter(Context context, List<Comment> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Comment item = items.get(position);

        holder.textViewName.setText(item.getUser().getName());
        holder.textViewContent.setText(item.getContent());
        holder.textViewDateCreated.setText(DateFormat.getDateInstance().format(item.getDateCreated()));

        RequestOptions requestOptions = new RequestOptions();

        Glide.with(context)
                .load(item.getUser().getAvatarUrl())
                .apply(requestOptions.placeholder(R.mipmap.ic_launcher).centerCrop().circleCrop())
                .into(holder.imageViewAvatar);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewAvatar) ImageView imageViewAvatar;
        @BindView(R.id.textViewName) TextView textViewName;
        @BindView(R.id.textViewContent) TextView textViewContent;
        @BindView(R.id.textViewDateCreated) TextView textViewDateCreated;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
