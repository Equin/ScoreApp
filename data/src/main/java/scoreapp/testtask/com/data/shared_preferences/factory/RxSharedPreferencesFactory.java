package scoreapp.testtask.com.data.shared_preferences.factory;

import scoreapp.testtask.com.data.shared_preferences.RxSharedPreferences;

public interface RxSharedPreferencesFactory {
    <T> RxSharedPreferences<T> makeCache(Class<T> entityType);
}