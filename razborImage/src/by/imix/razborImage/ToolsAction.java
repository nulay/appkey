package by.imix.razborImage;

import by.imix.razborImage.filters.ColorInRectFilter;
import by.imix.razborImage.filters.FullConcurrenceRect;
import by.imix.razborImage.pointWork.Area;
import by.imix.razborImage.pointWork.Point;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 13.01.14
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */
public class ToolsAction implements MouseListener, MouseMotionListener {

    private BufferedImage bi;
    private Point bound;
    private GlobalFrame globalFrame;

    public ToolsAction(BufferedImage bi, GlobalFrame globalFrame) {
        this.bi = bi;
        this.globalFrame=globalFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(globalFrame.getKeyinstr()==1 | globalFrame.getKeyinstr()==3){
            bound=new Point(e.getX(),e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(globalFrame.getKeyinstr()==1){
            Area mr=new Area(bound.getX(), bound.getY(), e.getX(), e.getY());
            ((ImagePanel)e.getSource()).addRect(mr);
            BufferedImage r=bi.getSubimage(mr.getBeginPoint().getX(), mr.getBeginPoint().getY(), mr.getWidth(), mr.getHeight());
            FullConcurrenceRect fcr=new FullConcurrenceRect(mr,r);
            globalFrame.getPanelToolsFiltr().addComponentFiltr(fcr);
            bound=null;
//                jlp.repaint();
        }
        if(globalFrame.getKeyinstr()==3){
            Area mr=new Area(bound.getX(), bound.getY(), e.getX(), e.getY());
            ((ImagePanel)e.getSource()).addRect(mr);
            //BufferedImage r=bi.getSubimage(mr.getBeginPoint().getX(), mr.getBeginPoint().getY(), mr.getWidth(), mr.getHeight());
            ColorInRectFilter fcr=new ColorInRectFilter(mr,globalFrame.getPointColor());
            globalFrame.getPanelToolsFiltr().addComponentFiltr(fcr);
            bound=null;
        }
        if(globalFrame.getKeyinstr()==2){
            int x = e.getX();
            int y = e.getY();

            WritableRaster wr=bi.getRaster();
            int[] rgb0 = wr.getPixel(x, y, new int[3]);
            int[] rgb1 = wr.getPixel(x-1, y-1, new int[3]);
            int[] rgb2 = wr.getPixel(x-1, y, new int[3]);
            int[] rgb3 = wr.getPixel(x-1, y+1, new int[3]);
            int[] rgb4 = wr.getPixel(x, y-1, new int[3]);
            int[] rgb5 = wr.getPixel(x, y+1, new int[3]);
            int[] rgb6 = wr.getPixel(x+1, y-1, new int[3]);
            int[] rgb7 = wr.getPixel(x+1, y, new int[3]);
            int[] rgb8 =wr.getPixel(x+1, y-1, new int[3]);
            java.util.Set pointColor=new HashSet();
            addPointinPC(pointColor,rgb0);
            addPointinPC(pointColor,rgb1);
            addPointinPC(pointColor,rgb2);
            addPointinPC(pointColor,rgb3);
            addPointinPC(pointColor,rgb4);
            addPointinPC(pointColor,rgb5);
            addPointinPC(pointColor,rgb6);
            addPointinPC(pointColor,rgb7);
            addPointinPC(pointColor,rgb8);

            globalFrame.setPointColor(pointColor);

            ((ImagePanel)e.getSource()).drawKrest(x,y);

            globalFrame.getCurimg().redrawImg(bi);
            JDialog dialog = new JDialog();
            JPanel p=new JPanel();
            p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
            for(Object rgbw:pointColor){
                int[] rgb= (int[]) rgbw;
                p.add(new JLabel("X = " + x + " Y = " + y + " RGB = " + rgb[0] + ":" + rgb[1] + ":" + rgb[2]));
            }
            dialog.add(p);
            dialog.pack();
            globalFrame.setKeyinstr(3);
            dialog.setVisible(true);
        }
    }

    public void addPointinPC(java.util.Set pointColor,int[] point){
        if(pointColor==null){
            pointColor=new HashSet();
        }
        boolean key=false;
        for(Object pc:pointColor) {
            int[] pci= (int[]) pc;
            if(pci[0]==point[0] && pci[1]==point[1] && pci[2]==point[2]){
                key=true;break;
            }
        }
        if(!key){
             pointColor.add(point);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        globalFrame.getPanelInfo().setText("X:"+e.getX()+"; Y:"+e.getY());
    }
}
