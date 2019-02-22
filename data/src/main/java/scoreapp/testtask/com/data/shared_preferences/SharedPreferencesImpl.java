package scoreapp.testtask.com.data.shared_preferences;

import android.content.SharedPreferences;

import javax.inject.Inject;

import scoreapp.testtask.com.data.utility.json_converter.JsonConverter;

public class SharedPreferencesImpl <T> implements scoreapp.testtask.com.data.shared_preferences.SharedPreferences<T> {

    private final Class<T> type;
    private String name;
    private final SharedPreferencesAccessor sharedPreferencesAccessor;
    private final JsonConverter jsonConverter;

    @Inject
    public SharedPreferencesImpl(
            Class<T> type,
            String name,
            SharedPreferencesAccessor sharedPreferencesAccessor,
            JsonConverter jsonConverter) {
        this.type = type;
        this.name = name;
        this.sharedPreferencesAccessor = sharedPreferencesAccessor;
        this.jsonConverter = jsonConverter;
    }

    @Override
    public T get() {
        SharedPreferences sharedPreferences = sharedPreferencesAccessor.getSharedPreferences();
        if (!sharedPreferences.contains(this.name)) {
            return null;
        }

        String jsonObject = sharedPreferences.getString(this.name, "");
        return this.jsonConverter.toObject(jsonObject, this.type);
    }

    @Override
    public void put(T entity) {
        SharedPreferences.Editor editor = sharedPreferencesAccessor.getSharedPreferencesEditor();
        editor.putString(this.name, this.jsonConverter.toJson(entity));
        editor.apply();
    }

    @Override
    public void clear() {
        SharedPreferences.Editor editor = sharedPreferencesAccessor.getSharedPreferencesEditor();
        editor.remove(this.name);
        editor.apply();
    }
}