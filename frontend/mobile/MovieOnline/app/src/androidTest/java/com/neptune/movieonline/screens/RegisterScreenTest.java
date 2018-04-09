package com.neptune.movieonline.screens;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.neptune.movieonline.R;
import com.neptune.movieonline.activities.LoginActivity;
import com.neptune.movieonline.activities.RegisterActivity;

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
public class RegisterScreenTest {
    @Rule
    public IntentsTestRule<RegisterActivity> activityRule = new IntentsTestRule<>(RegisterActivity.class);

    @Test
    public void shouldShowError_whenClickRegisterWithInvalidEmail() {
        ViewInteraction editTextEmail = onView(withId(R.id.editTextEmail)).perform(typeText("test"));
        onView(withId(R.id.buttonRegister)).perform(click());

        editTextEmail.check(matches(hasErrorText(R.string.error_email_invalid)));
    }

    @Test
    public void shouldShowError_whenClickRegisterWithEmptyInput() {
        onView(withId(R.id.buttonRegister)).perform(click());

        onView(withId(R.id.editTextName)).check(matches(hasErrorText(R.string.error_field_required)));
        onView(withId(R.id.editTextEmail)).check(matches(hasErrorText(R.string.error_field_required)));
        onView(withId(R.id.editTextPassword)).check(matches(hasErrorText(R.string.error_field_required)));
        onView(withId(R.id.editTextConfirmPassword)).check(matches(hasErrorText(R.string.error_field_required)));
    }

    @Test
    public void shouldShowError_whenClickRegisterWithNotMatchConfirmPassword() {
        onView(withId(R.id.editTextPassword)).perform(typeText("test"));
        ViewInteraction editTextConfirmPassword = onView(withId(R.id.editTextConfirmPassword)).perform(typeText("test_test"));
        onView(withId(R.id.buttonRegister)).perform(click());

        editTextConfirmPassword.check(matches(hasErrorText(R.string.error_confirm_password_incorrect)));
    }

    @Test
    public void shouldRegisterSuccess_whenClickRegisterWithValidInput() {
        onView(withId(R.id.editTextName)).perform(typeText("test"));
        onView(withId(R.id.editTextEmail)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.editTextPassword)).perform(typeText("test"));
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("test"));
        onView(withId(R.id.buttonRegister)).perform(click());

        intended(hasComponent(LoginActivity.class.getName()));
    }

    @Test
    public void shouldRegisterFail_whenClickRegisterWithConflictEmail() {
        onView(withId(R.id.editTextName)).perform(typeText("test"));
        ViewInteraction editTextEmail = onView(withId(R.id.editTextEmail)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.editTextPassword)).perform(typeText("test"));
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("test"));
        onView(withId(R.id.buttonRegister)).perform(click());

        editTextEmail.check(matches(hasErrorText(R.string.error_email_conflict)));
    }
}