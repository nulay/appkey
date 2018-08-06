package by.imix.taskexecutor.task.vk;

import by.imix.taskexecutor.Task;
import by.imix.taskexecutor.VKTaskAbstract;
import by.imix.taskexecutor.sightbot.SiteAccountDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Add Frands To VK account Task
 */
public class AddFrandsToVKaccountTask extends VKTaskAbstract implements Task {
    private static final Logger _log= LoggerFactory.getLogger(AddFrandsToVKaccountTask.class);

    public AddFrandsToVKaccountTask(SiteAccountDriver siteAccountDriver) {
        super(siteAccountDriver);

    }

    @Override
    public void execute() {
        _log.debug("start AddFrandsToVKaccountTask");
        if (browserForVK != null) {
            try {
                sleepOn(2000);
                browserForVK.addFriendToVKAccount();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
