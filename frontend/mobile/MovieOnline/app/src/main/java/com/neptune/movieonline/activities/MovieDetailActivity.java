package com.neptune.movieonline.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
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
import butterknife.OnClick;

public class MovieDetailActivity extends BaseActivity {

    @BindView(R.id.textViewName) TextView textViewName;
    @BindView(R.id.textViewDescription) TextView textViewDescription;
    @BindView(R.id.imageViewPoster) ImageView imageViewPoster;
    @BindView(R.id.recyclerViewComment) RecyclerView recyclerView;
    @BindView(R.id.editTextComment) EditText editTextComment;

    Movie MOVIE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        getDataFromIntent();
        setData();
        fetchComments();
        initView();
    }

    private void initView() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.line_divider);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
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

    @OnClick(R.id.buttonComment)
    public void onClickComment() {

    }
}
