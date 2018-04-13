package com.neptune.movieonline.screens;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.neptune.movieonline.R;
import com.neptune.movieonline.activities.CommentActivity;
import com.neptune.movieonline.activities.MovieDetailActivity;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.constants.Extra;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Neptune on 4/10/2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MovieDetailScreenTest {

    private static Movie MOVIE;

    @Rule
    public IntentsTestRule<MovieDetailActivity> activityRule = new IntentsTestRule<MovieDetailActivity>(MovieDetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(targetContext, MovieDetailActivity.class);
            intent.putExtra(Extra.MOVIE, MOVIE);
            return intent;
        }
    };

    @BeforeClass
    public static void init() {
        MOVIE = new Movie();
        MOVIE.setId(1);
        MOVIE.setName("Test");
        MOVIE.setDescription("Test Test");
    }

    @Test
    public void shouldShowRightData() {
        onView(withId(R.id.textViewName)).check(matches(withText(MOVIE.getName())));
        onView(withId(R.id.textViewDescription)).check(matches(withText(MOVIE.getDescription())));
    }

    @Test
    public void shouldNavigateToComment_whenClickCommentButton() {
        onView(withId(R.id.buttonComment)).perform(click());

        intended(hasComponent(CommentActivity.class.getName()));
    }
}
