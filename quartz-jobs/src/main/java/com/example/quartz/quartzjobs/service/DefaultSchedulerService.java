package com.example.quartz.quartzjobs.service;

import com.example.quartz.quartzjobs.constans.Constans;
import org.springframework.stereotype.Service;

/**
 * @author Tan
 */
@Service
@org.apache.dubbo.config.annotation.Service(version = "1.0.0",interfaceName ="quartz.job.demo.service.ISchedulerService" )
public class DefaultSchedulerService extends AbstractSchedulerService {


    @Override
    public String getJobName() {
        return Constans.JOB_NAME;
    }

    @Override
    public String getJobGroup() {
        return Constans.JOB_GROUP;
    }
}
