package scoreapp.testtask.com.data.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPreferencesAccessor {

    private final Context context;

    private SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferencesAccessor(Context context) {
        this.context = context;
    }


    public SharedPreferences getSharedPreferences() {
        if (this.sharedPreferences == null) {
            synchronized (SharedPreferencesAccessor.class) {
                if (this.sharedPreferences == null) {
                    this.sharedPreferences = this.context.getSharedPreferences(this.context.getPackageName(), Context.MODE_PRIVATE);
                }
            }
        }
            return this.sharedPreferences;
    }

    public SharedPreferences.Editor getSharedPreferencesEditor() {
        return getSharedPreferences().edit();
    }
}