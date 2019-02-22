package scoreapp.testtask.com.presentation.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

import scoreapp.testtask.com.domain.interactor.sharedPreferences.ResetSharedPreferences;
import scoreapp.testtask.com.domain.interactor.DefaultCompletableObserver;
import scoreapp.testtask.com.domain.interactor.DefaultObserver;
import scoreapp.testtask.com.domain.interactor.team.AddNewTeam;
import scoreapp.testtask.com.domain.interactor.team.GetTeams;
import scoreapp.testtask.com.domain.interactor.team.UpdateTeam;
import scoreapp.testtask.com.domain.model.OppositeTeam;
import scoreapp.testtask.com.domain.model.Team;
import scoreapp.testtask.com.presentation.view.contract.MainView;

import scoreapp.testtask.com.R;

public class MainPresenter extends BasePresenter {

    private final AddNewTeam addNewTeamUseCase;
    private final GetTeams getTeamsUseCase;
    private final UpdateTeam updateTeamUseCase;
    private final ResetSharedPreferences resetSharedPreferencesUseCase;
    private final Context context;

    private  MainView mainView;
    private List<Team> teams;

    @Inject
    public MainPresenter(Context context,
            AddNewTeam addNewTeamUseCase,
            GetTeams getTeamsUseCase,
            UpdateTeam updateTeamUseCase,
            ResetSharedPreferences resetSharedPreferencesUseCase) {
        this.context = context;
        this.addNewTeamUseCase = addNewTeamUseCase;
        this.getTeamsUseCase = getTeamsUseCase;
        this.updateTeamUseCase = updateTeamUseCase;
        this.resetSharedPreferencesUseCase = resetSharedPreferencesUseCase;
    }

    public void setView(MainView mainView) {
        this.mainView = mainView;
    }

    public void initialize() {
        getTeams();
    }

    public void onShowStatisticsButtonClicked() {
        if(teams.size() > 1) {
            this.mainView.showStatisticsScreen();
        } else {
            this.showToastMessage(R.string.should_have_more_teams);
        }
    }

    public void onAddTeamButtonClicked() {
        this.mainView.showAddTeamDialog();
    }

    public void onAddScoreButtonClicked() {
        this.mainView.showAddScoreDialog();
    }

    public void setTeamsToSpinnerAdapter() {
        this.mainView.setTeamsToSpinnerAdapter(this.teams);

        if(this.teams == null || this.teams.size() < 2) {
            this.showToastMessage(R.string.should_have_more_teams);
            this.mainView.dismissAddScoreDialog();
        }
    }

    public void onSaveTeamButtonClicked(String teamName) {
        Team team = new Team(teamName);
        this.addNewTeamUseCase.execute(new AddNewTeamObserver(), team);
    }

    public void onSaveTeamScoreButtonClicked(String firstTeamName, int firstTeamScore, String secondTeamName, int secondTeamScore ) {
        boolean isWinner = false;
        boolean isDraw = false;

        if(secondTeamScore > firstTeamScore) {
            isWinner = true;
        } else if (secondTeamScore == firstTeamScore) {
            isDraw = true;
        }

        OppositeTeam OppositeTeam = new OppositeTeam(secondTeamName, secondTeamScore, firstTeamScore, isWinner, isDraw);

        Team team = new Team();
        team.addOppositeTeam(OppositeTeam);

        team.setTeamName(firstTeamName);

        if(firstTeamName != secondTeamName) {
            this.updateTeamUseCase.execute(new UpdateTeamObserver(), team);
        } else {
            this.showToastMessage(R.string.teams_should_be_different);
        }
    }

    public void onResetStatisticsButtonClicked() {
        this.resetSharedPreferencesUseCase.execute(new ResetSharedPreferencesObserver(), null);
    }

    private void getTeams() {
        this.getTeamsUseCase.execute(new GetTeamsObserver(),null );
    }

    private void showToastMessage(int  message) {
        this.mainView.showToast(context.getResources().getString(message));
    }

    private void showToastMessage(String  message) {
        this.mainView.showToast(message);
    }

    @Override
    public void destroy() {
        this.mainView = null;
        this.addNewTeamUseCase.dispose();
        this.getTeamsUseCase.dispose();
        this.updateTeamUseCase.dispose();
        this.resetSharedPreferencesUseCase.dispose();
    }

    private class AddNewTeamObserver extends DefaultCompletableObserver {
        @Override
        public void onComplete() {
            MainPresenter.this.showToastMessage(R.string.new_team_created);
            MainPresenter.this.getTeams();
        }

        @Override
        public void onError(@NonNull Throwable e) {
            MainPresenter.this.showToastMessage(e.getMessage());
        }
    }

    private class UpdateTeamObserver extends DefaultCompletableObserver {
        @Override
        public void onComplete() {
            MainPresenter.this.showToastMessage(R.string.score_updated);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            MainPresenter.this.showToastMessage(R.string.uncatched_error);
        }
    }

    private class GetTeamsObserver extends DefaultObserver<List<Team>> {
        @Override
        public void onNext(List<Team> teamList) {
            MainPresenter.this.teams = teamList;
        }

        @Override
        public void onError(@NonNull Throwable e) {
            MainPresenter.this.teams = new ArrayList<>();
        }
    }

    private class ResetSharedPreferencesObserver extends DefaultCompletableObserver {
        @Override
        public void onComplete() {
            MainPresenter.this.showToastMessage(R.string.shared_reseted);
            MainPresenter.this.getTeams();
        }

        @Override
        public void onError(@NonNull Throwable e) {
            MainPresenter.this.showToastMessage(R.string.shared_not_reseted);
        }
    }
}
