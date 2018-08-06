package by.imix.taskexecutor;

import by.imix.taskexecutor.sightbot.SiteAccountDriver;

/**
 * Abstract class for all tasks with authorization on site
 */
public abstract class SiteAuthorizationTask {
    /**
     * SiteAccountDriver
     */
    protected SiteAccountDriver siteAccountDriver;



    public SiteAuthorizationTask(SiteAccountDriver siteAccountDriver) {
        this.siteAccountDriver = siteAccountDriver;
    }



    public SiteAccountDriver getSiteAccountDriver() {
        return siteAccountDriver;
    }

    public void setSiteAccountDriver(SiteAccountDriver accountDriver) {
        this.siteAccountDriver = accountDriver;
    }


    /**
     * Method for sleep execution
     * @param millisecSleep msec for sleeping
     */
    public static void sleepOn(int millisecSleep) {
        try {
            Thread.sleep(millisecSleep);
        } catch (InterruptedException e1) {
        }
    }
}
