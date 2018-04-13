package com.neptune.movieonline.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.neptune.movieonline.R;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.constants.Extra;
import com.neptune.movieonline.utils.helpers.GlideHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class MovieDetailActivity extends BaseActivity {

    @BindView(R.id.textViewName) TextView textViewName;
    @BindView(R.id.textViewDescription) TextView textViewDescription;
    @BindView(R.id.imageViewPoster) ImageView imageViewPoster;

    Movie MOVIE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setData();
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

    @OnClick(R.id.buttonComment)
    public void onClickComment() {
        Intent intent = new Intent(this, CommentActivity.class);
        intent.putExtra(Extra.MOVIE, MOVIE);
        startActivity(intent);
    }
}
