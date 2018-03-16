package com.neptune.movieonline.utils.constants;

import com.android.volley.DefaultRetryPolicy;

/**
 * Created by Neptune on 3/12/2018.
 */

public interface Rest {
    String URL = "http://10.0.2.2:5000/api";
    DefaultRetryPolicy DEFAULT_RETRY_POLICY = new DefaultRetryPolicy(1000000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    interface Auth {
        String URL = Rest.URL + "/auth";

        String LOGIN = Auth.URL + "/login";
        String REGISTER = Auth.URL + "/register";
    }
}
