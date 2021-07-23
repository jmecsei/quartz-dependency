package com.example.quartz.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.quartz.properties.JobsProperties;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Configuration that creates the Quartz job detail and trigger beans.
 */
@Slf4j
@AllArgsConstructor
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(JobsProperties.class)
@AutoConfigureBefore(QuartzAutoConfiguration.class)
@Import(PropertyConfiguredJobDetailRegistrar.class)
public class JobsConfiguration {

}
