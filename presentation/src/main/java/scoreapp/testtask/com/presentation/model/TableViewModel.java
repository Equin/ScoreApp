package scoreapp.testtask.com.presentation.model;

import scoreapp.testtask.com.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import scoreapp.testtask.com.domain.model.OppositeTeam;
import scoreapp.testtask.com.domain.model.Team;
import scoreapp.testtask.com.presentation.constant.HeaderTitle;

public class TableViewModel {

    private List<Team> tableData;
    private List<StatisticItemModel> winnerTeams;
    private List<StatisticItemModel> gamesPlayed;
    private List<StatisticItemModel> scoredGoals;
    private List<StatisticItemModel> concededGoals;
    private int rowSize;

    public TableViewModel() {
        winnerTeams = new ArrayList<>();
        gamesPlayed = new ArrayList<>();
        scoredGoals = new ArrayList<>();
        concededGoals = new ArrayList<>();
    }

    private HashMap<String,List<StatisticItemModel>> getStatisticsModel() {

        HashMap<String,List<StatisticItemModel>> cellsHashMapList = new HashMap<>();

        createStatisticItemModels();

        for(int upperTeam = 0; upperTeam < tableData.size(); upperTeam++) {
            String upperTeamName = tableData.get(upperTeam).getTeamName();

            for (int teams = 0; teams < tableData.size(); teams++) {
                List<OppositeTeam> OppositeTeams = tableData.get(teams).getOppositeTeamList();

                for (int opTeams = 0; opTeams < OppositeTeams.size(); opTeams++) {
                    OppositeTeam OppositeTeam = OppositeTeams.get(opTeams);

                    calculateWinsCount(OppositeTeam, upperTeamName, upperTeam, teams);
                    calculatePlayedGames(OppositeTeam, upperTeamName, upperTeam, teams);
                    calculateGoals(OppositeTeam, upperTeamName, upperTeam, teams);
                }
            }
        }

        cellsHashMapList.put("W", winnerTeams);
        cellsHashMapList.put("M", gamesPlayed);
        cellsHashMapList.put("SG", scoredGoals);
        cellsHashMapList.put("CG", concededGoals);

        return cellsHashMapList;
    }

    private void createStatisticItemModels() {
        for(int i = 0; i < tableData.size(); i++) {
            winnerTeams.add(new StatisticItemModelImpl(tableData.get(i).getTeamName()));
            gamesPlayed.add(new StatisticItemModelImpl(tableData.get(i).getTeamName()));
            scoredGoals.add(new StatisticItemModelImpl(tableData.get(i).getTeamName()));
            concededGoals.add(new StatisticItemModelImpl(tableData.get(i).getTeamName()));
        }
    }

    /**
     * getting info about won games from opposite team if name match upperTeam.
     * upperTeam -> team -> oppositeTeam
     */
    private void calculateWinsCount(OppositeTeam OppositeTeam, String upperTeamName, int upperTeam, int team) {
        if (upperTeamName.equals(OppositeTeam.getTeamName())) {
            if (OppositeTeam.isWinner()) {
                winnerTeams.get(upperTeam).incrementValue();
            } else if (!OppositeTeam.isDraw()) {
                winnerTeams.get(team).incrementValue();
            }
        }
    }

    /**
     * getting info about played games counting amount of same upperTeam
     *  and opposite team + amount of opposite teams in each team
     */
    private void calculatePlayedGames(OppositeTeam OppositeTeam, String upperTeamName, int upperTeam, int team) {
        if (upperTeamName.equals(OppositeTeam.getTeamName())) {
            gamesPlayed.get(upperTeam).incrementValue();
        } else if(tableData.get(team).getTeamName().equals(upperTeamName)){
            gamesPlayed.get(team).incrementValue();
        }
    }

    /**
     * getting info about scored and conceded goals from opposite team if it match upperTeam,
     * and if upperTeam match innerTeam getting revers version scoredGoals = concededGoals
     */
    private void calculateGoals(OppositeTeam OppositeTeam, String upperTeamName, int upperTeam, int teamIndex) {
        if (upperTeamName.equals(OppositeTeam.getTeamName())) {
            scoredGoals.get(upperTeam).addToValue(OppositeTeam.getScoredGoals());
            concededGoals.get(upperTeam).addToValue(OppositeTeam.getConcededGoals());
        } else if (tableData.get(teamIndex).getTeamName().equals(upperTeamName)) {
            scoredGoals.get(teamIndex).addToValue(OppositeTeam.getConcededGoals());
            concededGoals.get(teamIndex).addToValue(OppositeTeam.getScoredGoals());
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
