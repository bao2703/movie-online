package com.neptune.movieonline.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.neptune.movieonline.R;
import com.neptune.movieonline.adapters.CommentRecyclerViewAdapter;
import com.neptune.movieonline.models.Comment;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.constants.Extra;
import com.neptune.movieonline.utils.helpers.GlideHelper;
import com.neptune.movieonline.utils.helpers.GsonHelper;
import com.neptune.movieonline.utils.helpers.VolleyHelper;
import com.neptune.movieonline.utils.requests.GsonRequest;
import com.neptune.movieonline.utils.requests.MovieRequest;

import java.util.Arrays;

import butterknife.BindView;

public class MovieDetailActivity extends BaseActivity {

    @BindView(R.id.textViewName) TextView textViewName;
    @BindView(R.id.textViewDescription) TextView textViewDescription;
    @BindView(R.id.imageViewPoster) ImageView imageViewPoster;
    @BindView(R.id.recyclerViewComment) RecyclerView recyclerView;

    Movie MOVIE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        getDataFromIntent();
        setData();
        fetchComments();
    }

    private void getDataFromIntent() {
        MOVIE = GsonHelper.fromJson(getIntent().getStringExtra(Extra.MOVIE), Movie.class);
    }

    private void setData() {
        setTitle(MOVIE.getName());
        textViewName.setText(MOVIE.getName());
        textViewDescription.setText(MOVIE.getDescription());
        Glide.with(this)
                .load(MOVIE.getPosterUrl())
                .apply(GlideHelper.POSTER_OPTIONS)
                .into(imageViewPoster);
    }

    private void fetchComments() {
        GsonRequest<Comment[]> commentRequest = MovieRequest.getComments(MOVIE.getId(),
                new Response.Listener<Comment[]>() {
                    @Override
                    public void onResponse(Comment[] response) {
                        recyclerView.setAdapter(new CommentRecyclerViewAdapter(Arrays.asList(response)));
                    }
                }, null);
        VolleyHelper.getInstance().addToRequestQueue(commentRequest);
    }
}
