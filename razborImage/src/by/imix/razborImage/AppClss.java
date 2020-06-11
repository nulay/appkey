package by.imix.razborImage;

import by.imix.keyReader.KeyTimeEvent;
import by.imix.keyReader.TimeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Miha
 * Date: 04.02.2010 Time: 19:51:08
 */

public class AppClss {
    private static final Logger _log = LoggerFactory.getLogger(AppClss.class);
    private boolean keyEnd=false;
    private List<TimeEvent> lkp;
    private List<TimeEvent> lkpNow;
    private boolean keyPause;


    public AppClss() {
    }

    public void setAction(List<TimeEvent> lkp){
      this.lkp=lkp;
    }

    public void playNow(List<KeyTimeEvent> lkpNow) {
        lkpNow=lkpNow;
    }

    public void playBot() {
        _log.info("AppClss.playBot");
        keyEnd=false;
        if(true){
            _log.info("Запуск эмуляции");
            while(!keyEnd){
                try {
                    Thread.sleep(lkp.get(0).getTimeStartEvent());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i=0;i<lkp.size();i++){
                    if(keyEnd) break;
                    if(keyPause){
                        while(keyPause){
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                        }
                    }
                    if(lkpNow!=null){
                        for(int y=0;y<lkpNow.size();y++){
                            if(keyEnd) break;
                            TimeEvent se1=lkpNow.get(y);
                            if(se1 instanceof KeyTimeEvent) {
                                KeyTimeEvent kp1 = (KeyTimeEvent) se1;
                                _log.debug("Try to push:" + kp1.getKey());
                                Poehali p = new Poehali(kp1.getKeyCode(), kp1.getTimeStartEvent());
                                p.start();
                                if (y + 1 >= lkpNow.size()) break;
                                TimeEvent se2=lkpNow.get(y + 1);
                                if(se2 instanceof KeyTimeEvent) {
                                    KeyTimeEvent kp2 = (KeyTimeEvent) se2;
                                    try {
                                        Long time = kp2.getTimeStartEvent() - kp1.getTimeStartEvent();
                                        if (time < 0) {
                                            time = 0L;
                                        }
                                        Thread.sleep(time);
//                        Thread.sleep(10);
                                    } catch (InterruptedException e) {
                                        _log.error(e.getMessage());
                                    }
                                }
                            }
                        }
                        lkpNow=null;
                    }
                    TimeEvent se1=lkp.get(i);
                    if(se1 instanceof KeyTimeEvent) {
                        KeyTimeEvent kp1 = (KeyTimeEvent) se1;
                        _log.debug("Try to push:" + kp1.getKey());
                        Poehali p = new Poehali(kp1.getKeyCode(), kp1.getTimeStartEvent());
                        p.start();
                        if (i + 1 >= lkp.size()) break;
                        TimeEvent se2=lkp.get(i + 1);
                        if(se2 instanceof KeyTimeEvent) {
                            KeyTimeEvent kp2 = (KeyTimeEvent) se2;
                            try {
                                Long time = kp2.getTimeStartEvent() - kp1.getTimeStartEvent();
                                if (time < 0) {
                                    time = 0L;
                                }
                                Thread.sleep(time);
//                        Thread.sleep(10);
                            } catch (InterruptedException e) {
                                _log.error(e.getMessage());
                            }
                        }
                    }
                }
            }
            _log.info("Эмуляция остановлена");
            return;
        }

        _log.debug("start");
        DD d1=new DD("key",0, java.awt.event.KeyEvent.VK_Z,2000L, 2000L);
        d1.start();
//            by.imix.razborImage.DD d2=new by.imix.razborImage.DD("mouse",0,InputEvent.BUTTON3_MASK,new Long(9000));
//            d2.start();
        DD d3=new DD("key",0, java.awt.event.KeyEvent.VK_UP,10L,20000L);
        d3.start();

//            by.imix.razborImage.DD d5=new by.imix.razborImage.DD("key",0,KeyEvent.VK_X,10L,330L);
//            d5.start();

        DD d6=new DD("key",0, java.awt.event.KeyEvent.VK_LEFT,1000L,300L);
        d6.start();


        DD d4=new DD("key",0, java.awt.event.KeyEvent.VK_SPACE,1000L,60L);
        d4.start();
//            by.imix.razborImage.DD d5=new by.imix.razborImage.DD("key",0,KeyEvent.VK_2,new Long(7000));
//            d5.start();
//            by.imix.razborImage.DD d6=new by.imix.razborImage.DD("key",0,KeyEvent.VK_8,new Long(25000));
//            d6.start();
//            by.imix.razborImage.DD d7=new by.imix.razborImage.DD("key",0,KeyEvent.VK_1,new Long(22000));
//            d7.start();
//            by.imix.razborImage.DD d8=new by.imix.razborImage.DD("key",0,KeyEvent.VK_7,new Long(61000));
//            d8.start();
    }

    public static void main(String[] args){
        _log.debug("Проигрование эмуляции начнется через 10 секунд");
        try {
            Thread.sleep(10L*1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<TimeEvent> lkps=new ArrayList<TimeEvent>();
        try {
            FileInputStream fis = new FileInputStream("temp.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            lkps = (List<TimeEvent>) oin.readObject();

        } catch (IOException e) {
            _log.debug(e.getMessage());
        } catch (ClassNotFoundException e) {
            _log.debug(e.getMessage());
        }
        AppClss apc=new AppClss();
        apc.setAction(lkps);
        apc.playBot();

//
//        Thread thread=new Thread(){
//            public void run() {
//                for(int i=0 ;i<1000000; i++){
//                    try {
//                        Robot robot = new Robot();
//                        robot.mousePress(InputEvent.BUTTON3_MASK);
//                        robot.mouseRelease(InputEvent.BUTTON3_MASK);
//
//                    } catch (AWTException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        Thread.sleep(10000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                    }
//                }
//            }
//        };
//        thread.start();
//        Thread thread2=new Thread(){
//            public void run() {
//                for(int i=0 ;i<1000000; i++){
//                    try {
//                        Robot robot = new Robot();
//                        robot.keyPress(KeyEvent.VK_5);
//                        robot.keyRelease(KeyEvent.VK_5);
//                    } catch (AWTException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        Thread.sleep(35000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        thread2.start();
//        Thread thread3=new Thread(){
//            public void run() {
//                for(int i=0 ;i<1000000; i++){
//                    try {
//                        Thread.sleep(190000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        Robot robot = new Robot();
//                        robot.keyPress(KeyEvent.VK_6);
//                        robot.keyRelease(KeyEvent.VK_6);
//                    } catch (AWTException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        };
//        thread3.start();
//        for(int i=0 ;i<1000000; i++){
//            try {
//                Robot robot = new Robot();
//                robot.keyPress(KeyEvent.VK_9);
//                robot.keyRelease(KeyEvent.VK_9);
//            } catch (AWTException e) {
//                e.printStackTrace();
//            }
//            try {
//                Thread.sleep(30000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }


    public void setKeyEnd(boolean keyEnd) {
        this.keyEnd = keyEnd;
    }

    public boolean isKeyEnd() {
        return keyEnd;
    }

    public void stop(){
        setKeyEnd(true);
    }

    public boolean isKeyPause() {
        return keyPause;
    }

    public void setKeyPause(boolean keyPause) {
        this.keyPause = keyPause;
    }
}
