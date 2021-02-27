package tests.mapper;

import data.Teams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import pl.piotrszymanski.player_market.dto.TeamDto;
import pl.piotrszymanski.player_market.mapper.TeamMapper;
import pl.piotrszymanski.player_market.model.Team;
import pl.piotrszymanski.player_market.model.TeamConfig;

import static org.mockito.BDDMockito.then;
import static utils.AssertionUtils.recursiveEqualsIgnoringFields;

@ExtendWith(MockitoExtension.class)
public class TeamMapperTest {

    @InjectMocks
    TeamMapper teamMapper;
    @Spy
    ModelMapper modelMapper;

    private Team team;
    private Team teamWithoutId;
    private Team updatedTeam;
    private TeamDto createTeamDto;
    private TeamDto updatedTeamDto;

    @BeforeEach
    void init(){
        team = Teams.team();
        teamWithoutId = Teams.teamWithoutId();
        updatedTeam = Teams.updatedTeam(team.getUuid());
        createTeamDto = Teams.createTeamDto();
        updatedTeamDto = Teams.updateTeamDto();
    }

    @Test
    void shouldCreateEntityFromDto(){

        Team result = teamMapper.createEntity(createTeamDto);

        recursiveEqualsIgnoringFields(teamWithoutId, result, "uuid", "config");
        recursiveEqualsIgnoringFields(teamWithoutId.getConfig(), result.getConfig(), "uuid");
        then(modelMapper).should().map(createTeamDto, Team.class);
        then(modelMapper).should().map(createTeamDto, TeamConfig.class);
        then(modelMapper).shouldHaveNoMoreInteractions();
    }

    @Test
    void shouldUpdateEntity(){

        Team result = teamMapper.updateEntity(updatedTeamDto, team);

        recursiveEqualsIgnoringFields(updatedTeam, result, "uuid", "config");
        recursiveEqualsIgnoringFields(updatedTeam.getConfig(), result.getConfig(), "uuid");
        then(modelMapper).should().map(updatedTeamDto, team);
        then(modelMapper).should().map(updatedTeamDto, team.getConfig());
        then(modelMapper).shouldHaveNoMoreInteractions();
    }
}
