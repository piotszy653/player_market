package pl.piotrszymanski.player_market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.piotrszymanski.player_market.dto.PlayerInTeamDto;
import pl.piotrszymanski.player_market.dto.TeamDto;
import pl.piotrszymanski.player_market.model.Player;
import pl.piotrszymanski.player_market.model.Team;
import pl.piotrszymanski.player_market.service.TeamManagementService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("teams")
@Validated
public class TeamController {
    private final TeamManagementService teamManagementService;

    @GetMapping
    public Page<Team> getAll(Pageable pageable) {
        return teamManagementService.getAll(pageable);
    }

    @GetMapping("{id}")
    public Team getById(@PathVariable Long id) {
        return teamManagementService.getById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Team create(@RequestBody @Valid TeamDto dto) {
        return teamManagementService.create(dto);
    }

    @PutMapping("{id}")
    public Team update(@RequestBody @Valid TeamDto dto, @PathVariable Long id) {
        return teamManagementService.update(dto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        teamManagementService.delete(id);
    }

    @GetMapping("{id}/players")
    public Page<Player> getPlayers(@PathVariable Long id, Pageable pageable) {
        return teamManagementService.getPlayers(id, pageable);
    }

    @PatchMapping("add-player")
    public void addPlayer(@RequestBody @Valid PlayerInTeamDto dto) {
        teamManagementService.addPlayer(dto);
    }

    @PatchMapping("remove-player")
    public void removePlayer(@RequestBody @Valid PlayerInTeamDto dto) {
        teamManagementService.removePlayer(dto);
    }
}
