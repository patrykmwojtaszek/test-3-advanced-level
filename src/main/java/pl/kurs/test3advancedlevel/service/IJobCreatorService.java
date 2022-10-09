package pl.kurs.test3advancedlevel.service;

import pl.kurs.test3advancedlevel.model.Job;

public interface IJobCreatorService {

    Job createJob(Character letter, int quantity, int delay);

}
