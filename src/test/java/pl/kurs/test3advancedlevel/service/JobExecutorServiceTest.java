package pl.kurs.test3advancedlevel.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kurs.test3advancedlevel.Test3AdvancedLevelApplication;
import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.JobStatus;


@SpringBootTest(classes= Test3AdvancedLevelApplication.class)
public class JobExecutorServiceTest {

    @Mock
    private IJobService jobService;
    @Mock
    private IJobCreatorService jobCreatorService;
    @Mock
    private JobRunner jobRunner;
    @Mock
    private JobStatusChangerService jobStatusChangerService;
    private IJobExecutorService jobExecutorService;

    public JobExecutorServiceTest() {
    }

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.jobExecutorService = new JobExecutorService(this.jobService, this.jobCreatorService, this.jobRunner, this.jobStatusChangerService);
    }

    @Test
    public void createAndStartJob_shouldReturnJobForCreateAndStartJobJob() throws InterruptedException {
        //given
        Job job = new Job('x', 3, 10);
        job.setUuid("xxx");
        job.setJobStatus(JobStatus.NOT_STARTED);
        job.setId(1L);

        //when
        Mockito.when(jobCreatorService.createJob(Mockito.any(Character.class), Mockito.any(Integer.class), Mockito.any(Integer.class))).thenReturn(job);
        Job jobResult = jobExecutorService.createAndStartJob('x', 3, 10);

        //then
        Assertions.assertEquals(jobResult, jobResult);
    }
}