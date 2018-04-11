package com.neptune.movieonline.screens;

import android.support.test.espresso.ViewInteraction;
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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Neptune on 4/10/2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MovieListScreenTest {

    @Rule
    public IntentsTestRule<MovieListActivity> activityRule = new IntentsTestRule<>(MovieListActivity.class);

    @Test
    public void shouldDisplayItems() {
        ViewInteraction recyclerView = onView(withId(R.id.recyclerViewMovieList));

        recyclerView.check(matches(hasDescendant(withId(R.id.textViewName))));
        recyclerView.check(matches(hasDescendant(withId(R.id.textViewViews))));
        recyclerView.check(matches(hasDescendant(withId(R.id.imageViewPoster))));
    }

    @Test
    public void shouldNavigateToDetail_whenClickOnItem() {
        onView(withId(R.id.recyclerViewMovieList))
                .perform(actionOnItemAtPosition(5, click()));

        intended(hasComponent(MovieDetailActivity.class.getName()));
    }
}
