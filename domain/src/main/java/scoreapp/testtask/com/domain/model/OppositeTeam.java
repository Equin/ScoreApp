package scoreapp.testtask.com.domain.model;

import java.io.Serializable;

public class OppositeTeam implements Serializable {

    private String teamName;
    private int scoredGoals;
    private int concededGoals;
    private boolean isWinner;
    private boolean isDraw;

    public OppositeTeam(String teamName, int scoredGoals, int concededGoals, boolean isWinner, boolean isDraw) {
        this.teamName = teamName;
        this.scoredGoals = scoredGoals;
        this.concededGoals = concededGoals;
        this.isWinner = isWinner;
        this.isDraw = isDraw;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getConcededGoals() {
        return concededGoals;
    }

    public void setConcededGoals(int concededGoals) {
        this.concededGoals = concededGoals;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void isWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void isDraw(boolean isDraw) {
        this.isDraw = isDraw;
    }
}