package com.neptune.movieonline.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.neptune.movieonline.R;
import com.neptune.movieonline.fragments.MovieListFragment;
import com.neptune.movieonline.models.Genre;
import com.neptune.movieonline.utils.constants.Extra;

import static com.neptune.movieonline.R.id.fragment_container;

public class GenreDetailActivity extends BaseActivity {

    private Genre GENRE;
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
        setTitle(GENRE.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.item_search:
                startActivity(SearchActivity.class);
                break;
            default:
                break;
        }
        return true;
    }
}
