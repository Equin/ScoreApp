package scoreapp.testtask.com.data.shared_preferences;

public interface SharedPreferences <T> {
    T get();
    void put(T entity);
    void clear();
}
