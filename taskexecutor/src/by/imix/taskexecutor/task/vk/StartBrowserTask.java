package by.imix.taskexecutor.task.vk;

import by.imix.taskexecutor.Task;
import by.imix.taskexecutor.VKTaskAbstract;
import by.imix.taskexecutor.sightbot.SiteAccountDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Task for start browser
 */
public class StartBrowserTask extends VKTaskAbstract implements Task {
    private static final Logger _log= LoggerFactory.getLogger(StartBrowserTask.class);

    public StartBrowserTask(SiteAccountDriver siteAccountDriver) {
        super(siteAccountDriver);
    }

    @Override
    public void execute() {
        _log.debug("start StartBrowserTask");

        if (browserForVK != null) {
            try {
                sleepOn(2000);
                browserForVK.startBrowser();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}