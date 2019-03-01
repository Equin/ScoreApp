package scoreapp.testtask.com.data.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import scoreapp.testtask.com.data.entity.SharedPreferencesEntity;
import scoreapp.testtask.com.data.entity.SharedPreferencesEntityDataMapper;
import scoreapp.testtask.com.data.exception.DuplicationsOfTeams;
import scoreapp.testtask.com.data.shared_preferences.RxSharedPreferences;
import scoreapp.testtask.com.data.shared_preferences.factory.RxSharedPreferencesFactory;
import scoreapp.testtask.com.domain.model.OppositeTeam;
import scoreapp.testtask.com.domain.model.Team;
import scoreapp.testtask.com.domain.repository.TeamRepository;

public class TeamDataRepository implements TeamRepository {

    private final RxSharedPreferences<SharedPreferencesEntity> sharedPreferences;
    private final SharedPreferencesEntityDataMapper sharedPreferencesEntityDataMapper;

    @Inject
    public TeamDataRepository(
            RxSharedPreferencesFactory rxSharedPreferencesFactory) {
        this.sharedPreferences = rxSharedPreferencesFactory.makeCache(SharedPreferencesEntity.class);
        this.sharedPreferencesEntityDataMapper = new SharedPreferencesEntityDataMapper();
    }

    @Override
    public Observable<List<Team>> getTeams() {
        return sharedPreferences.makeGetObservable().map(this.sharedPreferencesEntityDataMapper::transform);
    }

    @Override
    public Completable addNewTeam(Team team) {
        SharedPreferencesEntity  sharedPreferencesEntity = this.sharedPreferences.get();
        List<Team> teamList = new ArrayList<>();

        if (sharedPreferencesEntity != null) {
            teamList = sharedPreferencesEntity.getListTeam();
        }

        if(this.sharedPreferencesEntityDataMapper.isUniqueTeam(team, teamList)) {
            teamList.add(team);
            return this.sharedPreferences.makePutObservable(new SharedPreferencesEntity(teamList));
        } else {
            return Completable.error(new DuplicationsOfTeams());
        }
    }

    @Override
    public Completable updateTeam(Team team) {
        List<Team>  teamList = this.sharedPreferences.get().getListTeam();

        return this.sharedPreferences.makePutObservable(
                this.sharedPreferencesEntityDataMapper.transformForUpdate(team, teamList));
    }

    @Override
    public Completable clearSharedPreferences() {
        return this.sharedPreferences.makeClearObservable();
    }
}
