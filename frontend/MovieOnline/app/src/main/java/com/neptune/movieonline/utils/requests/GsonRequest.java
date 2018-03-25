package com.neptune.movieonline.utils.requests;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonSyntaxException;
import com.neptune.movieonline.utils.helpers.GsonHelper;

import java.io.UnsupportedEncodingException;

/**
 * Created by Neptune on 3/14/2018.
 */

public class GsonRequest<T> extends Request<T> {

    private final Class<T> clazz;
    private final Response.Listener<T> listener;

    public GsonRequest(int method, String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.listener = listener;
    }

    public Object getBodyPayload() {
        return null;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return GsonHelper.toJson(getBodyPayload()).getBytes();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(GsonHelper.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException | JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=" + getParamsEncoding();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
