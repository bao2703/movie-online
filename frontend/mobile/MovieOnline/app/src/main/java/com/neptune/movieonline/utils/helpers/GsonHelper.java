package com.neptune.movieonline.utils.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by Neptune on 3/16/2018.
 */

public class GsonHelper {
    private static Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .setDateFormat("dd-MM-yyyy")
                    .create();
        }
        return gson;
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return getGson().fromJson(json, typeOfT);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return getGson().fromJson(json, clazz);
    }

    public static <T> T fromJson(byte[] bytes, Class<T> clazz) {
        return fromJson(new String(bytes), clazz);
    }

    public static <T> T fromJson(byte[] bytes, Type typeOfT) {
        return fromJson(new String(bytes), typeOfT);
    }

    public static HashMap<String, String> fromJson(byte[] data) {
        return fromJson(data,
                new TypeToken<HashMap<String, String>>() {
                }.getType());
    }

    public static String toJson(Object src) {
        return getGson().toJson(src);
    }
}
