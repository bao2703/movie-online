package com.neptune.movieonline.screens;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.neptune.movieonline.R;
import com.neptune.movieonline.activities.MovieDetailActivity;
import com.neptune.movieonline.activities.MovieListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Neptune on 4/10/2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MovieDetailScreenTest {

    @Rule
    public IntentsTestRule<MovieDetailActivity> activityRule = new IntentsTestRule<>(MovieDetailActivity.class);
}
