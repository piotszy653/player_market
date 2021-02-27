package pl.piotrszymanski.player_market.error;

import lombok.Getter;

import java.text.MessageFormat;

@Getter
public class CustomException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public CustomException(ExceptionCode exceptionCode, Object... args) {
        super(MessageFormat.format(exceptionCode.getMessageCode(), args));
        this.exceptionCode = exceptionCode;
    }

    public CustomException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessageCode());
        this.exceptionCode = exceptionCode;
    }
}
