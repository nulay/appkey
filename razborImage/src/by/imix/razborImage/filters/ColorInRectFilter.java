package by.imix.razborImage.filters;

import by.imix.keyReader.ObKeyPressed;
import by.imix.razborImage.MyRect;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Ирина
 * Date: 12.01.14
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
public class ColorInRectFilter implements Filtr, Serializable {
//    private Logger _log=Logger.getLogger(ColorInRectFilter.class);
    private java.util.Set pointColor;
    private MyRect rect;
    private String name;
    private boolean keyPrisut=true;//искать присутствие цвета или отсутствие по умолчанию присутствие
    private ObKeyPressed obKeyPressed;
    private boolean keyRootStart;

    public ColorInRectFilter(MyRect rect, Set pointColor) {
        this.pointColor = pointColor;
        this.rect = rect;
    }

    public ColorInRectFilter(MyRect rect, Set pointColor, boolean keyPrisut) {
        this.pointColor = pointColor;
        this.rect = rect;
        this.keyPrisut=keyPrisut;
    }

    @Override
    public boolean isCorrect(BufferedImage image) {
        BufferedImage ni=image.getSubimage(rect.getBeginPoint().getX(),rect.getBeginPoint().getY(),rect.getWidth(),rect.getHeight());

        for (int x = 0; x < ni.getWidth(); x++) {
            for (int y = 0; y < ni.getHeight(); y++) {
                int[] rgb = ni.getRaster().getPixel(x, y, new int[3]);
                if (issueColor(rgb)) {
//                    _log.info("Цвет в оласти присутствует");
                    return keyPrisut;
                }
            }
        }
//        _log.info("Цвет в оласти отсутствует");
        return !keyPrisut;
    }

    @Override
    public ObKeyPressed getObKeyPressed() {
        return obKeyPressed;
    }

    @Override
    public void setObKeyPressed(ObKeyPressed obkp) {
        this.obKeyPressed=obkp;
    }

    public boolean issueColor(int[] rgb){
        for(Object rgbw:pointColor){
            int[] rgbSh= (int[]) rgbw;
            if(rgb[0]==rgbSh[0] && rgb[1]==rgbSh[1] && rgb[2]==rgbSh[2]){
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyRect getRect() {
        return rect;
    }

    public void setRect(MyRect rect) {
        this.rect = rect;
    }

    public Set getPointColor() {
        return pointColor;
    }

    public void setPointColor(Set pointColor) {
        this.pointColor = pointColor;
    }

    public boolean isKeyPrisut() {
        return keyPrisut;
    }

    public void setKeyPrisut(boolean keyPrisut) {
        this.keyPrisut = keyPrisut;
    }

    @Override
    public String toString() {
        return getName();
    }

    public void setKeyRootStart(boolean keyRootStart) {
        this.keyRootStart = keyRootStart;
    }

    public boolean isKeyRootStart() {
        return keyRootStart;
    }
}
