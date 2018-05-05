package com.neptune.movieonline.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neptune.movieonline.R;
import com.neptune.movieonline.models.Episode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neptune on 3/17/2018.
 */

public class EpisodeRecyclerViewAdapter extends RecyclerView.Adapter<EpisodeRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Episode> items;

    public EpisodeRecyclerViewAdapter(Context context, List<Episode> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_episode, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Episode item = items.get(position);

        holder.textViewName.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewName) TextView textViewName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
