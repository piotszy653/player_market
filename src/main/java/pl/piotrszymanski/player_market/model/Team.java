package pl.piotrszymanski.player_market.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Team extends AuditableBaseEntity {

    @NonNull
    @NotBlank
    @Column(nullable = false)
    private String name;

    @NonNull
    @NotNull
    @OneToOne(optional = false, orphanRemoval = true, cascade = CascadeType.ALL)
    private TeamConfig config;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Player> players = new LinkedList<>();

    @JsonIgnore
    public double getTeamCommissionPercentage(){
        return config.getTeamCommissionPercentage();
    }

    @JsonIgnore
    public String getCurrencyCode(){
        return config.getCurrencyCode();
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }
}
