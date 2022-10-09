package pl.kurs.test3advancedlevel.validators;

import pl.kurs.test3advancedlevel.model.Letter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InvalidQuantityValidator implements ConstraintValidator<InvalidQuantity, Double> {

    @Override
    public void initialize(InvalidQuantity constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double aDouble, ConstraintValidatorContext constraintValidatorContext) {
        return aDouble >= 0;
    }

}
