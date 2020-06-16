package com.example.quartz.quartzjobs.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryForever;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Tan
 */
@Service
public class ZookeeperClientFactory implements InitializingBean, DisposableBean {

    private CuratorFramework client;

    @Value("${dubbo.registry.address}")
    private String address;

    private final String lock_prefix = "/ZK_LOCK_NODE-";


    public ZkLock getLock(String key,Long timeout) {
        InterProcessMutex lock = new InterProcessMutex(client, lock_prefix + key);
        return new ZkLock(lock,timeout);
    }



    @Override
    public void destroy() throws Exception {
        if(client!=null){
            client.close();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        client = CuratorFrameworkFactory.newClient(address.replace("zookeeper://",""),new RetryForever(2000));
        client.start();
    }
}
