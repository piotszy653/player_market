package pl.piotrszymanski.player_market.error.dto;

import lombok.Getter;
import pl.piotrszymanski.player_market.error.CustomException;
import pl.piotrszymanski.player_market.error.ExceptionCode;

import java.util.Date;
import java.util.TimeZone;

@Getter
public class ErrorDesc {
    private final Integer status;
    private final String messageCode;
    private final Long timestamp = new Date().getTime();
    private final TimeZone timezone = TimeZone.getDefault();

    public ErrorDesc(CustomException ex) {
        this.status = ex.getExceptionCode().getStatus().value();
        this.messageCode = ex.getMessage();
    }

    public ErrorDesc(ExceptionCode code){
        this.status = code.getStatus().value();
        this.messageCode = code.getMessageCode();
    }
}
