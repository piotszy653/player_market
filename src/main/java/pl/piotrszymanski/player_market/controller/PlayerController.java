package pl.piotrszymanski.player_market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.piotrszymanski.player_market.dto.PlayerFeesDto;
import pl.piotrszymanski.player_market.dto.PlayerDto;
import pl.piotrszymanski.player_market.model.Player;
import pl.piotrszymanski.player_market.model.Team;
import pl.piotrszymanski.player_market.service.FeeService;
import pl.piotrszymanski.player_market.service.PlayerService;
import pl.piotrszymanski.player_market.service.TeamService;

import javax.validation.Valid;
import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("players")
@Validated
public class PlayerController {
    private final PlayerService playerService;
    private final TeamService teamService;
    private final FeeService feeService;

    @GetMapping
    public Page<Player> getAll(Pageable pageable) {
        return playerService.getAll(pageable);
    }

    @GetMapping("{id}")
    public Player getById(@PathVariable Long id) {
        return playerService.getById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Player create(@RequestBody @Valid PlayerDto dto){
        return playerService.create(dto);
    }

    @PutMapping("{id}")
    public Player update(@RequestBody @Valid PlayerDto dto, @PathVariable Long id){
        return playerService.update(dto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id){
        playerService.delete(id);
    }

    @GetMapping("teams")
    public Set<Team> getPlayersTeams(@RequestParam Set<Long> playersIds){
        return teamService.getPlayersTeams(playersIds);
    }

    @GetMapping("transfer-fees")
    public Set<PlayerFeesDto> getPlayersFees(@RequestParam Set<Long> playersIds){
        return feeService.getPlayersFees(playersIds);
    }
}
