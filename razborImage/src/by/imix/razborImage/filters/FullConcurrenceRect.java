package by.imix.razborImage.filters;

import by.imix.keyReader.ObKeyPressed;
import by.imix.razborImage.pointWork.Area;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 11.01.14
 * Time: 13:10
 * To change this template use File | Settings | File Templates.
 */
public class FullConcurrenceRect implements Filtr, Serializable{
//    private Logger _log=Logger.getLogger(FullConcurrenceRect.class);
    private Area rect;
    // private BufferedImage rectImage;
    private String name;
    private int[] massRGB;
    private ObKeyPressed obkp;
    private boolean keyRootStart;

    public FullConcurrenceRect(Area rect, BufferedImage rectImage) {
        this.rect = rect;
        setRectImage(rectImage);
        //this.rectImage = rectImage;
    }

    public boolean isCorrect(BufferedImage image){
        BufferedImage ni=image.getSubimage(rect.getBeginPoint().getX(),rect.getBeginPoint().getY(),rect.getWidth(),rect.getHeight());
        return (bufferedImagesEqual(ni.getRGB(0,0,ni.getWidth(),ni.getHeight(),new int[ni.getWidth()*ni.getHeight()],0,0), massRGB));
    }

    public boolean bufferedImagesEqual(int[] img1, int[] img2) {
        for (int x = 0; x < img1.length; x++) {
                if(img1[x]!=img2[x]){
                    return false;
                }
        }
        return true;
    }

    public boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight() ) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    if (img1.getRGB(x, y) != img2.getRGB(x, y) ){
//                        _log.info("Облости не идентичны");
                        return false;
                    }
                }
            }
        }else {
//            _log.info("Облости не идентичны");
            return false;
        }
//        _log.info("Облости идентичны");
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public Area getRect() {
        return rect;
    }

    public void setRect(Area rect) {
        this.rect = rect;
    }

    public BufferedImage getRectImage() {
        return getImage();
    }

    public void setRectImage(BufferedImage rectImage) {
        massRGB=rectImage.getRGB(0,0,rectImage.getWidth(),rectImage.getHeight(),new int[rectImage.getWidth()*rectImage.getHeight()],0,0);

    }

    public int[] getMassRGB() {
        return massRGB;
    }

    public void setMassRGB(int[] massRGB) {
        this.massRGB = massRGB;

    }

    private BufferedImage getImage(){
        BufferedImage image=new BufferedImage(rect.getWidth(),rect.getHeight(), BufferedImage.TYPE_INT_RGB);
        image.setRGB(0,0,rect.getWidth(),rect.getHeight(),massRGB,0,0);
        return image;
    }

    public ObKeyPressed getObKeyPressed() {
        return obkp;
    }

    public void setObKeyPressed(ObKeyPressed obkp) {
        this.obkp = obkp;
    }

    public void setKeyRootStart(boolean keyRootStart) {
        this.keyRootStart = keyRootStart;
    }

    public boolean isKeyRootStart() {
        return keyRootStart;
    }
}
