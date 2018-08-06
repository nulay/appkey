package by.imix.taskexecutor;


import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Job start list task
 */
public class NoticeJob implements Job {
    private static final Logger _log= LoggerFactory.getLogger(NoticeJob.class);
    public final static String NOTICE_ELEMENT ="Notice";
    public final static String NAME ="Name";


    public NoticeJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        _log.debug("start:NoticeJob.execute");
        JobDataMap data = jobExecutionContext.getJobDetail().getJobDataMap();
        BrowserWorkerStandart browserWorker = (BrowserWorkerStandart) data.get(NOTICE_ELEMENT);
        browserWorker.remindEvent(data.getString(NAME));
    }
}
