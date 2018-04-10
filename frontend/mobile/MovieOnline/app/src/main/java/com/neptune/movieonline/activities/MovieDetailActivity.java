package com.neptune.movieonline.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.neptune.movieonline.R;
import com.neptune.movieonline.adapters.CommentListAdapter;
import com.neptune.movieonline.models.Comment;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.constants.Extra;
import com.neptune.movieonline.utils.helpers.GlideHelper;
import com.neptune.movieonline.utils.helpers.VolleyHelper;
import com.neptune.movieonline.utils.requests.GsonRequest;
import com.neptune.movieonline.utils.requests.MovieRequest;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

public class MovieDetailActivity extends BaseActivity {

    @BindView(R.id.textViewName) TextView textViewName;
    @BindView(R.id.textViewDescription) TextView textViewDescription;
    @BindView(R.id.imageViewPoster) ImageView imageViewPoster;
    @BindView(R.id.recyclerViewCommentList) RecyclerView recyclerViewCommentList;

    int movieId;
    Movie movie;
    CommentListAdapter commentListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        movieId = getIntent().getIntExtra(Extra.MOVIE_ID, Integer.MIN_VALUE);
        fetchMovie();
        fetchComments();
    }

    private void fetchMovie() {
        GsonRequest<Movie> movieRequest = MovieRequest.get(movieId,
                new Response.Listener<Movie>() {
                    @Override
                    public void onResponse(Movie response) {
                        movie = response;
                        textViewName.setText(movie.getName());
                        textViewDescription.setText(movie.getDescription());
                        Glide.with(MovieDetailActivity.this)
                                .load(movie.getPosterUrl())
                                .apply(GlideHelper.POSTER_OPTIONS)
                                .into(imageViewPoster);
                    }
                }, null);
        VolleyHelper.getInstance().addToRequestQueue(movieRequest);
    }

    private void fetchComments() {
        GsonRequest<Comment[]> commentRequest = MovieRequest.getComments(movieId,
                new Response.Listener<Comment[]>() {
                    @Override
                    public void onResponse(Comment[] response) {
                        movie.setComments(new ArrayList<>(Arrays.asList(response)));
                        commentListAdapter = new CommentListAdapter(movie.getComments());
                        recyclerViewCommentList.setAdapter(commentListAdapter);
                    }
                }, null);
        VolleyHelper.getInstance().addToRequestQueue(commentRequest);
    }
}
