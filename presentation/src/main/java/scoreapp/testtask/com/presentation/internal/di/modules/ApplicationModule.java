package scoreapp.testtask.com.presentation.internal.di.modules;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import scoreapp.testtask.com.data.executor.JobExecutor;
import scoreapp.testtask.com.data.repository.TeamDataRepository;
import scoreapp.testtask.com.data.shared_preferences.SharedPreferencesAccessor;
import scoreapp.testtask.com.data.shared_preferences.factory.RxSharedPreferencesFactory;
import scoreapp.testtask.com.data.shared_preferences.factory.RxSharedPreferencesFactoryImpl;
import scoreapp.testtask.com.data.utility.json_converter.JsonConverter;
import scoreapp.testtask.com.data.utility.json_converter.JsonConverterImpl;
import scoreapp.testtask.com.domain.executor.PostExecutionThread;
import scoreapp.testtask.com.domain.executor.ThreadExecutor;
import scoreapp.testtask.com.domain.repository.TeamRepository;
import scoreapp.testtask.com.presentation.AndroidApplication;
import scoreapp.testtask.com.presentation.UIThread;

@Module
public class ApplicationModule {

    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    SharedPreferencesAccessor provideSharedPreferencesAccessor() {
        return new SharedPreferencesAccessor(this.application);
    }

    @Provides
    @Singleton
    JsonConverter provideJsonConverter() {
        return new JsonConverterImpl(new Gson());
    }

    @Provides
    @Singleton
    RxSharedPreferencesFactory provideRxSharedPreferencesFactory(
            SharedPreferencesAccessor sharedPreferencesAccessor,
            JsonConverter jsonConverter) {
        return new RxSharedPreferencesFactoryImpl(sharedPreferencesAccessor, jsonConverter);
    }

    @Provides
    @Singleton
    TeamRepository provideTeamRepository(RxSharedPreferencesFactory rxSharedPreferencesFactory) {
        return new TeamDataRepository(rxSharedPreferencesFactory);
    }
}