package com.neptune.movieonline.utils.constants;

/**
 * Created by Neptune on 3/12/2018.
 */

public interface Rest {
    String URL = "http://10.0.2.2:5000/api";

    interface Auth {
        String URL = Rest.URL + "/auth";

        String LOGIN = Auth.URL + "/login";
        String REGISTER = Auth.URL + "/register";
    }

    interface Movie {
        String URL = Rest.URL + "/movies";

        String GET_ALL = Movie.URL;
        String GET = Movie.URL;
    }
}
