package pl.kurs.test3advancedlevel.validators;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JobTooLongValidator implements ConstraintValidator<JobTooLong, Object> {

    private double quantity;
    private double delay;

    @Override
    public void initialize(JobTooLong constraintAnnotation) {
        this.quantity = constraintAnnotation.quantity();
        this.delay = constraintAnnotation.delay();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object quantityValue = new BeanWrapperImpl(o)
                .getPropertyValue(String.valueOf(quantity));
        Object delayValue = new BeanWrapperImpl(o)
                .getPropertyValue(String.valueOf(delay));

        quantity = Double.parseDouble((String) quantityValue);
        delay = Double.parseDouble((String) delayValue);

        return quantity * delay <= 6000;
    }
}
