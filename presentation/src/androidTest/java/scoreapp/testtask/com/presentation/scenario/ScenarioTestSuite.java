package scoreapp.testtask.com.presentation.scenario;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;



import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import scoreapp.testtask.com.presentation.presenter.MainPresenter;
import scoreapp.testtask.com.presentation.screen_tests.MainFragmentAddScoreDialogTest;
import scoreapp.testtask.com.presentation.screen_tests.MainFragmentAddTeamDialogTest;
import scoreapp.testtask.com.presentation.screen_tests.MainFragmentStatisticsScreenTest;
import scoreapp.testtask.com.presentation.screen_tests.MainFragmentTests;
import scoreapp.testtask.com.presentation.screen_tests.ScreenTests;
import scoreapp.testtask.com.presentation.utils.UiTestsBase;
import scoreapp.testtask.com.presentation.view.activity.MainActivity;

public class ScenarioTestSuite extends UiTestsBase {

    @Rule
    public final ActivityTestRule<MainActivity> mainActivityActivityTestRule;


    public ScenarioTestSuite() {
        this.mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();

        Activity mainActivity = this.mainActivityActivityTestRule.getActivity();
        Intent intent = new Intent(mainActivity, MainActivity.class);
        mainActivity.startActivity(intent);
    }

    @Test
    public void profileEditingScenario() {
        ScreenTests[] screensTests = {
                new MainFragmentTests(),
                new MainFragmentAddTeamDialogTest(),
                new MainFragmentAddScoreDialogTest(),
                new MainFragmentStatisticsScreenTest()
        };

        for (ScreenTests test:screensTests) {
            test.run();
        }
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
