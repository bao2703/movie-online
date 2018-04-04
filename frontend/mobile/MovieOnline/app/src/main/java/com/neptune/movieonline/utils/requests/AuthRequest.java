package com.neptune.movieonline.utils.requests;

import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.neptune.movieonline.models.User;
import com.neptune.movieonline.utils.constants.Rest;

/**
 * Created by Neptune on 4/4/2018.
 */

public class AuthRequest {

    @NonNull
    public static GsonRequest<String> register(User payload, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        return new GsonRequest<>(String.class, Request.Method.POST, Rest.Auth.REGISTER, payload, listener, errorListener);
    }

    @NonNull
    public static GsonRequest<String> login(User payload, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        return new GsonRequest<>(String.class, Request.Method.POST, Rest.Auth.LOGIN, payload, listener, errorListener);
    }
}
