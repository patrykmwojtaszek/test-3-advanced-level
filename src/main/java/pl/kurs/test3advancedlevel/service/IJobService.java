package pl.kurs.test3advancedlevel.service;

import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.JobStatus;

import java.util.List;
import java.util.Optional;

public interface IJobService {

    Job add(Job entity);
    void cancel(Job job);
    Job findJobByUuid(String uuid);
    Job findJobById(Long id);
    Job findJobByLetter(Character letter);
    List<Job> getAllJobs();
    List<Job> getAllActiveJobs();
    Job update(Job entity);

}
