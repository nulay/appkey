package by.imix.razborImage.filters;

import by.imix.keyReader.TimeEvent;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 11.01.14
 * Time: 13:18
 * To change this template use File | Settings | File Templates.
 */
public class GroupFiltr implements ComponentFiltrov, Serializable {
    private List<Filtr> listFiltr;
    private String name;
    private Carta carta;
    private GroupFiltr nextGroup;

    public GroupFiltr(Carta carta) {
        this.carta=carta;
        carta.getListGroup().add(this);
        listFiltr=new ArrayList<Filtr>();
    }

    public boolean addFiltr(Filtr filtr){
        return listFiltr.add(filtr);
    }

    public boolean removeFiltr(Filtr filtr){
        return listFiltr.remove(filtr);
    }

    public List<Filtr> getListFiltr() {
        return listFiltr;
    }

    public void setListFiltr(List<Filtr> listFiltr) {
        this.listFiltr = listFiltr;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public GroupFiltr getNextGroup() {
        return nextGroup;
    }

    public void setNextGroup(GroupFiltr nextGroup) {
        this.nextGroup = nextGroup;
    }

    public void startGr(){
        Filtr f=null;
        while(f==null){
            BufferedImage cId=null;
            try {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                Robot robot = new Robot();
                cId = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

            } catch (AWTException ex) {
            }
            for(int i=0;i<listFiltr.size();i++){
                if(listFiltr.get(i).isCorrect(cId)){
                    f=listFiltr.get(i);
                    break;
                }
            }
        }
        List<TimeEvent> listKp=null;
        if(f.getObKeyPressed()!=null){
            listKp=f.getObKeyPressed().getListTimeEvents();
        }
        carta.startAction(listKp,f.isKeyRootStart());
        if(nextGroup!=null){
            nextGroup.startGr();
        }
    }
}
