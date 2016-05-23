package by.imix.razborImage.pointWork;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 11.01.14
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
public class Area implements Serializable {
    private Point beginPoint;
    private Point endPoint;


    Area(Integer bx, Integer by) {
        this.beginPoint=new Point(bx,by);
    }

    Area(Point beginPoint, Point endPoint) {
        this.beginPoint=beginPoint;
        this.endPoint=endPoint;
        parsePoint();
    }

    Area(Integer bx, Integer by, Integer ex, Integer ey) {
        this.beginPoint=new Point(bx,by);
        this.endPoint=new Point(ex,ey);
        parsePoint();
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public void setBeginPoint(Point beginPoint) {
        this.beginPoint = beginPoint;
        parsePoint();
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
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
        Point newPointB=new Point((beginPoint.getX()<endPoint.getX())?beginPoint.getX():endPoint.getX(),(beginPoint.getY()<endPoint.getY())?beginPoint.getY():endPoint.getY());
        Point newPointE=new Point((beginPoint.getX()>endPoint.getX())?beginPoint.getX():endPoint.getX(),(beginPoint.getY()>endPoint.getY())?beginPoint.getY():endPoint.getY());
        this.beginPoint=newPointB;
        this.endPoint=newPointE;
    }
}
