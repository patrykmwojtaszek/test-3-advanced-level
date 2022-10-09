package pl.kurs.test3advancedlevel.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {InvalidQuantityValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InvalidQuantity {

    String message() default "INVALID_QUANTITY";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
