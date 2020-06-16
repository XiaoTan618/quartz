package com.example.quartz.quartzjobs.job;


import com.example.quartz.quartzjobs.context.ApplicationContextHolder;
import com.example.quartz.quartzjobs.executor.DefaultJobExecutor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author Tan
 */
public class MainTaskJob extends QuartzJobBean {

    private String taskId;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ApplicationContextHolder.getBean(DefaultJobExecutor.class).run(taskId);
    }


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
