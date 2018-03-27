package com.neptune.movieonline.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.neptune.movieonline.R;
import com.neptune.movieonline.models.Error;
import com.neptune.movieonline.models.User;
import com.neptune.movieonline.utils.constants.ErrorCode;
import com.neptune.movieonline.utils.constants.Rest;
import com.neptune.movieonline.utils.helpers.DialogHelper;
import com.neptune.movieonline.utils.helpers.GsonHelper;
import com.neptune.movieonline.utils.helpers.VolleyHelper;
import com.neptune.movieonline.utils.requests.GsonRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextEmail) EditText editTextEmail;
    @BindView(R.id.editTextPassword) EditText editTextPassword;
    @BindView(R.id.buttonLogin) Button buttonLogin;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        VolleyHelper.initialize(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonLogin)
    public void onClickLogin() {
        if (!validateInput()) {
            return;
        }

        progressDialog = DialogHelper.createProgressDialog(LoginActivity.this);
        progressDialog.show();

        User payload = new User();
        payload.setEmail(editTextEmail.getText().toString());
        payload.setPassword(editTextPassword.getText().toString());

        GsonRequest<String> loginRequest = new GsonRequest<String>(Request.Method.POST, Rest.Auth.LOGIN, payload, String.class,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Intent homepageIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(homepageIntent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null && networkResponse.data != null) {
                            Error errorResponse = GsonHelper.fromJson(networkResponse.data, Error.class);

                            switch (errorResponse.getCode()) {
                                case ErrorCode.INVALID_LOGIN:
                                    Toast.makeText(LoginActivity.this, "Email or password is incorrect.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

        VolleyHelper.getInstance().addToRequestQueue(loginRequest);
    }

    private boolean validateInput() {
        final String email = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();

        boolean result = true;

        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.error_field_required));
            result = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.error_email_invalid));
            result = false;
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.error_field_required));
            result = false;
        }

        return result;
    }
}
