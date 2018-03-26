package com.neptune.movieonline.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.neptune.movieonline.R;
import com.neptune.movieonline.utils.helpers.VolleyHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.buttonLogin) Button buttonLogin;
    @BindView(R.id.buttonRegister) Button buttonRegister;
    @BindView(R.id.buttonMovieList) Button buttonMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VolleyHelper.initialize(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonRegister)
    public void onClickRegister() {
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }

    @OnClick(R.id.buttonLogin)
    public void onClickLogin() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    @OnClick(R.id.buttonMovieList)
    public void onClickMovieList() {
        Intent loginIntent = new Intent(this, MovieListActivity.class);
        startActivity(loginIntent);
    }
}
