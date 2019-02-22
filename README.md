# ScoreApp

It is a an application which generates the score table and statistics for tournaments.

## Given tasks

###Landing screen 
 
The 1st Screen contains 4 buttons: ADD TEAM, ADD SCORE, SHOW STATISTICS, RESET STATISTICS. When the user presses the ADD TEAM button, the Add Team Dialog opens. Here the user can add a new team to the application. The added team needs to be saved in shared preferences. When the user presses the ADD SCORE button, the Add Team Dialog appears. Here the user should be able to select two team from a dropdown list, introduce the score into the text fields and save the score. The score needs to be saved in shared preferences. The SHOW STATISTICS button opens the second screen. The RESET STATISTICS button clears the shared preferences. 
 
###Statistics screen 
 
The 2nd Screen contains a standard score table, which is generated dynamically and a Statistics table. The statistics table contains: played matches number, wins, scored goals and conceded goals. With the sort button the user can order the teams based on: played matches, wins, scored goals or conceded goals.

## Given tasks

![Main screen](/img/main.jpg?raw=true "Main screen")
![Add team dialog](/img/add_team.jpg?raw=true "Add team dialog")
![Add score dialog](/img/add_score.jpg?raw=true "Add score dialog")
![Statistics screen](/img/statistics.jpg?raw=true "Statistics screen")

## Tech/framework used

- [RxJava 2](https://github.com/ReactiveX/RxJava)
- [Dagger 2](https://github.com/google/dagger)
- [ButterKnife](https://github.com/JakeWharton/butterknife)
- [TableView](https://github.com/evrencoskun/TableView)
- [GSON](https://github.com/google/gson)
- [Espresso](https://developer.android.com/training/testing/espresso/)
