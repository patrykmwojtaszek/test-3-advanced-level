package pl.kurs.test3advancedlevel.service;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import pl.kurs.test3advancedlevel.Test3AdvancedLevelApplication;
import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.JobStatus;
import pl.kurs.test3advancedlevel.repository.JobRepository;

@SpringBootTest(classes= Test3AdvancedLevelApplication.class)
public class JobCreatorServiceTest {

    private NoArgGenerator generator;
    @Mock
    private IJobService jobService;
    private IJobCreatorService jobCreatorService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.generator = Generators.timeBasedGenerator();
        this.jobCreatorService = new JobCreatorService(generator, jobService);
    }

    @Test
    public void shouldReturnJobForCreateJob() {
        //given
        Job job = new Job('x', 3, 10);
        job.setUuid(generator.generate().toString());
        job.setJobStatus(JobStatus.NOT_STARTED);
        job.setId(1L);

        //when
        Mockito.when(jobService.add(Mockito.any(Job.class))).thenReturn(job);
        Job jobResult = jobCreatorService.createJob('x', 3, 10);

        //then
        Assertions.assertEquals(jobResult, jobResult);
    }




//        private IJobCreatorService _jobCreatorService;
//        private NoArgGenerator _generator;
//
//
//        private IJobService _jobService;
//
//    @Before
//    public void init() {
//
//
//
//
//
//  }
//
//        @Test
//        public void createJob() {
//
//            _generator = Generators.timeBasedGenerator();
//            _jobService = Mockito.mock(JobService.class);
//            _jobCreatorService = new JobCreatorService(_generator, _jobService);
//
//            Job job = new Job('a',1,1);
//
//            Job expected = new Job('a', 1, 1);
//            Mockito.when(_jobService.add(Mockito.any(Job.class))).thenReturn(expected);
//
//            Job actualResult = _jobCreatorService.createJob('a', 1, 1);
//
//        }
    }
