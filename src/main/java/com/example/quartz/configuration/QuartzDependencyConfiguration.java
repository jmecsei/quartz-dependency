package com.example.quartz.configuration;

import org.springframework.boot.autoconfigure.AbstractDependsOnBeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(SchedulerFactoryBean.class)
public class QuartzDependencyConfiguration extends AbstractDependsOnBeanFactoryPostProcessor {
    public QuartzDependencyConfiguration() {
        super(SchedulerFactoryBean.class, "quartzDependentBean");
    }
}
