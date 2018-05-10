package com.neptune.movieonline.utils.requests;

import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.neptune.movieonline.models.Comment;
import com.neptune.movieonline.models.Episode;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.constants.Api;

/**
 * Created by Neptune on 4/4/2018.
 */

public class MovieRequest {

    @NonNull
    public static GsonRequest<Movie[]> getAll(String searchString, String order, int take, Response.Listener<Movie[]> listener, Response.ErrorListener errorListener) {
        return new GsonRequest<>(Movie[].class, Request.Method.GET,
                Api.Movie.GET_ALL + "?searchString=" + searchString + "&order=" + order + "&take=" + take, listener, errorListener);
    }

    @NonNull
    public static GsonRequest<Movie[]> getAll(String searchString, String order, Response.Listener<Movie[]> listener, Response.ErrorListener errorListener) {
        return new GsonRequest<>(Movie[].class, Request.Method.GET,
                Api.Movie.GET_ALL + "?searchString=" + searchString, listener, errorListener);
    }

    @NonNull
    public static GsonRequest<Movie> get(int id, Response.Listener<Movie> listener, Response.ErrorListener errorListener) {
        return new GsonRequest<>(Movie.class, Request.Method.GET, Api.Movie.GET, id, listener, errorListener);
    }

    @NonNull
    public static GsonRequest<Comment[]> getComments(int id, Response.Listener<Comment[]> listener, Response.ErrorListener errorListener) {
        return new GsonRequest<>(Comment[].class, Request.Method.GET, Api.Movie.GET_COMMENTS, id, listener, errorListener);
    }

    @NonNull
    public static GsonRequest<Episode[]> getEpisodes(int id, Response.Listener<Episode[]> listener, Response.ErrorListener errorListener) {
        return new GsonRequest<>(Episode[].class, Request.Method.GET, Api.Movie.GET_EPISODES, id, listener, errorListener);
    }

    @NonNull
    public static GsonRequest<String> incView(int id, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        return new GsonRequest<>(String.class, Request.Method.POST, Api.Movie.INC_VIEW, id, listener, errorListener);
    }
}
