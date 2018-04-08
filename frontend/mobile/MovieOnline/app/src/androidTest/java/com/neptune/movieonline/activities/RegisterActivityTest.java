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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.neptune.movieonline.utils.ViewMatchersExtension.hasErrorText;

/**
 * Created by Neptune on 4/8/2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterActivityTest {
    @Rule
    public IntentsTestRule<RegisterActivity> activityRule = new IntentsTestRule<>(RegisterActivity.class);

    @Test
    public void test_whenClickRegisterWithInvalidEmail_shouldShowError() {
        ViewInteraction editTextEmail = onView(withId(R.id.editTextEmail)).perform(typeText("test"));
        onView(withId(R.id.buttonRegister)).perform(click());

        editTextEmail.check(matches(hasErrorText(R.string.error_email_invalid)));
    }

    @Test
    public void test_whenClickRegisterWithEmptyInput_shouldShowError() {
        ViewInteraction editTextName = onView(withId(R.id.editTextName)).perform(typeText(""));
        ViewInteraction editTextEmail = onView(withId(R.id.editTextEmail)).perform(typeText(""));
        ViewInteraction editTextPassword = onView(withId(R.id.editTextPassword)).perform(typeText(""));
        ViewInteraction editTextConfirmPassword = onView(withId(R.id.editTextConfirmPassword)).perform(typeText(""));
        onView(withId(R.id.buttonRegister)).perform(click());

        editTextName.check(matches(hasErrorText(R.string.error_field_required)));
        editTextEmail.check(matches(hasErrorText(R.string.error_field_required)));
        editTextPassword.check(matches(hasErrorText(R.string.error_field_required)));
        editTextConfirmPassword.check(matches(hasErrorText(R.string.error_field_required)));
    }

    @Test
    public void test_whenClickRegisterWithNotMatchConfirmPassword_shouldShowError() {
        onView(withId(R.id.editTextPassword)).perform(typeText("test"));
        ViewInteraction editTextConfirmPassword = onView(withId(R.id.editTextConfirmPassword)).perform(typeText("test_test"));
        onView(withId(R.id.buttonRegister)).perform(click());

        editTextConfirmPassword.check(matches(hasErrorText(R.string.error_confirm_password_incorrect)));
    }
}