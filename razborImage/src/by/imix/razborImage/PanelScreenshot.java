package by.imix.razborImage;

import by.imix.keyReader.EventStop;
import by.imix.keyReader.KeyRazbor;
import by.imix.razborImage.algoritm.Searther;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Panel for capture screen
 * User: miha
 * Date: 13.01.14
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
public class PanelScreenshot extends JToolBar implements FocusListener, ActionListener, EventStop {
    private Logger _log=Logger.getLogger(PanelScreenshot.class);
    
    GlobalService globalFrame;
    private JList listScr;

    private Vector<ImageFrame> jifList;
    private Vector<String> jifListName;

    private JFileChooser saverFileFiltr;
    private JFileChooser openerFile;

    private JButton but1HandZahvat;
    private JButton but2AutoZahvat;
    private JButton but3SaveScreen;
    private JButton but4openScreen;

    private JButton test;

    private KeyRazbor ks;

    public PanelScreenshot(GlobalService globalFrame) {
        super("p3",JToolBar.VERTICAL);
        setAlignmentX(Component.LEFT_ALIGNMENT);       
        this.globalFrame=globalFrame;

        JPanel rPanel=new JPanel();
        rPanel.setLayout(new BoxLayout(rPanel,BoxLayout.Y_AXIS));

        setMinimumSize(new Dimension(220, 170));
        setMaximumSize(new Dimension(220, 170));
        setPreferredSize(new Dimension(220, 170));

        JLabel lab=new JLabel("Скриншоты");
        setAlignmentX(Component.LEFT_ALIGNMENT);
        rPanel.add(lab);

        jifList=new Vector<ImageFrame>();
        jifListName=new Vector<String>();

        listScr=new JList(jifListName);

//        listScr.setMinimumSize(new Dimension(170,120));
//        listScr.setPreferredSize(new Dimension(170,120));
//        listScr.setMaximumSize(new Dimension(170,120));
        rPanel.add(new JScrollPane(listScr, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

        listScr.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(((JList)e.getSource()).getSelectedIndex() != -1){
                    try {
                        jifList.get(((JList)e.getSource()).getSelectedIndex()).setSelected(true);
                    } catch (PropertyVetoException e1) {
                    }
                }
            }
        });

        but1HandZahvat=new JButton(new ImageIcon("images/play.png"));
        but1HandZahvat.setName("but1HandZahvat");
        but1HandZahvat.setBorder(globalFrame.OFF_BORDER);
        but1HandZahvat.setToolTipText("Auto capture");
        but2AutoZahvat=new JButton(new ImageIcon("images/play.png"));
        but2AutoZahvat.setName("but2AutoZahvat");
        but2AutoZahvat.setToolTipText("Capture screen");
        but2AutoZahvat.setBorder(globalFrame.OFF_BORDER);
        but3SaveScreen=new JButton(new ImageIcon("images/save.png"));
        but3SaveScreen.setToolTipText("Save screen");
        but3SaveScreen.setName("but3SaveScreen");
        but3SaveScreen.setBorder(globalFrame.OFF_BORDER);
        but4openScreen=new JButton(new ImageIcon("images/folder.png"));
        but4openScreen.setName("but4openScreen");
        but4openScreen.setBorder(globalFrame.OFF_BORDER);
        but4openScreen.setToolTipText("openScreen");

        test=new JButton(new ImageIcon("images/play.png"));
        test.setToolTipText("TestButton");
        test.setName("test");
        test.setBorder(globalFrame.OFF_BORDER);

        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Searther sea=new Searther();
                sea.equilsTwoOb(jifList.get(0).getImgPanel().getImage(),jifList.get(1).getImgPanel().getImage());
            }
        });

        JPanel panforBut=new JPanel(new  FlowLayout());
        panforBut.add(but1HandZahvat);
        panforBut.add(but2AutoZahvat);
        panforBut.add(but3SaveScreen);
        panforBut.add(but4openScreen);

        panforBut.add(test);

        but1HandZahvat.addActionListener(this);
        but2AutoZahvat.addActionListener(this);
        but3SaveScreen.addActionListener(this);
        but4openScreen.addActionListener(this);

        rPanel.add(panforBut);

        this.addFocusListener(this);
        add(rPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(((JButton)e.getSource()).getName().equals("but3SaveScreen")){
            if(saverFileFiltr==null){
                saverFileFiltr=new JFileChooser();
                saverFileFiltr.setCurrentDirectory(new File("./"));
                saverFileFiltr.setDialogTitle("Сохранение скринов");
                saverFileFiltr.setFileSelectionMode(JFileChooser.FILES_ONLY);
                saverFileFiltr.addChoosableFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if(f.isDirectory()) return true;
                        return f.getName().endsWith("png");
                    }

                    @Override
                    public String getDescription() {
                        return "Файлы с записью эмуляции (*.png)";
                    }
                });
            }
            int res=saverFileFiltr.showSaveDialog((Component) globalFrame);
            if(res==JFileChooser.APPROVE_OPTION){
                File file=saverFileFiltr.getSelectedFile();
                if(!file.getName().endsWith(".png")){
                    file=new File(file.getParentFile(),file.getName()+".png");
                }

                try {
                    ImageIO.write(globalFrame.getCurimg().getImage(), "png", file);
                } catch (IOException e1) {
                    JOptionPane.showInternalMessageDialog((Component) globalFrame,"Не удалось сохранить файл","Ошибка!",JOptionPane.ERROR_MESSAGE);
                }

                JOptionPane.showMessageDialog((Component) globalFrame,"Файл сохранен");
            }
        }

        if(((JButton)e.getSource()).getName().equals("but4openScreen")){
            if (openerFile == null) {
                openerFile = new JFileChooser();
                openerFile.setDialogTitle("Открытие эмуляции");
                openerFile.setCurrentDirectory(new File("./"));
                openerFile.setMultiSelectionEnabled(true);
                openerFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
                openerFile.addChoosableFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if(f.isDirectory()) return true;
                        return f.getName().endsWith("png");
                    }

                    @Override
                    public String getDescription() {
                        return "Файлы с записью эмуляции (*.png)";
                    }
                });
            }
            int res = openerFile.showOpenDialog((Component) globalFrame);
            if (res == JFileChooser.APPROVE_OPTION) {
                File[] listF = openerFile.getSelectedFiles();
                for (File f : listF) {
                    BufferedImage bi= null;
                    try {
                        bi = ImageIO.read(f);
                    } catch (IOException e1) {
                        JOptionPane.showInternalMessageDialog((Component) globalFrame, "Не удалось считать файл " + f.getAbsolutePath(), "Ошибка!", JOptionPane.ERROR_MESSAGE);
                    }
                    globalFrame.openNewScrenPan(bi);
                }
            }
        }

        if(((JButton)e.getSource()).getName().equals("but1HandZahvat")){
            if(globalFrame.getKeySt()==null){
                ks = new KeyRazbor(this);
                but1HandZahvat.setBorder(globalFrame.ON_BORDER);
                globalFrame.setKeySt(GlobalService.ZAHVAT);
            }else{
                if(globalFrame.getKeySt()== Screen4.ZAHVAT){
                    ks.unregisterNativeHook();
                    ks=null;
                    but1HandZahvat.setBorder(globalFrame.OFF_BORDER);
                    globalFrame.setKeySt(null);
                }
            }
        }

        if(((JButton)e.getSource()).getName().equals("but2AutoZahvat")){
            if(globalFrame.getKeySt()==null){
                but2AutoZahvat.setBorder(globalFrame.ON_BORDER);
                globalFrame.setKeySt(Screen4.AUTO_ZAHVAT);
                for(int i=0;i<10; i++){
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e1) {
                    }
                    firePressed();
                }
                but2AutoZahvat.setBorder(globalFrame.OFF_BORDER);
                globalFrame.setKeySt(null);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void focusLost(FocusEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeFrame(Object source) {
        int g=jifList.indexOf(source);
        if(g!=-1){
            jifList.remove(g);
            jifListName.remove(g);
            listScr.setListData(jifListName);
        }
    }

    public void addFrame(ImageFrame jif) {
        jifList.add(jif);
        jifListName.add(jif.getTitle());
        listScr.setListData(jifListName);
        listScr.setSelectedIndex(jifList.size() - 1);
    }

    @Override
    public void firePressed() {
        globalFrame.grabScreen();
    }

    public void setSelectedEl(ImageFrame selectedEl) {
        listScr.setSelectedIndex(jifList.indexOf(selectedEl));
    }
}
