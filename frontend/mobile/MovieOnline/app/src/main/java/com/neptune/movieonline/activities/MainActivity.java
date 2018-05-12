package com.neptune.movieonline.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.neptune.movieonline.R;
import com.neptune.movieonline.fragments.GenreListFragment;
import com.neptune.movieonline.fragments.MovieListFragment;
import com.neptune.movieonline.models.Genre;
import com.neptune.movieonline.utils.constants.Extra;
import com.neptune.movieonline.utils.constants.Preference;

import butterknife.OnClick;

import static com.neptune.movieonline.R.id.fragment_container_newest;
import static com.neptune.movieonline.R.id.fragment_container_popular;

public class MainActivity extends BaseActivity implements GenreListFragment.OnGenreClickListener {

    private MovieListFragment newMovieListFragment;
    private MovieListFragment popularMovieListFragment;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences(Preference.SESSION, MODE_PRIVATE);

        checkLogin();

        newMovieListFragment = MovieListFragment.newInstanceGrid("NEW MOVIES");
        newMovieListFragment.fetchMovies("date-created", 6);

        popularMovieListFragment = MovieListFragment.newInstanceGrid("POPULAR MOVIES");
        popularMovieListFragment.fetchMovies("views", 6);

        getFragmentManager()
                .beginTransaction()
                .replace(fragment_container_newest, newMovieListFragment)
                .replace(fragment_container_popular, popularMovieListFragment)
                .commit();
    }

    @OnClick(R.id.buttonLogin)
    public void onClickLogin() {
        startActivity(LoginActivity.class);
    }

    @OnClick(R.id.buttonLogout)
    public void onClickLogout() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
        checkLogin();
    }

    public void checkLogin() {
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonLogout = findViewById(R.id.buttonLogout);
        String id = prefs.getString(Preference.Key.ID, null);
        if (id != null) {
            buttonLogin.setVisibility(View.GONE);
            buttonLogout.setVisibility(View.VISIBLE);
        } else {
            buttonLogin.setVisibility(View.VISIBLE);
            buttonLogout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGenreClickListener(Genre item) {
        Intent intent = new Intent(this, GenreDetailActivity.class);
        intent.putExtra(Extra.GENRE, item);
        startActivity(intent);
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
