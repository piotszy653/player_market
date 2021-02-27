package pl.piotrszymanski.player_market.error.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import pl.piotrszymanski.player_market.error.ExceptionCode;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ValidationErrorDesc extends ErrorDesc {
    private final List<ObjectErrorDesc> objectErrors;
    private final List<FieldErrorDesc> fieldErrors;

    public ValidationErrorDesc(List<ObjectErrorDesc> objectErrors, List<FieldErrorDesc> fieldErrors) {
        super(ExceptionCode.VALIDATION_ERROR);
        this.objectErrors = objectErrors;
        this.fieldErrors = fieldErrors;
    }
}
