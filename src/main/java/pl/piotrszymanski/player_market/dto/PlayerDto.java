package pl.piotrszymanski.player_market.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class PlayerDto {

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String lastName;

    @NotNull
    private final LocalDate birthDate;

    @NotNull
    private final LocalDate careerBeginning;
}
