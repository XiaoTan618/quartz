package com.example.quartz.quartzjobs.executor;

import com.example.quartz.quartzjobs.lock.ZkLock;
import com.example.quartz.quartzjobs.lock.ZookeeperClientFactory;
import com.example.quartz.quartzjobs.service.DispatchJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import quartz.job.demo.service.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tan
 */
@Slf4j
@Component
public class DefaultJobExecutor {

    @Autowired
    private ZookeeperClientFactory zookeeperClientFactory;

    @Autowired
    private DispatchJobService dispatchJobService;

    private static ExecutorService executor = Executors.newCachedThreadPool();

    public void run(String taskId ) {
        ZkLock zkLock  =  zookeeperClientFactory.getLock(taskId,2000L);
        try{

            if(!zkLock.tryLock() ){
                log.info("任务执行器未抢到锁");
                return;
            }
            Task task = dispatchJobService.getTask(taskId);
            executor.submit((Runnable) task::run);
            System.out.println("任务执行完成");
        }catch (Exception e) {
            log.error("执行任务异常:",e);
        }finally {
            zkLock.release();
        }

    }
}
