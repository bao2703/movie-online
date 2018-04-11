package com.neptune.movieonline.screens;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.neptune.movieonline.R;
import com.neptune.movieonline.activities.MovieDetailActivity;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.constants.Extra;
import com.neptune.movieonline.utils.helpers.GsonHelper;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Neptune on 4/10/2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MovieDetailScreenTest {

    private static Movie MOVIE;
    @Rule
    public ActivityTestRule<MovieDetailActivity> activityRule = new ActivityTestRule<MovieDetailActivity>(MovieDetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(targetContext, MovieDetailActivity.class);
            intent.putExtra(Extra.MOVIE, GsonHelper.toJson(MOVIE));
            return intent;
        }
    };

    @BeforeClass
    public static void init() {
        MOVIE = new Movie();
        MOVIE.setId(1);
    }

    @Test
    public void shouldDisplayViews() {
        onView(withId(R.id.textViewName)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewDescription)).check(matches(isDisplayed()));
        onView(withId(R.id.imageViewPoster)).check(matches(isDisplayed()));
    }
}
