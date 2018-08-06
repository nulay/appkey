package by.imix.razborImage;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 31.12.13
 * Time: 10:38
 * To change this template use File | Settings | File Templates.
 */
public class DD extends Thread{
    private String huDvig;
    private Integer counPovtorov;
    private Integer numKey;
    private Long timePovt;
    private Long relese;

    /**
     * @param huDvig - чем управлять "mouse" или "key"
     * @param counPovtorov  - количество повторов, 0 - бесконечно
     * @param numKey  - номер Event клавиши или кнопки мыши
     * @param timePovt  - время через которое нужно повторять нажатие
     * @param relese время через которое отпустить
     */
    public DD(String huDvig, Integer counPovtorov, Integer numKey, Long timePovt, Long relese) {
        this.huDvig=huDvig;
        this.counPovtorov=counPovtorov;
        this.numKey=numKey;
        this.timePovt=timePovt;
        this.relese=relese;
    }

    public void run() {
        if(counPovtorov==0){
            while(true){
                try {
                    Thread.sleep(timePovt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Robot robot = new Robot();
                    if(huDvig.equals("mouse")){
                        robot.mousePress(numKey);
                        if (relese!=null){
                            try {
                                Thread.sleep(relese);
                            } catch (InterruptedException e) {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                        }
                        robot.mouseRelease(numKey);
                    }else{
                        robot.keyPress(numKey);
                        if (relese!=null){
                            try {
                                Thread.sleep(relese);
                            } catch (InterruptedException e) {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                        }
                        robot.keyRelease(numKey);
                    }
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        }else{
            int i=0;
            while(i<counPovtorov){
                i++;
                try {
                    Thread.sleep(timePovt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Robot robot = new Robot();
                    if(huDvig.equals("mouse")){
                        robot.mousePress(numKey);
                        robot.mouseRelease(numKey);
                    }else{
                        robot.keyPress(numKey);
                        robot.keyRelease(numKey);
                    }
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}