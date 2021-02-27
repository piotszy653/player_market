package tests.service;

import data.Players;
import data.Teams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.piotrszymanski.player_market.dto.TeamDto;
import pl.piotrszymanski.player_market.error.CustomException;
import pl.piotrszymanski.player_market.mapper.TeamMapper;
import pl.piotrszymanski.player_market.model.Player;
import pl.piotrszymanski.player_market.model.Team;
import pl.piotrszymanski.player_market.model.TeamConfig;
import pl.piotrszymanski.player_market.repository.TeamRepository;
import pl.piotrszymanski.player_market.service.TeamService;

import java.util.Collections;
import java.util.List;

import static mocks.CrudRepoMocks.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static utils.AssertionUtils.recursiveEqualsIgnoringFields;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @InjectMocks
    TeamService teamService;
    @Mock
    TeamRepository repository;
    @Mock
    TeamMapper teamMapper;

    private Team team;
    private Team teamWithoutId;
    private Team updatedTeam;
    private TeamDto createTeamDto;
    private TeamDto updateTeamDto;

    private Player player;

    @BeforeEach
    void init(){
        TeamConfig teamConfig = Teams.config();
        team = Teams.team(teamConfig);
        teamWithoutId = Teams.teamWithoutId(teamConfig);
        updatedTeam = Teams.updatedTeam();
        createTeamDto = Teams.createTeamDto();
        updateTeamDto = Teams.updateTeamDto();

        player = Players.player();
    }

    @Test
    void shouldReturnTeamById(){
        findByIdMock(repository, team);

        Team result = teamService.getById(team.getId());

        assertEquals(team, result);
        then(repository).shouldHaveNoMoreInteractions();
        then(teamMapper).shouldHaveNoInteractions();
    }

    @Test
    void shouldThrowExceptionWhenGettingNotExistingTeam(){
        notFoundByIdMock(repository);

        assertThrows(CustomException.class, () -> teamService.getById(team.getId()));
        then(repository).shouldHaveNoMoreInteractions();
        then(teamMapper).shouldHaveNoInteractions();
    }

    @Test
    void shouldCreateTeam(){
        saveMock(repository);
        given(teamMapper.createEntity(createTeamDto)).willReturn(teamWithoutId);

        Team result = teamService.create(createTeamDto);

        recursiveEqualsIgnoringFields(team, result, "uuid");
        then(repository).shouldHaveNoMoreInteractions();
        then(teamMapper).should().createEntity(createTeamDto);
        then(teamMapper).shouldHaveNoMoreInteractions();
    }

    @Test
    void shouldUpdateTeam(){
        findByIdMock(repository, team);
        given(teamMapper.updateEntity(updateTeamDto, team)).willReturn(updatedTeam);
        updateMock(repository);

        Team result = teamService.update(updateTeamDto, team.getId());

        assertEquals(updatedTeam, result);
        then(repository).shouldHaveNoMoreInteractions();
        then(teamMapper).should().updateEntity(updateTeamDto, team);
        then(teamMapper).shouldHaveNoMoreInteractions();
    }

    @Test
    void shouldDeleteTeam(){
        findByIdMock(repository, team);

        teamService.delete(team.getId());

        then(repository).should().delete(team);
        then(repository).shouldHaveNoMoreInteractions();
        then(teamMapper).shouldHaveNoInteractions();
    }

    @Test
    void shouldThrowExceptionWhenDeletingNotExistingTeam(){
        notFoundByIdMock(repository);

        assertThrows(CustomException.class, () -> teamService.delete(team.getId()));
        then(repository).shouldHaveNoMoreInteractions();
        then(teamMapper).shouldHaveNoInteractions();
    }

    @Test
    void shouldAddPlayerToTeam(){
        List<Player> players = Collections.singletonList(player);

        teamService.addPlayer(team, player);

        assertEquals(players, team.getPlayers());
        assertEquals(team, player.getTeam());
        then(repository).should().save(team);
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void shouldRemovePlayerFromTeam(){
        team.addPlayer(player);

        teamService.removePlayer(team, player);

        assertTrue(team.getPlayers().isEmpty());
        assertNull(player.getTeam());
        then(repository).should().save(team);
        then(repository).shouldHaveNoMoreInteractions();
    }
}
