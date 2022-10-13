package pl.kurs.test3advancedlevel.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.JobStatus;

import static org.junit.Assert.*;

public class JobExecutorServiceTest {

    @Mock
    private IJobService jobService;
    @Mock
    private IJobCreatorService jobCreatorService;
    @Mock
    private JobRunner jobRunner;
    @Mock
    private JobStatusChangerService jobStatusChangerService;
    private JobExecutorService jobExecutorService;
    private Character letter;
    private int quantity;
    private int delay;

    public JobExecutorServiceTest() {
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.jobExecutorService = new JobExecutorService(this.jobService, this.jobCreatorService, this.jobRunner, this.jobStatusChangerService);
        this.letter = 'X';
        this.quantity = 3;
        this.delay = 100;
    }

    @Test
    public void shouldReturnJobForCreateJob() throws InterruptedException {
//        Character letter = this.letter;
//        int quantity = this.quantity;
//        int delay = this.delay;
//
//        Job jobResult = this.jobExecutorService.createAndStartJob(letter, quantity, delay);
//        Job jobTest = new Job(letter, quantity, delay);
//        jobTest.setJobStatus(JobStatus.NOT_STARTED);
////        jobTest.setUuid(jobResult.getUuid());
//
//        Assert.assertEquals(jobResult, jobTest);
    }
}