package scoreapp.testtask.com.presentation.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

import scoreapp.testtask.com.R;

public class Utils {

    private Utils() {
        //intentionally left empty
    }

    public static void addTeamName(String teamName) {
        onView(withId(R.id.editText_team_name)).perform(
                clearText(),
                ViewActions.typeText(teamName)
        );

        onView(withId(R.id.button_save_new_team)).perform(click());
    }

    public static void checkToastMessage(int toastMessage ){
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    public static void selectNextTeamFromSpinner(int spinnerId, String itemName) {
        onView(withId(spinnerId)).perform(click());
        onView(withText(itemName)).inRoot(isPlatformPopup()).perform(click());
    }

    public static void addNewTeamsScore(int firstTeamScore, int secondTeamScore) {
        onView(withId(R.id.editText_score_first_team)).perform(
                clearText(),
                ViewActions.typeText(String.valueOf(firstTeamScore))
        );

        onView(withId(R.id.editText_score_first_team)).perform(pressImeActionButton());

        onView(withId(R.id.editText_score_second_team)).perform(
                clearText(),
                ViewActions.typeText(String.valueOf(secondTeamScore))
        );

        onView(withId(R.id.button_save_score)).perform(click());
    }

    public static void checkForScoreTableCells(int cellPosition, String content, int cellTypeId) {
        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textView_greed), withText(content),
                        childAtPosition(
                                childAtPosition(
                                        withId(cellTypeId),
                                        cellPosition),
                                0),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));
    }

    public static Matcher<View> childAtPosition(
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
