package scoreapp.testtask.com.presentation.internal.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import scoreapp.testtask.com.domain.executor.PostExecutionThread;
import scoreapp.testtask.com.domain.executor.ThreadExecutor;
import scoreapp.testtask.com.domain.repository.TeamRepository;
import scoreapp.testtask.com.presentation.internal.di.modules.ApplicationModule;
import scoreapp.testtask.com.presentation.view.activity.BaseActivity;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity activity);

    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    TeamRepository teamRepository();
}
