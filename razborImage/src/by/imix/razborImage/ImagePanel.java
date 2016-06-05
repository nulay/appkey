package by.imix.razborImage;

import by.imix.razborImage.pointWork.Area;
import by.imix.razborImage.pointWork.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Panel for work with image
 * User: miha
 * Date: 11.01.14
 * Time: 12:59
 * To change this template use File | Settings | File Templates.
 */
public class ImagePanel extends JPanel {

    java.util.List<Point> listSelKr=new ArrayList<Point>();
    private java.util.List<Area> areaList =new ArrayList<Area>();

    private Color krestColor=Color.DARK_GRAY;

    private BufferedImage bi;

    public ImagePanel(BufferedImage bi) {
        this.bi = bi;
//            add(new JLabel("sdf"));
//            setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color oldColor=g.getColor();
        Graphics2D g2 = (Graphics2D) g;
        Stroke oldStroke=g2.getStroke();
//            Dimension dim = this.getPreferredSize();
        g2.drawImage(bi, null, WIDTH, HEIGHT);

        g2.setColor(krestColor);
        for(Point mp:listSelKr){
            g2.drawLine(mp.getX(),mp.getY()-5,mp.getX(),mp.getY()+5);
            g2.drawLine(mp.getX()-5,mp.getY(),mp.getX()+5,mp.getY());
        }

        float[] dashl = {4, 2};
        BasicStroke pen1 = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 2, dashl, 0);
        g2.setStroke(pen1);

        for (Area mr : areaList) {
            g2.drawRect(mr.getBeginPoint().getX(), mr.getBeginPoint().getY(), mr.getWidth(), mr.getHeight());
        }

        g2.setColor(Color.WHITE);
        float[] dash2 = {2, 4};
        BasicStroke pen2 = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 2, dash2, 0);
        g2.setStroke(pen2);

        for (Area mr : areaList) {
            g2.drawRect(mr.getBeginPoint().getX(), mr.getBeginPoint().getY(), mr.getWidth(), mr.getHeight());
        }

        g2.setStroke(oldStroke);
        g.setColor(oldColor);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(bi.getWidth(), bi.getHeight());
    }

    public void redrawImg(BufferedImage bi){
        this.bi=bi;
        this.repaint();
    }

    public boolean drawKrest(int x, int y) {
        boolean k=listSelKr.add(new Point(x,y));
        this.repaint();
        return k;
    }

    public boolean addRect(Area area){
        boolean k= areaList.add(area);
        this.repaint();
        return k;
    }

    public boolean removeRect(Area area){
        boolean k= areaList.remove(area);
        this.repaint();
        return k;
    }

    public java.util.List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(java.util.List<Area> areaList) {
        this.areaList = areaList;
    }

    public BufferedImage getImage() {
        return bi;
    }
}