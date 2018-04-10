package com.neptune.movieonline;

import android.app.Application;

import com.neptune.movieonline.utils.helpers.VolleyHelper;

/**
 * Created by Neptune on 4/10/2018.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyHelper.initialize(this);
    }
}
