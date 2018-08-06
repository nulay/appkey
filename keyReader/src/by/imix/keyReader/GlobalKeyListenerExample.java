package by.imix.keyReader;


/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 31.12.13
 * Time: 10:14
 * To change this template use File | Settings | File Templates.
 */

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class GlobalKeyListenerExample implements NativeKeyListener {
    private static final Logger _log = LoggerFactory.getLogger(GlobalKeyListenerExample.class);
    private Date startDate;
    private List<KeyPressed> lkp=new ArrayList<KeyPressed>();
    private List<KeyPressed> listkr=new ArrayList<KeyPressed>();
    private EventStopGKL es;
    private boolean keyRun;

//    private GlobalKeyListenerExample _log;
//
//    private void error(Object s) {
//        info(s);
//    }
//
//    private void info(Object s) {
//        _log.debug(s);
//    }


    public GlobalKeyListenerExample() {
        this(null);
    }

    public GlobalKeyListenerExample(EventStopGKL es) {
        this.es=es;
        startKeyLovec();
    }

    public void startKeyLovec(){
        if(!keyRun){
            startDate=new Date();
            try {
                GlobalScreen.registerNativeHook();
            }
            catch (NativeHookException ex) {
                _log.error("There was a problem registering the native hook.");
                _log.error(ex.getMessage());
            }
            //Construct the example object and initialze native hook.
            GlobalScreen.addNativeKeyListener(this);
            keyRun=true;
        }
    }



    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            stopKeyLovec();
        }
        _log.info("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        KeyPressed kp=null;
        for(KeyPressed kpR:lkp){
            if(kpR.getKeyCode().equals(e.getKeyCode())){
                kp=kpR;
                break;
            }
        }
        if(kp!=null) return;
        kp=new KeyPressed();
        kp.setKey(NativeKeyEvent.getKeyText(e.getKeyCode()));
        kp.setKeyCode(e.getKeyCode());
        kp.setPressed(new Date().getTime()-startDate.getTime());
        lkp.add(kp);
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        _log.info("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        KeyPressed mykp=null;
        for(KeyPressed kp:lkp){
            if(kp.getKeyCode().equals(e.getKeyCode())){
                mykp=kp;
                break;
            }
        }
        if(mykp!=null){
            lkp.remove(mykp);
            mykp.setRelessed(new Date().getTime()-startDate.getTime());
            listkr.add(mykp);
        }
    }


    public void nativeKeyTyped(NativeKeyEvent e) {
        _log.info("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }

    public static void main(String[] args) {
//        new NativeHookDemo();
        new GlobalKeyListenerExample();

//        GlobalScreen.unregisterNativeHook();
//        GlobalScreen.unregisterNativeHook();
//        GlobalScreen.unregisterNativeHook();
//        GlobalScreen.unregisterNativeHook();

    }

    public List<KeyPressed> getListKeyPressed() {
        return listkr;
    }

    public void stopKeyLovec(){
        Collections.sort(listkr,new Comparator<KeyPressed>(){
            //                boolean equals(Object obj){
//                   return true;
//                }
            @Override
            public int compare(KeyPressed o1, KeyPressed o2){
                return (o1.getPressed()>o2.getPressed())?1:(o1.getPressed().equals(o2.getPressed()))?0:-1;
            }
        });
        for(KeyPressed kpr:listkr){
            _log.info(kpr.toString());
//                kpr.setRelessed(kpr.getPressed()+10);
        }
        _log.info("Размер списка "+lkp.size());
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        keyRun=false;
        if(es!=null){
            es.fireStopped(this);
        }
    }

    public boolean isRun() {
        return keyRun;
    }
}