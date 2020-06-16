package com.example.quartz.quartzjobs;

import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class QuartzJobsApplication {



    public static void main(String[] args) throws SchedulerException {

       SpringApplication.run(QuartzJobsApplication.class, args);

    }

}
