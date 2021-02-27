package pl.piotrszymanski.player_market.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@Getter
public enum ExceptionCode {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST,"validation_error"),
    ILLEGAL_CURRENCY_CODE(BAD_REQUEST, "currencyCode.not_supported"),
    TEAM_NOT_FOUND_IN_DB(NOT_FOUND, "team.not_found.id:{0}"),
    PLAYER_NOT_FOUND_IN_DB(NOT_FOUND, "player.not_found.id:{0}");

    private final HttpStatus status;
    private final String messageCode;


}
