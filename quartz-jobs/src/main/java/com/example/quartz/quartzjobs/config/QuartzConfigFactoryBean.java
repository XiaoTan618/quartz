package com.example.quartz.quartzjobs.config;

import com.example.quartz.quartzjobs.constans.Constans;
import com.example.quartz.quartzjobs.job.MainTaskJob;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.naming.factory.TransactionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

import javax.sql.DataSource;

/**
 * @author Tan
 */
@Configuration
public class QuartzConfigFactoryBean {



    @Bean
    @ConditionalOnClass({DataSource.class,DataSourceTransactionManager.class,})
    public SchedulerFactoryBean schedulerFactoryBean(HikariDataSource dataSource, DataSourceTransactionManager tx, JobDetailFactoryBean jobDetailFactoryBean)  {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfigLocation(new ClassPathResource("quartz.properties"));
        bean.setSchedulerName("Task-Scheduler");
        bean.setOverwriteExistingJobs(true);
        bean.setTransactionManager(tx);
        bean.setStartupDelay(5);
        bean.setJobDetails(jobDetailFactoryBean.getObject());
        return bean;
    }


    @Bean
    public JobDetailFactoryBean jobDetail(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setJobClass(MainTaskJob.class);
        jobDetailFactoryBean.setName(Constans.JOB_NAME);
        jobDetailFactoryBean.setGroup(Constans.JOB_GROUP);
        jobDetailFactoryBean.setDescription("quartz-demo");
        return jobDetailFactoryBean;
    }






}
