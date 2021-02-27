package pl.piotrszymanski.player_market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.piotrszymanski.player_market.dto.TeamDto;
import pl.piotrszymanski.player_market.error.CustomException;
import pl.piotrszymanski.player_market.error.ExceptionCode;
import pl.piotrszymanski.player_market.mapper.TeamMapper;
import pl.piotrszymanski.player_market.model.Player;
import pl.piotrszymanski.player_market.model.Team;
import pl.piotrszymanski.player_market.repository.TeamRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository repository;
    private final TeamMapper mapper;

    public Page<Team> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Team getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException(ExceptionCode.TEAM_NOT_FOUND_IN_DB, Long.toString(id)));
    }

    public Team save(Team team) {
        return repository.save(team);
    }

    public Team create(TeamDto dto) {
        return save(mapper.createEntity(dto));
    }

    public Team update(TeamDto dto, Long id) {
        Team team = getById(id);
        return save(mapper.updateEntity(dto, team));
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }

    public void addPlayer(Team team, Player player) {
        team.addPlayer(player);
        player.addTeam(team);
        save(team);
    }

    public void removePlayer(Team team, Player player) {
        team.removePlayer(player);
        player.removeTeam();
        save(team);
    }

    public Set<Team> getPlayersTeams(Set<Long> playersIds) {
        return repository.findAllByPlayers_IdIn(playersIds);
    }
}
