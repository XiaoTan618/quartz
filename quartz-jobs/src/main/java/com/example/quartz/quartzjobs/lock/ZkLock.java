package com.example.quartz.quartzjobs.lock;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

/**
 * @author Tan
 */
public class ZkLock {

    private InterProcessMutex lock ;

    private Long timeout;

    public ZkLock(InterProcessMutex lock, Long timeout) {
        this.lock = lock;
        this.timeout = timeout;
    }

    public boolean tryLock() {
        try {
            return lock.acquire(timeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void release() {
        try {
            lock.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
