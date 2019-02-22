package scoreapp.testtask.com.presentation.internal.di.components;

import dagger.Component;
import scoreapp.testtask.com.presentation.internal.di.PerActivity;
import scoreapp.testtask.com.presentation.internal.di.modules.ActivityModule;
import scoreapp.testtask.com.presentation.internal.di.modules.CoreModule;
import scoreapp.testtask.com.presentation.view.fragment.MainFragment;
import scoreapp.testtask.com.presentation.view.fragment.ScoreTableFragment;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, CoreModule.class})
public interface CoreComponent {
    void inject(MainFragment mainFragment);
    void inject(ScoreTableFragment scoreTableFragment);
}
