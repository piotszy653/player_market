package tests.service;

import data.Fees;
import data.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.piotrszymanski.player_market.dto.PlayerFeesDto;
import pl.piotrszymanski.player_market.mapper.FeeMapper;
import pl.piotrszymanski.player_market.model.Player;
import pl.piotrszymanski.player_market.service.FeeService;
import pl.piotrszymanski.player_market.service.PlayerService;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class FeeServiceTest {

    @InjectMocks
    FeeService feeService;
    @Mock
    PlayerService playerService;
    @Mock
    FeeMapper feeMapper;

    private Set<Player> players;
    private Set<Long> playersIds;
    private Set<PlayerFeesDto> playersFeesDtos;

    @BeforeEach
    void init(){
        players = Players.players();
        playersFeesDtos = Fees.playerFeesDtos(players);
        playersIds = players.stream().map(Player::getId).collect(Collectors.toSet());
    }

   @Test
    void shouldReturnPlayersFeesByIds(){
        given(playerService.getAllByIds(playersIds)).willReturn(players);
        given(feeMapper.toFeesDtos(players)).willReturn(playersFeesDtos);

        Set<PlayerFeesDto> result = feeService.getPlayersFees(playersIds);

        assertEquals(playersFeesDtos, result);
        then(playerService).shouldHaveNoMoreInteractions();
        then(feeMapper).shouldHaveNoMoreInteractions();
   }
}
