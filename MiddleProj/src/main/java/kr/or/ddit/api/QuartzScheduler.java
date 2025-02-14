package kr.or.ddit.api;


import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzScheduler {

    public static void main(String[] args) {
        try {
            JobDetail job = JobBuilder.newJob(ApiExplorerJob.class)
                    .withIdentity("apiExplorerJob", "apiGroup")
                    .build();

            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 9 * * ?");

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("apiExplorerTrigger", "apiGroup")
                    .startNow()
                    .withSchedule(cronScheduleBuilder)
                    .build();

            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);

            // Thread.sleep(60000); // 60초 후 종료
            // scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
