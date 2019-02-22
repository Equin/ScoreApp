package scoreapp.testtask.com.presentation.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;
import scoreapp.testtask.com.presentation.AndroidApplication;
import scoreapp.testtask.com.presentation.internal.di.components.ApplicationComponent;
import scoreapp.testtask.com.presentation.internal.di.modules.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    private enum FragmentAction {
        ADD,
        REPLACE
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }  else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getApplicationComponent().inject(this);
        getAndroidApplication().setRunningActivity(this);
    }

    @Override
    public void onStart(){
        super.onStart();

        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (this.equals(getAndroidApplication().getRunningActivity())) {
            getAndroidApplication().setRunningActivity(null);
        }
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        setFragment(containerViewId, fragment, FragmentAction.ADD);
    }

    protected void replaceFragment(int containerViewId, Fragment fragment) {
        setFragment(containerViewId, fragment, FragmentAction.REPLACE);
    }

    protected ApplicationComponent getApplicationComponent() {
        return getAndroidApplication().getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    private void setFragment(
            final int containerViewId,
            final Fragment fragment,
            final FragmentAction fragmentAction) {
        new Handler().post(() -> setFragmentAux(containerViewId, fragment, fragmentAction));
    }

    private void setFragmentAux(int containerViewId, Fragment fragment, FragmentAction fragmentAction) {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (fragmentAction) {
            case REPLACE:
                fragmentTransaction.replace(containerViewId, fragment);
                break;
            case ADD:
                fragmentTransaction.replace(containerViewId, fragment);
                fragmentTransaction.addToBackStack(null);
                break;
        }

        fragmentTransaction.commitAllowingStateLoss();
    }

    private AndroidApplication getAndroidApplication() {
        return ((AndroidApplication) getApplication());
    }
}