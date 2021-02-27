package pl.piotrszymanski.player_market.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class PlayerInTeamDto {

    @NotNull
    private final Long teamId;

    @NotNull
    private final Long playerId;

}
