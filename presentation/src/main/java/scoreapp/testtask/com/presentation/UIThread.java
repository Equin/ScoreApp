package scoreapp.testtask.com.presentation;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import scoreapp.testtask.com.domain.executor.PostExecutionThread;

public class UIThread implements PostExecutionThread {

    @Inject
    public UIThread() {
        // intentionally left empty
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
