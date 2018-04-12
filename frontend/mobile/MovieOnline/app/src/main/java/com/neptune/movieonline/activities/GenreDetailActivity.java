package com.neptune.movieonline.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.neptune.movieonline.R;
import com.neptune.movieonline.adapters.MovieRecyclerViewAdapter;
import com.neptune.movieonline.models.Genre;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.constants.Extra;
import com.neptune.movieonline.utils.helpers.GsonHelper;
import com.neptune.movieonline.utils.helpers.VolleyHelper;
import com.neptune.movieonline.utils.requests.GenreRequest;
import com.neptune.movieonline.utils.requests.GsonRequest;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

public class GenreDetailActivity extends BaseActivity {

    @BindView(R.id.recyclerViewMovie) RecyclerView recyclerView;

    Genre GENRE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_detail);
        setData();
        fetchMovies();
    }

    private void setData() {
        GENRE = GsonHelper.fromJson(getIntent().getStringExtra(Extra.GENRE), Genre.class);
        setTitle(GENRE.getName());
    }

    private void fetchMovies() {
        GsonRequest<Movie[]> moviesRequest = GenreRequest.getMovies(GENRE.getId(),
                new Response.Listener<Movie[]>() {
                    @Override
                    public void onResponse(Movie[] response) {
                        recyclerView.setAdapter(new MovieRecyclerViewAdapter(GenreDetailActivity.this, new ArrayList<>(Arrays.asList(response))));
                    }
                }, null);
        VolleyHelper.getInstance().addToRequestQueue(moviesRequest);
    }
}
