package by.imix.taskexecutor.temp;

import by.imix.taskexecutor.sightbot.Account;
import by.imix.taskexecutor.sightbot.SettingsBrowser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrowserForVKImpl implements BrowserForVK {
    private static final Logger _log= LoggerFactory.getLogger(BrowserForVKImpl.class);
    private VKAction vkAction;
    private SettingsBrowser settingsBrowser;
    private boolean stopBrowser = true;
    private boolean checkIn = false;

    private BrowserForVKImpl(SettingsBrowser settingsBrowser) {
        this.settingsBrowser = settingsBrowser;
    }

    private static Map<SettingsBrowser, BrowserForVK> INSTANCE = new HashMap<>();

    public static BrowserForVK getInstance(SettingsBrowser settingsBrowser) {
        BrowserForVK vkAction = INSTANCE.get(settingsBrowser);
        if (vkAction == null) {
            vkAction = new BrowserForVKImpl(settingsBrowser);
            INSTANCE.put(settingsBrowser, vkAction);
        }
        return vkAction;
    }

    @Override
    public void tellRandomArticle(String groupURL) {
        vkAction.tellRandomArticle(groupURL);
    }

    @Override
    public void tellRandomArticleAnyOneSharedIt(String groupURL) {
        vkAction.tellRandomArticleAnyOneSharedIt(groupURL);
    }

    @Override
    public void likeRandomArticle(String groupURL) {
        vkAction.likeRandomArticle(groupURL);
    }

    @Override
    public void loginToSite(Account account) {
        loginToSite(account.getLogin(), account.getPassword());
    }

    @Override
    public void loginToSite(String login, String password) {
        if (stopBrowser) {
            startBrowser();
        }
        if (!checkIn) {
            vkAction.loginToSite(login, password);
            checkIn = true;
        }
    }

    @Override
    public void logoutFromSite() {
        vkAction.logoutFromSite();
        checkIn = false;
    }

    @Override
    public void addFriendToVKAccount() {
        vkAction.addFriendToVKAccount();
    }

    @Override
    public void addArticle(String groupURL, String article) {
        vkAction.addArticle(groupURL,article);
    }

    @Override
    public void addArticle(String groupURL, String article, String[] images) {
        vkAction.addArticle(groupURL,article,images);
    }


    public void stopBrowser() {
        _log.debug("stopBrowser");
        stopBrowser = true;
        vkAction = null;
    }


    public void startBrowser() {
        _log.debug("startBrowser");
        if (stopBrowser) {
            vkAction = new VKActionImpl(settingsBrowser.startBrowser());
            stopBrowser = false;
        }
    }

    @Override
    public boolean isCheckIn() {
        return checkIn;
    }

    @Override
    public List<Article> findRecords(String url) {
        return vkAction.findRecords(url);
    }
}
