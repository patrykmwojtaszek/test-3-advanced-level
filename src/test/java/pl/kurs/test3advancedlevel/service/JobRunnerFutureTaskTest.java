package pl.kurs.test3advancedlevel.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kurs.test3advancedlevel.Test3AdvancedLevelApplication;
import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.repository.JobRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes= Test3AdvancedLevelApplication.class)
public class JobRunnerFutureTaskTest {

    @Mock
    private JobRunner jobRunner;
    @Mock
    private IJobService jobService;
    @Mock
    private Job job;
    private JobRunnerFutureTask jobRunnerFutureTask;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.jobRunnerFutureTask = new JobRunnerFutureTask(jobRunner, jobService, job);
    }

    @Test
    public void done_shouldDone() {
        //given
        //when
        jobRunnerFutureTask.done();
        //then
        Mockito.verify(jobService, Mockito.times(1)).update(job);
    }


}