package com.neptune.movieonline.requests.auth;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.neptune.movieonline.models.User;
import com.neptune.movieonline.utils.constants.Param;
import com.neptune.movieonline.utils.constants.Rest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Neptune on 3/16/2018.
 */

public class RegisterRequest extends StringRequest {
    private User model;

    public RegisterRequest(User model, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, Rest.Auth.REGISTER, listener, errorListener);
        setRetryPolicy(Rest.DEFAULT_RETRY_POLICY);
        this.model = model;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();
        params.put(Param.User.NAME, model.getName());
        params.put(Param.User.EMAIL, model.getEmail());
        params.put(Param.User.PASSWORD, model.getPassword());
        return params;
    }
}
