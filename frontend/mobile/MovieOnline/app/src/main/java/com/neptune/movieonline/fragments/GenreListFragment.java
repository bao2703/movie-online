package com.neptune.movieonline.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.neptune.movieonline.R;
import com.neptune.movieonline.adapters.GenreRecyclerViewAdapter;
import com.neptune.movieonline.models.Genre;
import com.neptune.movieonline.utils.helpers.VolleyHelper;
import com.neptune.movieonline.utils.requests.GenreRequest;
import com.neptune.movieonline.utils.requests.GsonRequest;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenreListFragment extends Fragment {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    private OnGenreClickListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnGenreClickListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_genre_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        fetchGenres();
    }

    private void fetchGenres() {
        GsonRequest<Genre[]> genresRequest = GenreRequest.getAll(
                new Response.Listener<Genre[]>() {
                    @Override
                    public void onResponse(Genre[] response) {
                        recyclerView.setAdapter(new GenreRecyclerViewAdapter(new ArrayList<>(Arrays.asList(response)), listener));
                    }
                }, null);
        VolleyHelper.getInstance().addToRequestQueue(genresRequest);
    }

    public interface OnGenreClickListener {
        void onGenreClickListener(Genre item);
    }
}
