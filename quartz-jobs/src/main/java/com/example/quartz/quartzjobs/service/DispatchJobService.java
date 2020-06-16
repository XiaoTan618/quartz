package com.example.quartz.quartzjobs.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quartz.job.demo.service.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Tan
 */
@Service
@Slf4j
public class DispatchJobService {

    @Autowired
    private List<RegistryConfig> registryConfigs;


    private Map<String, ReferenceConfig<Task>> map = new HashMap<>();

    public Task getTask(String taskId) {
        try {
            ReferenceConfig<Task> referenceConfig = map.get(taskId);
            if (Objects.nonNull(referenceConfig)) {
                return referenceConfig.get();
            }
            ReferenceConfig<Task> config = new ReferenceConfig<>();
            config.setRegistries(registryConfigs);
            config.setGroup(taskId);
            config.setInterface(Task.class);
            config.setTimeout(3000);
            config.setRetries(0);
            config.setCheck(true);
            map.put(taskId, config);
            return config.get();
        }catch (Exception e) {
            log.error("获取dubbo服务异常:{}",taskId);
            throw e;
        }
    }

}
