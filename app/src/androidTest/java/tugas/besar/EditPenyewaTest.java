package tugas.besar;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditPenyewaTest {

    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityTestRule = new ActivityTestRule<>(SplashScreenActivity.class);

    @Test
    public void editPenyewaTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.inputEmail),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_first_layout),
                                        0),
                                1)));
        textInputEditText.perform(scrollTo(), click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.inputEmail),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_first_layout),
                                        0),
                                0)));
        textInputEditText2.perform(scrollTo(), replaceText("tubes123pbp@gmail.com"), closeSoftKeyboard());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.inputPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_last_layout),
                                        0),
                                1)));
        textInputEditText3.perform(scrollTo(), replaceText("Tubes9876"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btnSignIn), withText("Sign In"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1)));
        materialButton.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.btnList), withText("List Penyewa"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                0)));
        materialButton2.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.linearLayout),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.penyewaRecyclerView),
                                        0),
                                0),
                        isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btnEdit), withText("EDIT"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        materialButton3.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBack();

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.etNamaPenyewa), withText("joko"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twNamaPenyewa),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.etNamaPenyewa), withText("joko"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twNamaPenyewa),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText5.perform(replaceText(""));

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.etNamaPenyewa),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twNamaPenyewa),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText6.perform(closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.btnUpdatePenyewa), withText("UPDATE"),
                        childAtPosition(
                                allOf(withId(R.id.footer),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        materialButton4.perform(click());

//        ViewInteraction textInputEditText7 = onView(
//                allOf(withId(R.id.etDurasi), withText("5 Hari"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.twDurasi),
//                                        0),
//                                1),
//                        isDisplayed()));
//        textInputEditText7.perform(replaceText(""));

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.etDurasi),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twDurasi),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText8.perform(closeSoftKeyboard());


        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btnUpdatePenyewa), withText("UPDATE"),
                        childAtPosition(
                                allOf(withId(R.id.footer),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.etPembayaran), withText("Cash"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twPembayaran),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText9.perform(replaceText(""));

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.etPembayaran),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twPembayaran),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText10.perform(closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.btnUpdatePenyewa), withText("UPDATE"),
                        childAtPosition(
                                allOf(withId(R.id.footer),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.etNamaPenyewa),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twNamaPenyewa),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText11.perform(click());

        ViewInteraction textInputEditText12 = onView(
                allOf(withId(R.id.etNamaPenyewa),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twNamaPenyewa),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText12.perform(replaceText("lukito"), closeSoftKeyboard());

        ViewInteraction textInputEditText13 = onView(
                allOf(withId(R.id.etDurasi),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twDurasi),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText13.perform(replaceText("3"), closeSoftKeyboard());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.btnUpdatePenyewa), withText("UPDATE"),
                        childAtPosition(
                                allOf(withId(R.id.footer),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction textInputEditText14 = onView(
                allOf(withId(R.id.etDurasi), withText("3"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twDurasi),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText14.perform(replaceText(""));

        ViewInteraction textInputEditText15 = onView(
                allOf(withId(R.id.etDurasi),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twDurasi),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText15.perform(closeSoftKeyboard());


        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.btnUpdatePenyewa), withText("UPDATE"),
                        childAtPosition(
                                allOf(withId(R.id.footer),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        materialButton8.perform(click());

        ViewInteraction textInputEditText16 = onView(
                allOf(withId(R.id.etDurasi),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twDurasi),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText16.perform(click());

        ViewInteraction textInputEditText17 = onView(
                allOf(withId(R.id.etDurasi),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twDurasi),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText17.perform(replaceText("3"), closeSoftKeyboard());

        pressBack();

        ViewInteraction textInputEditText18 = onView(
                allOf(withId(R.id.etPembayaran),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twPembayaran),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText18.perform(replaceText("Debit"), closeSoftKeyboard());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.btnUpdatePenyewa), withText("UPDATE"),
                        childAtPosition(
                                allOf(withId(R.id.footer),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        materialButton9.perform(click());
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
