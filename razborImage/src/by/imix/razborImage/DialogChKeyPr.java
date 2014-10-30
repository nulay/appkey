package by.imix.razborImage;

import by.imix.keyReader.KeyPressed;
import by.imix.keyReader.ObKeyPressed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 09.01.14
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
public class DialogChKeyPr extends JDialog{
    private ObKeyPressed okp;

    public DialogChKeyPr(Frame fr, ObKeyPressed okp) {
        super(fr, "Настройка клавиш");
        this.okp=okp;
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        JPanel p1=new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(new JLabel("Название"));
        final JTextField tf=new JTextField(30);
        if(okp.getTitle()!=null){
            tf.setText(okp.getTitle());
        }
        p1.add(tf);
        add(p1);
        add(new JLabel("Описание",JLabel.LEFT));

        final JTextArea ta=new JTextArea(4,30);
        if(okp.getDescription()!=null){
            ta.setText(okp.getDescription());
        }
        add(ta);
        JPanel pEl=new JPanel();
        pEl.setLayout(new BoxLayout(pEl,BoxLayout.Y_AXIS));

        JPanel panI=new JPanel(new GridLayout(1,6,5,12));
        panI.add(new JLabel("№ п.п."));
        panI.add(new JLabel("Клавиша"));
        panI.add(new JLabel("Нажатие"));
        panI.add(new JLabel("Отпускание"));
        panI.add(new JLabel("Время задержки"));
        panI.add(new JLabel("Клавиши управления"));
        pEl.add(panI);

        int i=1;
        for(KeyPressed kp:okp.getListKP()){
            final JPanel pan=new JPanel(new GridLayout(1,6,5,12));
            pan.add(new JLabel(i+""));
            pan.add(new JLabel(kp.getKey()));
            pan.add(new JTextField(kp.getPressed()+"",4));
            pan.add(new JTextField(kp.getRelessed()+"",4));
            pan.add(new JLabel(kp.getTimePressed()+""));
            JButton butDel=new JButton(new ImageIcon("images/del.png"));
            butDel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Container c=pan.getParent();
                    c.remove(pan);
                    c.getParent().getParent().repaint();
                }
            });
            pan.add(butDel);
            pEl.add(pan);
            i++;
        }

        JScrollPane pSc=new JScrollPane(pEl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        add(pSc);

        JPanel p2=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton bOk=new JButton("Ok");
        JButton bCanc=new JButton("Отмена");

        bCanc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogChKeyPr.this.setVisible(false);
//                ((Screen4)DialogChKeyPr.this.getOwner()).saveObKeyPressed(null);
            }
        });

        bOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ObKeyPressed obKeyPressed=new ObKeyPressed();
                obKeyPressed.setTitle(tf.getText());
                obKeyPressed.setDescription(ta.getText());
                obKeyPressed.setListKP(DialogChKeyPr.this.okp.getListKP());

                java.util.List<KeyPressed> lkp=new ArrayList<KeyPressed>();

                Component[] lcomp= ((JPanel)((JViewport)((JScrollPane)((JPanel)((JButton)e.getSource()).getParent().getParent()).getComponent(3)).getComponent(0)).getComponent(0)).getComponents();
                for(int i=1;i<lcomp.length;i++){
                    JPanel comp=(JPanel)lcomp[i];
                    KeyPressed kpr=obKeyPressed.getListKP().get(Integer.parseInt(((JLabel)comp.getComponent(0)).getText())-1);
                    kpr.setPressed(Long.parseLong(((JTextField)comp.getComponent(2)).getText()));
                    kpr.setRelessed(Long.parseLong(((JTextField)comp.getComponent(3)).getText()));
                    lkp.add(kpr);
                }
                obKeyPressed.setListKP(lkp);
                DialogChKeyPr.this.setVisible(false);
                ((Screen4)DialogChKeyPr.this.getOwner()).getPanelToolsEmulation().saveObKeyPressed(obKeyPressed);
            }
        });
        p2.add(bOk);
        p2.add(bCanc);

        add(p2);
        setSize(800,600);
        setLocationRelativeTo(null);
    }
}
