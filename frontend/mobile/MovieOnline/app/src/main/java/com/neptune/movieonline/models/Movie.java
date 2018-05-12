package com.neptune.movieonline.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Neptune on 3/25/2018.
 */

public class Movie implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private long views;
    private String posterUrl;
    private List<Comment> comments;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public String getPosterUrl() {
        if (posterUrl.contains("http")) return posterUrl;
        return "http://10.0.2.2:5000/" + posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
