package tugas.besar;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import tugas.besar.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityTestRule = new ActivityTestRule<>(SplashScreenActivity.class);

    @Test
    public void registerActivityTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btnSignUp), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                0)));
        materialButton.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBack();

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.btnSignUpReg), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        6),
                                0)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.namaReg),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_nama),
                                        0),
                                0)));
        textInputEditText.perform(scrollTo(), click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.namaReg),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_nama),
                                        0),
                                0)));
        textInputEditText2.perform(scrollTo(), replaceText("lukito"), closeSoftKeyboard());

        pressBack();

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btnSignUpReg), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        6),
                                0)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.emailReg),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_email),
                                        0),
                                0)));
        textInputEditText3.perform(scrollTo(), replaceText("lukito"), closeSoftKeyboard());

        pressBack();

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.btnSignUpReg), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        6),
                                0)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.passwordReg),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_password),
                                        0),
                                0)));
        textInputEditText4.perform(scrollTo(), replaceText("test123"), closeSoftKeyboard());

        pressBack();

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btnSignUpReg), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        6),
                                0)));
        materialButton5.perform(scrollTo(), click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.passwordReg), withText("test123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_password),
                                        0),
                                0)));
        textInputEditText5.perform(scrollTo(), click());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.passwordReg), withText("test123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_password),
                                        0),
                                0)));
        textInputEditText6.perform(scrollTo(), replaceText("pbp"));

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.passwordReg), withText("pbp"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_password),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText7.perform(closeSoftKeyboard());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.emailReg), withText("lukito"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_email),
                                        0),
                                0)));
        textInputEditText8.perform(scrollTo(), replaceText("lukito@gmail.com"));

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.emailReg), withText("lukito@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_email),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText9.perform(closeSoftKeyboard());

        pressBack();

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.btnSignUpReg), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        6),
                                0)));
        materialButton6.perform(scrollTo(), click());

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.passwordReg), withText("pbp"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_password),
                                        0),
                                0)));
        textInputEditText10.perform(scrollTo(), replaceText("pbp123"));

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.passwordReg), withText("pbp123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_password),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText11.perform(closeSoftKeyboard());

        pressBack();

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.btnSignUpReg), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        6),
                                0)));
        materialButton7.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
