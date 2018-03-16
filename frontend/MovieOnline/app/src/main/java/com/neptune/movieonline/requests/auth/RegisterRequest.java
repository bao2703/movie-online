package com.neptune.movieonline.requests.auth;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.neptune.movieonline.models.User;
import com.neptune.movieonline.requests.GsonRequest;
import com.neptune.movieonline.utils.constants.Rest;
import com.neptune.movieonline.utils.helpers.GsonHelper;

/**
 * Created by Neptune on 3/16/2018.
 */

public class RegisterRequest extends GsonRequest {
    private User model;

    public RegisterRequest(User model, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, Rest.Auth.REGISTER, String.class, null, listener, errorListener);
        this.model = model;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return GsonHelper.toJson(model).getBytes();
    }
}
