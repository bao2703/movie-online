package com.neptune.movieonline.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.neptune.movieonline.R;
import com.neptune.movieonline.adapters.MovieListAdapter;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.helpers.VolleyHelper;
import com.neptune.movieonline.utils.requests.GsonRequest;
import com.neptune.movieonline.utils.requests.MovieRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerViewMovieList) RecyclerView recyclerViewMovieList;

    private MovieListAdapter movieListAdapter;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        VolleyHelper.initialize(this);
        ButterKnife.bind(this);
        fetchMovies();
    }

    private void fetchMovies() {
        GsonRequest<Movie[]> moviesRequest = MovieRequest.getAll(
                new Response.Listener<Movie[]>() {
                    @Override
                    public void onResponse(Movie[] response) {
                        movieList = new ArrayList<>(Arrays.asList(response));
                        movieListAdapter = new MovieListAdapter(MovieListActivity.this, movieList);
                        recyclerViewMovieList.setAdapter(movieListAdapter);
                    }
                }, null);
        VolleyHelper.getInstance().addToRequestQueue(moviesRequest);
    }
}
