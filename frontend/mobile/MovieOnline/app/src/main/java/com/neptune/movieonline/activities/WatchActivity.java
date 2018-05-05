package com.neptune.movieonline.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.neptune.movieonline.R;
import com.neptune.movieonline.models.Episode;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.constants.Extra;

public class WatchActivity extends BaseActivity {

    private Movie MOVIE;
    private Episode EPISODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);
        setData();
    }

    private void setData() {
        MOVIE = (Movie) getIntent().getSerializableExtra(Extra.MOVIE);
        EPISODE = (Episode) getIntent().getSerializableExtra(Extra.EPISODE);
        setTitle(MOVIE.getName() + " - " + EPISODE.getName());
    }
}
