package com.neptune.movieonline.utils.helpers;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Neptune on 3/12/2018.
 */

public class VolleyHelper {
    private static VolleyHelper instance;
    private RequestQueue requestQueue;

    public synchronized static void initialize(Context context) {
        if (instance == null) {
            instance = new VolleyHelper();
            instance.requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
    }

    public synchronized static VolleyHelper getInstance() {
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(request);
    }
}
