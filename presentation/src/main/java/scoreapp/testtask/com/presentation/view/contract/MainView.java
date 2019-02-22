package scoreapp.testtask.com.presentation.view.contract;

import java.util.Collection;
import java.util.List;

import scoreapp.testtask.com.domain.model.Team;

public interface MainView extends LoadDataView {
    void showAddTeamDialog();
    void showAddScoreDialog();
    void showStatisticsScreen();
    void setTeamsToSpinnerAdapter(List<Team> teams);
    void dismissAddScoreDialog();
}
