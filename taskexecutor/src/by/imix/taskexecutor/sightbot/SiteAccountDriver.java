package by.imix.taskexecutor.sightbot;

import by.imix.taskexecutor.temp.VKActionSettings;

/**
 * Account with settings
 */
public class SiteAccountDriver {

    //settings browser
    private SettingsBrowser settingsBrowser;
    //Account
    private Account account;
    //Settings Account
    private VKActionSettings vkActionSettings;

    public SiteAccountDriver(Account account, SettingsBrowser settingsBrowser, VKActionSettings vkActionSettings) {
        this.account = account;
        this.settingsBrowser=settingsBrowser;
        this.vkActionSettings = vkActionSettings;
    }

    public SettingsBrowser getSettingsBrowser() {
        return settingsBrowser;
    }

    public void setSettingsBrowser(SettingsBrowser settingsBrowser) {
        this.settingsBrowser = settingsBrowser;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public VKActionSettings getVkActionSettings() {
        return vkActionSettings;
    }

    public void setVkActionSettings(VKActionSettings vkActionSettings) {
        this.vkActionSettings = vkActionSettings;
    }
}
