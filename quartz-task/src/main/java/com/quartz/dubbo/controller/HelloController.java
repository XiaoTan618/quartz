package com.quartz.dubbo.controller;

import com.quartz.dubbo.service.DubboReferenceFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quartz.job.demo.model.SchedulerTaskDTO;

/**
 * @author Tan
 */
@RestController
@RequestMapping("/v")
public class HelloController {

    @Autowired
    DubboReferenceFactory dubboReferenceFactory;

    @GetMapping("/hello")
    public String hello(){
        SchedulerTaskDTO schedulerTaskDTO = new SchedulerTaskDTO();
        schedulerTaskDTO.setCornExpression("0/6 * * * * ?");
        schedulerTaskDTO.setTriggerName("test");
        schedulerTaskDTO.setTriggerGroup("printHelloEvery5s");
        schedulerTaskDTO.setTaskId("myTask");
        dubboReferenceFactory.addTask(schedulerTaskDTO);
        return "success";
    }
}
