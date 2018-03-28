package com.neptune.movieonline.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.neptune.movieonline.R;
import com.neptune.movieonline.activities.MovieDetailActivity;
import com.neptune.movieonline.adapters.viewholders.MovieItemViewHolder;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.helpers.GlideHelper;

import java.util.List;

/**
 * Created by Neptune on 3/17/2018.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieItemViewHolder> {

    private Context context;
    private List<Movie> movieList;

    public MovieListAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return new MovieItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemViewHolder holder, int position) {
        final Movie item = movieList.get(position);

        holder.getTextViewName().setText(item.getName());
        holder.getTextViewViews().setText(String.valueOf(item.getViews()));

        Glide.with(context)
                .load(item.getPosterUrl())
                .apply(GlideHelper.POSTER_OPTIONS)
                .into(holder.getImageViewPoster());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("id", item.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
