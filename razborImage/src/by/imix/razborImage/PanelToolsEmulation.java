package by.imix.razborImage;

import by.imix.keyReader.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 13.01.14
 * Time: 11:53
 * To change this template use File | Settings | File Templates.
 */
public class PanelToolsEmulation extends JToolBar implements ToolsEmulation, FocusListener, ActionListener, EventStopGKL {
    private static final Logger _log = LoggerFactory.getLogger(PanelToolsEmulation.class);
    private GlobalService screen4;

    private JFileChooser openerFile;
    private JFileChooser saverFileemulation;
 
    //    private Vector l;//список названий эмуляций
    private Vector<ObKeyPressed> l2;//список объектов эмуляций
    private Vector<File> l3;//список файлов объектов эмуляций
    private JList listObj;

    private JButton but4Play;
    private JButton but5Rec;
    private JButton butOpenEmulations;
    private JButton but7Stop;
    private JButton but8EditEmulation;
    private JButton but9delete;

    private DialogChKeyPr dialogCrEm;

    public PanelToolsEmulation(GlobalService screen4) {
        super("p1", JToolBar.VERTICAL);
        setAlignmentX(Component.LEFT_ALIGNMENT);
        this.screen4 = screen4;
      
        JPanel rPanel = new JPanel();
        rPanel.setLayout(new BoxLayout(rPanel, BoxLayout.Y_AXIS));
        rPanel.add(Box.createHorizontalGlue());
        setMinimumSize(new Dimension(220, 170));
        setMaximumSize(new Dimension(220, 170));
        setPreferredSize(new Dimension(220, 170));
//        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        // listObPan.setBorder(OFF_BORDER);

        JLabel lab = new JLabel(" Список эмуляций");
        setAlignmentX(Component.LEFT_ALIGNMENT);
        rPanel.add(lab);

//        l=new VectorremindEvent();
        l2 = new Vector<ObKeyPressed>();
        l3 = new Vector<File>();

        listObj = new JList(l2);
//        listObj.setMinimumSize(new Dimension(170, 120));
//        listObj.setPreferredSize(new Dimension(170,120));
//        listObj.setMaximumSize(new Dimension(170, 120));

        listObj.addFocusListener(this);

        rPanel.add(new JScrollPane(listObj, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

        but4Play = new JButton(new ImageIcon("images/play.png"));//проигрывание
        but4Play.setName("but4Play");
        but4Play.setEnabled(false);
        but4Play.setBorder(screen4.OFF_BORDER);
        but5Rec = new JButton(new ImageIcon("images/rec.png"));//запись
        but5Rec.setName("but5Rec");
        but5Rec.setBorder(screen4.OFF_BORDER);
        butOpenEmulations = new JButton(new ImageIcon("images/folder.png"));
        butOpenEmulations.setName("but6Folder");
        butOpenEmulations.setBorder(screen4.OFF_BORDER);
        but7Stop = new JButton(new ImageIcon("images/stop.png"));//стоп
        but7Stop.setName("but7Stop");
        but7Stop.setEnabled(false);
        but7Stop.setBorder(screen4.OFF_BORDER);
        but8EditEmulation = new JButton(new ImageIcon("images/redact.png"));
        but8EditEmulation.setName("but8Correct");
        but8EditEmulation.setBorder(screen4.OFF_BORDER);
        but8EditEmulation.setEnabled(false);
        but9delete = new JButton(new ImageIcon("images/delete.png"));
        but9delete.setName("but9delete");
        but9delete.setBorder(screen4.OFF_BORDER);
        but9delete.setEnabled(false);

        JPanel panforBut = new JPanel(new FlowLayout());
        but7Stop.addActionListener(this);
        panforBut.add(but7Stop);
        but5Rec.addActionListener(this);
        panforBut.add(but5Rec);
        but4Play.addActionListener(this);
        panforBut.add(but4Play);
        butOpenEmulations.addActionListener(this);
        panforBut.add(butOpenEmulations);
        but8EditEmulation.addActionListener(this);
        panforBut.add(but8EditEmulation);
        but9delete.addActionListener(this);
        panforBut.add(but9delete);

        rPanel.add(panforBut);


        panforBut.setMaximumSize(new Dimension(170, 25));

        add(rPanel);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (((JList) e.getSource()).getSelectedIndex() != -1) {
            showEmButton();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()).getName().equals("but4Play")) {
            if (screen4.getClss() == null || screen4.getClss().isKeyEnd()) {
                int i = listObj.getSelectedIndex();
                if (i == -1) {
                    final JDialog dialog1 = new JDialog((Frame) screen4);
                    dialog1.getContentPane().setLayout(new BoxLayout(dialog1.getContentPane(), BoxLayout.Y_AXIS));

                    dialog1.add(new JLabel("Не выбран бой"));
                    JButton jb = new JButton("Ok");
                    jb.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog1.setVisible(false);
                        }
                    });
                    dialog1.add(jb);
                    dialog1.pack();
                    dialog1.setModal(true);
                    dialog1.setVisible(true);
                    return;
                }

                but4Play.setBorder(screen4.ON_BORDER);
                final JDialog dialog = new JDialog((Frame) screen4, "Проигрывание эмуляции");
                final JLabel l = new JLabel("Бой начнется через 3 секунды, переключитесь в игру");
                dialog.add(l);
                dialog.pack();
                dialog.setModal(true);

                Thread th = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for (int i = 0; i < 3; i++) {
                                Thread.sleep(1L * 1000L);
                                l.setText("Бой начнется через " + (2 - i) + " секунд, переключитесь в игру");
                            }
                        } catch (InterruptedException ez) {
                            _log.error(ez.getMessage());
                        }
                        dialog.setVisible(false);
                        if (screen4.getClss() == null) {
                            AppClss appClss = new AppClss();
                            appClss.setAction(l2.get(listObj.getSelectedIndex()).getListTimeEvents());
                            screen4.setClss(appClss);
                        }

                        screen4.getClss().playBot();       //l2.get(listObj.getSelectedIndex()).getListKP()
                    }
                });
                th.start();
                dialog.setVisible(true);
                screen4.setKeySt(GlobalService.PLAY);
                but7Stop.setEnabled(true);
                hideEmButton();
            }
        }

        if (((JButton) e.getSource()).getName().equals("but5Rec")) {
            if (screen4.getGlklE() == null || !screen4.getGlklE().isRun()) {
                but5Rec.setBorder(screen4.ON_BORDER);
                final JDialog dialog = new JDialog((Frame) screen4, "Старт записи");
                final JLabel lab = new JLabel("Запсь начнется через 5 секунды, переключитесь в игру, для остановки и записи нажмите Escape.");
                dialog.add(lab);
                dialog.pack();
                dialog.setSize(new Dimension(dialog.getWidth(), dialog.getHeight() + 20));
                dialog.setModal(true);

                Thread th = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for (int i = 0; i < 1; i++) {
                                lab.setText("Запсь начнется через " + (5 - i) + " секунды, переключитесь в игру, для остановки и записи нажмите Escape.");
                                Thread.sleep(1L * 1000L);
                            }
                            dialog.setVisible(false);
                        } catch (InterruptedException ez) {
                            ez.printStackTrace();
                        }
                        if (screen4.getGlklE() == null) {
                            screen4.setGlklE(new GlobalCatcher(PanelToolsEmulation.this, true, true));
                        } else {
                            screen4.getGlklE().startKeyCatcher();
                        }

                    }
                });
                th.start();
                dialog.setVisible(true);
                but7Stop.setEnabled(true);
                screen4.setKeySt(GlobalService.REC);
                listObj.removeSelectionInterval(0, listObj.getSelectedIndex());
                hideEmButton();
            }
        }

        if (((JButton) e.getSource()).getName().equals("but6Folder")) {
            if (openerFile == null) {
                openerFile = new JFileChooser();
                openerFile.setDialogTitle("Открытие эмуляции");
                openerFile.setCurrentDirectory(new File("./"));
                openerFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//                    openerFile.addChoosableFileFilter();
            }
            int res = openerFile.showOpenDialog((Component) screen4);
            if (res == JFileChooser.APPROVE_OPTION) {
                File[] listF = openerFile.getSelectedFile().listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith("xml");
                    }
                });
                if (l2.size() > 0) {
                    int res2 = JOptionPane.showConfirmDialog((Component) screen4, "Заменить текущие эмуляции новыми?", "Добавление эмуляций", JOptionPane.YES_NO_OPTION);
                    if (res2 == JOptionPane.YES_OPTION) {
                        l2.clear();
                        l3.clear();
                    }
                }
                for (File f : listF) {
                    ObKeyPressed okp = (ObKeyPressed) screen4.getFileOperation().readObjectFromFile(f, ObKeyPressed.class);
//                    l.add((okp.getTitle() == null || okp.getTitle().equals("")) ? "NoName" : okp.getTitle());
                    l2.add(okp);
                    l3.add(f);
                }
                listObj.setListData(l2);
            }
        }

        if (((JButton) e.getSource()).getName().equals("but7Stop")) {
            if (screen4.getKeySt().equals(Screen4.PLAY)) {
                screen4.getClss().setKeyEnd(true);
                showEmButton();
                but4Play.setBorder(butOpenEmulations.getBorder());
            }
            if (screen4.getKeySt().equals(Screen4.REC)) {
                but5Rec.setBorder(butOpenEmulations.getBorder());
                screen4.setKeySt(null);
                screen4.getGlklE().stopKeyCatcher();
            }
            screen4.setKeySt(null);
            but7Stop.setEnabled(false);
        }

        if (((JButton) e.getSource()).getName().equals("but8Correct")) {
            dialogCrEm = new DialogChKeyPr((Frame) screen4, l2.get(listObj.getSelectedIndex()));
            dialogCrEm.setVisible(true);
        }

        if (((JButton) e.getSource()).getName().equals("but9delete")) {
            int res = JOptionPane.showConfirmDialog((Component) screen4, "Вы уверены что хотите удалить Эмуляцию?", "Удаление эмуляции", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                //_log.debug();
                if (l3.get(listObj.getSelectedIndex()).delete()) {
                    l3.remove(listObj.getSelectedIndex());
//                    l.remove(listObj.getSelectedIndex());
                    l2.remove(listObj.getSelectedIndex());
                    listObj.setListData(l2);
                    hideEmButton();
                } else {
                    JOptionPane.showMessageDialog((Component) screen4, "Не удалось удалить эмуляцию", "Удаление эмуляции", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void hideEmButton() {
        but9delete.setEnabled(false);
        but8EditEmulation.setEnabled(false);
        but4Play.setEnabled(false);
    }

    public void showEmButton() {
        but9delete.setEnabled(true);
        but8EditEmulation.setEnabled(true);
        but4Play.setEnabled(true);
    }

    public void saveObKeyPressed(ObKeyPressed obKeyPressed) {
        if (obKeyPressed != null) {
            if (saverFileemulation == null) {
                saverFileemulation = new JFileChooser();
                saverFileemulation.setCurrentDirectory(new File("./"));
                saverFileemulation.setDialogTitle("Сохранение эмуляции");
                saverFileemulation.setFileSelectionMode(JFileChooser.FILES_ONLY);
                saverFileemulation.addChoosableFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if (f.isDirectory()) return true;
                        return f.getName().endsWith("emu");
                    }

                    @Override
                    public String getDescription() {
                        return "Файлы с записью эмуляции (*.emu)";
                    }
                });
            }
            int res = saverFileemulation.showSaveDialog((Component) screen4);
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = saverFileemulation.getSelectedFile();
                if (!file.getName().endsWith(".xml")) {
                    file = new File(file.getParentFile(), file.getName() + ".xml");
                }
//                screen4.getFileOperation().saveFile(file, obKeyPressed);
                screen4.getFileOperation().saveFile(file, obKeyPressed, ObKeyPressed.class);
                l2.add(obKeyPressed);
                listObj.updateUI();

                JOptionPane.showMessageDialog((Component) screen4, "Файл сохранен");
            }
        }
    }

    public Vector getAllObjEmulation() {
        return l2;
    }

    @Override
    public void fireStopped(final java.util.List<TimeEvent> timeEvents) {
        dialogCrEm = new DialogChKeyPr((Frame) screen4, new ObKeyPressed(timeEvents));
        dialogCrEm.setVisible(true);
        if (screen4.getKeySt() != null) {
            but5Rec.setBorder(butOpenEmulations.getBorder());
            screen4.setKeySt( null);
            but7Stop.setEnabled(false);
        }
    }
}
