package scoreapp.testtask.com.domain.interactor.team;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import scoreapp.testtask.com.domain.executor.PostExecutionThread;
import scoreapp.testtask.com.domain.executor.ThreadExecutor;
import scoreapp.testtask.com.domain.interactor.UseCase;
import scoreapp.testtask.com.domain.model.Team;
import scoreapp.testtask.com.domain.repository.TeamRepository;

public class GetTeams extends UseCase<List<Team>, Void> {

    private final TeamRepository teamRepository;

    @Inject
    public GetTeams(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            TeamRepository teamRepository) {
        super(threadExecutor, postExecutionThread);
        this.teamRepository = teamRepository;
    }

    @Override
    protected Observable<List<Team>> buildUseCaseObservable(Void aVoid) {
        return this.teamRepository.getTeams();

    }
}