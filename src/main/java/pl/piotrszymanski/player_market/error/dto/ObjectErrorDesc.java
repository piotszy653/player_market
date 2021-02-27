package pl.piotrszymanski.player_market.error.dto;

import lombok.Getter;
import org.springframework.validation.ObjectError;

@Getter
public class ObjectErrorDesc {
    private final String name;
    private final String error;

    public ObjectErrorDesc(ObjectError objectError){
        this.name = objectError.getObjectName();
        this.error = objectError.getDefaultMessage();
    }
}
