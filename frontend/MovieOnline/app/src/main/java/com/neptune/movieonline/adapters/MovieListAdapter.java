package com.neptune.movieonline.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neptune.movieonline.R;
import com.neptune.movieonline.adapters.viewholders.MovieItemViewHolder;
import com.neptune.movieonline.models.Movie;

import java.util.List;

/**
 * Created by Neptune on 3/17/2018.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieItemViewHolder> {
    private List<Movie> data;

    public MovieListAdapter(List<Movie> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_item, parent, false);
        return new MovieItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemViewHolder holder, int position) {
        Movie item = data.get(position);
        holder.getTextViewName().setText(item.getName());
        holder.getTextViewViews().setText(String.valueOf(item.getViews()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
