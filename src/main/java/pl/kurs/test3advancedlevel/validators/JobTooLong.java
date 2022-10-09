package pl.kurs.test3advancedlevel.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//@Documented
@Constraint(validatedBy = {JobTooLongValidator.class})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JobTooLong {

    String message() default "JOB_TOO_LONG";
    double quantity();
    double delay();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        JobTooLong[] value();
    }

}
