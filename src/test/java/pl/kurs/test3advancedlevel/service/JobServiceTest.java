package pl.kurs.test3advancedlevel.service;

import com.fasterxml.uuid.Generators;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kurs.test3advancedlevel.Test3AdvancedLevelApplication;
import pl.kurs.test3advancedlevel.commands.CreateJobCommand;
import pl.kurs.test3advancedlevel.exceptions.NoEntityException;
import pl.kurs.test3advancedlevel.exceptions.WrongIdException;
import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.JobStatus;
import pl.kurs.test3advancedlevel.repository.JobRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes= Test3AdvancedLevelApplication.class)
class JobServiceTest {

    @Mock
    private JobRepository jobRepository;
    private IJobService jobService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.jobService = new JobService(jobRepository);
    }

    @Test
    public void add_shouldAdd() {
        //given
        Job job = new Job('x', 3, 10);
        //when
//        Mockito.when(jobService.add(Mockito.any(Job.class))).thenReturn(job);
        Mockito.when(jobRepository.save(Mockito.any(Job.class))).thenReturn(job);
        Job jobResult = jobService.add(job);
        //then
        Assertions.assertEquals(job, jobResult);
    }

    @Test
    public void add_shouldThrowNoEntityExceptionWhenEntityIsNull() {
//        Job job = new Job('x', 3, 100);
//        job.setJobStatus(JobStatus.RUNNING);
//
//        Mockito.when(mapper.map(Mockito.any(CreateJobCommand.class), Mockito.eq(Job.class))).thenReturn(job);

        Exception e = Assertions.assertThrows(NoEntityException.class, () -> jobService.add(null));
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(NoEntityException.class);
    }

    @Test
    public void cancel_shouldCancel() {
        //given
        Job job = new Job('x', 3, 10);
        job.setId(1L);
        //when
        jobService.cancel(job);
        //then
        Mockito.verify(jobRepository, Mockito.times(1)).save(job);
    }

    @Test
    public void cancel_shouldThrowNoEntityExceptionWhenEntityIsNull() {
        Exception e = Assertions.assertThrows(NoEntityException.class, () -> jobService.cancel(null));
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(NoEntityException.class);
    }

    @Test
    public void cancel_shouldThrowWrongIdExceptionWhenIdIsNull() {
        //given
        Job job = new Job('x', 3, 10);
        //when
        Exception e = Assertions.assertThrows(WrongIdException.class, () -> jobService.cancel(job));
        SoftAssertions sa = new SoftAssertions();
        //then
        sa.assertThat(e).isExactlyInstanceOf(WrongIdException.class);
    }

    @Test
    public void findJobByUuid_shouldFindJob() {
        //given
        Job job = new Job('x', 3, 10);
        job.setUuid("xxx");
        //when
        Mockito.when(jobRepository.findJobByUuid(Mockito.any(String.class))).thenReturn(Optional.of(job));
        Job jobResult = jobService.findJobByUuid("xxx");
        //then
        Assertions.assertEquals(job, jobResult);
    }

    @Test
    public void findJobByUuid_shouldThrowWrongIdExceptionWhenUuidIsNull() {
        //given
        //when
        Exception e = Assertions.assertThrows(WrongIdException.class, () -> jobService.findJobByUuid(null));
        SoftAssertions sa = new SoftAssertions();
        //then
        sa.assertThat(e).isExactlyInstanceOf(WrongIdException.class);
    }

    @Test
    public void findJobByUuid_shouldThrowNoEntityExceptionWhenEntityDoesNotExist() {
        //given
        //when
        Exception e = Assertions.assertThrows(NoEntityException.class, () -> jobService.findJobByUuid("xxx"));
        SoftAssertions sa = new SoftAssertions();
        //then
        sa.assertThat(e).isExactlyInstanceOf(NoEntityException.class);
    }

    @Test
    public void findJobById_shouldFindJob() {
        //given
        Job job = new Job('x', 3, 10);
        job.setId(1L);
        //when
        Mockito.when(jobRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(job));
        Job jobResult = jobService.findJobById(1L);
        //then
        Assertions.assertEquals(job, jobResult);
    }

    @Test
    public void findJobById_shouldThrowWrongIdExceptionWhenIdIsNull() {
        //given
        //when
        Exception e = Assertions.assertThrows(WrongIdException.class, () -> jobService.findJobById(null));
        SoftAssertions sa = new SoftAssertions();
        //then
        sa.assertThat(e).isExactlyInstanceOf(WrongIdException.class);
    }

    @Test
    public void findJobById_shouldThrowNoEntityExceptionWhenEntityDoesNotExist() {
        //given
        //when
        Exception e = Assertions.assertThrows(NoEntityException.class, () -> jobService.findJobById(1L));
        SoftAssertions sa = new SoftAssertions();
        //then
        sa.assertThat(e).isExactlyInstanceOf(NoEntityException.class);
    }

    @Test
    public void findJobByLetter_shouldFindJob() {
        //given
        Job job = new Job('x', 3, 10);
        //when
        Mockito.when(jobRepository.findJobByLetter(Mockito.any(Character.class))).thenReturn(Optional.of(job));
        Job jobResult = jobService.findJobByLetter('x');
        //then
        Assertions.assertEquals(job, jobResult);
    }

    @Test
    public void findJobByLetter_shouldThrowWrongIdExceptionWhenLetterIsNull() {
        //given
        //when
        Exception e = Assertions.assertThrows(WrongIdException.class, () -> jobService.findJobByLetter(null));
        SoftAssertions sa = new SoftAssertions();
        //then
        sa.assertThat(e).isExactlyInstanceOf(WrongIdException.class);
    }

    @Test
    public void update_shouldUpdate() {
        //given
        Job job = new Job('x', 3, 10);
        //when
        Mockito.when(jobRepository.save(Mockito.any(Job.class))).thenReturn(job);
        Job jobResult = jobService.update(job);
        //then
        Assertions.assertEquals(job, jobResult);
    }

    @Test
    public void update_shouldThrowNoEntityExceptionWhenEntityIsNull() {
        //given
        //when
        Exception e = Assertions.assertThrows(NoEntityException.class, () -> jobService.update(null));
        SoftAssertions sa = new SoftAssertions();
        //then
        sa.assertThat(e).isExactlyInstanceOf(NoEntityException.class);
    }

}