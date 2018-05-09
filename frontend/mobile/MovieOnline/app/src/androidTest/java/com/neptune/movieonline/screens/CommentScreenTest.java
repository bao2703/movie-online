package com.neptune.movieonline.screens;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.neptune.movieonline.R;
import com.neptune.movieonline.activities.CommentActivity;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.constants.Extra;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.neptune.movieonline.utils.ViewMatchersExtension.atPosition;
import static org.junit.Assert.assertEquals;

/**
 * Created by Neptune on 4/13/2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CommentScreenTest {
    private static Movie MOVIE;

    @Rule
    public IntentsTestRule<CommentActivity> activityRule = new IntentsTestRule<CommentActivity>(CommentActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(targetContext, CommentActivity.class);
            intent.putExtra(Extra.MOVIE, MOVIE);
            return intent;
        }
    };

    @BeforeClass
    public static void init() {
        MOVIE = new Movie();
        MOVIE.setId(1);
        MOVIE.setName("Test");
    }

    @Test
    public void shouldDisplayItems() {
        ViewInteraction recyclerView = onView(withId(R.id.recyclerView));

        recyclerView.check(matches(hasDescendant(withId(R.id.textViewName))));
        recyclerView.check(matches(hasDescendant(withId(R.id.textViewContent))));
        recyclerView.check(matches(hasDescendant(withId(R.id.textViewDateCreated))));
        recyclerView.check(matches(hasDescendant(withId(R.id.imageViewAvatar))));
    }

    @Test
    public void shouldCreateNewComment() {
        final int prev = getRecyclerViewItemCount();

        onView(withId(R.id.editTextComment)).perform(typeText("test"));
        onView(withId(R.id.buttonComment)).perform(click());

        final int after = getRecyclerViewItemCount();

        assertEquals(prev + 1, after);

        onView(withId(R.id.recyclerView))
                .check(matches(atPosition(0, hasDescendant(withText("test")))));
    }

    private int getRecyclerViewItemCount() {
        RecyclerView recyclerView = activityRule.getActivity().findViewById(R.id.recyclerView);
        return recyclerView.getAdapter().getItemCount();
    }
}
