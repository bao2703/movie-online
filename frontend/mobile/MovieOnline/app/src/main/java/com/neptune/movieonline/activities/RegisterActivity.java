package com.neptune.movieonline.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.neptune.movieonline.R;
import com.neptune.movieonline.models.Error;
import com.neptune.movieonline.models.User;
import com.neptune.movieonline.utils.constants.ErrorCode;
import com.neptune.movieonline.utils.helpers.DialogHelper;
import com.neptune.movieonline.utils.helpers.GsonHelper;
import com.neptune.movieonline.utils.helpers.VolleyHelper;
import com.neptune.movieonline.utils.requests.AuthRequest;
import com.neptune.movieonline.utils.requests.GsonRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC on 3/9/2018.
 */

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.editTextName) EditText editTextName;
    @BindView(R.id.editTextEmail) EditText editTextEmail;
    @BindView(R.id.editTextPassword) EditText editTextPassword;
    @BindView(R.id.editTextConfirmPassword) EditText editTextConfirmPassword;
    @BindView(R.id.buttonRegister) Button buttonRegister;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        VolleyHelper.initialize(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonRegister)
    public void onClickRegister() {
        if (!validateInput()) {
            return;
        }

        progressDialog = DialogHelper.createProgressDialog(RegisterActivity.this);
        progressDialog.show();

        User payload = new User();
        payload.setName(editTextName.getText().toString());
        payload.setEmail(editTextEmail.getText().toString());
        payload.setPassword(editTextPassword.getText().toString());

        GsonRequest<String> registerRequest = AuthRequest.register(payload,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
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
                                case ErrorCode.EMAIL_CONFLICT:
                                    editTextEmail.setError(getString(R.string.error_email_conflict));
                                    editTextEmail.requestFocus();
                            }
                        }
                    }
                });

        VolleyHelper.getInstance().addToRequestQueue(registerRequest);
    }

    private boolean validateInput() {
        boolean result = true;

        final String name = editTextName.getText().toString();
        final String email = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();
        final String confirmPassword = editTextConfirmPassword.getText().toString();

        if (name.isEmpty()) {
            editTextName.setError(getString(R.string.error_field_required));
            result = false;
        }

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

        if (confirmPassword.isEmpty()) {
            editTextConfirmPassword.setError(getString(R.string.error_field_required));
            result = false;
        }

        if (!password.equals(confirmPassword)) {
            editTextConfirmPassword.setError(getString(R.string.error_confirm_password_incorrect));
            result = false;
        }

        return result;
    }
}
