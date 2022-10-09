package pl.kurs.test3advancedlevel.service;

import org.springframework.stereotype.Component;
import pl.kurs.test3advancedlevel.exceptions.NoEntityException;
import pl.kurs.test3advancedlevel.exceptions.WrongIdException;
import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.JobStatus;
import pl.kurs.test3advancedlevel.repository.JobRepository;

import java.util.List;
import java.util.Objects;

@Component
public class JobService implements IJobService{

    private JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job add(Job entity) {
        if (Objects.isNull(entity)) throw new NoEntityException("No entity to add!");
//        if (Objects.nonNull(entity.getId())) throw new WrongIdException("Id should be null");
        return jobRepository.save(entity);
    }

    @Override
    public void cancel(Job jobForCancel) {
        if (Objects.isNull(jobForCancel)) throw new NoEntityException("No entity to cancel!");
        if (Objects.isNull(jobForCancel.getId())) throw new WrongIdException("Id should be not null");
        jobForCancel.setJobStatus(JobStatus.CANCELLED);
        jobRepository.save(jobForCancel);
    }

    @Override
    public Job findJobByUuid(String uuid) {
        if (Objects.isNull(uuid)) throw new WrongIdException("Uuid should be not null");
        return jobRepository.findJobByUuid(uuid) .orElseThrow(() -> new NoEntityException("Entity with uuid " + uuid + " do not exists"));
    }


    @Override
    public Job findJobById(Long id) {
        if (Objects.isNull(id)) throw new WrongIdException("Id should be not null");
        return jobRepository.findById(id).orElseThrow(() -> new NoEntityException("Entity with id " + id + " do not exists"));
    }

    @Override
    public Job findJobByLetter(Character letter) {
        if (Objects.isNull(letter)) throw new WrongIdException("Letter should be not null");
        return jobRepository.findJobByLetter(letter).orElse(new Job());
//                .orElseThrow(() -> new NoEntityException("Job with letter " + letter + " do not exists"));
    }

    @Override
    public Job update(Job entity) {
        if (Objects.isNull(entity)) throw new NoEntityException("No entity to add!");
        return jobRepository.save(entity);
    }


}
