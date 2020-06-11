package by.imix.keyReader;


/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 31.12.13
 * Time: 10:14
 * To change this template use File | Settings | File Templates.
 */

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class KeyCatcher implements NativeKeyListener, Catcher {
    private static final Logger _log = LoggerFactory.getLogger(KeyCatcher.class);

    private Map<Integer, KeyTimeEvent> keyPressed = new HashMap<>();
    private List<TimeEvent> listReleasedsKeys = new ArrayList<>();


    private AdapterJavaKey swingKeyAdapter = new AdapterJavaKey();
    private Date startDate;

    public KeyCatcher() {
        this(new Date());
    }

    public KeyCatcher(Date startDate) {
        this.startDate=startDate;
        GlobalScreen.addNativeKeyListener(this);
    }

    public void nativeKeyPressed(NativeKeyEvent e) {

        swingKeyAdapter.nativeKeyPressed(e);
        java.awt.event.KeyEvent keyEvent = swingKeyAdapter.getKeyEvent();
        _log.info("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        _log.info("Key Pressed KeyCode: " + keyEvent.getKeyCode());

        KeyTimeEvent keyPressedR = keyPressed.get(e.getKeyCode());

        if(keyPressedR!=null) return;
        keyPressedR=new KeyTimeEvent();
        keyPressedR.setKey(NativeKeyEvent.getKeyText(e.getKeyCode()));
        keyPressedR.setKeyCode(keyEvent.getKeyCode());
        keyPressedR.setTimeStartEvent(new Date().getTime()-startDate.getTime());
        keyPressed.put(e.getKeyCode(), keyPressedR);

    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        _log.info("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        KeyTimeEvent mykp = keyPressed.get(e.getKeyCode());
        if(mykp!=null){
            keyPressed.remove(e.getKeyCode());
            mykp.setTimeStopEvent(new Date().getTime()-startDate.getTime());
            listReleasedsKeys.add(mykp);
        }
    }


    public void nativeKeyTyped(NativeKeyEvent e) {
        _log.info("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }

    @Override
    public List<TimeEvent> getSimpleEvents() {
        return listReleasedsKeys;
    }
}