package pl.kurs.test3advancedlevel.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {DuplicatedLetterValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DuplicatedLetter {

    String message() default "{DUPLICATED_LETTER}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
