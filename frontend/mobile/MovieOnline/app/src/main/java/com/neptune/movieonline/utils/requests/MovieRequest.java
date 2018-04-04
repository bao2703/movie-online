package com.neptune.movieonline.utils.requests;

import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.constants.Rest;

/**
 * Created by Neptune on 4/4/2018.
 */

public class MovieRequest {

    @NonNull
    public static GsonRequest<Movie[]> getAll(Response.Listener<Movie[]> listener, Response.ErrorListener errorListener) {
        return new GsonRequest<>(Movie[].class, Request.Method.GET, Rest.Movie.GET_ALL, listener, errorListener);
    }

    @NonNull
    public static GsonRequest<Movie> get(int id, Response.Listener<Movie> listener, Response.ErrorListener errorListener) {
        return new GsonRequest<>(Movie.class, Request.Method.GET, Rest.Movie.GET, id, listener, errorListener);
    }
}
