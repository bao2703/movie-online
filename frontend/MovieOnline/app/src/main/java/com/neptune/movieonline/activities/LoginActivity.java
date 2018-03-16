package com.neptune.movieonline.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.neptune.movieonline.R;
import com.neptune.movieonline.models.User;
import com.neptune.movieonline.utils.helpers.VolleyHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();

        buttonLogin.setOnClickListener(LoginActivity.this);
    }

    @Override
    public void onClick(View view) {
        User model = new User();
        model.setEmail(editTextEmail.getText().toString());
        model.setPassword(editTextPassword.getText().toString());
    }

    private void initialize() {
        VolleyHelper.initialize(this);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
    }
}
