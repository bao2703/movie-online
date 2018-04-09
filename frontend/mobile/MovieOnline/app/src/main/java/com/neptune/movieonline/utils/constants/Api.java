package com.neptune.movieonline.utils.constants;

/**
 * Created by Neptune on 3/12/2018.
 */

public interface Api {
    String URL = "http://10.0.2.2:5000/api";

    interface Auth {
        String URL = Api.URL + "/auth";

        String LOGIN = Auth.URL + "/login";
        String REGISTER = Auth.URL + "/register";
    }

    interface Movie {
        String URL = Api.URL + "/movies";

        String GET_ALL = Movie.URL;
        String GET = Movie.URL;
        String GET_COMMENTS = Movie.URL + "/comments";
    }

    interface Comment {
        String URL = Api.URL + "/comments";

        String GET_ALL = Comment.URL;
    }
}