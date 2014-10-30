package by.imix.botTank;

import by.imix.keyReader.KeyPressed;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Miha
 * Date: 04.02.2010 Time: 19:51:08
 */

public class AppClss {
    private Logger _log=Logger.getLogger(AppClss.class);
    private boolean keyEnd=false;
    private List<KeyPressed> lkp;
    private List<KeyPressed> lkpNow;
    private boolean keyPause;


    public AppClss() {
    }

    public void setAction(List<KeyPressed> lkp){
        this.lkp=lkp;
    }

    public void playNow(List<KeyPressed> lkpNow) {
        lkpNow=lkpNow;
    }

    public void playBot() {
        keyEnd=false;
        if(true){
            _log.info("Запуск эмуляции");
            while(!keyEnd){
                try {
                    Thread.sleep(lkp.get(0).getPressed());
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
                            KeyPressed kp1=lkpNow.get(y);
                            Poehali p=new Poehali(kp1.getKeyCode(),kp1.getTimePressed());
                            p.start();
                            if(y+1>=lkpNow.size()) break;
                            KeyPressed kp2=lkpNow.get(y+1);
                            try {
                                Long time=kp2.getPressed()-kp1.getPressed();
                                if(time<0){
                                    time=0L;
                                }
                                Thread.sleep(time);
//                        Thread.sleep(10);
                            } catch (InterruptedException e) {
                                _log.error(e.getMessage());
                            }
                        }
                        lkpNow=null;
                    }
                    KeyPressed kp1=lkp.get(i);
                    Poehali p=new Poehali(kp1.getKeyCode(),kp1.getTimePressed());
                    p.start();
                    if(i+1>=lkp.size()) break;
                    KeyPressed kp2=lkp.get(i+1);
                    try {
                        Long time=kp2.getPressed()-kp1.getPressed();
                        if(time<0){
                            time=0L;
                        }
                        Thread.sleep(time);
//                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        _log.error(e.getMessage());
                    }
                }
            }
            _log.info("Эмуляция остановлена");
            return;
        }

        System.out.println("start");
        DD d1=new DD("key",0, KeyEvent.VK_Z,2000L, 2000L);
        d1.start();
//            DD d2=new DD("mouse",0,InputEvent.BUTTON3_MASK,new Long(9000));
//            d2.start();
        DD d3=new DD("key",0,KeyEvent.VK_UP,10L,20000L);
        d3.start();

//            DD d5=new DD("key",0,KeyEvent.VK_X,10L,330L);
//            d5.start();

        DD d6=new DD("key",0,KeyEvent.VK_LEFT,1000L,300L);
        d6.start();


        DD d4=new DD("key",0,KeyEvent.VK_SPACE,1000L,60L);
        d4.start();
//            DD d5=new DD("key",0,KeyEvent.VK_2,new Long(7000));
//            d5.start();
//            DD d6=new DD("key",0,KeyEvent.VK_8,new Long(25000));
//            d6.start();
//            DD d7=new DD("key",0,KeyEvent.VK_1,new Long(22000));
//            d7.start();
//            DD d8=new DD("key",0,KeyEvent.VK_7,new Long(61000));
//            d8.start();
    }


    public static void main(String[] args){
        try {
            Robot robot = new Robot();
//            robot.mousePress(InputEvent.BUTTON3_MASK);
//            robot.mouseRelease(InputEvent.BUTTON3_MASK);
            Random r=new Random();
            Thread.sleep(1200+r.nextInt(600));
            while(true){
                Thread.sleep(200+r.nextInt(600));
                robot.mouseMove(1000+r.nextInt(20),530+r.nextInt(30));
                Thread.sleep(200+r.nextInt(300));
                robot.mouseMove(1000+r.nextInt(15),470+r.nextInt(20));
                Thread.sleep(100+r.nextInt(100));
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.

        } catch (AWTException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void mains(String[] args){
        System.out.println("Проигрование эмуляции начнется через 10 секунд");
        try {
            Thread.sleep(10L*1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<KeyPressed> lkps=new ArrayList<KeyPressed>();
        try {
            FileInputStream fis = new FileInputStream("temp.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            lkps = (List<KeyPressed>) oin.readObject();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
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
