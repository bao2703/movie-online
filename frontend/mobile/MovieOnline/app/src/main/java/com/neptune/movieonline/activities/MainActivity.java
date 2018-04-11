package com.neptune.movieonline.activities;

import android.os.Bundle;

import com.neptune.movieonline.R;
import com.neptune.movieonline.fragments.GenreListFragment;
import com.neptune.movieonline.models.Genre;

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
        startActivity(MovieListActivity.class);
    }
}
