package com.neptune.movieonline.utils;

/**
 * Created by Neptune on 3/12/2018.
 */

public final class Rest {
    public static final String URL = "http://10.0.2.2:5000/api";

    public final class Auth {
        public static final String URL = Rest.URL + "/auth";

        public static final String REGISTER = Auth.URL + "/register";
    }
}
