package com.neptune.movieonline.utils.requests;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
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
    private Object bodyPayload;

    public GsonRequest(int method, String url, Class<T> clazz, Response.Listener<T> listener) {
        this(method, url, null, clazz, listener, null);
    }

    public GsonRequest(int method, String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(method, url, null, clazz, listener, errorListener);
    }

    public GsonRequest(int method, String url, int id, Class<T> clazz, Response.Listener<T> listener) {
        this(method, url, id, clazz, listener, null);
    }

    public GsonRequest(int method, String url, int id, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(method, url + "/" + id, null, clazz, listener, errorListener);
    }

    public GsonRequest(int method, String url, Object bodyPayload, Class<T> clazz, Response.Listener<T> listener) {
        this(method, url, bodyPayload, clazz, listener, null);
    }

    public GsonRequest(int method, String url, Object bodyPayload, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.listener = listener;
        this.bodyPayload = bodyPayload;
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=" + getParamsEncoding();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        Object payload = getBodyPayload();
        if (payload == null) return null;
        return GsonHelper.toJson(payload).getBytes();
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
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    public Object getBodyPayload() {
        return bodyPayload;
    }

    public Object setBodyPayload(Object bodyPayload) {
        return this.bodyPayload = bodyPayload;
    }
}
