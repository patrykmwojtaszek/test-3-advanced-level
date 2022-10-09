package pl.kurs.test3advancedlevel.validators;

import pl.kurs.test3advancedlevel.model.JobStatus;
import pl.kurs.test3advancedlevel.service.IJobService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DuplicatedLetterValidator implements ConstraintValidator<DuplicatedLetter, Character> {

    private IJobService jobService;

    public DuplicatedLetterValidator(IJobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public void initialize(DuplicatedLetter constraintAnnotation) {
    }

    @Override
    public boolean isValid(Character character, ConstraintValidatorContext constraintValidatorContext) {
        return jobService.findJobByLetter(character).getJobStatus() == JobStatus.RUNNING;
    }
}
