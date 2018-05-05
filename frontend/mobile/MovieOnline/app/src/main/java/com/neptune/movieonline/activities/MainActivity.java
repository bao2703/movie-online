package com.neptune.movieonline.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.neptune.movieonline.R;
import com.neptune.movieonline.fragments.GenreListFragment;
import com.neptune.movieonline.models.Genre;
import com.neptune.movieonline.utils.constants.Extra;

import butterknife.OnClick;

public class MainActivity extends BaseActivity implements GenreListFragment.OnGenreClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.buttonRegister)
    public void onClickRegister() {
        startActivity(RegisterActivity.class);
    }

    @OnClick(R.id.buttonLogin)
    public void onClickLogin() {
        startActivity(LoginActivity.class);
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
