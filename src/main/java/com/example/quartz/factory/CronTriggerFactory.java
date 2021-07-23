package com.example.quartz.factory;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.TriggerBuilder;

public class CronTriggerFactory {

    private CronTriggerFactory() {
    }

    /**
     * Build a {@link CronTrigger} from the {@link JobDetail} and cron expression.
     *
     * @param jobDetail      {@link JobDetail}
     * @param cronExpression cron expression
     * @return the build {@link CronTrigger}
     */
    public static CronTrigger createTrigger(JobDetail jobDetail, String cronExpression) {
        //@formatter:off
        return TriggerBuilder.newTrigger()
            .forJob(jobDetail)
            .withIdentity(jobDetail.getJobClass().getName())
            .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
            .build();
        //@formatter:on
    }

}
