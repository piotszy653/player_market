package tests.mapper;

import data.Fees;
import data.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import pl.piotrszymanski.player_market.dto.PlayerFeesDto;
import pl.piotrszymanski.player_market.mapper.FeeMapper;
import pl.piotrszymanski.player_market.model.Player;

import java.util.Set;

import static utils.AssertionUtils.recursiveEquals;

@ExtendWith(MockitoExtension.class)
public class FeeMapperTest {

    @InjectMocks
    FeeMapper feeMapper;

    private Set<Player> players;
    private Set<PlayerFeesDto> playersFeesDtos;


    @BeforeEach
    void init(){
        ReflectionTestUtils.setField(feeMapper, "transferFeeConstant", Fees.transferFeeConstant);

        players = Players.players();
        playersFeesDtos = Fees.playerFeesDtos(players);
    }

   @Test
    void shouldReturnPlayerFeesDtos(){

        Set<PlayerFeesDto> result = feeMapper.toFeesDtos(players);

        recursiveEquals(playersFeesDtos, result);
   }
}
