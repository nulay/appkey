package by.imix.taskexecutor.task.vk;

import by.imix.taskexecutor.Task;
import by.imix.taskexecutor.VKTaskAbstract;
import by.imix.taskexecutor.sightbot.SiteAccountDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Task like artikle
 */
public class LikeArtikleTask extends VKTaskAbstract implements Task {
    private static final Logger _log= LoggerFactory.getLogger(ShareArticleTask.class);
    private String groupName;

    public LikeArtikleTask(SiteAccountDriver siteAccountDriver, String groupName) {
        super(siteAccountDriver);
        this.groupName = groupName;
    }

    @Override
    public void execute() {
        _log.debug("start ShareArticleTask");

        if (browserForVK != null) {
            try {
                sleepOn(2000);
                browserForVK.likeRandomArticle(groupName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
