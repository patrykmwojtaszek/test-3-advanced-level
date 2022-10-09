package pl.kurs.test3advancedlevel.service;

import org.springframework.stereotype.Service;
import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.JobStatus;
import pl.kurs.test3advancedlevel.model.Letter;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

@Service
public class JobExecutorService implements IJobExecutorService {

    private IJobService jobService;
    private IJobCreatorService jobCreatorService;
    private JobRunner jobRunner;
    private JobStatusChangerService jobStatusChangerService;

    public JobExecutorService(IJobService jobService, IJobCreatorService jobCreatorService, JobRunner jobRunner, JobStatusChangerService jobStatusChangerService) {
        this.jobService = jobService;
        this.jobCreatorService = jobCreatorService;
        this.jobRunner = jobRunner;
        this.jobStatusChangerService = jobStatusChangerService;
    }

    @Override
    public Job createAndStartJob(Character letter, int quantity, int delay) throws InterruptedException {
        Job job = jobCreatorService.createJob(letter, quantity, delay);
        jobRunner.setJob(job);
        jobRunner.setJobService(jobService);
        jobStatusChangerService.setJob(job);
        jobStatusChangerService.setJobService(jobService);

//        Thread t1 = new Thread (jobRunner);
//
        FutureTask<Job> jobRunnerFutureTask = new JobRunnerFutureTask(jobRunner, jobService, job);
//        futureTask1.run();
////        t1.start();
//
//        Thread t2 = new Thread(jobStatusChangerService);
//        t2.start();
////        t2.join();

        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(jobRunnerFutureTask);
        pool.submit(jobStatusChangerService);
        pool.shutdown();

        return job;
    }

}
