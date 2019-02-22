package scoreapp.testtask.com.data.shared_preferences;

import io.reactivex.Completable;
import io.reactivex.Observable;
import scoreapp.testtask.com.data.exception.ObjectNotFound;

public class RxSharedPreferencesImpl <T> implements RxSharedPreferences<T> {

    private final SharedPreferences<T> sharedPreferences;

    public RxSharedPreferencesImpl(SharedPreferences<T> sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public T get() {
        return this.sharedPreferences.get();
    }

    @Override
    public void put(T entity) {
        this.sharedPreferences.put(entity);
    }

    @Override
    public void clear() {
        this.sharedPreferences.clear();
    }

    @Override
    public Observable<T> makeGetObservable() {
        return Observable.create(emitter -> {
            T result = this.sharedPreferences.get();

            if (result == null) {
                emitter.onError(new ObjectNotFound());
                return;
            }

            emitter.onNext(result);
            emitter.onComplete();
        });
    }

    @Override
    public Completable makePutObservable(T entity) {
        return Completable.fromAction(() -> {
            this.sharedPreferences.put(entity);
        });
    }

    @Override
    public Completable makeClearObservable() {
        return Completable.fromAction(this.sharedPreferences::clear);
    }
}
