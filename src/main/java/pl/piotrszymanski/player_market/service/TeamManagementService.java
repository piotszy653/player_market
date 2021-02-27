package pl.piotrszymanski.player_market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.piotrszymanski.player_market.dto.PlayerInTeamDto;
import pl.piotrszymanski.player_market.dto.TeamDto;
import pl.piotrszymanski.player_market.model.Player;
import pl.piotrszymanski.player_market.model.Team;

@Service
@RequiredArgsConstructor
public class TeamManagementService {

    private final TeamService teamService;
    private final PlayerService playerService;

    public Page<Team> getAll(Pageable pageable) {
        return teamService.getAll(pageable);
    }

    public Team getById(Long id) {
        return teamService.getById(id);
    }

    public Team create(TeamDto dto) {
        return teamService.create(dto);
    }

    public Team update(TeamDto dto, Long id) {
        return teamService.update(dto, id);
    }

    public void delete(Long id) {
        teamService.delete(id);
    }

    public void addPlayer(PlayerInTeamDto dto){
        teamService.addPlayer(teamService.getById(dto.getTeamId()), playerService.getById(dto.getPlayerId()));
    }

    public void removePlayer(PlayerInTeamDto dto){
        teamService.removePlayer(teamService.getById(dto.getTeamId()), playerService.getById(dto.getPlayerId()));
    }

    public Page<Player> getPlayers(Long teamId, Pageable pageable) {
        return playerService.getAllByTeam(teamId, pageable);
    }
}
