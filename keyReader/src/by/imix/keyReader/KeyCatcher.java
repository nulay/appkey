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

import java.awt.event.KeyEvent;
import java.util.*;

public class KeyCatcher implements NativeKeyListener {
    private static final Logger _log = LoggerFactory.getLogger(KeyCatcher.class);
    private Date startDate;
    private Map<Integer, KeyPressed> keyPressed = new HashMap<>();
    private List<KeyPressed> listReleasedsKeys = new ArrayList<KeyPressed>();
    private EventStopGKL es;
    private boolean keyRun;
    private AdapterJavaKey swingKeyAdapter = new AdapterJavaKey();

//    private GlobalKeyListenerExample _log;
//
//    private void error(Object s) {
//        info(s);
//    }
//
//    private void info(Object s) {
//        _log.debug(s);
//    }


    public KeyCatcher() {
        this(null);
    }

    public KeyCatcher(EventStopGKL es) {
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
        swingKeyAdapter.nativeKeyPressed(e);
        KeyEvent keyEvent = swingKeyAdapter.getKeyEvent();
        _log.info("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        _log.info("Key Pressed KeyCode: " + keyEvent.getKeyCode());

        KeyPressed keyPressedR = keyPressed.get(e.getKeyCode());

        if(keyPressedR!=null) return;
        keyPressedR=new KeyPressed();
        keyPressedR.setKey(NativeKeyEvent.getKeyText(e.getKeyCode()));
        keyPressedR.setKeyCode(keyEvent.getKeyCode());
        keyPressedR.setPressed(new Date().getTime()-startDate.getTime());
        keyPressed.put(e.getKeyCode(), keyPressedR);

    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        _log.info("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        KeyPressed mykp = keyPressed.get(e.getKeyCode());
        if(mykp!=null){
            keyPressed.remove(e.getKeyCode());
            mykp.setRelessed(new Date().getTime()-startDate.getTime());
            listReleasedsKeys.add(mykp);
        }
    }


    public void nativeKeyTyped(NativeKeyEvent e) {
        _log.info("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }

    public static void main(String[] args) {
//        new NativeHookDemo();
        new KeyCatcher();

//        GlobalScreen.unregisterNativeHook();
//        GlobalScreen.unregisterNativeHook();
//        GlobalScreen.unregisterNativeHook();
//        GlobalScreen.unregisterNativeHook();

    }

    public List<KeyPressed> getListKeyPressed() {
        return listReleasedsKeys;
    }

    public void stopKeyLovec(){
        Collections.sort(listReleasedsKeys,new Comparator<KeyPressed>(){
            //                boolean equals(Object obj){
//                   return true;
//                }
            @Override
            public int compare(KeyPressed o1, KeyPressed o2){
                return (o1.getPressed()>o2.getPressed())?1:(o1.getPressed().equals(o2.getPressed()))?0:-1;
            }
        });
        for(KeyPressed kpr: listReleasedsKeys){
            _log.info(kpr.toString());
//                kpr.setRelessed(kpr.getPressed()+10);
        }
        _log.info("Размер списка "+ keyPressed.size());
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