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
        return sharedPreferences.makeGetObservable().map(sharedPreferencesEntityDataMapper::transform);
    }

    @Override
    public Completable addNewTeam(Team team) {
        SharedPreferencesEntity  sharedPreferencesEntity = this.sharedPreferences.get();
        List<Team> teamList = new ArrayList<>();

        if (sharedPreferencesEntity!=null) {
            teamList = sharedPreferencesEntity.getListTeam();
        }

        if(isUniqueTeam(team, teamList)) {
            teamList.add(team);
            return this.sharedPreferences.makePutObservable(new SharedPreferencesEntity(teamList));
        } else {
            return Completable.error(new DuplicationsOfTeams());
        }
    }

    @Override
    public Completable updateTeam(Team team) {
        List<Team>  teamList = this.sharedPreferences.get().getListTeam();

        return this.sharedPreferences.makePutObservable(updateSharedPreferencesEntity(team, teamList));
    }

    @Override
    public Completable clearSharedPreferences() {
        return this.sharedPreferences.makeClearObservable();
    }

    private SharedPreferencesEntity updateSharedPreferencesEntity(Team team, List<Team> listTeam) {
        int teamIdToAddOppositeTeam = getTeamId(team,listTeam);
        OppositeTeam newOppositeTeam = team.getOppositeTeam(0);

        if (!isUniqueTeam(team, listTeam)) {
            for(int teamId = 0; teamId < listTeam.size(); teamId++){
                List<OppositeTeam> OppositeTeams = listTeam.get(teamId).getOppositeTeamList();

                for(int OppositeTeam = 0; OppositeTeam < OppositeTeams.size(); OppositeTeam++ ) {

                    if(!isUniqueTeam(newOppositeTeam, OppositeTeams) && teamId == teamIdToAddOppositeTeam){
                        OppositeTeams.remove(getOppositeTeamId(newOppositeTeam, OppositeTeams));
                        OppositeTeams.add(newOppositeTeam);
                    } else if(teamId == teamIdToAddOppositeTeam) {
                        listTeam.get(teamIdToAddOppositeTeam).addOppositeTeam(newOppositeTeam);
                        break;
                    }
                }

                if(OppositeTeams.size() == 0 && teamId == teamIdToAddOppositeTeam) {
                    listTeam.get(teamIdToAddOppositeTeam).addOppositeTeam(newOppositeTeam);
                }
            }
        }

        return new SharedPreferencesEntity(listTeam);
    }

    private boolean isUniqueTeam(Team team, List<Team> listTeam) {
        boolean isUniqueTeam = true;

        for(int i=0; i<listTeam.size(); i++) {
            if(listTeam.get(i).getTeamName().equals(team.getTeamName())) {
                isUniqueTeam = false;
            }
        }

        return isUniqueTeam;
    }

    private boolean isUniqueTeam(OppositeTeam team, List<OppositeTeam> listTeam) {
        boolean isUniqueTeam = true;

        for(int i=0; i<listTeam.size(); i++) {
            if(listTeam.get(i).getTeamName().equals(team.getTeamName())) {
                isUniqueTeam = false;
            }
        }

        return isUniqueTeam;
    }

    private int getOppositeTeamId(OppositeTeam team, List<OppositeTeam> listTeam) {
        int teamId = -1;

        for(int i=0; i<listTeam.size(); i++) {
            if(listTeam.get(i).getTeamName().equals(team.getTeamName())) {
                teamId = i;
            }
        }

        return teamId;
    }

    private int getTeamId(Team team, List<Team> listTeam) {
        int teamId = -1;

        for(int i=0; i<listTeam.size(); i++) {
            if(listTeam.get(i).getTeamName().equals(team.getTeamName())) {
                teamId = i;
            }
        }

        return teamId;
    }
}
