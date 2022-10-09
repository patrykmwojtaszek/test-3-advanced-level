package pl.kurs.test3advancedlevel.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pl.kurs.test3advancedlevel.commands.CreateJobCommand;
import pl.kurs.test3advancedlevel.dto.JobDto;
import pl.kurs.test3advancedlevel.dto.JobSimpleDto;
import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.JobStatus;
import pl.kurs.test3advancedlevel.repository.JobRepository;
import pl.kurs.test3advancedlevel.service.IJobCreatorService;
import pl.kurs.test3advancedlevel.service.IJobExecutorService;
import pl.kurs.test3advancedlevel.service.IJobService;
import pl.kurs.test3advancedlevel.service.JobService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private IJobExecutorService jobExecutorService;
    private IJobService jobService;
    private ModelMapper mapper;

    public JobController(IJobExecutorService jobExecutorService, IJobService jobService, ModelMapper mapper) {
        this.jobExecutorService = jobExecutorService;
        this.jobService = jobService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<JobSimpleDto> addJob(@Valid @RequestBody CreateJobCommand createJobCommand) throws InterruptedException {
        Job jobForSave = mapper.map(createJobCommand, Job.class);

//        if (jobService.findJobByLetter(jobForSave.getLetter()).getJobStatus() == JobStatus.RUNNING ) throw new RuntimeException("DUPLICATED_LETTER");

        jobForSave = jobExecutorService.createAndStartJob(jobForSave.getLetter(), jobForSave.getQuantity(), jobForSave.getDelay());
        JobSimpleDto jobSimpleDto = mapper.map(jobForSave, JobSimpleDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(jobSimpleDto);
    }

    @PostMapping(value = "/{uuid}/cancel", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<JobStatus> cancelJob(@PathVariable(name = "uuid") String uuid) {
        Job job = mapper.map(jobService.findJobByUuid(uuid), Job.class);

        if(job.getJobStatus() == JobStatus.NOT_STARTED)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JobStatus.NOT_STARTED);

        if(job.getJobStatus() == JobStatus.CANCELLED)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JobStatus.CANCELLED);

        jobService.cancel(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(job.getJobStatus());
    }

    @GetMapping(value = "/{uuid}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<JobStatus> findJobByUuid(@PathVariable(name = "uuid") String uuid) {
        Job job = mapper.map(jobService.findJobByUuid(uuid), Job.class);
        return ResponseEntity.status(HttpStatus.OK).body(job.getJobStatus());
    }

    @GetMapping(value = "/findJobById/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<JobDto> findJobById(@PathVariable(name = "id") long id) {
        JobDto jobDto = mapper.map(jobService.findJobById(id), JobDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobDto);
    }


}
