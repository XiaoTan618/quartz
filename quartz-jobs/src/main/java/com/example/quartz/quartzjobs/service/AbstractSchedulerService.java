package com.example.quartz.quartzjobs.service;

import lombok.extern.slf4j.Slf4j;
import quartz.job.demo.model.SchedulerTaskDTO;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import quartz.job.demo.service.ISchedulerService;

/**
 * @author Tan
 */
@Slf4j
public abstract class AbstractSchedulerService implements ISchedulerService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public abstract String getJobName();

    public abstract String getJobGroup();



    @Override
    public void add(SchedulerTaskDTO task) {
        Scheduler scheduler = schedulerFactoryBean.getObject();
        JobKey jobKey = new JobKey(getJobName(),getJobGroup());
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCornExpression());
        if(task.isSkipFirstExecute()) {
            scheduleBuilder.withMisfireHandlingInstructionDoNothing();
        }else {
            scheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
        }
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(task.getTriggerName(),task.getTriggerGroup())
                .withSchedule(scheduleBuilder)
                .forJob(jobKey)
                .build();
        cronTrigger.getJobDataMap().put("taskId",task.getTaskId());
        try {
            scheduler.scheduleJob(cronTrigger);
            log.info("Scheduler 信息:{}",scheduler.getMetaData());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delTask(SchedulerTaskDTO task) {

    }

    @Override
    public void triggerTask(SchedulerTaskDTO task) {

    }
}
