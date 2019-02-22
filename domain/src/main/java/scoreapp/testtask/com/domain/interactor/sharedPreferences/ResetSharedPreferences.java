package scoreapp.testtask.com.domain.interactor.sharedPreferences;

import javax.inject.Inject;

import io.reactivex.Completable;
import scoreapp.testtask.com.domain.executor.PostExecutionThread;
import scoreapp.testtask.com.domain.executor.ThreadExecutor;
import scoreapp.testtask.com.domain.interactor.CompletableUseCase;
import scoreapp.testtask.com.domain.repository.TeamRepository;

public class ResetSharedPreferences extends CompletableUseCase<Void> {

    private final TeamRepository teamRepository;

    @Inject
    public ResetSharedPreferences(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            TeamRepository teamRepository) {
        super(threadExecutor, postExecutionThread);
        this.teamRepository = teamRepository;
    }

    @Override
    protected Completable buildUseCaseObservable(Void team) {
        return this.teamRepository.clearSharedPreferences();
    }
}