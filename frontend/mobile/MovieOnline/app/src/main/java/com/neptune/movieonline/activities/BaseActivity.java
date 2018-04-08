package com.neptune.movieonline.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.neptune.movieonline.R;
import com.neptune.movieonline.utils.helpers.VolleyHelper;

import butterknife.ButterKnife;

/**
 * Created by Neptune on 4/8/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public void startActivity(Class cls) {
        startActivity(new Intent(this, cls));
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        VolleyHelper.initialize(this);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
