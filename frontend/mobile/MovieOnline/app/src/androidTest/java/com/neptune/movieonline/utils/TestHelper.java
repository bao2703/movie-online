package com.neptune.movieonline.utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

/**
 * Created by Neptune on 4/8/2018.
 */

public class TestHelper {
    public static String getString(int resId) {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        return targetContext.getResources().getString(resId);
    }
}
