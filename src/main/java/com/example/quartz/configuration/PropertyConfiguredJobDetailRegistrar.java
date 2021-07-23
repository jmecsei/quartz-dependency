package com.example.quartz.configuration;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import com.example.quartz.factory.CronTriggerFactory;
import com.example.quartz.factory.JobDetailFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PropertyConfiguredJobDetailRegistrar implements ImportBeanDefinitionRegistrar {

    private final Environment environment;

    @Override
    public void registerBeanDefinitions(final AnnotationMetadata importingClassMetadata, final BeanDefinitionRegistry registry) {
        int i = 0;
        String className = environment.getProperty("jobs-config.jobs[" + i + "].className");
        String cronExpression = environment.getProperty("jobs-config.jobs[" + i + "].cronExpression");
        while (className != null) {
            try {
                // Create JobDetail bean
                JobDetail jobDetail = JobDetailFactory.createJobDetail((Class<? extends Job>) Class.forName(className));
                registry.registerBeanDefinition(
                        className + ".JobDetail", BeanDefinitionBuilder.genericBeanDefinition(JobDetail.class, () -> jobDetail).getBeanDefinition());
                LOGGER.info("JobDetail created for class {}", className);

                // Create CronTrigger bean
                CronTrigger cronTrigger = CronTriggerFactory.createTrigger(jobDetail, cronExpression);
                registry.registerBeanDefinition(
                        className + ".CronTrigger", BeanDefinitionBuilder.genericBeanDefinition(CronTrigger.class, () -> cronTrigger).getBeanDefinition());
                LOGGER.info("CronTrigger created for class {} with expression {}", className, cronExpression);
            } catch (ClassNotFoundException | ClassCastException e) {
                LOGGER.error("Cannot create JobDetail from class {}", className, e);
            }

            i++;
            className = environment.getProperty("jobs-config.jobs[" + i + "].className");
            cronExpression = environment.getProperty("jobs-config.jobs[" + i + "].cronExpression");
        }
    }
}
