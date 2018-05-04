package com.neptune.movieonline.activities;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.neptune.movieonline.R;
import com.neptune.movieonline.fragments.MovieListFragment;

import static com.neptune.movieonline.R.id.fragment_container;

public class SearchActivity extends BaseActivity {

    private MovieListFragment movieListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        movieListFragment = MovieListFragment.newInstance();
        getFragmentManager()
                .beginTransaction()
                .replace(fragment_container, movieListFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        final MenuItem searchItem = menu.findItem(R.id.item_search);
        searchItem.setActionView(new SearchView(this));

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setIconified(false);

        EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.white));

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                onBackPressed();
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListFragment.fetchMovies(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                movieListFragment.fetchMovies(newText);
                return true;
            }
        });

        return true;
    }
}
