package by.imix.razborImage;

import by.imix.razborImage.filters.Carta;
import by.imix.razborImage.filters.ComponentFiltrov;
import by.imix.razborImage.filters.Filtr;
import by.imix.razborImage.filters.GroupFiltr;
import by.imix.razborImage.filters.dialogFilter.DialogFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

/**
 * Panel for filters
 * User: miha
 * Date: 13.01.14
 * Time: 13:05
 * To change this template use File | Settings | File Templates.
 */
public class PanelToolsFiltr extends JToolBar implements ActionListener{
    private static final Logger _log = LoggerFactory.getLogger(PanelToolsFiltr.class);
    private GlobalService screen4;

    private JButton but1createCarta;
    private JButton but2createGroup;

    private JButton but10OnRectObl;
    private JButton but11createFColor;
    private JButton but12saveFiltr;
    private JButton but12openCart;
    private JButton but13delete;

    private JButton but14play;

    private JButton butedit;

    private JTree jTree;

    private Vector<ComponentFiltrov> jifList;
    private Vector<String> jifListName;

    private JFileChooser saverFileFiltr;
    private JFileChooser openerFile;

    public PanelToolsFiltr(GlobalService screen4) {
        super("p2",JToolBar.VERTICAL);
//        setLayout();
        setAlignmentX(Component.LEFT_ALIGNMENT);
        this.screen4=screen4;

        JPanel rPanel=new JPanel();
        rPanel.setLayout(new BoxLayout(rPanel,BoxLayout.Y_AXIS));

        but10OnRectObl=new JButton(new ImageIcon("images/select.png"));
        but10OnRectObl.setToolTipText("Select");
        but10OnRectObl.setName("but10OnRectObl");
        but10OnRectObl.setBorder(screen4.OFF_BORDER);
        but10OnRectObl.setEnabled(false);

        but11createFColor=new JButton(new ImageIcon("images/eyedropper.png"));
        but11createFColor.setName("but11createFColor");
        but11createFColor.setBorder(screen4.OFF_BORDER);
        but11createFColor.setEnabled(false);
        but11createFColor.setToolTipText("Create color");

        but12saveFiltr=new JButton(new ImageIcon("images/save.png"));
        but12saveFiltr.setName("but12saveFiltr");
        but12saveFiltr.setBorder(screen4.OFF_BORDER);
        but12saveFiltr.setEnabled(false);
        but12saveFiltr.setToolTipText("Save filter");

        but1createCarta=new JButton(new ImageIcon("images/map.png"));
        but1createCarta.setName("but1createCarta");
        but1createCarta.setBorder(screen4.OFF_BORDER);
        but2createGroup=new JButton(new ImageIcon("images/select.png"));
        but2createGroup.setName("but2createGroup");
        but2createGroup.setBorder(screen4.OFF_BORDER);
        but2createGroup.setEnabled(false);
        but2createGroup.setToolTipText("Create group");

        but12openCart=new JButton(new ImageIcon("images/folder.png"));
        but12openCart.setName("but12openCart");
        but12openCart.setBorder(screen4.OFF_BORDER);
        but12openCart.setToolTipText("Open cart");


        but13delete=new JButton(new ImageIcon("images/rem.png"));
        but13delete.setName("but13delete");
        but13delete.setBorder(screen4.OFF_BORDER);
        but13delete.setEnabled(false);
        but13delete.setToolTipText("Remove");

        butedit=new JButton(new ImageIcon("images/redact.png"));
        butedit.setName("butedit");
        butedit.setBorder(screen4.OFF_BORDER);
        butedit.setToolTipText("Edit");

        but14play=new JButton(new ImageIcon("images/play.png"));
        but14play.setName("but14play");
        but14play.setBorder(screen4.OFF_BORDER);
        but14play.setEnabled(false);
        but13delete.setToolTipText("Play");

        setMinimumSize(new Dimension(220, 170));
        setMaximumSize(new Dimension(220, 170));
        setPreferredSize(new Dimension(220, 170));
        // listObPan2.setBorder(OFF_BORDER);

        JLabel lab=new JLabel(" Фильтры");
        setAlignmentX(Component.LEFT_ALIGNMENT);
        rPanel.add(lab);

        jifList=new Vector<ComponentFiltrov>();
        jifListName=new Vector<String>();

        TreeNode treeNode=new DefaultMutableTreeNode("Карты");

        jTree=new JTree(new DefaultTreeModel(treeNode));
        jTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        jTree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
//                int n=jTree.getSelectionRows()[0];
//                jTree.getSelectionPath().getLastPathComponent();
                DefaultMutableTreeNode dmtn= (DefaultMutableTreeNode)jTree.getLastSelectedPathComponent();
                if(dmtn==null) return;
                Object obj=dmtn.getUserObject();
                if (obj instanceof Carta) {
                    but12saveFiltr.setEnabled(true);
                    but2createGroup.setEnabled(true);
                    but14play.setEnabled(true);
                } else {
                    but12saveFiltr.setEnabled(false);
                    but2createGroup.setEnabled(false);
                    but14play.setEnabled(false);
                }

                if (obj instanceof GroupFiltr) {
                    but10OnRectObl.setEnabled(true);
                    but11createFColor.setEnabled(true);
                } else {
                    but10OnRectObl.setEnabled(false);
                    but11createFColor.setEnabled(false);
                }
            }

        });

//        listObj2.setMinimumSize(new Dimension(170,120));
//        listObj2.setPreferredSize(new Dimension(170,120));
//        listObj2.setMaximumSize(new Dimension(170,120));
        rPanel.add(new JScrollPane(jTree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

        JPanel panforBut2=new JPanel(new  FlowLayout());
        panforBut2.add(but1createCarta);
        panforBut2.add(but2createGroup);
        panforBut2.add(but10OnRectObl);
        panforBut2.add(but11createFColor);
        panforBut2.add(but12saveFiltr);
        panforBut2.add(but12openCart);
        panforBut2.add(butedit);
        panforBut2.add(but14play);

        but10OnRectObl.addActionListener(this);
        but11createFColor.addActionListener(this);
        but12saveFiltr.addActionListener(this);

        but1createCarta.addActionListener(this);
        but2createGroup.addActionListener(this);
        butedit.addActionListener(this);
        but12openCart.addActionListener(this);
        but14play.addActionListener(this);

        rPanel.add(panforBut2);
        add(rPanel,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(((JButton)e.getSource()).getName().equals("but10OnRectObl")){
            if(screen4.getKeyinstr()==0){
                screen4.setKeyinstr(1);
                but10OnRectObl.setBorder(screen4.ON_BORDER);
            }else{
                if(screen4.getKeyinstr()==1){
                    screen4.setKeyinstr(0);
                    but10OnRectObl.setBorder(screen4.OFF_BORDER);
                }
            }
        }

        if(((JButton)e.getSource()).getName().equals("but11createFColor")){
            if(screen4.getKeyinstr()==0){
                screen4.setKeyinstr(2);
                but11createFColor.setBorder(screen4.ON_BORDER);
            }else{
                if(screen4.getKeyinstr()==2 | screen4.getKeyinstr()==3){
                    screen4.setKeyinstr(0);
                    but11createFColor.setBorder(screen4.OFF_BORDER);
                }
            }
        }

        if(((JButton)e.getSource()).getName().equals("but12saveFiltr")){
            if(saverFileFiltr==null){
                saverFileFiltr=new JFileChooser();
                saverFileFiltr.setCurrentDirectory(new File("./"));
                saverFileFiltr.setDialogTitle("Сохранение карт");
                saverFileFiltr.setFileSelectionMode(JFileChooser.FILES_ONLY);
                saverFileFiltr.addChoosableFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if(f.isDirectory()) return true;
                        return f.getName().endsWith("cat");
                    }

                    @Override
                    public String getDescription() {
                        return "Файлы с записью эмуляции (*.cat)";
                    }
                });
            }
            int res=saverFileFiltr.showSaveDialog((Component) screen4);
            if(res==JFileChooser.APPROVE_OPTION){
                File file=saverFileFiltr.getSelectedFile();
                if(!file.getName().endsWith(".cat")){
                    file=new File(file.getParentFile(),file.getName()+".cat");
                }
                screen4.getFileOperation().saveFile(file,(Carta)((DefaultMutableTreeNode)jTree.getLastSelectedPathComponent()).getUserObject());

                JOptionPane.showMessageDialog((Component) screen4,"Файл сохранен");
            }

        }
        if(((JButton)e.getSource()).getName().equals("but12openCart")){
            if (openerFile == null) {
                openerFile = new JFileChooser();
                openerFile.setDialogTitle("Открытие карты");
                openerFile.setCurrentDirectory(new File("./"));
                //openerFile.setMultiSelectionEnabled(true);
                openerFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
                openerFile.addChoosableFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if(f.isDirectory()) return true;
                        return f.getName().endsWith("cat");
                    }

                    @Override
                    public String getDescription() {
                        return "Файлы с картами фильтров (*.cat)";
                    }
                });
            }
            int res = openerFile.showOpenDialog((Component) screen4);
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = openerFile.getSelectedFile();
                Carta crt=(Carta)screen4.getFileOperation().readObjectFromFile(file);
                addCartaToTree(crt);
            }
        }

        if(((JButton)e.getSource()).getName().equals("but1createCarta")){
            showDialogSettingsCart(new Carta());

        }
        if(((JButton)e.getSource()).getName().equals("but2createGroup")){
            showDialogSettingsGroup(new GroupFiltr((Carta)((DefaultMutableTreeNode)jTree.getLastSelectedPathComponent()).getUserObject()));
        }

        if(((JButton)e.getSource()).getName().equals("butedit")){
            DefaultMutableTreeNode dmtn= (DefaultMutableTreeNode)jTree.getLastSelectedPathComponent();
            Object obj=dmtn.getUserObject();
            if(obj!=null){
                if(obj instanceof Carta){
                    showDialogSettingsCart((Carta) obj);
                }
                if(obj instanceof GroupFiltr){
                    showDialogSettingsGroup((GroupFiltr)obj);
                }
                if(obj instanceof Filtr){
                    showDialogSettingsFiltr((Filtr) obj);
                }
            }

        }
        if(((JButton)e.getSource()).getName().equals("but14play")){
            DefaultMutableTreeNode dmtn= (DefaultMutableTreeNode)jTree.getLastSelectedPathComponent();
            Carta obj=(Carta)dmtn.getUserObject();
            obj.startCarta();
        }
    }

    private void addCartaToTree(Carta crt) {
        DefaultMutableTreeNode dmtnc= (DefaultMutableTreeNode)jTree.getModel().getRoot() ;
        DefaultMutableTreeNode cartNode=new DefaultMutableTreeNode(crt);
        dmtnc.insert(cartNode,dmtnc.getChildCount());
        for(GroupFiltr gf:crt.getListGroup()){
            DefaultMutableTreeNode grF=new DefaultMutableTreeNode(gf);
            cartNode.insert(grF,cartNode.getChildCount());
            for(Filtr f:gf.getListFiltr()){
                DefaultMutableTreeNode filNode=new DefaultMutableTreeNode(f);
                grF.insert(filNode,grF.getChildCount());
            }
        }
    }

    private void showDialogSettingsFiltr(Filtr filtr) {
    }

    public void addComponentFiltr(ComponentFiltrov fcr) {
        if(fcr instanceof Filtr){
            int res=DialogFilter.openDialog((Frame) screen4,(Filtr)fcr);
            if(res==1){
                _log.info("nagali ok");
            }
        }

        if(fcr instanceof Filtr){
            DefaultMutableTreeNode dmtnc= (DefaultMutableTreeNode)jTree.getLastSelectedPathComponent();
            GroupFiltr grF=(GroupFiltr)dmtnc.getUserObject();
            grF.getListFiltr().add((Filtr)fcr);
        }
        DefaultMutableTreeNode dmtn= (DefaultMutableTreeNode)jTree.getLastSelectedPathComponent();
        DefaultMutableTreeNode dmn=new DefaultMutableTreeNode(fcr);

        if(dmtn==null){
            JOptionPane.showMessageDialog(this,
                    "Not select root node.",
                    "Warning error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int[] childIndices = new int[1];
        childIndices[0] = dmtn.getChildCount();
        dmtn.insert(dmn, dmtn.getChildCount());

        ((DefaultTreeModel)jTree.getModel()).nodesWereInserted(dmtn, childIndices);
//        ((DefaultTreeModel )jTree.getModel()).nodesChanged(dmtn,null);
        refrashTree();

    }

    public void refrashTree() {
        jTree.fireTreeExpanded(jTree.getSelectionPath());
    }

    public void showDialogSettingsCart(final Carta cart){
        final JDialog dialog =new JDialog((Frame) screen4,"Настройки карты",true);
        dialog.getContentPane().setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        JPanel p1=new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(new JLabel("Название Карты"));
        final JTextField tf=new JTextField("",30);
        p1.add(tf);
        dialog.add(p1);
        if(cart.getName()!=null){
            tf.setText(cart.getName());
        }

        JPanel pbut=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton butOk=new JButton("Ok");
        JButton butCancal=new JButton("Cancal");

        butOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                if(cart.getName()==null){
                    cart.setName(tf.getText());
                    addComponentFiltr(cart);
                }else{
                    cart.setName(tf.getText());

                }
                refrashTree();
//                listObj2.setListData(jifListName);
//                listObj2.setSelectedIndex(jifList.indexOf(cart));
            }
        });

        pbut.add(butOk);
        pbut.add(butCancal);

        dialog.add(pbut);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void showDialogSettingsGroup(final GroupFiltr grfilter){
        final JDialog dialog =new JDialog((Frame) screen4,"Настройки группы",true);
        dialog.getContentPane().setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        JPanel p1=new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(new JLabel("Название группы"));
        final JTextField tf=new JTextField("",30);
        p1.add(tf);
        dialog.add(p1);
        if(grfilter!=null){
            tf.setText(grfilter.getName());
        }

        JPanel pbut=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton butOk=new JButton("Ok");
        JButton butCancal=new JButton("Cancal");
        butOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                if(grfilter.getName()==null){
                    grfilter.setName(tf.getText());
                    jTree.fireTreeCollapsed(null);
                    addComponentFiltr(grfilter);

                }else{
                    grfilter.setName(tf.getText());

                }
                refrashTree();
            }
        });

        pbut.add(butOk);
        pbut.add(butCancal);
        dialog.add(pbut);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
