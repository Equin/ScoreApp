package scoreapp.testtask.com.domain.interactor.team;

import javax.inject.Inject;

import io.reactivex.Completable;
import scoreapp.testtask.com.domain.executor.PostExecutionThread;
import scoreapp.testtask.com.domain.executor.ThreadExecutor;
import scoreapp.testtask.com.domain.interactor.CompletableUseCase;
import scoreapp.testtask.com.domain.model.Team;
import scoreapp.testtask.com.domain.repository.TeamRepository;

public class UpdateTeam extends CompletableUseCase<Team> {

    private final TeamRepository teamRepository;

    @Inject
    public UpdateTeam(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            TeamRepository teamRepository) {
        super(threadExecutor, postExecutionThread);
        this.teamRepository = teamRepository;
    }

    @Override
    protected Completable buildUseCaseObservable(Team team) {
        return this.teamRepository.updateTeam(team);
    }
}
