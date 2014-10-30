package by.imix.razborImage.filters.dialogFilter;

import by.imix.razborImage.filters.ColorInRectFilter;
import by.imix.razborImage.filters.Filtr;
import by.imix.razborImage.filters.FullConcurrenceRect;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ирина
 * Date: 16.01.14
 * Time: 1:29
 * To change this template use File | Settings | File Templates.
 */
public class DialogFilter extends JDialog{
    private int num=0;

    public DialogFilter(Frame owner, String s) {
        super(owner,s);
    }

    public static int openDialog(Frame owner, Filtr filtr){
        DialogFilter jd=new DialogFilter(owner,"Настройки фильтра");
        if(filtr instanceof ColorInRectFilter){
             jd.getContentPane().add(new DialogColorInRectFilter(jd,(ColorInRectFilter)filtr),BorderLayout.CENTER);
        }
        if(filtr instanceof FullConcurrenceRect){
            jd.getContentPane().add(new DialogFullConcurrenceRect(jd,(FullConcurrenceRect)filtr),BorderLayout.CENTER);
        }
        jd.pack();
        jd.setLocationRelativeTo(null);
        jd.setModal(true);
        jd.setVisible(true);
        return jd.num;

    }

    public void setNum(int i) {
        num=i;
        setVisible(false);
    }
}
