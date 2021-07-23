package com.example.quartz.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Configuration properties that contains a list of the jobs.
 */
@Data
@ConfigurationProperties(prefix = "jobs-config")
public class JobsProperties {
    private List<JobProperties> jobs;
}
