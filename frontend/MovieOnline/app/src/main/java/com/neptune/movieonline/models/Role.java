package com.neptune.movieonline.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Neptune on 3/14/2018.
 */

public enum Role {
    @SerializedName("None")
    NONE("None"),
    @SerializedName("Administrator")
    ADMINISTRATOR("Administrator"),
    @SerializedName("Moderator")
    MODERATOR("Moderator");

    private String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return (value);
    }
}
