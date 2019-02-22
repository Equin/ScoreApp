package scoreapp.testtask.com.presentation.internal.di.modules;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import scoreapp.testtask.com.domain.executor.PostExecutionThread;
import scoreapp.testtask.com.domain.executor.ThreadExecutor;
import scoreapp.testtask.com.domain.interactor.sharedPreferences.ResetSharedPreferences;
import scoreapp.testtask.com.domain.interactor.team.AddNewTeam;
import scoreapp.testtask.com.domain.interactor.team.GetTeams;
import scoreapp.testtask.com.domain.interactor.team.UpdateTeam;
import scoreapp.testtask.com.domain.repository.TeamRepository;
import scoreapp.testtask.com.presentation.internal.di.PerActivity;
import scoreapp.testtask.com.presentation.presenter.MainPresenter;
import scoreapp.testtask.com.presentation.view.custom_view.CustomScoreTable;

@Module
public class CoreModule {

    @Provides
    @PerActivity
    GetTeams provideGetTeams(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            TeamRepository teamRepository) {
        return new GetTeams(
                threadExecutor,
                postExecutionThread,
                teamRepository);
    }

    @Provides
    @PerActivity
    UpdateTeam provideUpdateTeam(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            TeamRepository teamRepository) {
        return new UpdateTeam(
                threadExecutor,
                postExecutionThread,
                teamRepository);
    }

    @Provides
    @PerActivity
    AddNewTeam provideAddNewTeam(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            TeamRepository teamRepository) {
        return new AddNewTeam(
                threadExecutor,
                postExecutionThread,
                teamRepository);
    }

    @Provides
    @PerActivity
    ResetSharedPreferences provideClearSharedPreferences(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            TeamRepository teamRepository) {
        return new ResetSharedPreferences(
                threadExecutor,
                postExecutionThread,
                teamRepository);
    }

    @Provides
    @PerActivity
    MainPresenter provideMainPresenter(
            Context context,
            AddNewTeam addNewTeam,
            GetTeams getTeams,
            UpdateTeam updateTeam,
            ResetSharedPreferences resetSharedPreferences) {
        return new MainPresenter(context, addNewTeam, getTeams, updateTeam, resetSharedPreferences);
    }

    @Provides
    @PerActivity
    CustomScoreTable provideCustomScoreTable(Activity activity, Context context) {
        return new CustomScoreTable(activity, context);
    }
}