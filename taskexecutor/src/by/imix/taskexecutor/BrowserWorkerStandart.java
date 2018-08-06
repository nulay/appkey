package by.imix.taskexecutor;

import by.imix.taskexecutor.sightbot.Account;
import by.imix.taskexecutor.sightbot.SettingsBrowser;
import by.imix.taskexecutor.sightbot.SettingsBrowserFirefox;
import by.imix.taskexecutor.sightbot.SiteAccountDriver;
import by.imix.taskexecutor.task.vk.*;
import by.imix.taskexecutor.temp.BrowserForVK;
import by.imix.taskexecutor.temp.BrowserForVKImpl;
import by.imix.taskexecutor.temp.VKActionSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main class worker select a browser, start it, contain accounts, contain tasks, stop browser
 */
public class BrowserWorkerStandart implements BrowserWorker  {
    private static final Logger _log= LoggerFactory.getLogger(BrowserWorkerStandart.class);
    private final Account account;
    //VK account
    private SiteAccountDriver siteAccountdriver;
    //List Task for execute
    private List<Task> listTaskForExecute;
    //SettingsBrowser
    private SettingsBrowser settingsBrowser;
    //tasks - you need 5 times along day go to vk and make tasks
    //morning about 8 clock you need:
    //     - add new friends 10 peoples
    //     - add some people in all groups (https://vk.com/english__class, https://vk.com/gomelauto, https://vk.com/imixweb,
    //                                      https://vk.com/pazlomania, https://vk.com/imixphoto)
    //
    //     - add some news in car group https://vk.com/gomelauto
    //     - add some news in computer group https://vk.com/imixweb
    //     - add some news to anekdot group https://vk.com/notgravity
    //     - add some news to films group https://vk.com/smedia_club
    //     - add some news to building group https://vk.com/build_help
    //     - add some news to photo group https://vk.com/imixphoto
    //before diner about 11 clock you need:
    //      - add new friends 10 peoples
    //      - add some people in all groups (https://vk.com/english__class, https://vk.com/gomelauto, https://vk.com/imixweb,
    //                                      https://vk.com/pazlomania, https://vk.com/imixphoto)
    //     - add some news in car group https://vk.com/gomelauto
    //     - add some news in computer group https://vk.com/imixweb
    //     - add some news to anekdot group https://vk.com/notgravity
    //     - add some news to films group https://vk.com/smedia_club
    //     - add some news to building group https://vk.com/build_help
    //     - add some news to photo group https://vk.com/imixphoto
    //after diner about 14 clock you need:
    //      - add new friends 10 peoples
    //      - add some people in all groups (https://vk.com/english__class, https://vk.com/gomelauto, https://vk.com/imixweb,
    //                                      https://vk.com/pazlomania, https://vk.com/imixphoto)
    //     - add some news in car group https://vk.com/gomelauto
    //     - add some news in computer group https://vk.com/imixweb
    //     - add some news to anekdot group https://vk.com/notgravity
    //     - add some news to films group https://vk.com/smedia_club
    //     - add some news to building group https://vk.com/build_help
    //     - add some news to photo group https://vk.com/imixphoto
    //evening about 18 clock you need:
    //      - add new friends 10 peoples
    //      - add some people in all groups (https://vk.com/english__class, https://vk.com/gomelauto, https://vk.com/imixweb,
    //                                      https://vk.com/pazlomania, https://vk.com/imixphoto)
    //     - add some news in car group https://vk.com/gomelauto
    //     - add some news in computer group https://vk.com/imixweb
    //     - add some news to anekdot group https://vk.com/notgravity
    //     - add some news to films group https://vk.com/smedia_club
    //     - add some news to building group https://vk.com/build_help
    //     - add some news to photo group https://vk.com/imixphoto
    //evening about 21 clock you need:
    //      - add new friends 10 peoples
    //      - add some people in all groups (https://vk.com/english__class, https://vk.com/gomelauto, https://vk.com/imixweb,
    //                                      https://vk.com/pazlomania, https://vk.com/imixphoto)
    //     - add some news in car group https://vk.com/gomelauto
    //     - add some news in computer group https://vk.com/imixweb
    //     - add some news to anekdot group https://vk.com/notgravity
    //     - add some news to films group https://vk.com/smedia_club
    //     - add some news to building group https://vk.com/build_help
    //     - add some news to photo group https://vk.com/imixphoto

    public static void main(String args[]){
        Account account = new Account("Miha", "+375297350741", "vkurody3");

//        bw.createNotice();

        account = new Account("MihaJa", "nulay1@mail.ru", "vkurody3");
        account = new Account("irNaid", "+375299780180", "vkurody3");
        account = new Account("irNaid2", "neznashk@mail.ru", "vkurody3");
        VKActionSettings vkActionSettings = new VKActionSettings();
        BrowserWorkerStandart bw=new BrowserWorkerStandart(account, vkActionSettings, "C:\\files\\work\\Firefox32\\firefox.exe");
        bw.remindEvent("morning");


        Account account2 = new Account("MihaJa", "nulay1@mail.ru", "vkurody3");
        BrowserWorkerStandart bw2=new BrowserWorkerStandart(account2, vkActionSettings, "C:\\files\\work\\Firefox32\\firefox.exe");
//        bw.createNotice();




        bw2.remindEvent("morning");
    }

    private Map<String, List<Task>> mapListTask;

    public BrowserWorkerStandart(Account account, VKActionSettings vkActionSettings, String pathBrowser) {
        SettingsBrowser settingsBrowser = SettingsBrowserFirefox.initSettingsBrowser("vkWorker", pathBrowser);

        this.siteAccountdriver = new SiteAccountDriver(account, settingsBrowser, vkActionSettings);
        this.account=account;

//        Task task = new PostFilmToVKTask(miha);
//        task.execute();
        mapListTask = new HashMap<>();



        Task task0 = new StartBrowserTask(this.siteAccountdriver);

        Task task1 = new LoginVKTask(this.siteAccountdriver);

        Task task2 = new AddFrandsToVKaccountTask(this.siteAccountdriver);
        Task task3 = new LogoutVKTask(this.siteAccountdriver);
        Task task4 = new LikeArtikleTask(this.siteAccountdriver, "imixphoto");
        Task task5 = new LikeArtikleTask(this.siteAccountdriver, "notgravity");
        Task task6 = new LikeArtikleTask(this.siteAccountdriver, "smedia_club");



//        Task task3 = new StopBrowserTask(this.siteAccountdriver);
        List listMorningTask = new ArrayList<>();
        listMorningTask.add(task0);
        listMorningTask.add(task1);
        listMorningTask.add(task2);
        listMorningTask.add(task4);
        listMorningTask.add(task3);

        List listAfter_morningTask = new ArrayList<>();
        listAfter_morningTask.add(task0);
        listAfter_morningTask.add(task1);
        listAfter_morningTask.add(task2);
        listAfter_morningTask.add(task5);
        listAfter_morningTask.add(task3);

        List listAfternoonTask = new ArrayList<>();
        listAfternoonTask.add(task0);
        listAfternoonTask.add(task1);
        listAfternoonTask.add(task2);
        listAfternoonTask.add(task6);
        listAfternoonTask.add(task3);

        List listEveningTask = new ArrayList<>();
        listEveningTask.add(task0);
        listEveningTask.add(task1);
        listEveningTask.add(task2);
        listEveningTask.add(task6);
        listEveningTask.add(task3);

        List listDeep_eveningTask = new ArrayList<>();
        listDeep_eveningTask.add(task0);
        listDeep_eveningTask.add(task1);
        listDeep_eveningTask.add(task2);
        listDeep_eveningTask.add(task6);
        listDeep_eveningTask.add(task3);

        mapListTask.put("morning", listMorningTask);
        mapListTask.put("after_morning", listAfter_morningTask);
        mapListTask.put("afternoon", listAfternoonTask);
        mapListTask.put("evening", listEveningTask);
        mapListTask.put("deep_evening", listDeep_eveningTask);

    }


    public void createNotice() {
        //create scheduler for notice account about his task
        _log.debug("createNotice " + account.getName());
        SchedulerCreator.createNoticeAction(this.getAccount().getName(), this, "morning", 8);
        SchedulerCreator.createNoticeAction(this.getAccount().getName(), this, "after_morning", 10);
        SchedulerCreator.createNoticeAction(this.getAccount().getName(), this, "afternoon", 14);
        SchedulerCreator.createNoticeAction(this.getAccount().getName(), this, "evening", 14);
        SchedulerCreator.createNoticeAction(this.getAccount().getName(), this, "deep_evening", 21);
    }

    @Override
    public void remindEvent(String nameSet){
        _log.debug("remindEvent " + account.getName() +" name set "+nameSet);
        BrowserForVK browserForVK = BrowserForVKImpl.getInstance(siteAccountdriver.getSettingsBrowser());
        if(browserForVK.isCheckIn()){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            remindEvent(nameSet);
        }else {
            List<Task> taskList = mapListTask.get(nameSet);
            for (Task task : taskList) {
                task.execute();
            }
        }
    }

    /**
     * @return
     */
    public Account getAccount() {
        return account;
    }

    public SettingsBrowser getSettingsBrowser() {
        return settingsBrowser;
    }

    public void setSettingsBrowser(SettingsBrowser settingsBrowser) {
        this.settingsBrowser = settingsBrowser;
    }

    public SiteAccountDriver getSiteAccountdriver() {
        return siteAccountdriver;
    }

    public void setSiteAccountdriver(SiteAccountDriver siteAccountdriver) {
        this.siteAccountdriver = siteAccountdriver;
    }
}





