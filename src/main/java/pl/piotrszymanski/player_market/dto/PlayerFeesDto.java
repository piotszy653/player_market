package pl.piotrszymanski.player_market.dto;

import lombok.Getter;
import pl.piotrszymanski.player_market.model.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class PlayerFeesDto {

    private final long playerId;
    private final BigDecimal transferFee;
    private final BigDecimal contractFee;
    private final String currencyCode;

    public PlayerFeesDto(Player player, double transferFee, double contractFee){
        this.playerId = player.getId();
        this.transferFee = BigDecimal.valueOf(transferFee).setScale(2, RoundingMode.HALF_UP);
        this.contractFee = BigDecimal.valueOf(contractFee).setScale(2, RoundingMode.HALF_UP);
        this.currencyCode = player.getTeam().getCurrencyCode();
    }
}
