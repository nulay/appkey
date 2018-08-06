package by.imix.taskexecutor.task.vk;

import by.imix.taskexecutor.Task;
import by.imix.taskexecutor.VKTaskAbstract;
import by.imix.taskexecutor.sightbot.SiteAccountDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Stop Browser Task
 */
public class StopBrowserTask extends VKTaskAbstract implements Task {
    private static final Logger _log= LoggerFactory.getLogger(StopBrowserTask.class);

    public StopBrowserTask(SiteAccountDriver siteAccountDriver) {
        super(siteAccountDriver);
    }

    @Override
    public void execute() {
        _log.debug("start StopBrowserTask");

        if (browserForVK != null) {
            try {
                sleepOn(2000);
                browserForVK.stopBrowser();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
