package pl.kurs.test3advancedlevel.validators;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.kurs.test3advancedlevel.model.Letter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
