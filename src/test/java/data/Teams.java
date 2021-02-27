package data;


import pl.piotrszymanski.player_market.dto.TeamDto;
import pl.piotrszymanski.player_market.model.Team;
import pl.piotrszymanski.player_market.model.TeamConfig;

import java.util.UUID;

import static data.BaseData.withId;

public class Teams {

    private static final String name = "Name";
    private static final String updatedName = "Updated name";
    private static final String currencyCode = "USD";
    private static final String updatedCurrencyCode = "EUR";
    private static final double teamCommissionPercentage = 9.5;
    private static final double updatedTeamCommissionPercentage = 2.5;

    public static Team team(TeamConfig config) {
        return withId(teamWithoutId(config));
    }

    public static Team teamWithoutId(TeamConfig config) {
        return new Team(name, config);
    }

    public static Team team() {
        Team team = teamWithoutId();
        team.setId(BaseData.defaultId);
        team.getConfig().setId(BaseData.defaultId);

        return team;
    }

    public static Team teamWithoutId() {
        return new Team(name, configWithoutId());
    }

    public static Team updatedTeam() {
        Team team = new Team(updatedName, updatedConfig());
        return withId(team);
    }

    public static Team updatedTeam(UUID uuid) {
        Team team = new Team(updatedName, updatedConfig());
        team.setUuid(uuid);
        return withId(team);
    }

    public static TeamConfig config() {
        TeamConfig config = new TeamConfig(currencyCode, teamCommissionPercentage);
        return withId(config);
    }

    public static TeamConfig configWithoutId() {
        return new TeamConfig(currencyCode, teamCommissionPercentage);
    }


    public static TeamConfig updatedConfig() {
        TeamConfig config = new TeamConfig(updatedCurrencyCode, updatedTeamCommissionPercentage);
        return withId(config);
    }

    public static TeamDto createTeamDto() {
        return new TeamDto(name, currencyCode, teamCommissionPercentage);
    }

    public static TeamDto updateTeamDto() {
        return new TeamDto(updatedName, updatedCurrencyCode, updatedTeamCommissionPercentage);
    }
}
