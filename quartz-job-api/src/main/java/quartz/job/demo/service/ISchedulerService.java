package quartz.job.demo.service;


import quartz.job.demo.model.SchedulerTaskDTO;

/**
 * @author Tan
 */
public interface ISchedulerService {

    void add(SchedulerTaskDTO task) ;


    void delTask(SchedulerTaskDTO task);


    void triggerTask(SchedulerTaskDTO task);




}
