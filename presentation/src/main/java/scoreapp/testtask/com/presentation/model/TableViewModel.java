package scoreapp.testtask.com.presentation.model;

import scoreapp.testtask.com.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import scoreapp.testtask.com.domain.model.OppositeTeam;
import scoreapp.testtask.com.domain.model.Team;
import scoreapp.testtask.com.presentation.constant.HeaderTitle;

public class TableViewModel {

    private static final String WINNER_TEAM_HM_KEY = "W";
    private static final String GAMES_PLAYED_HM_KEY = "M";
    private static final String SCORED_GOALS_HM_KEY = "SG";
    private static final String CONCEDED_GOALS_HM_KEY = "CG";

    private List<Team> tableData;
    private List<StatisticItemModel> winnerTeams;
    private List<StatisticItemModel> gamesPlayed;
    private List<StatisticItemModel> scoredGoals;
    private List<StatisticItemModel> concededGoals;
    private int rowSize;

    public TableViewModel() {
        this.winnerTeams = new ArrayList<>();
        this.gamesPlayed = new ArrayList<>();
        this.scoredGoals = new ArrayList<>();
        this.concededGoals = new ArrayList<>();
    }

    private HashMap<String,List<StatisticItemModel>> getStatisticsModel() {

        HashMap<String,List<StatisticItemModel>> cellsHashMapList = new HashMap<>();

        createStatisticItemModels();

        for(int upperTeam = 0; upperTeam < this.tableData.size(); upperTeam++) {
            String upperTeamName =  this.tableData.get(upperTeam).getTeamName();

            for (int teams = 0; teams <  this.tableData.size(); teams++) {
                List<OppositeTeam> OppositeTeams =  this.tableData.get(teams).getOppositeTeamList();

                for (int opTeams = 0; opTeams < OppositeTeams.size(); opTeams++) {
                    OppositeTeam OppositeTeam = OppositeTeams.get(opTeams);

                    calculateWinsCount(OppositeTeam, upperTeamName, upperTeam, teams);
                    calculatePlayedGames(OppositeTeam, upperTeamName, upperTeam, teams);
                    calculateGoals(OppositeTeam, upperTeamName, upperTeam, teams);
                }
            }
        }

        cellsHashMapList.put(WINNER_TEAM_HM_KEY, this.winnerTeams);
        cellsHashMapList.put(GAMES_PLAYED_HM_KEY, this.gamesPlayed);
        cellsHashMapList.put(SCORED_GOALS_HM_KEY, this.scoredGoals);
        cellsHashMapList.put(CONCEDED_GOALS_HM_KEY, this.concededGoals);

        return cellsHashMapList;
    }

    private void createStatisticItemModels() {
        for(int i = 0; i < this.tableData.size(); i++) {
            this.winnerTeams.add(new StatisticItemModelImpl(this.tableData.get(i).getTeamName()));
            this.gamesPlayed.add(new StatisticItemModelImpl(this.tableData.get(i).getTeamName()));
            this.scoredGoals.add(new StatisticItemModelImpl(this.tableData.get(i).getTeamName()));
            this.concededGoals.add(new StatisticItemModelImpl(this.tableData.get(i).getTeamName()));
        }
    }

    /**
     * getting info about won games from opposite team if name match upperTeam.
     * upperTeam -> team -> oppositeTeam
     */
    private void calculateWinsCount(OppositeTeam OppositeTeam, String upperTeamName, int upperTeam, int team) {
        if (upperTeamName.equals(OppositeTeam.getTeamName())) {
            if (OppositeTeam.isWinner()) {
                this.winnerTeams.get(upperTeam).incrementValue();
            } else if (!OppositeTeam.isDraw()) {
                this.winnerTeams.get(team).incrementValue();
            }
        }
    }

    /**
     * getting info about played games counting amount of same upperTeam
     *  and opposite team + amount of opposite teams in each team
     */
    private void calculatePlayedGames(OppositeTeam OppositeTeam, String upperTeamName, int upperTeam, int team) {
        if (upperTeamName.equals(OppositeTeam.getTeamName())) {
            this.gamesPlayed.get(upperTeam).incrementValue();
        } else if(this.tableData.get(team).getTeamName().equals(upperTeamName)){
            this.gamesPlayed.get(team).incrementValue();
        }
    }

    /**
     * getting info about scored and conceded goals from opposite team if it match upperTeam,
     * and if upperTeam match innerTeam getting revers version scoredGoals = concededGoals
     */
    private void calculateGoals(OppositeTeam OppositeTeam, String upperTeamName, int upperTeam, int teamIndex) {
        if (upperTeamName.equals(OppositeTeam.getTeamName())) {
            this.scoredGoals.get(upperTeam).addToValue(OppositeTeam.getScoredGoals());
            this.concededGoals.get(upperTeam).addToValue(OppositeTeam.getConcededGoals());
        } else if (tableData.get(teamIndex).getTeamName().equals(upperTeamName)) {
            this.scoredGoals.get(teamIndex).addToValue(OppositeTeam.getConcededGoals());
            this.concededGoals.get(teamIndex).addToValue(OppositeTeam.getScoredGoals());
        }
    }

    private List<RowHeader> getSimpleRowHeaderList() {
        List<RowHeader> list = new ArrayList<>();
        for (int i = 0; i < this.tableData.size(); i++) {
            RowHeader header = new RowHeader(String.valueOf(i), this.tableData.get(i).getTeamName());
            list.add(header);
        }

        return list;
    }

    private List<ColumnHeader> getSimpleColumnHeaderList() {
        List<ColumnHeader> list = new ArrayList<>();

        for (int i = 0; i < HeaderTitle.headerNames.length; i++) {
            ColumnHeader header = new ColumnHeader(String.valueOf(i), HeaderTitle.headerNames[i]);
            list.add(header);
        }

        return list;
    }

    private List<List<Cell>> getSimpleCellList() {
        HashMap<String,List<StatisticItemModel>> cellsHashMapList = getStatisticsModel();
        List<List<Cell>> list = new ArrayList<>();

        for (int i = 0; i < this.rowSize; i++) {
            List<Cell> cellList = new ArrayList<>();

            for (int j = 0; j < HeaderTitle.headerNames.length; j++) {
                String id = j + "-" + i;
                Cell cell = new Cell(id,"");
                List<StatisticItemModel> statisticItemModel = cellsHashMapList.get(HeaderTitle.headerNames[j]);

                if(statisticItemModel != null) {
                     cell = new Cell(id, statisticItemModel.get(i).getValue());
                }

                cellList.add(cell);
            }

            list.add(cellList);
        }

        return list;
    }

    public List<List<Cell>> getCellList() {
        return getSimpleCellList();
    }

    public List<RowHeader> getRowHeaderList() {
        return getSimpleRowHeaderList();
    }

    public List<ColumnHeader> getColumnHeaderList() {
        return getSimpleColumnHeaderList();
    }

    public void setTableData(List<Team> tableData) {
        this.tableData = tableData;
        this.rowSize = tableData.size();
    }
}
