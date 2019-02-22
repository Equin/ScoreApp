package scoreapp.testtask.com.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import scoreapp.testtask.com.presentation.internal.di.HasComponent;
import scoreapp.testtask.com.presentation.internal.di.components.CoreComponent;
import scoreapp.testtask.com.presentation.internal.di.components.DaggerCoreComponent;
import scoreapp.testtask.com.presentation.internal.di.modules.ActivityModule;
import scoreapp.testtask.com.presentation.internal.di.modules.CoreModule;
import scoreapp.testtask.com.presentation.view.fragment.ScoreTableFragment;

import scoreapp.testtask.com.R;

public class ScoreTableActivity extends BaseActivity
        implements HasComponent<CoreComponent> {

    private CoreComponent coreComponent;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ScoreTableActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //this is done coz TableView library has some issues with restoring state
        if (savedInstanceState != null) {
            savedInstanceState = null;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_fragment);

        initializeInjector();

        if (savedInstanceState == null) {
            replaceFragment(R.id.container_for_fragment, ScoreTableFragment.newInstance());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void initializeInjector() {
        this.coreComponent = DaggerCoreComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .coreModule(new CoreModule())
                .build();
    }

    @Override
    public CoreComponent getComponent() {
        if (this.coreComponent == null) {
            initializeInjector();
        }
        return this.coreComponent;
    }
}