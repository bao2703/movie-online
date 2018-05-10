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
import com.neptune.movieonline.models.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neptune on 3/17/2018.
 */

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private int resource;
    private List<Movie> items;
    private OnMovieClickListener listener;

    public MovieRecyclerViewAdapter(int resource, Context context, List<Movie> items, OnMovieClickListener listener) {
        this.context = context;
        this.resource = resource;
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(resource, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Movie item = items.get(position);

        holder.textViewName.setText(item.getName());
        holder.textViewViews.setText(String.valueOf(item.getViews()));
        Glide.with(context)
                .load(item.getPosterUrl())
                .apply(new RequestOptions().centerCrop())
                .into(holder.imageViewPoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMovieClickListener(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Movie> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public interface OnMovieClickListener {
        void onMovieClickListener(Movie item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewName) TextView textViewName;
        @BindView(R.id.textViewViews) TextView textViewViews;
        @BindView(R.id.imageViewPoster) ImageView imageViewPoster;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
