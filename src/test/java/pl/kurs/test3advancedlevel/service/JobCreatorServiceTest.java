package pl.kurs.test3advancedlevel.service;

import com.fasterxml.uuid.NoArgGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;
import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.JobStatus;

import static org.junit.Assert.*;

public class JobCreatorServiceTest {

    @Mock
    private NoArgGenerator generatorMock;
    @Mock
    private IJobService jobServiceMock;
    private JobCreatorService jobCreatorService;
    private Character letter;
    private int quantity;
    private int delay;

    public JobCreatorServiceTest() {
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.generatorMock = Mockito.mock(NoArgGenerator.class, Mockito.CALLS_REAL_METHODS);
        this.jobCreatorService = new JobCreatorService(this.generatorMock, this.jobServiceMock);
        this.letter = 'X';
        this.quantity = 3;
        this.delay = 100;
    }

    @Test
    public void shouldReturnJobForCreateJob() {
//        Character letter = this.letter;
//        int quantity = this.quantity;
//        int delay = this.delay;
//
//        System.out.println(this.generatorMock.generate().toString());
//        Job jobResult = this.jobCreatorService.createJob(letter, quantity, delay);
//        Job jobTest = new Job(letter, quantity, delay);
//        jobTest.setJobStatus(JobStatus.NOT_STARTED);
//
//        Assert.assertEquals(jobResult, jobTest);
    }


}