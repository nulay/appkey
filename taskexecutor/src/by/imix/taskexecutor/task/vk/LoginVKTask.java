package by.imix.taskexecutor.task.vk;

import by.imix.taskexecutor.Task;
import by.imix.taskexecutor.VKTaskAbstract;
import by.imix.taskexecutor.sightbot.SiteAccountDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Task Login to VK
 */
public class LoginVKTask extends VKTaskAbstract implements Task {
    private static final Logger _log= LoggerFactory.getLogger(LoginVKTask.class);
    public LoginVKTask(SiteAccountDriver siteAccountDriver) {
        super(siteAccountDriver);
    }

    @Override
    public void execute() {
        _log.debug("start LoginVKTask");
        if (browserForVK != null) {
            try {
                if(!browserForVK.isCheckIn()) {
                    browserForVK.loginToSite(siteAccountDriver.getAccount());
                }
            } catch (Exception e) {
                _log.error("I may not possible login to site", e);
            }
        }
    }


}