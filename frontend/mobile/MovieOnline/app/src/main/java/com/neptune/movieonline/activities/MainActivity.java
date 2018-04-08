package com.neptune.movieonline.activities;

import android.os.Bundle;
import android.widget.Button;

import com.neptune.movieonline.R;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.buttonLogin) Button buttonLogin;
    @BindView(R.id.buttonRegister) Button buttonRegister;
    @BindView(R.id.buttonMovieList) Button buttonMovieList;

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
