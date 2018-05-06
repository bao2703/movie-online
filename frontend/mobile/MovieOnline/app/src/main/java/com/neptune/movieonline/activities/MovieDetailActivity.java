package com.neptune.movieonline.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.neptune.movieonline.R;
import com.neptune.movieonline.models.Error;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.constants.ErrorCode;
import com.neptune.movieonline.utils.constants.Extra;
import com.neptune.movieonline.utils.helpers.GlideHelper;
import com.neptune.movieonline.utils.helpers.GsonHelper;
import com.neptune.movieonline.utils.helpers.VolleyHelper;
import com.neptune.movieonline.utils.requests.AuthRequest;
import com.neptune.movieonline.utils.requests.GsonRequest;
import com.neptune.movieonline.utils.requests.MovieRequest;

import butterknife.BindView;
import butterknife.OnClick;

public class MovieDetailActivity extends BaseActivity {

    @BindView(R.id.textViewName) TextView textViewName;
    @BindView(R.id.textViewDescription) TextView textViewDescription;
    @BindView(R.id.imageViewPoster) ImageView imageViewPoster;

    private Movie MOVIE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setData();
        incView();
    }

    private void setData() {
        MOVIE = (Movie) getIntent().getSerializableExtra(Extra.MOVIE);
        setTitle(MOVIE.getName());
        textViewName.setText(MOVIE.getName());
        textViewDescription.setText(MOVIE.getDescription());
        Glide.with(this)
                .load(MOVIE.getPosterUrl())
                .apply(GlideHelper.POSTER_OPTIONS)
                .into(imageViewPoster);
    }

    @OnClick(R.id.buttonEpisodes)
    public void onClickEpisodes() {
        Intent intent = new Intent(this, EpisodeListActivity.class);
        intent.putExtra(Extra.MOVIE, MOVIE);
        startActivity(intent);
    }

    @OnClick(R.id.buttonComment)
    public void onClickComment() {
        Intent intent = new Intent(this, CommentActivity.class);
        intent.putExtra(Extra.MOVIE, MOVIE);
        startActivity(intent);
    }

    public void incView() {
        GsonRequest<String> request = MovieRequest.incView(MOVIE.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, null);
        VolleyHelper.getInstance().addToRequestQueue(request);
    }
}
