package com.neptune.movieonline.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.neptune.movieonline.R;
import com.neptune.movieonline.adapters.MovieRecyclerViewAdapter;
import com.neptune.movieonline.models.Movie;
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

public class MovieListFragment extends Fragment implements Response.Listener<Movie[]> {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    private MovieRecyclerViewAdapter adapter;
    private OnMovieClickListener listener;

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnMovieClickListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.line_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new MovieRecyclerViewAdapter(getActivity(), new ArrayList<Movie>(), listener);
        recyclerView.setAdapter(adapter);
    }

    public void fetchMovies(String searchString) {
        GsonRequest<Movie[]> moviesRequest = MovieRequest.getAll(searchString, this, null);
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

    public interface OnMovieClickListener {
        void onMovieClickListener(Movie item);
    }
}
