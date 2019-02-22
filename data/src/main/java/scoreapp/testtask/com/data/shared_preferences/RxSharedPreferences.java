package scoreapp.testtask.com.data.shared_preferences;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface RxSharedPreferences<T> extends SharedPreferences<T> {
    Observable<T> makeGetObservable();
    Completable makePutObservable(T entity);
    Completable makeClearObservable();
}
