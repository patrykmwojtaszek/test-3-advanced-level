package pl.kurs.test3advancedlevel.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.JobStatus;
import pl.kurs.test3advancedlevel.model.Letter;
import pl.kurs.test3advancedlevel.service.JobCreatorService;
import pl.kurs.test3advancedlevel.service.JobService;

@Aspect
@Component
//@Service
public class JobExecutorServiceAspect {

    private JobService jobService;
    private JobCreatorService jobCreatorService;

    @Before(value = "execution(* pl.kurs.test3advancedlevel.service.JobExecutorService.*(..)) && args(letter,quantity,delay)")
    public void beforeAdvice(JoinPoint joinPoint, Character letter, int quantity, int delay) {

        System.out.println("Before method:" + joinPoint.getSignature());
        System.out.println("JOB IS RUNNING");
    }

    @After(value = "execution(* pl.kurs.test3advancedlevel.service.JobExecutorService.createAndStartJob(..)) && args(letter,quantity,delay)")
    public void afterAdvice(JoinPoint joinPoint, Character letter, int quantity, int delay) {
        System.out.println("After method:" + joinPoint.getSignature());

        System.out.println("JOB IS DONE");
    }

}
