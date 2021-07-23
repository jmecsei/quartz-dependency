package com.example.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class Job_B implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("{} is running ...", getClass().getName());
    }
}
