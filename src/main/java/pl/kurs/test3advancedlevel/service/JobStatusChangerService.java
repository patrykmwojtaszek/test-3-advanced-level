package pl.kurs.test3advancedlevel.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.JobStatus;

@Service
@Scope("prototype")
public class JobStatusChangerService implements Runnable{

    private Job job;
    private IJobService jobService;

    public void setJob(Job job) {
        this.job = job;
    }

    public void setJobService(IJobService jobService) {
        this.jobService = jobService;
    }


    @Override
    public void run() {
        job.setJobStatus(JobStatus.RUNNING);
        jobService.update(job);
    }
}
