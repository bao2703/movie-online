package com.neptune.movieonline.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.neptune.movieonline.R;
import com.neptune.movieonline.models.User;
import com.neptune.movieonline.requests.GsonRequest;
import com.neptune.movieonline.utils.Rest;
import com.neptune.movieonline.utils.VolleyManager;

/**
 * Created by PC on 3/9/2018.
 */

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextName, editTextEmail, editTextPassword, editTextConfirmPassword;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialize();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GsonRequest<User> gsonRequest = new GsonRequest<>(Rest.Auth.URL + "/test", User.class, null, new Response.Listener<User>() {
                    @Override
                    public void onResponse(User response) {
                        Log.e("Request", response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Request", error.toString());
                    }
                });
                VolleyManager.getInstance().addToRequestQueue(gsonRequest);
            }
        });
    }

    private void initialize() {
        VolleyManager.initialize(this);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
    }
}
