package by.imix.razborImage;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 14.01.14
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
public class ImageFrame extends JInternalFrame implements InternalFrameListener {
    private final Screen4 screen4;
    private ImagePanel img;
    private ToolsAction toolsAction;

    public ImageFrame(Screen4 screen4, String numFr, BufferedImage cI) {
        super("Win#"+numFr,true,true,true);
        this.screen4=screen4;

        setLayout(new BorderLayout());
        JPanel jlp=new JPanel(new BorderLayout());

        img=new ImagePanel(cI);
        screen4.curimg=img;

//        JScrollPane js=new JScrollPane(img);
        jlp.add(new JScrollPane(img));    //   ,JLayeredPane.DEFAULT_LAYER
        img.setBounds(0,0,Double.valueOf(img.getPreferredSize().getWidth()).intValue(),Double.valueOf(img.getPreferredSize().getHeight()).intValue());

        add(jlp, BorderLayout.CENTER);

        toolsAction=new ToolsAction(cI,screen4);
        img.addMouseListener(toolsAction);
        img.addMouseMotionListener(toolsAction);

        setSize(400, 300);
        setVisible(true);

        this.addInternalFrameListener(this);


        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }

        screen4.panelScreenshot.addFrame(this);

    }

    public ImagePanel getImgPanel() {
        return img;
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        screen4.panelScreenshot.removeFrame(e.getSource());
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {
//               _log.info("sdf");
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {
//                _log.info("sdf");
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
        screen4.panelScreenshot.setSelectedEl((ImageFrame)e.getSource());
        screen4.panelInfo.setTextInfoImg("<html>Ширина:"+img.getImage().getWidth()+"<br>Высота:"+img.getImage().getHeight()+"</html>" );
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
//                _log.info("sdf");
    }
}
