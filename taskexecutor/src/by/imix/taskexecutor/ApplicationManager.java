package by.imix.taskexecutor;

import by.imix.taskexecutor.filter.Filter;
import by.imix.taskexecutor.sightbot.Account;
import by.imix.taskexecutor.sightbot.SettingsBrowser;
import by.imix.taskexecutor.temp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by miha on 30.05.2018.
 */
public class ApplicationManager {
    private static final Logger _log= LoggerFactory.getLogger(ApplicationManager.class);
//    private static final org.apache.log4j.Logger _log4= org.apache.log4j.Logger.getLogger(ApplicationManager.class);
    public static void main(String args[]) {
        ApplicationManager applicationManager = new ApplicationManager();
    }
//    static final String path = "taskexecutor/src/log4j.xml";
    public ApplicationManager() {
        configureLogger();

//        DOMConfigurator.configure(path);

        _log.info("Start Application");
//        _log4.debug("Hello");
        VKActionSettings vkActionSettings = new VKActionSettings();
        startAccount(new Account("Miha", "+375297350741", "vkurody3"), vkActionSettings);
        startAccount(new Account("MihaJa", "nulay1@mail.ru", "vkurody3"), vkActionSettings);
        startAccount(new Account("irNaid", "+375299780180", "vkurody3"), vkActionSettings);
        startAccount(new Account("irNaid2", "neznashk@mail.ru", "vkurody3"), vkActionSettings);


//        for (Account user : listUser) {
//            ApplicationManager.startLiker(user);
//        }
//
//        ApplicationManager.startPostFilm(irNaid);
//
//        ApplicationManager.startPostAnekdot(irNaid);


        while (true) {
            Scanner in = new Scanner(System.in);
            String a = in.next();//считываем ввод
            _log.debug(a);//выводим сумму a+b

            if (a.equals("start write anekdot")) {
                _log.debug("start write anekdot");
            }
            if (a.equals("start write film")) {
                _log.debug("start write film");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
            }
        }
    }

    private void configureLogger() {
//        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
//
//        try {
//            JoranConfigurator configurator = new JoranConfigurator();
//            configurator.setContext(context);
//            // Call context.reset() to clear any previous configuration, e.g. default
//            // configuration. For multi-step configuration, omit calling context.reset().
//            context.reset();
//            configurator.doConfigure(args[0]);
//        } catch (JoranException je) {
//            // StatusPrinter will handle this
//        }
//        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    }

    private void startAccount(final Account account, final VKActionSettings vkActionSettings) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BrowserWorkerStandart bw=new BrowserWorkerStandart(account, vkActionSettings, "C:\\files\\work\\Firefox32\\firefox.exe");
                bw.createNotice();
//                bw.remindEvent("morning");

            }
        }).start();
    }


    public static SynchronousQueue<Article> queueForFilm = new SynchronousQueue();
    private static SettingsBrowser settingsBrowser =  null;
    //SettingsBrowserFirefox.initSettingsBrowser("Miha", "C:\\files\\work\\Firefox32\\firefox.exe");

    private static void startCatcherFilms() {
//        String configFiles = "modules" + File.separator + "startservicechange" + File.separator + "src" + File.separator + "spring-app-modules.xml";
//
//        Thread CatcherFilms = new Thread(new Runnable() {
//            FileSystemXmlApplicationContext factory = new FileSystemXmlApplicationContext(configFiles);
//            ParserImpl b1 = (MegacriticParser) factory.getBean("MegacriticParser");
//            ParserImpl b2 = (SerialochkaParser) factory.getBean("SerialochkaParser");
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        b1.startParseAndSave();
//                        b2.startParseAndSave();
//                    } catch (ChangerException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        Thread.sleep(1000 * 60 * 60 * 4);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        CatcherFilms.start();
    }


    private static void startPostAnekdot(final Account account) {
        Thread thAnek = new Thread(new Runnable() {

            public void run() {
                while (true) {
                    _log.debug("thAnek:start execution");

                    Date d1 = new Date();
                    if (d1.getHours() + 1 > 23 || d1.getHours() < 5) {
                        _log.debug("thAnek:sleep 6 hours");
                        sleepOn(1000 * 60 * 60 * 6);
                    }
                    String anek = getAnekdotString();
                    if (anek != null) {
                        BrowserForVK vkActionST = null;
                        try {
                            vkActionST =  BrowserForVKImpl.getInstance(settingsBrowser);
                            vkActionST.startBrowser();
                        } catch (Exception e) {
                        }
                        if (vkActionST != null) {
                            try {
                                vkActionST.loginToSite(account.getLogin(), account.getPassword());
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                vkActionST.addArticle("https://vk.com/notgravity", changeV(anek.replaceAll("[aа].{8}\\..{2}t", ""), Filter.strCh));
                            } catch (Exception e) {
                                _log.debug("thAnek:didn`t find anekdot");
                            } finally {
                                vkActionST.stopBrowser();
                            }
                        }
                    }
                    _log.debug("thAnek:sleep 30 min");
                    sleepOn(1000 * 60 * 30);
                }
            }
        });

        thAnek.start();
    }

    /**
     * Method return anekdot
     *
     * @return
     */
    private static String getAnekdotString() {
        String anek = "";
        try {
            anek = new LoaderArticle().getAnekdot().getArticle();
            if (anek.split(" ").length < 4) {
                return getAnekdotString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return anek;
    }

    private static void startPostFilm(final Account user) {
        Thread thFilm = new Thread(new Runnable() {

            public void run() {
                while (true) {
                    _log.debug("thFilm:start execution");

                    Date d1 = new Date();
                    if (d1.getHours() + 1 > 23 || d1.getHours() < 5) {
                        _log.debug("thFilm:sleep 6 hours");
                        sleepOn(1000 * 60 * 60 * 6);
                    }
                    Article article = getArticle();
                    if (article != null) {
                        BrowserForVK vkActionST = null;
                        try {
                            vkActionST =  BrowserForVKImpl.getInstance(settingsBrowser);
                            vkActionST.startBrowser();
                        } catch (Exception e) {
                        }
                        if (vkActionST != null) {
                            try {
                                vkActionST.loginToSite(user.getLogin(), user.getPassword());
                                sleepOn(2000);
                                boolean chP = true;
                                int repeat = 0;
                                while (chP & repeat < 18) {
                                    repeat++;
                                    if (article != null && article.getArticle() != null) {
                                        vkActionST.addArticle("https://vk.com/smedia_club", article.getArticle(), article.getImages());
                                        int t = new Random().nextInt(10);
                                        _log.debug("thFilm:sleep " + t + " мин.");
                                        sleepOn(1000 * 60 * t);
                                    } else {
                                        chP = false;
                                    }
                                    article = getArticle();
                                }
                            } catch (Exception e) {
                                // e.printStackTrace();
                                try {
                                    queueForFilm.put(article);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                            } finally {
                                vkActionST.stopBrowser();
                            }
                        }
                    }
                    _log.debug("thFilm:sleep 2 hours");
                    sleepOn(1000 * 60 * 60 * 2);

                }
            }
        });

        thFilm.start();
    }

    private static Article getArticle() {
        Article article = null;
        if (queueForFilm.size() > 0) {
            try {
                article = queueForFilm.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // for(int i=0;i<1000;i++) {
            try {
                article = new LoaderArticle().getFilm();
            } catch (IOException e) {
            }
            // }
        }
        return article;
    }

    private static void startLiker(final Account account) {
        Thread thLike = new Thread(new Runnable() {

            public void run() {
                while (true) {
                    _log.debug("thLike:start execution");
                    sleepOn(1000 * 60 * new Random().nextInt(60));
                    BrowserForVK vkActionST = null;
                    try {
                        vkActionST =  BrowserForVKImpl.getInstance(settingsBrowser);
                        vkActionST.startBrowser();
                    } catch (Exception e) {
                    }
                    if (vkActionST != null) {
                        try {
                            vkActionST.loginToSite(account.getLogin(), account.getPassword());
                            sleepOn(2000);
                            vkActionST.addFriendToVKAccount();
                            sleepOn(2000);
                            vkActionST.tellRandomArticle("https://vk.com/notgravity");
                            sleepOn(2000);
                            if (new Random().nextInt(3) == 2) {
                                vkActionST.tellRandomArticle("https://vk.com/smedia_club");
                            }
                            sleepOn(2000);

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            vkActionST.stopBrowser();
                        }
                    }
                }
            }
        });
        thLike.start();
    }


    /**
     * Method for change word from changeSent on value from this map in doc
     *
     * @param doc        document
     * @param changeSent map for change
     * @return change doc
     */
    public static String changeV(String doc, Map<String, String> changeSent) {
        for (String sent : changeSent.keySet()) {
            try {
                doc = doc.replaceAll(sent, changeSent.get(sent));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return doc;
    }

    /**
     * Sleep on millisec time
     *
     * @param millisecSleep sec for sleep
     */
    public static void sleepOn(int millisecSleep) {
        try {
            Thread.sleep(millisecSleep);
        } catch (InterruptedException e1) {
        }
    }
}
