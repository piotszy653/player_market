package data;

import pl.piotrszymanski.player_market.dto.PlayerFeesDto;
import pl.piotrszymanski.player_market.model.Player;

import java.util.Set;
import java.util.stream.Collectors;

public class Fees {

    public static final int transferFeeConstant = 200_000;


    public static Set<PlayerFeesDto> playerFeesDtos(Set<Player> players){
        return players.stream()
                .map(player -> new PlayerFeesDto(player, transferFee(player), contractFee(player)))
                .collect(Collectors.toSet());
    }


    private static double transferFee(Player player){
        return (double)player.getMothsOfExperience() * transferFeeConstant / player.getAge();
    }

    private static double contractFee(Player player){
        return transferFee(player) * (player.getTeamCommissionPercentage()/100 + 1);
    }

}
