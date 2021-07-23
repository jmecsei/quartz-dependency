package com.example.quartz.properties;

import lombok.Data;

/**
 * Properties that are required to configure a job and its triggers.
 */
@Data
public class JobProperties {
    private String className;
    private String cronExpression;
}
