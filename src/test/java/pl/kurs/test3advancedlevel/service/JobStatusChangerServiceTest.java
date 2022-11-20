package pl.kurs.test3advancedlevel.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kurs.test3advancedlevel.Test3AdvancedLevelApplication;
import pl.kurs.test3advancedlevel.model.Job;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes= Test3AdvancedLevelApplication.class)
public class JobStatusChangerServiceTest {

    @Mock
    private Job job;
    @Mock
    private IJobService jobService;
    private JobStatusChangerService jobStatusChangerService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.jobStatusChangerService = new JobStatusChangerService();
        jobStatusChangerService.setJob(job);
        jobStatusChangerService.setJobService(jobService);
    }

    @Test
    public void run_shouldRun() {
        //given
        //when
        jobStatusChangerService.run();
        //then
        Mockito.verify(jobService, Mockito.times(1)).update(job);
    }
}