package scoreapp.testtask.com.presentation.screen_tests;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import scoreapp.testtask.com.presentation.utils.ToastMatcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import scoreapp.testtask.com.R;
import scoreapp.testtask.com.presentation.utils.ToastMatcher;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static scoreapp.testtask.com.presentation.utils.TestConstants.TEAM_ONE;
import static scoreapp.testtask.com.presentation.utils.TestConstants.TEAM_THREE;
import static scoreapp.testtask.com.presentation.utils.TestConstants.TEAM_TWO;
import static scoreapp.testtask.com.presentation.utils.Utils.addTeamName;
import static scoreapp.testtask.com.presentation.utils.Utils.checkToastMessage;


public class MainFragmentAddTeamDialogTest implements ScreenTests {
    @Override
    public void run() {
        testAddTeams_shouldShowToastWithTextNewTeamAdded();
        testAddDuplicateTeam_shouldShowToastWithTextSuchTeamAlreadyExist();
    }

    private void testAddTeams_shouldShowToastWithTextNewTeamAdded() {
        onView(withId(R.id.button_add_team)).perform(click());
        onView(withId(R.id.relativeLayout_add_team)).check(matches(isDisplayed()));

        addTeamName(TEAM_ONE);
        checkToastMessage(R.string.new_team_created);

        addTeamName(TEAM_TWO);
        checkToastMessage(R.string.new_team_created);

        addTeamName(TEAM_THREE);
        checkToastMessage(R.string.new_team_created);
    }

    private void testAddDuplicateTeam_shouldShowToastWithTextSuchTeamAlreadyExist() {
        addTeamName(TEAM_ONE);
        checkToastMessage(R.string.duplicate_of_team);
        onView(withId(R.id.editText_team_name)).perform(pressImeActionButton());
    }
}
