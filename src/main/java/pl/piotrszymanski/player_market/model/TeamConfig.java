package pl.piotrszymanski.player_market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TeamConfig extends BaseEntity {

    @NotNull
    @Column(nullable = false)
    private String currencyCode;

    @Column(nullable = false, precision = 5, scale = 2)
    @Min(0)
    @Max(10)
    private double teamCommissionPercentage;
}
