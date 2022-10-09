package pl.kurs.test3advancedlevel.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.JobStatus;

import java.util.concurrent.Callable;

@Service
@Scope("prototype")
public class JobRunner implements Callable<Job> {

    private Job job;
    private IJobService jobService;

    public void setJob(Job job) {
        this.job = job;
    }

    public void setJobService(IJobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public Job call() {
        return executeJob();
    }

    private Job executeJob() {
        try {
            for (int i = 0; i < job.getQuantity(); i++) {
                System.out.println(job.getLetter());
                Thread.sleep(job.getDelay());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        job.setJobStatus(JobStatus.DONE);
        return jobService.update(job);
    }
}
