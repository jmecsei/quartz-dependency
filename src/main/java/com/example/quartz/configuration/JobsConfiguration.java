package com.example.quartz.configuration;

import com.example.quartz.factory.CronTriggerFactory;
import com.example.quartz.factory.JobDetailFactory;
import com.example.quartz.properties.JobProperties;
import com.example.quartz.properties.JobsProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.context.support.GenericWebApplicationContext;

/**
 * Configuration that creates the Quartz job detail and trigger beans.
 */
@Slf4j
@AllArgsConstructor
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(JobsProperties.class)
public class JobsConfiguration {

    private final JobsProperties jobsProperties;
    private final GenericWebApplicationContext context;

    @Bean(name = "jobs-from-property-file")
    public Object createJobs() {
        for (JobProperties jobProperties : jobsProperties.getJobs()) {
            try {
                // Create JobDetail bean
                JobDetail jobDetail = JobDetailFactory.createJobDetail((Class<? extends Job>) Class.forName(jobProperties.getClassName()));
                context.registerBean(jobProperties.getClassName() + ".JobDetail", JobDetail.class, () -> jobDetail);
                LOGGER.info("JobDetail created for class {}", jobProperties.getClassName());

                // Create CronTrigger bean
                CronTrigger cronTrigger = CronTriggerFactory.createTrigger(jobDetail, jobProperties.getCronExpression());
                context.registerBean(jobProperties.getClassName() + ".CronTrigger", CronTrigger.class, () -> cronTrigger);
                LOGGER.info("CronTrigger created for class {} with expression {}", jobProperties.getClassName(), jobProperties.getCronExpression());

            } catch (ClassNotFoundException | ClassCastException e) {
                LOGGER.error("Cannot create JobDetail from class {}", jobProperties.getClassName(), e);
            }
        }
        return new Object();
    }

    @Bean(name = "quartzDependentBean")
    @DependsOn("jobs-from-property-file")
    public Object quartzDependentBean() {
        return new Object();
    }
}
