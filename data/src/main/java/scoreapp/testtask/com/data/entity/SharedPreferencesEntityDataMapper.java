package scoreapp.testtask.com.data.entity;

import java.util.List;

import scoreapp.testtask.com.domain.model.OppositeTeam;
import scoreapp.testtask.com.domain.model.Team;

public class SharedPreferencesEntityDataMapper {

    public List<Team> transform(SharedPreferencesEntity sharedPreferencesEntity) {
        return sharedPreferencesEntity.getListTeam();
    }

    public SharedPreferencesEntity transformForUpdate(Team team, List<Team> listTeam) {
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

    public boolean isUniqueTeam(Team team, List<Team> listTeam) {
        boolean isUniqueTeam = true;

        for(int i=0; i < listTeam.size(); i++) {
            if(listTeam.get(i).getTeamName().equals(team.getTeamName())) {
                isUniqueTeam = false;
            }
        }

        return isUniqueTeam;
    }

    private boolean isUniqueTeam(OppositeTeam team, List<OppositeTeam> listTeam) {
        boolean isUniqueTeam = true;

        for(int i=0; i < listTeam.size(); i++) {
            if(listTeam.get(i).getTeamName().equals(team.getTeamName())) {
                isUniqueTeam = false;
            }
        }

        return isUniqueTeam;
    }

    private int getOppositeTeamId(OppositeTeam team, List<OppositeTeam> listTeam) {
        int teamId = -1;

        for(int i=0; i < listTeam.size(); i++) {
            if(listTeam.get(i).getTeamName().equals(team.getTeamName())) {
                teamId = i;
            }
        }

        return teamId;
    }

    private int getTeamId(Team team, List<Team> listTeam) {
        int teamId = -1;

        for(int i=0; i < listTeam.size(); i++) {
            if(listTeam.get(i).getTeamName().equals(team.getTeamName())) {
                teamId = i;
            }
        }

        return teamId;
    }
}