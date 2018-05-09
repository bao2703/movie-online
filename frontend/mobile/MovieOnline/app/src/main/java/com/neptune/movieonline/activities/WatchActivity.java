package com.neptune.movieonline.activities;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.neptune.movieonline.R;
import com.neptune.movieonline.models.Episode;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.constants.Extra;

import butterknife.BindView;

public class WatchActivity extends BaseActivity {

    @BindView(R.id.videoView) VideoView videoView;
    private Movie MOVIE;
    private Episode EPISODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_watch);

        setData();
    }

    private void setData() {
        MOVIE = (Movie) getIntent().getSerializableExtra(Extra.MOVIE);
        EPISODE = (Episode) getIntent().getSerializableExtra(Extra.EPISODE);
        setTitle(MOVIE.getName() + " - " + EPISODE.getName());

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setVideoPath(EPISODE.getUrl());
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}
