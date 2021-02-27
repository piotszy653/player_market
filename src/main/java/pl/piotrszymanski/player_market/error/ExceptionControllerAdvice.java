package pl.piotrszymanski.player_market.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.piotrszymanski.player_market.error.dto.ErrorDesc;
import pl.piotrszymanski.player_market.error.dto.FieldErrorDesc;
import pl.piotrszymanski.player_market.error.dto.ObjectErrorDesc;
import pl.piotrszymanski.player_market.error.dto.ValidationErrorDesc;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorDesc> handleCustomException(CustomException ex) {
        return ResponseEntity
                .status(ex.getExceptionCode().getStatus())
                .body(new ErrorDesc(ex));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDesc> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectErrorDesc> objectErrors = getObjectErrorsDesc(e);
        List<FieldErrorDesc> fieldErrors = getFieldErrorsDesc(e);
        ValidationErrorDesc validationErrorDesc = new ValidationErrorDesc(objectErrors, fieldErrors);

        return ResponseEntity.status(validationErrorDesc.getStatus())
                .body(validationErrorDesc);
    }


    private List<ObjectErrorDesc> getObjectErrorsDesc(MethodArgumentNotValidException e){
        return e.getBindingResult().getGlobalErrors()
                .stream().map(ObjectErrorDesc::new)
                .collect(Collectors.toList());
    }

    private List<FieldErrorDesc> getFieldErrorsDesc(MethodArgumentNotValidException e){
        return e.getBindingResult().getFieldErrors()
                .stream().map(FieldErrorDesc::new)
                .collect(Collectors.toList());
    }

}
