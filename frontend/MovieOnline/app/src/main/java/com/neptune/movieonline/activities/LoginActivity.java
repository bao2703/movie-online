package com.neptune.movieonline.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.neptune.movieonline.R;
import com.neptune.movieonline.models.User;
import com.neptune.movieonline.utils.helpers.VolleyHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.editTextEmail)
    EditText editTextEmail;

    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    @BindView(R.id.buttonLogin)
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        VolleyHelper.initialize(this);
        ButterKnife.bind(this);

        buttonLogin.setOnClickListener(LoginActivity.this);
    }

    @Override
    public void onClick(View view) {
        User model = new User();
        model.setEmail(editTextEmail.getText().toString());
        model.setPassword(editTextPassword.getText().toString());
    }
}
