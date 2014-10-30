package by.imix.razborImage.filters.dialogFilter;

import by.imix.keyReader.ObKeyPressed;
import by.imix.razborImage.Screen4;
import by.imix.razborImage.filters.ColorInRectFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Ирина
 * Date: 15.01.14
 * Time: 16:48
 * To change this template use File | Settings | File Templates.
 */
public class DialogColorInRectFilter extends JPanel{
    private ColorInRectFilter colorInRectFilter;
    private DialogFilter df;
    public DialogColorInRectFilter(DialogFilter df,ColorInRectFilter colorInRectFilter) {
//        super(fr, "Настройки фильтра цвета в области");
        this.df=df;
        this.colorInRectFilter=colorInRectFilter;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JPanel p1=new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(new JLabel("Название"));
        final JTextField tf=new JTextField(20);
        if(colorInRectFilter!=null){
            tf.setText(colorInRectFilter.getName());
        }
        p1.add(tf);
        add(p1);

        JPanel p2=new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(new JLabel("Область поиска"));
        p2.add(new JLabel("X:"));
        final JTextField tf2=new JTextField(3);
        p2.add(tf2);
        p2.add(new JLabel(" Y:"));
        final JTextField tf3=new JTextField(3);
        p2.add(tf3);
        p2.add(new JLabel(" H:"));
        final JTextField tf4=new JTextField(3);

        p2.add(tf4);
        p2.add(new JLabel(" W:"));
        final JTextField tf5=new JTextField(3);
        if(colorInRectFilter!=null){
            tf2.setText(colorInRectFilter.getRect().getBeginPoint().getX()+"");
            tf3.setText(colorInRectFilter.getRect().getBeginPoint().getY()+"");
            tf4.setText(colorInRectFilter.getRect().getWidth()+"");
            tf5.setText(colorInRectFilter.getRect().getHeight()+"");
        }
        p2.add(tf5);
        add(p2);

//        JPanel p3=new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel p5=new JPanel(new FlowLayout(FlowLayout.LEFT));
        p5.add(new JLabel("Цвета поиска "));
        JButton buttonaddColor=new JButton("+");
        p5.add(buttonaddColor);
        add(p5);

        JPanel pEl=new JPanel(new FlowLayout(FlowLayout.LEFT));
//        pEl.setLayout(new BoxLayout(pEl,BoxLayout.Y_AXIS));
        if(colorInRectFilter!=null){
            tf2.setText(colorInRectFilter.getRect().getBeginPoint().getX()+"");
            tf3.setText(colorInRectFilter.getRect().getBeginPoint().getY()+"");
            tf4.setText(colorInRectFilter.getRect().getWidth()+"");
            tf5.setText(colorInRectFilter.getRect().getHeight()+"");

            for(Object objC:colorInRectFilter.getPointColor()){
                JButton jbnc=new JButton();
                jbnc.setBorder(Screen4.ON_BORDER);
                int[] colR= (int[]) objC;
                jbnc.setBackground(new Color(colR[0],colR[1],colR[2]));

                pEl.add(jbnc);
            }
        }

        JPanel pEl2=new JPanel(new FlowLayout(FlowLayout.LEFT));
        final Vector vokp=new Vector();
        vokp.add("<Без действия>");
        vokp.addAll(((Screen4)df.getOwner()).getPanelToolsEmulation().getAllObjEmulation());


        final JComboBox cb=new JComboBox(vokp);
        pEl2.add(new JLabel("Действие"));
        pEl2.add(cb);
        final JCheckBox checkBox =new JCheckBox("Выполнить неотложно");
        pEl2.add(checkBox);
        add(pEl2);


        JPanel p7=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton bOk=new JButton("Ok");
        JButton bCanc=new JButton("Отмена");

        bCanc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogColorInRectFilter.this.df.setNum(0);
            }
        });

        bOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorInRectFilter colorInRectFilter = DialogColorInRectFilter.this.colorInRectFilter;
                colorInRectFilter.setName(tf.getText());
                colorInRectFilter.getRect().getBeginPoint().setX(Integer.parseInt(tf2.getText()));
                colorInRectFilter.getRect().getBeginPoint().setY(Integer.parseInt(tf3.getText()));
                colorInRectFilter.getRect().getEndPoint().setX(Integer.parseInt(tf4.getText())+colorInRectFilter.getRect().getBeginPoint().getX());
                colorInRectFilter.getRect().getEndPoint().setY(Integer.parseInt(tf5.getText())+colorInRectFilter.getRect().getBeginPoint().getY());
                if(checkBox.isSelected()){
                    colorInRectFilter.setKeyRootStart(true);
                }

                if(cb.getSelectedIndex()!=-1 && cb.getSelectedIndex()!=0){
                    colorInRectFilter.setObKeyPressed((ObKeyPressed)vokp.get(cb.getSelectedIndex()));
                }


                DialogColorInRectFilter.this.df.setNum(1);
            }
        });
        p7.add(bOk);
        p7.add(bCanc);

        add(p7);
//        setSize(800,600);
//        setLocationRelativeTo(null);
    }
}
