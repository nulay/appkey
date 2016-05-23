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
   
    private ImagePanel img;
    private ToolsAction toolsAction;
    private GlobalFrame globalFrame;

    public ImageFrame(GlobalFrame globalFrame, String numFr, BufferedImage cI) {
        super("Win#"+numFr,true,true,true);
        
        this.globalFrame=globalFrame;

        setLayout(new BorderLayout());
        JPanel jlp=new JPanel(new BorderLayout());

        img=new ImagePanel(cI);
        globalFrame.setCurimg(img);

//        JScrollPane js=new JScrollPane(img);
        jlp.add(new JScrollPane(img));    //   ,JLayeredPane.DEFAULT_LAYER
        img.setBounds(0,0,Double.valueOf(img.getPreferredSize().getWidth()).intValue(),Double.valueOf(img.getPreferredSize().getHeight()).intValue());

        add(jlp, BorderLayout.CENTER);

        toolsAction=new ToolsAction(cI,globalFrame);
        img.addMouseListener(toolsAction);
        img.addMouseMotionListener(toolsAction);

        setSize(400, 300);
        setVisible(true);

        this.addInternalFrameListener(this);


        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }

        globalFrame.getPanelScreenshot().addFrame(this);

    }

    public ImagePanel getImgPanel() {
        return img;
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        globalFrame.getPanelScreenshot().removeFrame(e.getSource());
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
        globalFrame.getPanelScreenshot().setSelectedEl((ImageFrame)e.getSource());
        globalFrame.getPanelInfo().setTextInfoImg("<html>Ширина:"+img.getImage().getWidth()+"<br>Высота:"+img.getImage().getHeight()+"</html>" );
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
//                _log.info("sdf");
    }
}
