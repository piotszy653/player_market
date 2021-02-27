package pl.piotrszymanski.player_market.error.dto;

import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
public class FieldErrorDesc {
    private final String name;
    private final Object value;
    private final String error;

    public FieldErrorDesc(FieldError error){
        this.name = error.getField();
        this.value = error.getRejectedValue();
        this.error = error.getCode();
    }
}
