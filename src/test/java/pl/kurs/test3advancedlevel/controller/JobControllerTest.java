package pl.kurs.test3advancedlevel.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.Generators;
import lombok.Synchronized;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assert;
import org.assertj.core.api.SoftAssertions;

import org.hibernate.annotations.Synchronize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.kurs.test3advancedlevel.Test3AdvancedLevelApplication;
import pl.kurs.test3advancedlevel.commands.CreateJobCommand;
import pl.kurs.test3advancedlevel.dto.JobDto;
import pl.kurs.test3advancedlevel.dto.JobSimpleDto;
import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.JobStatus;
import pl.kurs.test3advancedlevel.service.IJobExecutorService;
import pl.kurs.test3advancedlevel.service.IJobService;
import pl.kurs.test3advancedlevel.service.JobCreatorService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes= Test3AdvancedLevelApplication.class)
@AutoConfigureMockMvc
public class JobControllerTest {

    @Autowired
    private MockMvc postman;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private CreateJobCommand createJobCommand;
    @Mock
    private IJobExecutorService jobExecutorService;
    @Mock
    private IJobService jobService;
    @Mock
    private ModelMapper mapper;
    private JobController jobController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.jobController = new JobController(jobExecutorService, jobService, mapper);
    }

    @Test
    public void addJob_shouldAddJob() throws Exception {
        //given
        Job job = new Job('x', 3, 100);
        job.setJobStatus(JobStatus.NOT_STARTED);
//        job.setUuid("xxx");
        job.setId(1L);

        String json = objectMapper.writeValueAsString(job);
        //when
        postman.perform(post("/api/jobs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
        //then
        String response = postman.perform(MockMvcRequestBuilders.get("/api/jobs/findJobById/" + job.getId()))
                .andReturn()
                .getResponse()
                .getContentAsString();
        Job responseObj = objectMapper.readValue(response, Job.class);
        job.setUuid(responseObj.getUuid());
        job.setJobStatus(responseObj.getJobStatus());

        Assertions.assertEquals(job, responseObj);
    }

    @Test
    public void addJob_shouldReturnResponseEntityForAddJob() throws InterruptedException {
        Job job = new Job('x', 3, 100);
        job.setJobStatus(JobStatus.NOT_STARTED);
        job.setUuid("xxx");
        JobSimpleDto jobSimpleDto = new JobSimpleDto("xxx");

//        Mockito.when(mapper.map(Mockito.any(CreateJobCommand.class), Job.class)).thenReturn(job);
        Mockito.when(mapper.map(Mockito.any(CreateJobCommand.class), Mockito.eq(Job.class))).thenReturn(job);

//        Mockito.when(jobService.findJobByLetter(Mockito.any(Character.class)).getJobStatus() == JobStatus.RUNNING).thenReturn(false);
//        Mockito.when(jobService.findJobByLetter(Mockito.any(Character.class)).getJobStatus()).thenReturn(JobStatus.NOT_STARTED);
        Mockito.when(jobService.findJobByLetter(Mockito.any(Character.class))).thenReturn(job);

        Mockito.when(jobExecutorService.createAndStartJob(Mockito.any(Character.class), Mockito.any(int.class), Mockito.any(int.class))).thenReturn(job);
        Mockito.when(mapper.map(Mockito.any(Job.class), Mockito.eq(JobSimpleDto.class))).thenReturn(jobSimpleDto);
        ResponseEntity<JobSimpleDto> jobSimpleDtoResponseEntityResult = jobController.addJob(createJobCommand);

        ResponseEntity<JobSimpleDto> jobSimpleDtoResponseEntityTest = ResponseEntity.status(HttpStatus.CREATED).body(jobSimpleDto);

        Assertions.assertTrue(jobSimpleDtoResponseEntityResult.equals(jobSimpleDtoResponseEntityTest));
    }

    @Test
    public void addJob_shouldThrowRuntimeExceptionWhenLetterIsDuplicated() {
        Job job = new Job('x', 3, 100);
        job.setJobStatus(JobStatus.RUNNING);

        Mockito.when(mapper.map(Mockito.any(CreateJobCommand.class), Mockito.eq(Job.class))).thenReturn(job);

        Exception e = Assertions.assertThrows(RuntimeException.class, () -> jobController.addJob(createJobCommand));
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    @Synchronized
    public void cancelJob_shouldCancelJob() throws Exception {
        //given
        Job job = new Job('x', 3, 10);
        job.setJobStatus(JobStatus.RUNNING);

        String json = objectMapper.writeValueAsString(job);
        //when
        String response = postman.perform(post("/api/jobs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JobSimpleDto responseObj = objectMapper.readValue(response, JobSimpleDto.class);

        //then
        postman.perform(post("/api/jobs/" + responseObj.getUuid() + "/cancel"))
                .andExpect(status().isCreated());

//        // USUN TO
//        postman.perform(get("/api/jobs/" + responseObj.getUuid()))
//                .andExpect(status().isOk());

        postman.perform(post("/api/jobs/" + responseObj.getUuid() + "/cancel"))
                .andExpect(status().isBadRequest());
    }

//    @Test
//    @Synchronized
//    public void findJobByUuid_shouldFindJobByUuid() throws Exception {
//        //given
//        Job job = new Job('y', 2, 20);
//
//        String json = objectMapper.writeValueAsString(job);
//        //when
//        String response = postman.perform(post("/api/jobs")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        JobSimpleDto responseObj = objectMapper.readValue(response, JobSimpleDto.class);
//
//        //then
//        postman.perform(get("/api/jobs/" + responseObj.getUuid()))
//                .andExpect(status().isOk());
//    }

}