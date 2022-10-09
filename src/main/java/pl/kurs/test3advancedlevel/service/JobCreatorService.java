package pl.kurs.test3advancedlevel.service;

import com.fasterxml.uuid.NoArgGenerator;
import org.springframework.stereotype.Service;
import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.JobStatus;

@Service
public class JobCreatorService implements IJobCreatorService{

    private NoArgGenerator generator;
    private IJobService jobService;

//    public JobCreatorService(NoArgGenerator generator) {
//        this.generator = generator;
//    }

    public JobCreatorService(NoArgGenerator generator, IJobService jobService) {
        this.generator = generator;
        this.jobService = jobService;
    }

    @Override
    public Job createJob(Character letter, int quantity, int delay) {
        Job job = new Job(letter, quantity, delay);
        job.setUuid(generator.generate().toString());
        job.setJobStatus(JobStatus.NOT_STARTED);
        return jobService.add(job);
    }

}
