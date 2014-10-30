package by.imix.razborImage.filters;

import by.imix.keyReader.ObKeyPressed;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 11.01.14
 * Time: 13:08
 * To change this template use File | Settings | File Templates.
 */
public interface Filtr extends ComponentFiltrov,Serializable {

    boolean isCorrect(BufferedImage image);
    ObKeyPressed getObKeyPressed();
    void setObKeyPressed(ObKeyPressed obkp);
    void setKeyRootStart(boolean keyRootStart);
    public boolean isKeyRootStart();
}
