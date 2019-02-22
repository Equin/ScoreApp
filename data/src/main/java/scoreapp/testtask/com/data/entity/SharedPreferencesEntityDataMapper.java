package scoreapp.testtask.com.data.entity;

import java.util.List;

import scoreapp.testtask.com.domain.model.Team;

public class SharedPreferencesEntityDataMapper {

    public List<Team> transform(SharedPreferencesEntity sharedPreferencesEntity) {
        return sharedPreferencesEntity.getListTeam();
    }
}