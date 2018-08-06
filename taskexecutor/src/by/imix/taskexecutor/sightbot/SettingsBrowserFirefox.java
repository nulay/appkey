package by.imix.taskexecutor.sightbot;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

/**
 * Settings Browser for firefox
 */
public class SettingsBrowserFirefox extends SettingsBrowser{
    private FirefoxProfile myprofile;
    private FirefoxBinary binary;
    protected SettingsBrowserFirefox(String profileName, String pathBrowser) {
        super(pathBrowser, profileName);
        ProfilesIni profile = new ProfilesIni();
        File pathToBinary = new File(pathBrowser);
//        File pathToBinary = new File("C:\\files\\work\\Firefox32\\firefox.exe");
        binary = new FirefoxBinary(pathToBinary);


        myprofile = profile.getProfile(profileName);
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.

    }

    public RemoteWebDriver startBrowser() {
        setRemoteWebDriver(new FirefoxDriver(binary, myprofile));
        return getRemoteWebDriver();
    }

    public static SettingsBrowser initSettingsBrowser(String profileName, String pathBrowser){
        if(mapSettingBrowsers.get(profileName) == null){
            mapSettingBrowsers.put(profileName, new SettingsBrowserFirefox(profileName, pathBrowser));
        }
        return mapSettingBrowsers.get(profileName);
    }
}
