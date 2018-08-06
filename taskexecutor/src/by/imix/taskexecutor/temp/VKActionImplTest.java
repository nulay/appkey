package by.imix.taskexecutor.temp;

import by.imix.taskexecutor.sightbot.SettingsBrowser;
import by.imix.taskexecutor.sightbot.SettingsBrowserFirefox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by miha on 18.12.2016.
 */
public class VKActionImplTest {
    private static final Logger _log= LoggerFactory.getLogger(VKActionImplTest.class);
    public static SynchronousQueue<String> queueForFilm = new SynchronousQueue<String>();
    public static int sdf=0;

//    public static void main(String[] args) {
//        for(int i=0;i<20;i++){
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            startD();
//        }
//    }
    public static void main(String[] args) {
        List<String> user = new ArrayList<String>();
        user.add("irNaid");
        user.add("+375299780180");
        user.add("vkurody3");

//        final List<List> listUser = new ArrayList<>();
//        listUser.add(user);
        SettingsBrowser settingsBrowser =  SettingsBrowserFirefox.initSettingsBrowser(user.get(0),"C:\\files\\work\\Firefox32\\firefox.exe");
        VKAction vkActionST =  BrowserForVKImpl.getInstance(settingsBrowser);
        vkActionST.loginToSite(user.get(1), user.get(2));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        vkActionST.likeRandomArticle("imixphoto");
//        vkActionST.logoutFromSite();
    }

    public static void startD(){
        new Thread(new Runnable() {

            public void run() {
                try {
                    queueForFilm.put("Hello "+(sdf++));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {

            public void run() {
                try {
                    _log.debug(queueForFilm.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
