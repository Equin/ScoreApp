package scoreapp.testtask.com.presentation.internal.di.components;

import android.app.Activity;

import dagger.Component;
import scoreapp.testtask.com.presentation.internal.di.PerActivity;
import scoreapp.testtask.com.presentation.internal.di.modules.ActivityModule;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
