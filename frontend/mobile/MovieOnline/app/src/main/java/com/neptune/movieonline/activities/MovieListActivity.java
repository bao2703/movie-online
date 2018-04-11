package com.neptune.movieonline.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.neptune.movieonline.R;
import com.neptune.movieonline.adapters.MovieRecyclerViewAdapter;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.helpers.VolleyHelper;
import com.neptune.movieonline.utils.requests.GsonRequest;
import com.neptune.movieonline.utils.requests.MovieRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MovieListActivity extends BaseActivity {

    @BindView(R.id.recyclerViewMovie) RecyclerView recyclerView;
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        fetchMovies();
    }

    private void fetchMovies() {
        GsonRequest<Movie[]> moviesRequest = MovieRequest.getAll(
                new Response.Listener<Movie[]>() {
                    @Override
                    public void onResponse(Movie[] response) {
                        movies = new ArrayList<>(Arrays.asList(response));
                        recyclerView.setAdapter(new MovieRecyclerViewAdapter(MovieListActivity.this, movies));
                    }
                }, null);
        VolleyHelper.getInstance().addToRequestQueue(moviesRequest);
    }
}


