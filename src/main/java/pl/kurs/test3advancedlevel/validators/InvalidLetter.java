package pl.kurs.test3advancedlevel.validators;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {InvalidLetterValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
//@ResponseStatus(HttpStatus.BAD_REQUEST)
public @interface InvalidLetter {

    String message() default "{INVALID_LETTER}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
