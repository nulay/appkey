package by.imix.taskexecutor.sightbot;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * Settings Browser
 */
public abstract class SettingsBrowser {
    protected String pathBrowser;
    protected String profileName;
    private RemoteWebDriver remoteWebDriver;
    protected static Map<String, SettingsBrowser> mapSettingBrowsers = new HashMap<>();

    protected SettingsBrowser(String pathBrowser, String profileName) {
        this.pathBrowser = pathBrowser;
        this.profileName = profileName;
    }

    public String getPathBrowser() {
        return pathBrowser;
    }

    public void setPathBrowser(String pathBrowser) {
        this.pathBrowser = pathBrowser;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public abstract RemoteWebDriver startBrowser();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SettingsBrowser that = (SettingsBrowser) o;

        if (pathBrowser != null ? !pathBrowser.equals(that.pathBrowser) : that.pathBrowser != null) return false;
        return profileName != null ? profileName.equals(that.profileName) : that.profileName == null;

    }

    @Override
    public int hashCode() {
        int result = pathBrowser != null ? pathBrowser.hashCode() : 0;
        result = 31 * result + (profileName != null ? profileName.hashCode() : 0);
        return result;
    }

    public RemoteWebDriver getRemoteWebDriver() {
        return remoteWebDriver;
    }

    public void setRemoteWebDriver(RemoteWebDriver remoteWebDriver) {
        this.remoteWebDriver = remoteWebDriver;
    }

    public static SettingsBrowser initSettingsBrowser(String profileName, String pathBrowser){
        if(mapSettingBrowsers.get(profileName) == null){
            mapSettingBrowsers.put(profileName, new SettingsBrowserFirefox(pathBrowser, profileName));
        }
        return mapSettingBrowsers.get(profileName);
    }
}
