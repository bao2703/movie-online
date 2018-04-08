package com.neptune.movieonline.utils;

import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import org.hamcrest.Matcher;

/**
 * Created by Neptune on 4/8/2018.
 */

public final class ViewMatchersExtension {
    public static Matcher<View> hasErrorText(final int resId) {
        return ViewMatchers.hasErrorText(TestHelper.getString(resId));
    }
}
