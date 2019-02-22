package scoreapp.testtask.com.data.shared_preferences.factory;

import javax.inject.Inject;

import scoreapp.testtask.com.data.shared_preferences.RxSharedPreferences;
import scoreapp.testtask.com.data.shared_preferences.RxSharedPreferencesImpl;
import scoreapp.testtask.com.data.shared_preferences.SharedPreferencesAccessor;
import scoreapp.testtask.com.data.shared_preferences.SharedPreferencesImpl;
import scoreapp.testtask.com.data.utility.json_converter.JsonConverter;

public class RxSharedPreferencesFactoryImpl implements RxSharedPreferencesFactory {

    private final SharedPreferencesAccessor sharedPreferencesAccessor;
    private final JsonConverter jsonConverter;

    @Inject
    public RxSharedPreferencesFactoryImpl(
            SharedPreferencesAccessor sharedPreferencesAccessor,
            JsonConverter jsonConverter) {
        this.sharedPreferencesAccessor = sharedPreferencesAccessor;
        this.jsonConverter = jsonConverter;
    }

    public <T> RxSharedPreferences<T> makeCache(Class<T> entityClass) {
        SharedPreferencesImpl<T> sharedPreferences = new SharedPreferencesImpl<>(
                entityClass,
                entityClass.getName(),
                this.sharedPreferencesAccessor,
                this.jsonConverter);
        return new RxSharedPreferencesImpl<>(sharedPreferences);
    }

}
