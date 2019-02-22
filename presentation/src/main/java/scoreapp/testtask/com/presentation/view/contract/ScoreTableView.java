package scoreapp.testtask.com.presentation.view.contract;

import java.util.List;

import scoreapp.testtask.com.domain.model.Team;

public interface ScoreTableView extends LoadDataView{
   void renderTables(List<Team> teams);
}
