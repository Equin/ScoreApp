package scoreapp.testtask.com.presentation.screen_tests;


import android.os.SystemClock;
import android.view.KeyEvent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static scoreapp.testtask.com.presentation.utils.Utils.checkToastMessage;

import scoreapp.testtask.com.R;
import scoreapp.testtask.com.presentation.utils.ToastMatcher;

public class MainFragmentTests implements ScreenTests {
    @Override
    public void run() {
        testAddTeamButton_shouldOpenAddTeamDialog();
        testAddScoreButton_shouldShowToastMessageWithNotEnoughTeamCountMessage();
        testShowStatisticsButton_shouldShowToastMessageWithNotEnoughTeamCountMessage();
        testResetStatisticsButton_shouldShowToastMessageWithStatisticsResetedText();
    }

    private void testAddTeamButton_shouldOpenAddTeamDialog() {
        onView(withId(R.id.button_add_team)).perform(click());
        onView(withId(R.id.relativeLayout_add_team)).check(matches(isDisplayed()));
        onView(withId(R.id.editText_team_name)).perform(pressImeActionButton());
    }

    private void testAddScoreButton_shouldShowToastMessageWithNotEnoughTeamCountMessage() {
        onView(withId(R.id.button_add_score)).perform(click());
        checkToastMessage(R.string.should_have_more_teams);
    }

    private void testShowStatisticsButton_shouldShowToastMessageWithNotEnoughTeamCountMessage() {
        onView(withId(R.id.button_show_statistics)).perform(click());
        checkToastMessage(R.string.should_have_more_teams);
    }

    private void testResetStatisticsButton_shouldShowToastMessageWithStatisticsResetedText() {
        onView(withId(R.id.button_reset_statistics)).perform(click());
        checkToastMessage(R.string.shared_reseted);
    }
}
