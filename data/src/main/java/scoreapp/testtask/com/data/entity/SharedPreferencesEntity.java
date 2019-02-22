package scoreapp.testtask.com.data.entity;

import java.util.List;

import scoreapp.testtask.com.domain.model.Team;

public class SharedPreferencesEntity {

    private List<Team> listTeam;

    public SharedPreferencesEntity(List<Team> listTeam) {
        this.listTeam = listTeam;
    }

    public List<Team> getListTeam() {
        return listTeam;
    }

    public void setListTeam(List<Team> listTeam) {
        this.listTeam = listTeam;
    }
}
