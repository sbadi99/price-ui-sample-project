package com.goodrx.android.ui;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import com.goodrx.android.R;
import com.goodrx.android.ui.activity.MainActivity;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * High-Level Espresso test that traverses the zip code flow.
 */
@LargeTest @RunWith(AndroidJUnit4.class) public class EnterZipCodeActivityTest {


  @Rule public ActivityTestRule<MainActivity> zipCodeTestRule =
      new ActivityTestRule<>(MainActivity.class);

  /**
   * The test that automates the zip code flow
   */
  @Test public void enterZipCodeActivityTest() {
    ViewInteraction appCompatButton = onView(
        allOf(withId(R.id.setLocation), withText("Set Location"),
            childAtPosition(childAtPosition(withId(android.R.id.content), 0), 2), isDisplayed()));
    appCompatButton.perform(click());

    ViewInteraction appCompatTextView = onView(
        allOf(withId(R.id.enter_location), withText("Enter Location"),
            childAtPosition(childAtPosition(withId(android.R.id.content), 0), 4), isDisplayed()));
    appCompatTextView.perform(click());

    ViewInteraction appCompatEditText = onView(allOf(withId(R.id.enter_zip_code),
        childAtPosition(childAtPosition(withId(android.R.id.content), 0), 0), isDisplayed()));
    appCompatEditText.perform(replaceText("91750"), closeSoftKeyboard());

    ViewInteraction submit = onView(
        allOf(withId(R.id.zip_code_submit), withText("Submit"),
            childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));
    submit.perform(click());
  }

  /**
   * The matcher that allows the views to matched by position
   * @param parentMatcher the parent matcher
   * @param position the position of the child
   * @return
   */
  private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher,
      final int position) {

    return new TypeSafeMatcher<View>() {
      @Override public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent) && view.equals(
            ((ViewGroup) parent).getChildAt(position));
      }
    };
  }
}
