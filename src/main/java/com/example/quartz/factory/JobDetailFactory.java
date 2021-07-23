package com.example.quartz.factory;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;

public class JobDetailFactory {

    private JobDetailFactory() {
    }

    /**
     * Build a {@link JobDetail} for a {@link Job} class. The identifier of the job will be the class name.
     *
     * @param jobClass Job class
     * @return the build {@link JobDetail}
     */
    public static JobDetail createJobDetail(Class<? extends Job> jobClass) {
        //@formatter:off
        return JobBuilder.newJob(jobClass)
                         .withIdentity(jobClass.getName())
                         .storeDurably()
                         .build();
        //@formatter:on
    }

}
