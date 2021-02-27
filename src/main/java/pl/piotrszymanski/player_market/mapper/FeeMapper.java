package pl.piotrszymanski.player_market.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.piotrszymanski.player_market.dto.PlayerFeesDto;
import pl.piotrszymanski.player_market.model.Player;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FeeMapper {
    @Value("${transfer-fee-constant}")
    private int transferFeeConstant;

    public Set<PlayerFeesDto> toFeesDtos(Set<Player> players){
        return players.stream()
                .map(this::toFeesDto)
                .collect(Collectors.toSet());
    }

    private PlayerFeesDto toFeesDto(Player player) {
        double transferFee = getTransferFee(player);
        double contractFee = getContractFee(player, transferFee);

        return new PlayerFeesDto(player, transferFee, contractFee);
    }

    private double getContractFee(Player player, double transferFee) {
        return transferFee * (player.getTeamCommissionPercentage()/100 + 1);
    }

    private double getTransferFee(Player player) {
        return (double)player.getMothsOfExperience() * transferFeeConstant / player.getAge();
    }
}
