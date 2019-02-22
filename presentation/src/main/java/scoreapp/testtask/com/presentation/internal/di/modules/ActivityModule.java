package scoreapp.testtask.com.presentation.internal.di.modules;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import scoreapp.testtask.com.presentation.internal.di.PerActivity;

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return this.activity;
    }
}
