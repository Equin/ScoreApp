package scoreapp.testtask.com.presentation.screen_tests;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import scoreapp.testtask.com.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static scoreapp.testtask.com.presentation.utils.TestConstants.SORT_BY;
import static scoreapp.testtask.com.presentation.utils.TestConstants.TEAMS_COUNT_IN_SCORE_TABLE;
import static scoreapp.testtask.com.presentation.utils.TestConstants.TEAM_NAME;
import static scoreapp.testtask.com.presentation.utils.TestConstants.SCORES_TABLE;
import static scoreapp.testtask.com.presentation.utils.Utils.checkForScoreTableCells;


public class MainFragmentStatisticsScreenTest implements ScreenTests {
    @Override
    public void run() {
        testScoreTable_shouldHaveAllScoresAndTableHeaders();
        testAddScoreToSameTeams_shouldShowToastWithTextTeamsShouldBeDifferent();
    }

    private void testScoreTable_shouldHaveAllScoresAndTableHeaders() {
        onView(withId(R.id.button_show_statistics)).perform(click());
        onView(withId(R.id.relative_layout_score_fragment)).check(matches(isDisplayed()));
        SystemClock.sleep(1000);

        for(int i = 0; i < TEAMS_COUNT_IN_SCORE_TABLE; i++) {
            checkForScoreTableCells(i, TEAM_NAME+(i+1), R.id.recyclerView_column_header);
            checkForScoreTableCells(i, TEAM_NAME+(i+1), R.id.recyclerView_row_header);
        }

        for(int i = 0; i < TEAMS_COUNT_IN_SCORE_TABLE; i++) {
            for(int j = 0; j < TEAMS_COUNT_IN_SCORE_TABLE; j++) {
                checkForScoreTableCells(i, SCORES_TABLE[i][j], R.id.row_item_recycler_view);
            }
        }
    }

    private void testAddScoreToSameTeams_shouldShowToastWithTextTeamsShouldBeDifferent() {
        onView(withId(R.id.spinner_sort)).perform(click());
        onView(withText(SORT_BY)).inRoot(isPlatformPopup()).perform(click());
        Espresso.pressBack();
        onView(withId(R.id.constraintLayout_main_fragment)).check(matches(isDisplayed()));
    }
}
