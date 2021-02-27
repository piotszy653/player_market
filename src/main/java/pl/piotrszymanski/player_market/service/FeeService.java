package pl.piotrszymanski.player_market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.piotrszymanski.player_market.dto.PlayerFeesDto;
import pl.piotrszymanski.player_market.mapper.FeeMapper;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class FeeService {

    private final PlayerService playerService;
    private final FeeMapper feeMapper;

    public Set<PlayerFeesDto> getPlayersFees(Set<Long> playersIds) {
        return feeMapper.toFeesDtos(playerService.getAllByIds(playersIds));
    }
}
