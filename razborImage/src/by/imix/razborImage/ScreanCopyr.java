package by.imix.razborImage;

import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class service for screen copy
 * User: miha
 * Date: 08.01.14
 * Time: 9:55
 * To change this template use File | Settings | File Templates.
 */
public class ScreanCopyr {
    private Logger _log=Logger.getLogger(Screen4.class);
    private int countCopy=10;
    private Long timeNextCopy=3000L;
    private BufferedImages bufferedImages;

    public ScreanCopyr() {
         bufferedImages=new BufferedImages();
    }

    public ScreanCopyr(int countCopy,Long timeNextCopy) {
        this();
        this.countCopy=countCopy;
        this.timeNextCopy=timeNextCopy;
    }

    public BufferedImages stratScreanCopyr(){
        int i=0;
        if(i<countCopy){
            try {
                Thread.sleep(timeNextCopy);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bufferedImages.addImage(copyImage());
            i++;
        }
        return bufferedImages;
    }

    public BufferedImage copyImage(){
        try {
            Robot robot = new Robot();
            return robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        } catch (AWTException ex) {
            _log.error("Не удалось снять скриншот");
            return null;
        }
    }
}
