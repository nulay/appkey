package by.imix.razborImage.filters;

import by.imix.botTank.AppClss;
import by.imix.keyReader.KeyPressed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 14.01.14
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 */
public class Carta implements ComponentFiltrov, Serializable {
    private String name;
    private List<GroupFiltr> listGroup;
    private AppClss appClss=new AppClss();

    public Carta() {
        listGroup=new ArrayList<GroupFiltr>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public List<GroupFiltr> getListGroup() {
        return listGroup;
    }

    public void setListGroup(List<GroupFiltr> listGroup) {
        this.listGroup = listGroup;
    }

    @Override
    public String toString() {
        return getName();
    }

    public void startCarta(){
        while(true){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            getListGroup().get(0).startGr();
        }
    }

    public void startAction(final List<KeyPressed> lkp,boolean kna){
        if(lkp==null){
            //останавливваем апп
            appClss.stop();
        }

        if(kna){
            appClss.setKeyPause(true);
            AppClss appClss2=new AppClss();
            appClss2.setAction(lkp);
            appClss2.playBot();
            appClss.setKeyPause(false);
        }

//        if(paral){
//            AppClss appClss2=new AppClss();
//            appClss2.setAction(lkp);
//            appClss2.playBot();
//        }

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                appClss.setAction(lkp);
                appClss.playBot();
            }
        });
        th.start();
    }
}
