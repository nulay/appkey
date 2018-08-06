package by.imix.taskexecutor;

import by.imix.taskexecutor.sightbot.Account;
import by.imix.taskexecutor.sightbot.SettingsBrowser;
import by.imix.taskexecutor.sightbot.SettingsBrowserFirefox;
import by.imix.taskexecutor.temp.Article;
import by.imix.taskexecutor.temp.BrowserForVK;
import by.imix.taskexecutor.temp.BrowserForVKImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Class allow add some news to vk group
 */
public class VKworkHelper {
    private static Logger log = LoggerFactory.getLogger(VKworkHelper.class);

    /**
     * Account
     */
    private Account account;

    //Count time try add Article
    private static int COUNT_TIME_DEFAULT = 6;

    /**
     * Constructor
     *
     * @param account VK account data
     */
    public VKworkHelper(Account account) {
        this.account = account;

    }

    /**
     * Method try add Article few time if not possible add from 1 time
     *
     * @param article article
     */
    public boolean addArticleWithRepeatUnsuccess(Article article) {
        log.debug("startPostFilm : start execution");

        if (article != null) {
            BrowserForVK vkActionST = null;
            try {
                SettingsBrowser settingsBrowser = SettingsBrowserFirefox.initSettingsBrowser("Miha", "C:\\files\\work\\Firefox32\\firefox.exe");

                vkActionST =  BrowserForVKImpl.getInstance(settingsBrowser);
                vkActionST.startBrowser();
            } catch (Exception e) {
            }
            if (vkActionST != null) {
                try {
                    vkActionST.loginToSite(account.getLogin(), account.getPassword());
                    sleepOn(2000);
                    boolean chP = true;
                    int repeat = 0;
                    while (chP & repeat < COUNT_TIME_DEFAULT) {
                        repeat++;
                        if (article != null && article.getArticle() != null) {
                            vkActionST.addArticle("https://vk.com/smedia_club", article.getArticle(), article.getImages());
                            //sleep 1-10 min
                            int t = new Random().nextInt(10);
                            log.debug("thFilm:sleep " + t + " мин.");
                            sleepOn(1000 * 60 * t);
                        } else {
                            chP = false;
                        }
                    }
                } catch (Exception e) {
                    return false;
                } finally {
                    vkActionST.stopBrowser();
                }
            }
        }
        log.debug("startPostFilm : finish execution");
        return true;
    }

    /**
     * Method for sleeping
     *
     * @param millisecSleep millisec for sleep
     */
    public static void sleepOn(int millisecSleep) {
        try {
            Thread.sleep(millisecSleep);
        } catch (InterruptedException e1) {
        }
    }

}
