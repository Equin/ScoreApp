package scoreapp.testtask.com.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import scoreapp.testtask.com.domain.interactor.DefaultObserver;
import scoreapp.testtask.com.domain.interactor.team.GetTeams;
import scoreapp.testtask.com.domain.model.Team;
import scoreapp.testtask.com.presentation.view.contract.ScoreTableView;

public class ScoreTablePresenter extends BasePresenter {

    private final GetTeams getTeamsUseCase;

    private ScoreTableView scoreTableView;
    private List<Team> teams;

    @Inject
    public ScoreTablePresenter(GetTeams getTeamsUseCase) {
        this.getTeamsUseCase = getTeamsUseCase;
    }

    public void setView(ScoreTableView scoreTableView) {
        this.scoreTableView = scoreTableView;
    }

    public void initialize() {
        this.showLoading();
        getTeams();
    }

    private void getTeams() {
        this.getTeamsUseCase.execute(new GetTeamsObserver(), null);
    }

    private void renderScoreTable(List<Team> teamList) {
        this.scoreTableView.renderTables(teamList);
    }

    private void showLoading() {
        this.scoreTableView.showLoading();
    }

    private void hideLoading() {
        this.scoreTableView.hideLoading();
    }

    @Override
    public void destroy() {
        this.scoreTableView = null;
        this.getTeamsUseCase.dispose();
        this.teams = null;
    }

    private class GetTeamsObserver extends DefaultObserver<List<Team>> {
        @Override
        public void onNext(List<Team> teamList) {
            ScoreTablePresenter.this.teams = teamList;
            renderScoreTable(ScoreTablePresenter.this.teams);
            ScoreTablePresenter.this.hideLoading();
        }

        @Override
        public void onError(@NonNull Throwable e) {
            ScoreTablePresenter.this.teams = new ArrayList<>();
            ScoreTablePresenter.this.hideLoading();
        }
    }
}
