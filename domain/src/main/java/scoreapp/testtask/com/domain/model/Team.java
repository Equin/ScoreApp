package scoreapp.testtask.com.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team implements Serializable {

    private String teamName;
    private List<OppositeTeam> OppositeTeamList;

    public Team() {
        this.OppositeTeamList = new ArrayList<>();
    }

    public Team(String teamName) {
        this();
        this.teamName = teamName;
    }

    public Team(String teamName, List<OppositeTeam> OppositeTeamList) {
        this.teamName = teamName;
        this.OppositeTeamList = OppositeTeamList;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<OppositeTeam> getOppositeTeamList() {
        return OppositeTeamList;
    }

    public OppositeTeam getOppositeTeam(int id) {
        return OppositeTeamList.get(id);
    }

    public void setOppositeTeamList(List<OppositeTeam> OppositeTeamList) {
        this.OppositeTeamList = OppositeTeamList;
    }

    public void addOppositeTeam(OppositeTeam OppositeTeam) {
        this.OppositeTeamList.add(OppositeTeam);
    }
}
