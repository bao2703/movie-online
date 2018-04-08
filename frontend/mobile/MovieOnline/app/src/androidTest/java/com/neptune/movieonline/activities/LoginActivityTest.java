package com.neptune.movieonline.activities;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.neptune.movieonline.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.neptune.movieonline.utils.ViewMatchersExtension.hasErrorText;

/**
 * Created by Neptune on 4/8/2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public IntentsTestRule<LoginActivity> activityRule = new IntentsTestRule<>(LoginActivity.class);

    @Test
    public void test_whenClickLoginWithInvalidEmail_shouldShowError() {
        ViewInteraction editTextEmail = onView(withId(R.id.editTextEmail)).perform(typeText("test"));
        onView(withId(R.id.buttonLogin)).perform(click());

        editTextEmail.check(matches(hasErrorText(R.string.error_email_invalid)));
    }

    @Test
    public void test_whenClickLoginWithEmptyInput_shouldShowError() {
        ViewInteraction editTextEmail = onView(withId(R.id.editTextEmail)).perform(typeText(""));
        ViewInteraction editTextPassword = onView(withId(R.id.editTextPassword)).perform(typeText(""));
        onView(withId(R.id.buttonLogin)).perform(click());

        editTextEmail.check(matches(hasErrorText(R.string.error_field_required)));
        editTextPassword.check(matches(hasErrorText(R.string.error_field_required)));
    }

    @Test
    public void test_whenClickLoginWithRightAccount_shouldNavigateToMainScreen() {
        onView(withId(R.id.editTextEmail)).perform(typeText("admin@gmail.com"));
        onView(withId(R.id.editTextPassword)).perform(typeText("1"));
        onView(withId(R.id.buttonLogin)).perform(click());

        intended(hasComponent(MainActivity.class.getName()));
    }
}