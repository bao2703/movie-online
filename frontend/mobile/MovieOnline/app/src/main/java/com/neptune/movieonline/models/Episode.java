package com.neptune.movieonline.models;

import java.io.Serializable;

/**
 * Created by Neptune on 5/5/2018.
 */

public class Episode implements Serializable {

    private Integer id;
    private String name;
    private String Url;
    private Integer movieId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
}
