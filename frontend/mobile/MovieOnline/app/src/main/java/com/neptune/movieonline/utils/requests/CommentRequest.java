package com.neptune.movieonline.utils.requests;

import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.neptune.movieonline.models.Comment;
import com.neptune.movieonline.utils.constants.Api;

/**
 * Created by Neptune on 4/4/2018.
 */

public class CommentRequest {

    @NonNull
    public static GsonRequest<Comment[]> getAll(Response.Listener<Comment[]> listener, Response.ErrorListener errorListener) {
        return new GsonRequest<>(Comment[].class, Request.Method.GET, Api.Comment.GET_ALL, listener, errorListener);
    }

    @NonNull
    public static GsonRequest<String> comments(Comment payload, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        return new GsonRequest<>(String.class, Request.Method.POST, Api.Comment.COMMENT, payload, listener, errorListener);
    }
}
