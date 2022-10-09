package pl.kurs.test3advancedlevel.service;

import pl.kurs.test3advancedlevel.model.Job;
import pl.kurs.test3advancedlevel.model.Letter;

public interface IJobExecutorService {

    Job createAndStartJob(Character letter, int quantity, int delay) throws InterruptedException;

}
