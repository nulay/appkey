package by.imix.taskexecutor.task.vk;

import by.imix.taskexecutor.Task;
import by.imix.taskexecutor.VKTaskAbstract;
import by.imix.taskexecutor.sightbot.SiteAccountDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logout task
 */
public class LogoutVKTask extends VKTaskAbstract implements Task {
    private static final Logger _log= LoggerFactory.getLogger(LogoutVKTask.class);

    public LogoutVKTask(SiteAccountDriver siteAccountDriver) {
        super(siteAccountDriver);
    }

    @Override
    public void execute() {
        _log.debug("start VKlogoutTask");
        if (browserForVK != null) {
            try {
                browserForVK.logoutFromSite();
            } catch (Exception e) {
                _log.error("I may not possible logout from site", e);
            }
        }
    }
}
