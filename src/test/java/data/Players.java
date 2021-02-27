package data;

import pl.piotrszymanski.player_market.dto.PlayerDto;
import pl.piotrszymanski.player_market.model.Player;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class Players {

    private static final String firstName = "Name";
    private static final String updatedFirstName = "Updated Name";
    private static final String lastName = "Last Name";
    private static final String updatedLastName = "Updated Last Name";
    private static final LocalDate birthDate = LocalDate.of(1990, 6, 6);
    private static final LocalDate updatedBirthDate = LocalDate.of(1991, 7, 7);
    private static final LocalDate careerBeginning = LocalDate.of(2015, 6, 30);
    private static final LocalDate updatedCareerBeginning = LocalDate.of(2014, 1, 31);

    public static Player player(){
        Player player = new Player(firstName, lastName, birthDate, careerBeginning, null);
        player.setId(BaseData.defaultId);
        return player;
    }

    public static Player playerWithTeam(){
        Player player = new Player(firstName, lastName, birthDate, careerBeginning, Teams.team());
        player.setId(BaseData.defaultId);
        return player;
    }

    public static Player updatedPlayer(){
        Player player = new Player(updatedFirstName, updatedLastName, updatedBirthDate, updatedCareerBeginning, null);
        player.setId(BaseData.defaultId);
        return player;
    }

    public static Player updatedPlayerWithTeam(){
        Player player = new Player(updatedFirstName, updatedLastName, updatedBirthDate, updatedCareerBeginning, Teams.updatedTeam());
        player.setId(BaseData.defaultId);
        return player;
    }

    public static Player updatedPlayer(UUID uuid){
        Player player = updatedPlayer();
        player.setUuid(uuid);

        return player;
    }

    public static Set<Player> players() {
        return new LinkedHashSet<>(Arrays.asList(playerWithTeam(), updatedPlayerWithTeam()));
    }

    public static PlayerDto createPlayerDto(){
        return new PlayerDto(firstName, lastName, birthDate, careerBeginning);
    }

    public static PlayerDto updatePlayerDto(){
        return new PlayerDto(updatedFirstName, updatedLastName, updatedBirthDate, updatedCareerBeginning);
    }
}
