package scoreapp.testtask.com.presentation;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import scoreapp.testtask.com.BuildConfig;
import scoreapp.testtask.com.presentation.internal.di.components.ApplicationComponent;
import scoreapp.testtask.com.presentation.internal.di.components.DaggerApplicationComponent;
import scoreapp.testtask.com.presentation.internal.di.modules.ApplicationModule;

public class AndroidApplication  extends Application {

    private ApplicationComponent applicationComponent;

    private Activity runningActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetection();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    public Activity getRunningActivity() {
        return this.runningActivity;
    }

    public void setRunningActivity(Activity runningActivity) {
        this.runningActivity = runningActivity;
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}