package by.imix.razborImage;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 11.01.14
 * Time: 11:33
 * To change this template use File | Settings | File Templates.
 */
public class MyPoint implements Serializable {
    private int x,y;

    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
