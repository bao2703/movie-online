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

    @BindView(R.id.buttonLogin)
    Button buttonLogin;

    @BindView(R.id.buttonRegister)
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VolleyHelper.initialize(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonRegister)
    public void onClickRegister() {
        Intent intentRegister = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intentRegister);
    }

    @OnClick(R.id.buttonLogin)
    public void onClickLogin() {
        Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intentLogin);
    }
}
