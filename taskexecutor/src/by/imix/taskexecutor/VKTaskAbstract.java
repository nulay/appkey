package by.imix.taskexecutor;

import by.imix.taskexecutor.sightbot.SiteAccountDriver;
import by.imix.taskexecutor.temp.BrowserForVK;
import by.imix.taskexecutor.temp.BrowserForVKImpl;

/**
 * Abstract class for all tasks with authorization on VK
 */
public class VKTaskAbstract extends SiteAuthorizationTask {
    /**
     * vkAction
     */

    protected BrowserForVK browserForVK;

    public VKTaskAbstract(SiteAccountDriver siteAccountDriver) {
        super(siteAccountDriver);
        browserForVK = BrowserForVKImpl.getInstance(siteAccountDriver.getSettingsBrowser());
    }

    public VKTaskAbstract(SiteAccountDriver siteAccountDriver, BrowserForVK browserForVK) {
        super(siteAccountDriver);
        this.browserForVK = browserForVK;
    }

    public BrowserForVK getBrowserForVK() {
        return browserForVK;
    }

    public void setBrowserForVK(BrowserForVK browserForVK) {
        this.browserForVK = browserForVK;
    }
}
