package by.imix.razborImage;

import by.imix.botTank.AppClss;
import by.imix.keyReader.GlobalKeyListenerExample;

import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Set;

/**
 * Created by miha on 22.05.2016.
 */
public interface GlobalService {
    BevelBorder ON_BORDER = new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY,Color.GRAY);
    BevelBorder OFF_BORDER = new BevelBorder(BevelBorder.RAISED,Color.LIGHT_GRAY,Color.GRAY);

    Integer ZAHVAT = 1;
    Integer PLAY = 2;
    Integer REC = 3;
    Integer AUTO_ZAHVAT = 4;

    PanelToolsFiltr getPanelToolsFiltr();
    PanelInfo getPanelInfo();
    PanelScreenshot getPanelScreenshot();
    void openNewScrenPan(BufferedImage bufferedImage);

    void setKeySt(Integer keySt);
    Integer getKeySt();

    AppClss getClss();
    void setClss(AppClss clss);

    GlobalKeyListenerExample getGlklE();
    void setGlklE(GlobalKeyListenerExample glklE);

    FileOperation getFileOperation();
    void setFileOperation(FileOperation fo);

    void setCurimg(ImagePanel imagePanel);
    ImagePanel getCurimg();

    int getKeyinstr();
    void setKeyinstr(int keyinstr);

    Set getPointColor();
    void setPointColor(Set pointColor);

    void grabScreen();

    void startRazbor();

    ToolsEmulation getToolEmulation();
}
