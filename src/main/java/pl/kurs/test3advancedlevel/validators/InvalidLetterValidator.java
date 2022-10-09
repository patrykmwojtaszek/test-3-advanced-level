package pl.kurs.test3advancedlevel.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidLetterValidator implements ConstraintValidator<InvalidLetter, Character> {

    @Override
    public void initialize(InvalidLetter constraintAnnotation) {
    }

    @Override
    public boolean isValid(Character s, ConstraintValidatorContext constraintValidatorContext) {
        return Character.isLetter(s);
    }
}
