package pl.piotrszymanski.player_market.validation;

import pl.piotrszymanski.player_market.error.CustomException;
import pl.piotrszymanski.player_market.error.ExceptionCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Currency;

public class CurrencyCodeValidator implements ConstraintValidator<ValidCurrencyCode, String> {


    @Override
    public boolean isValid(String currencyCode, ConstraintValidatorContext context) {
        try {
            Currency.getInstance(currencyCode);
            return true;
        }catch (NullPointerException | IllegalArgumentException e){
            throw new CustomException(ExceptionCode.ILLEGAL_CURRENCY_CODE);
        }
    }
}