package by.imix.razborImage;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 11.01.14
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
public class MyRect implements Serializable {
    private MyPoint beginPoint;
    private MyPoint endPoint;


    MyRect(Integer bx, Integer by) {
        this.beginPoint=new MyPoint(bx,by);
    }

    MyRect(MyPoint beginPoint, MyPoint endPoint) {
        this.beginPoint=beginPoint;
        this.endPoint=endPoint;
        parsePoint();
    }

    MyRect(Integer bx, Integer by,Integer ex,Integer ey) {
        this.beginPoint=new MyPoint(bx,by);
        this.endPoint=new MyPoint(ex,ey);
        parsePoint();
    }

    public MyPoint getBeginPoint() {
        return beginPoint;
    }

    public void setBeginPoint(MyPoint beginPoint) {
        this.beginPoint = beginPoint;
        parsePoint();
    }

    public MyPoint getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(MyPoint endPoint) {
        this.endPoint = endPoint;
        parsePoint();
    }

    public int getWidth() {
        return endPoint.getX()-beginPoint.getX();
    }

    public int getHeight() {
        return endPoint.getY()-beginPoint.getY();
    }

    public void parsePoint(){
        MyPoint newPointB=new MyPoint((beginPoint.getX()<endPoint.getX())?beginPoint.getX():endPoint.getX(),(beginPoint.getY()<endPoint.getY())?beginPoint.getY():endPoint.getY());
        MyPoint newPointE=new MyPoint((beginPoint.getX()>endPoint.getX())?beginPoint.getX():endPoint.getX(),(beginPoint.getY()>endPoint.getY())?beginPoint.getY():endPoint.getY());
        this.beginPoint=newPointB;
        this.endPoint=newPointE;
    }
}
