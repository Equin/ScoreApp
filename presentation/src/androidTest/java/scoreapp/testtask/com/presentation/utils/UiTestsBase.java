package scoreapp.testtask.com.presentation.utils;

import android.content.Context;
import android.content.SharedPreferences;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.InstrumentationRegistry;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;
import scoreapp.testtask.com.data.shared_preferences.SharedPreferencesAccessor;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UiTestsBase {

    private SharedPreferences sharedPreferences;
    private SharedPreferencesAccessor sharedPreferencesAccessor;

    public UiTestsBase() {
        //intentionally left empty
    }

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();

        this.sharedPreferencesAccessor = new SharedPreferencesAccessor(context);
        this.sharedPreferences = context.getSharedPreferences(
                context.getPackageName(),
                Context.MODE_PRIVATE
        );
    }

    @Test
    public void testStub() {
        Assert.assertTrue(true);
    }

    @After
    public void tearDown() throws Exception {
        this.sharedPreferencesAccessor.getSharedPreferencesEditor().clear().commit();
        this.sharedPreferences.edit().clear().commit();
    }
}
