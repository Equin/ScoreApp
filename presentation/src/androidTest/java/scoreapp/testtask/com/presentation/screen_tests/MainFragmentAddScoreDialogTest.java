package scoreapp.testtask.com.presentation.screen_tests;

import scoreapp.testtask.com.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static scoreapp.testtask.com.presentation.utils.TestConstants.TEAM_ONE;
import static scoreapp.testtask.com.presentation.utils.TestConstants.TEAM_THREE;
import static scoreapp.testtask.com.presentation.utils.TestConstants.TEAM_TWO;
import static scoreapp.testtask.com.presentation.utils.Utils.addNewTeamsScore;
import static scoreapp.testtask.com.presentation.utils.Utils.checkToastMessage;
import static scoreapp.testtask.com.presentation.utils.Utils.selectNextTeamFromSpinner;


public class MainFragmentAddScoreDialogTest implements ScreenTests {
    @Override
    public void run() {
        testAddTeamsScore_shouldShowToastWithTextNewTeamAdded();
        testAddScoreToSameTeams_shouldShowToastWithTextTeamsShouldBeDifferent();
    }

    private void testAddTeamsScore_shouldShowToastWithTextNewTeamAdded() {
        onView(withId(R.id.button_add_score)).perform(click());
        onView(withId(R.id.relativeLayout_add_score)).check(matches(isDisplayed()));

        addNewTeamsScore(1, 1);
        checkToastMessage(R.string.score_updated);

        selectNextTeamFromSpinner(R.id.spinner_second_team, TEAM_THREE);

        addNewTeamsScore(1, 3);
        checkToastMessage(R.string.score_updated);

        selectNextTeamFromSpinner(R.id.spinner_first_team, TEAM_TWO);
        selectNextTeamFromSpinner(R.id.spinner_second_team, TEAM_ONE);

        addNewTeamsScore(2, 1);
        checkToastMessage(R.string.score_updated);

        selectNextTeamFromSpinner(R.id.spinner_first_team, TEAM_TWO);
        selectNextTeamFromSpinner(R.id.spinner_second_team, TEAM_THREE);

        addNewTeamsScore(2, 3);
        checkToastMessage(R.string.score_updated);

        selectNextTeamFromSpinner(R.id.spinner_second_team, TEAM_ONE);
        selectNextTeamFromSpinner(R.id.spinner_first_team, TEAM_THREE);

        addNewTeamsScore(4, 5);
        checkToastMessage(R.string.score_updated);

        selectNextTeamFromSpinner(R.id.spinner_second_team, TEAM_TWO);

        addNewTeamsScore(7, 6);
        checkToastMessage(R.string.score_updated);

        onView(withId(R.id.editText_score_second_team)).perform(pressImeActionButton());
    }

    private void testAddScoreToSameTeams_shouldShowToastWithTextTeamsShouldBeDifferent() {
        onView(withId(R.id.button_add_score)).perform(click());
        onView(withId(R.id.relativeLayout_add_score)).check(matches(isDisplayed()));

        selectNextTeamFromSpinner(R.id.spinner_first_team, TEAM_TWO);

        addNewTeamsScore(1, 2);
        checkToastMessage(R.string.teams_should_be_different);

        onView(withId(R.id.editText_score_second_team)).perform(pressImeActionButton());
    }
}
