package com.neptune.movieonline.utils.requests;

import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.neptune.movieonline.models.Genre;
import com.neptune.movieonline.utils.constants.Api;

/**
 * Created by Neptune on 4/11/2018.
 */

public class GenreRequest {

    @NonNull
    public static GsonRequest<Genre[]> getAll(Response.Listener<Genre[]> listener, Response.ErrorListener errorListener) {
        return new GsonRequest<>(Genre[].class, Request.Method.GET, Api.Genre.GET_ALL, listener, errorListener);
    }
}
