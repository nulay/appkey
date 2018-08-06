package by.imix.razborImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 14.01.14
 * Time: 13:10
 * To change this template use File | Settings | File Templates.
 */
public class PanelInfo extends JToolBar {
    private static final Logger _log = LoggerFactory.getLogger(PanelInfo.class);
    private GlobalService screen4;

    private JLabel labInfo;
    private JLabel labInfoImg;

    public PanelInfo(GlobalService screen4) {
        super("p4",JToolBar.VERTICAL);
        this.screen4=screen4;
        setAlignmentX(Component.LEFT_ALIGNMENT);

        setMinimumSize(new Dimension(220, 170));
        setMaximumSize(new Dimension(220, 170));
        setPreferredSize(new Dimension(220, 170));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));


        labInfo=new JLabel("");
        add(labInfo);

        labInfoImg=new JLabel("");
        add(labInfoImg);
    }

    public void setText(String text){
        labInfo.setText(text);
    }
    public void setTextInfoImg(String text){
        labInfoImg.setText(text);
    }
}
