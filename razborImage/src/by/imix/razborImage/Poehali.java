package by.imix.razborImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 03.01.14
 * Time: 13:51
 * To change this template use File | Settings | File Templates.
 */
public class Poehali extends Thread {
    private static final Logger _log = LoggerFactory.getLogger(Screen4.class);
    private Integer numKey;
    private Long relese;
    public Poehali(Integer numKey, Long relese) {
        this.numKey=numKey;
        this.relese=relese;
    }

    public void run() {
        _log.debug("Try press keyNum:" + numKey);
        try {
            Robot robot = new Robot();
            robot.keyPress(numKey);
            Thread.sleep(relese);
            robot.keyRelease(numKey);
        } catch (InterruptedException e) {
            e.printStackTrace();
            _log.debug("Error numKey is:"+ numKey);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
