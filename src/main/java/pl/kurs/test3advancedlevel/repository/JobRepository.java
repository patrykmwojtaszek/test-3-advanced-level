package pl.kurs.test3advancedlevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.JobStatus;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {

    Optional<Job> findJobByUuid(String uuid);
    Optional<Job> findJobByLetter(Character letter);
}
