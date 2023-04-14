package com.example.cse_5236_app;

import androidx.test.espresso.matcher.RootMatchers;

import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;



import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.assertion.ViewAssertions;

import com.example.cse_5236_app.ui.Login.LoginActivity;
import com.example.cse_5236_app.ui.Login.LoginFragment;

import org.junit.Test;

public class LoginFragmentTest {

    @Test
    public void testLoginSuccess() {
        // Launch the activity and get the scenario
        ActivityScenario<LoginActivity> scenario = ActivityScenario.launch(LoginActivity.class);

        // Find the email and password fields, and enter valid credentials
        Espresso.onView(ViewMatchers.withId(R.id.username_text)).perform(ViewActions.typeText("JJ"));
        Espresso.onView(ViewMatchers.withId(R.id.password_text)).perform(ViewActions.typeText("12345"));

        // Click the login button
        Espresso.onView(ViewMatchers.withId(R.id.login_button)).perform(ViewActions.click());

        // Check if the home screen is displayed
        Espresso.onView(ViewMatchers.withId(R.id.nav_host_fragment_activity_main)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Close the activity scenario
        scenario.close();
    }

    @Test
    public void testLoginFailure() {
        // Launch the activity and get the scenario
        ActivityScenario<LoginActivity> scenario = ActivityScenario.launch(LoginActivity.class);

        // Find the email and password fields, and enter invalid credentials
        Espresso.onView(ViewMatchers.withId(R.id.username_text)).perform(ViewActions.typeText("invalid"));
        Espresso.onView(ViewMatchers.withId(R.id.password_text)).perform(ViewActions.typeText("password"));

        // Click the login button
        Espresso.onView(ViewMatchers.withId(R.id.login_button)).perform(ViewActions.click());

        // Check if the login screen is displayed
        Espresso.onView(ViewMatchers.withId(R.id.login_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));


        // Close the activity scenario
        scenario.close();
    }

    @Test
    public void testNewUserSuccess() {
        // Launch the activity and get the scenario
        ActivityScenario<LoginActivity> scenario = ActivityScenario.launch(LoginActivity.class);

        // Find the email and password fields, and enter valid credentials
        Espresso.onView(ViewMatchers.withId(R.id.username_text)).perform(ViewActions.typeText("testNewUser"));
        Espresso.onView(ViewMatchers.withId(R.id.password_text)).perform(ViewActions.typeText("12345"));

        // Click the login button
        Espresso.onView(ViewMatchers.withId(R.id.new_user_button)).perform(ViewActions.click());

        // Check if the login screen is displayed
        Espresso.onView(ViewMatchers.withId(R.id.login_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));


        // Click the login button
        Espresso.onView(ViewMatchers.withId(R.id.login_button)).perform(ViewActions.click());

        // Check if the home screen is displayed
        Espresso.onView(ViewMatchers.withId(R.id.nav_host_fragment_activity_main)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Close the activity scenario
        scenario.close();
    }
}

