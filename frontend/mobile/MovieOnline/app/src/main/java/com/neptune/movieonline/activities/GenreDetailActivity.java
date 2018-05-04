package com.neptune.movieonline.activities;

import android.os.Bundle;

import com.neptune.movieonline.R;
import com.neptune.movieonline.fragments.MovieListFragment;
import com.neptune.movieonline.models.Genre;
import com.neptune.movieonline.utils.constants.Extra;

import static com.neptune.movieonline.R.id.fragment_container;

public class GenreDetailActivity extends BaseActivity {

    Genre GENRE;
    private MovieListFragment movieListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_detail);
        setData();

        movieListFragment = MovieListFragment.newInstance();
        movieListFragment.fetchMovies(GENRE.getId());
        getFragmentManager()
                .beginTransaction()
                .replace(fragment_container, movieListFragment)
                .commit();
    }

    private void setData() {
        GENRE = (Genre) getIntent().getSerializableExtra(Extra.GENRE);
    }
}
