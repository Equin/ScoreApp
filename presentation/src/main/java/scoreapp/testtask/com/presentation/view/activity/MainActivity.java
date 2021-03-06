package scoreapp.testtask.com.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import scoreapp.testtask.com.presentation.internal.di.HasComponent;
import scoreapp.testtask.com.presentation.internal.di.components.CoreComponent;
import scoreapp.testtask.com.presentation.internal.di.components.DaggerCoreComponent;
import scoreapp.testtask.com.presentation.internal.di.modules.ActivityModule;
import scoreapp.testtask.com.presentation.internal.di.modules.CoreModule;
import scoreapp.testtask.com.presentation.view.fragment.MainFragment;

import scoreapp.testtask.com.R;

public class MainActivity extends BaseActivity
        implements HasComponent<CoreComponent>,
        MainFragment.MainFragmentInteractionListener {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    private CoreComponent coreComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_fragment);

        initializeInjector();

        if (savedInstanceState == null) {
            replaceFragment(R.id.container_for_fragment, MainFragment.newInstance());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void initializeInjector() {
        this.coreComponent = DaggerCoreComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
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

    @Override
    public void showStatisticsScreen() {
        startActivity(ScoreTableActivity.getCallingIntent(getApplicationContext()));
    }
}
