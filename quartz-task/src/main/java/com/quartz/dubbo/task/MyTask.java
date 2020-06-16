package com.quartz.dubbo.task;

import org.apache.dubbo.config.annotation.Service;
import quartz.job.demo.service.Task;

/**
 * @author Tan
 */
@Service(group = "myTask",registry = "z1")
public class MyTask implements Task {

    @Override
    public boolean run() {
        System.out.println("hello");
        return true;
    }
}
