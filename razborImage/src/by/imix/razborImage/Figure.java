package by.imix.razborImage;

import by.imix.razborImage.pointWork.Area;

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
    private java.util.List<Area> areaList;

//    public static final Integer RECT=1;
//    public static final Integer OVAL=0;


// параметры: цвет и тип фигуры

    Figure() {
        setOpaque(false);
        areaList =new ArrayList<Area>();
//        Area r=new Area(10,10,40,40);
//        areaList.add(r);
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

        for (Area mr : areaList) {
            g2.fillRect(mr.getBeginPoint().getX(), mr.getBeginPoint().getY(), mr.getWidth(), mr.getHeight());
        }

        g2.setColor(oldColor);
    }

    public boolean addRect(Area area){
        boolean k= areaList.add(area);
        this.repaintNew(area);
        return k;
    }

    public boolean removeRect(Area area){
        return areaList.remove(area);
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public void repaintNew(Area mr) {
        setBounds(mr.getBeginPoint().getX(),mr.getBeginPoint().getY(),mr.getWidth(),mr.getHeight());
        this.repaint();
       // repaint();
    }
}

