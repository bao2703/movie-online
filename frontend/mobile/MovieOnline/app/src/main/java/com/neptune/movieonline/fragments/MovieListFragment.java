package com.neptune.movieonline.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.neptune.movieonline.R;
import com.neptune.movieonline.activities.MovieDetailActivity;
import com.neptune.movieonline.adapters.MovieRecyclerViewAdapter;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.constants.Extra;
import com.neptune.movieonline.utils.helpers.VolleyHelper;
import com.neptune.movieonline.utils.requests.GenreRequest;
import com.neptune.movieonline.utils.requests.GsonRequest;
import com.neptune.movieonline.utils.requests.MovieRequest;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neptune on 4/12/2018.
 */

public class MovieListFragment extends Fragment implements Response.Listener<Movie[]>, MovieRecyclerViewAdapter.OnMovieClickListener {

    private static final String ARG_RESOURCE = "RESOURCE";
    private static final String ARG_TITLE = "TITLE";
    private static int RESOURCE;
    private static String TITLE;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private MovieRecyclerViewAdapter adapter;

    public static MovieListFragment newInstanceGrid(String title) {
        MovieListFragment fragment = new MovieListFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_RESOURCE, R.layout.fragment_movie_card_list);
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);

        return fragment;
    }

    public static MovieListFragment newInstance() {
        MovieListFragment fragment = new MovieListFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_RESOURCE, R.layout.fragment_movie_list);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            RESOURCE = getArguments().getInt(ARG_RESOURCE);
            TITLE = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(RESOURCE, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        int resource;
        if (RESOURCE == R.layout.fragment_movie_list) {
            resource = R.layout.item_movie;
        } else {
            TextView textViewTitle = view.findViewById(R.id.textViewTitle);
            textViewTitle.setText(TITLE);

            resource = R.layout.item_movie_card;
            recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        }

        adapter = new MovieRecyclerViewAdapter(resource, getActivity(), new ArrayList<Movie>(), this);
        recyclerView.setAdapter(adapter);

    }

    public void fetchMovies(String searchString) {
        GsonRequest<Movie[]> moviesRequest = MovieRequest.getAll(searchString, "", this, null);
        VolleyHelper.getInstance().addToRequestQueue(moviesRequest);
    }

    public void fetchMovies(String order, int take) {
        GsonRequest<Movie[]> moviesRequest = MovieRequest.getAll("", order, take, this, null);
        VolleyHelper.getInstance().addToRequestQueue(moviesRequest);
    }

    public void fetchMovies(Integer genreId) {
        GsonRequest<Movie[]> moviesRequest = GenreRequest.getMovies(genreId, this, null);
        VolleyHelper.getInstance().addToRequestQueue(moviesRequest);
    }

    @Override
    public void onResponse(Movie[] response) {
        adapter.setItems(new ArrayList<>(Arrays.asList(response)));
    }

    @Override
    public void onMovieClickListener(Movie item) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(Extra.MOVIE, item);
        startActivity(intent);
    }
}
