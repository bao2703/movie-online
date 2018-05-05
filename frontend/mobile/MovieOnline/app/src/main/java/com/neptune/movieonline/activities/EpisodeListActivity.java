package com.neptune.movieonline.activities;

import android.os.Bundle;

import com.neptune.movieonline.R;
import com.neptune.movieonline.fragments.EpisodeListFragment;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.constants.Extra;

import static com.neptune.movieonline.R.id.fragment_container;

/**
 * Created by Neptune on 5/5/2018.
 */

public class EpisodeListActivity extends BaseActivity {

    private Movie MOVIE;
    private EpisodeListFragment episodeListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_list);
        setData();
        episodeListFragment = EpisodeListFragment.newInstance();
        episodeListFragment.fetchEpisodes(MOVIE.getId());
        getFragmentManager()
                .beginTransaction()
                .replace(fragment_container, episodeListFragment)
                .commit();
    }

    private void setData() {
        MOVIE = (Movie) getIntent().getSerializableExtra(Extra.MOVIE);
        setTitle(MOVIE.getName());
    }
}
