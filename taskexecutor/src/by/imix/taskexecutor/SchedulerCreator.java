package by.imix.taskexecutor;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.DirectSchedulerFactory;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.simpl.RAMJobStore;
import org.quartz.simpl.SimpleThreadPool;
import org.quartz.spi.JobStore;
import org.quartz.spi.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Scheduler creator
 */
public class SchedulerCreator {

    private static final Logger _log= LoggerFactory.getLogger(SchedulerCreator.class);

    public static void createNoticeAction(String name, BrowserWorker browserWorker, String nameReminder, int hour) {
        Scheduler sched = getScheduler(name);
        JobDetail job = getJob(browserWorker, name, nameReminder);
        Trigger trigger = getTrigger(nameReminder+"_"+name,hour);
        createNoticeAction(sched, job, trigger);
    }
    /**
     * StartMorningTask
     * @param sched
     * @param job
     * @param trigger
     */
    private static void createNoticeAction(Scheduler sched, JobDetail job, Trigger trigger) {
        _log.debug("startDaiylyTask");

        try {
            sched.scheduleJob(job,trigger);
            sched.start();

        } catch (SchedulerException e) {
            _log.debug("Can not start Scheduler", e);
        }

    }

    private static JobDetail getJob(BrowserWorker browserWorker, String name, String nameReminder) {
        JobDetailImpl job = new JobDetailImpl();
        job.setName("job_"+name+"_"+ nameReminder);
        job.setGroup("group_"+name+"_"+ nameReminder);
        job.setJobClass(NoticeJob.class);

        job.getJobDataMap().put(NoticeJob.NOTICE_ELEMENT, browserWorker);
        job.getJobDataMap().put(NoticeJob.NAME, nameReminder);
        return job;
    }

    private static Trigger getTrigger(String name, Integer hour) {
        CronTriggerImpl trigger = new CronTriggerImpl();
        trigger.setName("trigger_" + name);
        trigger.setGroup("Group_" + name);
        trigger.setTimeZone(TimeZone.getTimeZone("Europe/Minsk"));
//        trigger.setStartTime(new Date());
        try {
            trigger.setCronExpression("01 "+getRandom(1,59)+" "+hour+" * * ? * ?");
//            trigger.setCronExpression("03 50 23 * * ? * ?");
        } catch (ParseException e) {
            _log.debug("Can not start trigger", e);
        }
        return trigger;
    }

    private static Random rnd = new Random(System.currentTimeMillis());
    private static int getRandom(int min, int max){
        int number = min + rnd.nextInt(max - min + 1);
        return number;
    }
    /**
     * Method create Scheduler for Account
     * @param name name
     * @return Scheduler
     */
    public static Scheduler getScheduler(String name) {
        Scheduler sched=null;

        name="";

        UUID instanceId = UUID.randomUUID();
        JobStore jobStore = new RAMJobStore();
        ThreadPool threadPool = new SimpleThreadPool(4,
                Thread.NORM_PRIORITY);

        try {
            threadPool.initialize();

            DirectSchedulerFactory df = DirectSchedulerFactory
                    .getInstance();

            String schedulerName = name + "_Scheduler";
            try {
                df.createScheduler(schedulerName,
                        instanceId.toString(), threadPool, jobStore);
            } catch (SchedulerException e) {

            } finally {
                sched = df.getScheduler(name + "_Scheduler");
            }



        } catch (SchedulerException e) {
            _log.error("Don't possible create Scheduler ", e);
        }
        return sched;
    }

}
