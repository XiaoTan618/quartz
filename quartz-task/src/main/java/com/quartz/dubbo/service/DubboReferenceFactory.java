package com.quartz.dubbo.service;


import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import quartz.job.demo.model.SchedulerTaskDTO;
import quartz.job.demo.service.ISchedulerService;
import quartz.job.demo.service.Task;


/**
 * @author Tan
 */
@Service
public class DubboReferenceFactory {

    @Reference(version = "1.0.0",interfaceClass = ISchedulerService.class,check = false,registry = "z1")
    public ISchedulerService service;


    public void addTask(SchedulerTaskDTO taskDTO) {
        service.add(taskDTO);
    }


}
