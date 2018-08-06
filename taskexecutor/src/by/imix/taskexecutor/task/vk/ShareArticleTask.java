package by.imix.taskexecutor.task.vk;

import by.imix.taskexecutor.Task;
import by.imix.taskexecutor.VKTaskAbstract;
import by.imix.taskexecutor.sightbot.SiteAccountDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Task what tell friends about article
 */
public class ShareArticleTask extends VKTaskAbstract implements Task {
    private static final Logger _log= LoggerFactory.getLogger(ShareArticleTask.class);
    private String groupName;

    public ShareArticleTask(SiteAccountDriver siteAccountDriver, String groupName) {
        super(siteAccountDriver);
        this.groupName = groupName;
    }

    @Override
    public void execute() {
        _log.debug("start ShareArticleTask");

        if (browserForVK != null) {
            try {
                sleepOn(2000);
                browserForVK.tellRandomArticle(groupName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
