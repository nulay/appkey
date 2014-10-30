package by.imix.razborImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 11.01.14
 * Time: 12:59
 * To change this template use File | Settings | File Templates.
 */
public class ImagePanel extends JPanel {

    java.util.List<MyPoint> listSelKr=new ArrayList<MyPoint>();
    private java.util.List<MyRect> myRectList=new ArrayList<MyRect>();

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
        for(MyPoint mp:listSelKr){
            g2.drawLine(mp.getX(),mp.getY()-5,mp.getX(),mp.getY()+5);
            g2.drawLine(mp.getX()-5,mp.getY(),mp.getX()+5,mp.getY());
        }

        float[] dashl = {4, 2};
        BasicStroke pen1 = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 2, dashl, 0);
        g2.setStroke(pen1);

        for (MyRect mr : myRectList) {
            g2.drawRect(mr.getBeginPoint().getX(), mr.getBeginPoint().getY(), mr.getWidth(), mr.getHeight());
        }

        g2.setColor(Color.WHITE);
        float[] dash2 = {2, 4};
        BasicStroke pen2 = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 2, dash2, 0);
        g2.setStroke(pen2);

        for (MyRect mr : myRectList) {
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
        boolean k=listSelKr.add(new MyPoint(x,y));
        this.repaint();
        return k;
    }

    public boolean addRect(MyRect myRect){
        boolean k=myRectList.add(myRect);
        this.repaint();
        return k;
    }

    public boolean removeRect(MyRect myRect){
        boolean k=myRectList.remove(myRect);
        this.repaint();
        return k;
    }

    public java.util.List<MyRect> getMyRectList() {
        return myRectList;
    }

    public void setMyRectList(java.util.List<MyRect> myRectList) {
        this.myRectList = myRectList;
    }

    public BufferedImage getImage() {
        return bi;
    }
}