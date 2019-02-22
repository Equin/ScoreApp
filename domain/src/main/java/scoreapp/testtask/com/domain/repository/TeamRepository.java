package scoreapp.testtask.com.domain.repository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import scoreapp.testtask.com.domain.model.Team;

public interface TeamRepository {
    Observable<List<Team>> getTeams();
    Completable addNewTeam(Team team);
    Completable updateTeam(Team team);
    Completable clearSharedPreferences();
}
