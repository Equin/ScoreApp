package scoreapp.testtask.com.data.exception;

public class DuplicationsOfTeams extends Exception {

    @Override
    public String getMessage() {
        return "Team with such name already exists";
    }
}
