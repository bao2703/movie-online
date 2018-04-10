package com.neptune.movieonline.activities;

import android.os.Bundle;

import com.neptune.movieonline.R;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

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

    @OnClick(R.id.buttonMovieList)
    public void onClickMovieList() {
        startActivity(MovieListActivity.class);
    }
}
