package com.neptune.movieonline.requests;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.neptune.movieonline.models.Auth;

/**
 * Created by Neptune on 3/12/2018.
 */

public class AuthRequest extends Request<Auth> {
    public AuthRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    @Override
    protected Response<Auth> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    protected void deliverResponse(Auth response) {

    }
}
