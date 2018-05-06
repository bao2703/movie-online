package com.neptune.movieonline.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.neptune.movieonline.R;
import com.neptune.movieonline.models.Error;
import com.neptune.movieonline.models.User;
import com.neptune.movieonline.utils.constants.ErrorCode;
import com.neptune.movieonline.utils.constants.Preference;
import com.neptune.movieonline.utils.helpers.DialogHelper;
import com.neptune.movieonline.utils.helpers.GsonHelper;
import com.neptune.movieonline.utils.helpers.VolleyHelper;
import com.neptune.movieonline.utils.requests.AuthRequest;
import com.neptune.movieonline.utils.requests.GsonRequest;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.editTextEmail) EditText editTextEmail;
    @BindView(R.id.editTextPassword) EditText editTextPassword;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @OnClick(R.id.buttonRegister)
    public void onClickRegister() {
        startActivity(RegisterActivity.class);
    }

    @OnClick(R.id.buttonLogin)
    public void onClickLogin() {
        if (!validateInput()) {
            return;
        }

        progressDialog = DialogHelper.createProgressDialog(LoginActivity.this);
        progressDialog.show();

        User payload = new User();
        payload.setEmail(getEmail());
        payload.setPassword(getPassword());

        GsonRequest<String> loginRequest = AuthRequest.login(payload,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        saveSession();
                        startActivity(MainActivity.class);
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

    public void saveSession() {
        SharedPreferences.Editor editor = getSharedPreferences(Preference.SESSION, Context.MODE_PRIVATE).edit();
        editor.putString(Preference.Key.EMAIL, getEmail());
        editor.apply();
    }

    private boolean validateInput() {
        final String email = getEmail();
        final String password = getPassword();

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

    public String getEmail() {
        return editTextEmail.getText().toString();
    }

    public String getPassword() {
        return editTextPassword.getText().toString();
    }
}
