package com.example.quartz.configuration;

import org.quartz.JobDetail;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.quartz.factory.JobDetailFactory;
import com.example.quartz.job.Job_B;

@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(JobsConfiguration.class)
public class Job_B_AutoConfiguration {

    @Bean("com.example.quartz.job.Job_B.JobDetail")
    @ConditionalOnMissingBean(name = {"com.example.quartz.job.Job_B.JobDetail"})
    public JobDetail registerJobDetail() {
        return JobDetailFactory.createJobDetail(Job_B.class);
    }

/*
    @DependsOn("jobs-from-property-file")
    @Bean(name = "com.example.quartz.job.Job_B.CronTrigger")
    @ConditionalOnMissingBean(name = { "com.example.quartz.job.Job_B.CronTrigger" })
    public CronTrigger registerJobTrigger(@Qualifier("com.example.quartz.job.Job_B.JobDetail") JobDetail jobDetail) {
        return CronTriggerFactory.createTrigger(jobDetail, "30 * * * * ?");
    }
*/
}
