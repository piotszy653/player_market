package pl.piotrszymanski.player_market.dto;

import lombok.Getter;
import pl.piotrszymanski.player_market.validation.ValidCurrencyCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class TeamDto {

    @NotBlank
    private final String name;

    @ValidCurrencyCode
    @NotBlank
    private final String currencyCode;

    @NotNull
    @Min(0)
    @Max(10)
    private final double teamCommissionPercentage;

    public TeamDto(String name, String currencyCode, double teamCommissionPercentage) {
        this.name = name.trim();
        this.currencyCode = currencyCode.toUpperCase();
        this.teamCommissionPercentage = teamCommissionPercentage;
    }
}
