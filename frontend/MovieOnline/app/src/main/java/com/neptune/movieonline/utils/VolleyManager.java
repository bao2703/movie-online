package com.neptune.movieonline.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Neptune on 3/12/2018.
 */

public class VolleyManager {
    private static VolleyManager instance;
    private RequestQueue requestQueue;

    public synchronized static void initialize(Context context) {
        if (instance == null) {
            instance = new VolleyManager();
            instance.requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
    }

    public synchronized static VolleyManager getInstance() {
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
