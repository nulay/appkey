package by.imix.razborImage.poinBuilderWindow;

/**
 * Point Builder Frame
 * User: miha
 * Date: 31.12.13
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */

import by.imix.keyReader.GlobalCatcher;
import by.imix.keyReader.KeyCatcher;
import by.imix.keyReader.KeyRazbor;
import by.imix.razborImage.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author mutagen
 */
public class PoinBuilderFrame extends JFrame implements GlobalService {
    private static final Logger _log = LoggerFactory.getLogger(PoinBuilderFrame.class);

    private Set pointColor;
    private KeyRazbor ks;
    private java.util.List<Point> listO = new ArrayList<Point>();

    protected AppClss clss;

    protected GlobalCatcher glklE;
    protected FileOperation fo;

    protected Integer keySt = null;


    private ToolsAction toolsAction;
    private JDesktopPane jdpDesktop;

    protected int keyinstr = 0;

    private PanelCreatePointWork panelCreatePointWork;

    private PanelToolsEmulation panelToolsEmulation;

    protected PanelScreenshot panelScreenshot;
    private PanelToolsFiltr panelToolsFiltr;

    protected PanelInfo panelInfo;

    public PoinBuilderFrame() {
        super("Point Builder");
//        setType(javax.swing.JFrame.Type.UTILITY);
        setName("Point Builder");
        getContentPane().setLayout(new BorderLayout());

        JPanel pWin = new JPanel(new BorderLayout());

        JPanel tools = new JPanel();
        tools.setBorder(ON_BORDER);
        tools.setLayout(new BoxLayout(tools, BoxLayout.Y_AXIS));

        panelCreatePointWork = new PanelCreatePointWork(this);
        tools.add(panelCreatePointWork);

//        panelToolsEmulation = new PanelToolsEmulation(this);
//        tools.add(panelToolsEmulation);


        panelToolsFiltr = new PanelToolsFiltr(this);
        tools.add(panelToolsFiltr);

        panelScreenshot = new PanelScreenshot(this);
        tools.add(panelScreenshot);

        panelInfo = new PanelInfo(this);
        tools.add(panelInfo);

        add(new JScrollPane(tools, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.EAST);

//        pWin.add(jpButtontop,BorderLayout.NORTH);


        add(pWin, BorderLayout.CENTER);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (ks != null)
                    ks.unregisterNativeHook();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

//        f.pack();
        setSize(1040, 780);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create and Set up the GUI.
        jdpDesktop = new JDesktopPane();
        // A specialized layered pane to be used with JInternalFrames
        //createFrame(); // Create first window
        pWin.add(jdpDesktop, BorderLayout.CENTER);
        // Make dragging faster by setting drag mode to Outline
        jdpDesktop.putClientProperty("JDesktopPane.dragMode", "outline");
        this.setTitle("Poin Builder");
//        grabScreen();

    }

    public ToolsEmulation getToolEmulation() {
        return panelCreatePointWork;
    }

    public void searthPoint() {
        listO.clear();
        for (int x = 0; x < cI.getWidth(); x++) {
            for (int y = 0; y < cI.getHeight(); y++) {
                int[] rgb = cI.getRaster().getPixel(x, y, new int[3]);
                if (issueColor(rgb)) {
                    listO.add(new Point(x, y));
                }
            }
        }
    }

    public boolean issueColor(int[] rgb) {
        for (Object rgbw : pointColor) {
            int[] rgbSh = (int[]) rgbw;
            if (rgb[0] == rgbSh[0] && rgb[1] == rgbSh[1] && rgb[2] == rgbSh[2]) {
                return true;
            }
        }
        return false;
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new PoinBuilderFrame();
    }

    private BufferedImage cI = null;
    protected ImagePanel curimg = null;
    private int numFr = 0;


    public void grabScreen() {
        BufferedImage cId = null;
        try {
            Robot robot = new Robot();
            cId = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        } catch (AWTException ex) {
        }
        openNewScrenPan(cId);


//        jif.repaint();
//        jifList.add(jif);
//        jifListName.add("Win#" + numFr);
//        listObj2.setListData(jifListName);
        //jlp.repaint();
    }

    public void openNewScrenPan(BufferedImage bi) {
        cI = bi;
        numFr++;
        ImageFrame imF = new ImageFrame(this, numFr + "", cI);
        jdpDesktop.add(imF);


    }

    public void stopPoehali() {
        clss.setKeyEnd(true);
    }

    public void startRazbor() {
        try {
            Robot robot = new Robot();
            cI = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        } catch (AWTException ex) {
            _log.error(ex.getMessage());
        }
//        for(Filtr f: groupFiltr.getListFiltr()){
//            f.isCorrect(cI);
//        }
    }

    @Override
    public void setPointColor(Set pointColor) {
        this.pointColor = pointColor;
    }

    @Override
    public void setCurimg(ImagePanel imagePanel) {
        this.curimg = imagePanel;
    }

    @Override
    public ImagePanel getCurimg() {
        return curimg;
    }

    @Override
    public int getKeyinstr() {
        return keyinstr;
    }

    @Override
    public void setKeyinstr(int keyinstr) {
        this.keyinstr = keyinstr;
    }

    @Override
    public Set getPointColor() {
        return pointColor;
    }

    @Override
    public PanelToolsEmulation getPanelToolsEmulation() {
        return panelToolsEmulation;
    }

    @Override
    public PanelToolsFiltr getPanelToolsFiltr() {
        return panelToolsFiltr;
    }

    @Override
    public PanelInfo getPanelInfo() {
        return panelInfo;
    }

    @Override
    public PanelScreenshot getPanelScreenshot() {
        return panelScreenshot;
    }

    @Override
    public Integer getKeySt() {
        return keySt;
    }

    @Override
    public void setKeySt(Integer keySt) {
        this.keySt = keySt;
    }

    @Override
    public AppClss getClss() {
        return clss;
    }

    @Override
    public void setClss(AppClss clss) {
        this.clss = clss;
    }

    @Override
    public GlobalCatcher getGlklE() {
        return glklE;
    }

    @Override
    public void setGlklE(GlobalCatcher glklE) {
        this.glklE = glklE;
    }

    @Override
    public FileOperation getFileOperation() {
        if(fo==null){
            fo=new FileOperation();
        }
        return fo;
    }

    @Override
    public void setFileOperation(FileOperation fo) {
        this.fo = fo;
    }
}

