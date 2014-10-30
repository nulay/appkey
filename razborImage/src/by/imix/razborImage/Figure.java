package by.imix.razborImage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ирина
 * Date: 10.01.14
 * Time: 22:18
 * To change this template use File | Settings | File Templates.
 */
public class Figure extends JPanel {
    private java.util.List<MyRect> myRectList;

//    public static final Integer RECT=1;
//    public static final Integer OVAL=0;


// параметры: цвет и тип фигуры

    Figure() {
        setOpaque(false);
        myRectList=new ArrayList<MyRect>();
//        MyRect r=new MyRect(10,10,40,40);
//        myRectList.add(r);
//        setBounds(r.getBx(), r.getBy(), r.getWidth(),r.getHeight());
        //setBounds(beg.getX(), beg.getY(), end.getX() - beg.getX(), end.getY() - beg.getY());
    }

    @Override
    public void paintComponent(Graphics g) {
// прорисовка фигуры
        super.paintComponent(g);
        Color oldColor=g.getColor();
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.BLACK);

        float[] dashl = {10, 20};
        BasicStroke pen1 = new BasicStroke(20, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 20, dashl, 0);
        g2.setStroke(pen1);

        for (MyRect mr : myRectList) {
            g2.fillRect(mr.getBeginPoint().getX(), mr.getBeginPoint().getY(), mr.getWidth(), mr.getHeight());
        }

        g2.setColor(oldColor);
    }

    public boolean addRect(MyRect myRect){
        boolean k=myRectList.add(myRect);
        this.repaintNew(myRect);
        return k;
    }

    public boolean removeRect(MyRect myRect){
        return myRectList.remove(myRect);
    }

    public List<MyRect> getMyRectList() {
        return myRectList;
    }

    public void setMyRectList(List<MyRect> myRectList) {
        this.myRectList = myRectList;
    }

    public void repaintNew(MyRect mr) {
        setBounds(mr.getBeginPoint().getX(),mr.getBeginPoint().getY(),mr.getWidth(),mr.getHeight());
        this.repaint();
       // repaint();
    }
}

