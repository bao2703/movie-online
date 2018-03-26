package com.neptune.movieonline.utils.requests;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.neptune.movieonline.utils.helpers.GsonHelper;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Neptune on 3/14/2018.
 */

public class GsonRequest<T> extends Request<T> {

    private final Class<T> clazz;
    private final Response.Listener<T> listener;
    private Object bodyPayload;

    public GsonRequest(int method, String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(method, url, clazz, null, listener, errorListener);
    }

    public GsonRequest(int method, String url, Class<T> clazz, Response.Listener<T> listener) {
        this(method, url, clazz, null, listener, null);
    }

    public GsonRequest(int method, String url, Class<T> clazz, Object bodyPayload, Response.Listener<T> listener) {
        this(method, url, clazz, bodyPayload, listener, null);
    }

    public GsonRequest(int method, String url, Class<T> clazz, Object bodyPayload, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.listener = listener;
        this.bodyPayload = bodyPayload;
    }

    public Object getBodyPayload() {
        return bodyPayload;
    }
    public Object setBodyPayload(Object bodyPayload) {
        return this.bodyPayload = bodyPayload;
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
    public String getBodyContentType() {
        return "application/json; charset=" + getParamsEncoding();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
