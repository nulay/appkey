package by.imix.botTank;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 03.01.14
 * Time: 13:51
 * To change this template use File | Settings | File Templates.
 */
public class Poehali extends Thread {
    private Integer numKey;
    private Long relese;
    public Poehali(Integer numKey, Long relese) {
        this.numKey=numKey;
        this.relese=relese;
    }

    public void run() {
        try {
            Robot robot = new Robot();
            robot.keyPress(numKey);
            Thread.sleep(relese);
            robot.keyRelease(numKey);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (AWTException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
