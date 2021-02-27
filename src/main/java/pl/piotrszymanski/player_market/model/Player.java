package pl.piotrszymanski.player_market.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Player extends AuditableBaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Column(nullable = false)
    private LocalDate birthDate;

    //Field for calculating the months of experience. When expanding the project, I would add a career history for that.
    @NotNull
    @Column(nullable = false)
    private LocalDate careerBeginning;

    @ManyToOne
    @JsonIgnore
    private Team team;


    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public int getMothsOfExperience() {
        return (int) Period.between(careerBeginning, LocalDate.now()).toTotalMonths();
    }

    public Long getTeamId() {
        return Optional.ofNullable(team)
                .map(Team::getId)
                .orElse(null);
    }

    @JsonIgnore
    public double getTeamCommissionPercentage() {
        return team.getTeamCommissionPercentage();
    }

    public void addTeam(Team team) {
        this.team = team;
    }

    public void removeTeam() {
        this.team = null;
    }
}
