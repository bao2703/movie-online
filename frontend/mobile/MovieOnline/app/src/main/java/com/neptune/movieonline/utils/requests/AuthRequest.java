package com.neptune.movieonline.utils.requests;

import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.neptune.movieonline.models.Auth;
import com.neptune.movieonline.models.User;
import com.neptune.movieonline.utils.constants.Api;

/**
 * Created by Neptune on 4/4/2018.
 */

public class AuthRequest {

    @NonNull
    public static GsonRequest<String> register(User payload, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        return new GsonRequest<>(String.class, Request.Method.POST, Api.Auth.REGISTER, payload, listener, errorListener);
    }

    @NonNull
    public static GsonRequest<Auth> login(User payload, Response.Listener<Auth> listener, Response.ErrorListener errorListener) {
        return new GsonRequest<>(Auth.class, Request.Method.POST, Api.Auth.LOGIN, payload, listener, errorListener);
    }
}
