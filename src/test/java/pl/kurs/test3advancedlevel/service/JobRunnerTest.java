package pl.kurs.test3advancedlevel.service;


import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kurs.test3advancedlevel.Test3AdvancedLevelApplication;
import pl.kurs.test3advancedlevel.model.Job;


@SpringBootTest(classes= Test3AdvancedLevelApplication.class)
public class JobRunnerTest {

    @Mock
    private Job job;
    @Mock
    private IJobService jobService;
    private JobRunner jobRunner;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.jobRunner = new JobRunner();
        jobRunner.setJob(job);
        jobRunner.setJobService(jobService);
    }

    @Test
    public void shouldReturnJobForExecuteJob() {
        Job job = new Job('X', 5, 10);

        Mockito.when(jobService.update(Mockito.any(Job.class))).thenReturn(job);
        Job jobResult = jobRunner.call();

        Assertions.assertEquals(jobResult, job);
    }
}