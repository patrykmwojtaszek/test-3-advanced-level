package pl.kurs.test3advancedlevel.service;

import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.JobStatus;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class JobRunnerFutureTask extends FutureTask<Job> {

    private IJobService jobService;
    private Job job;

    public JobRunnerFutureTask(Callable<Job> callable, IJobService jobService, Job job) {
        super(callable);
        this.jobService = jobService;
        this.job = job;
    }


    public void done() {
        job.setJobStatus(JobStatus.DONE);
        jobService.update(job);
    }

}
