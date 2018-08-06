package by.imix.keyReader;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.LoggerFactory;

import java.awt.event.KeyEvent;

/**
 * Class for capture image Capture key = F
 * User: Ирина
 * Date: 13.01.14
 * Time: 0:07
 * To change this template use File | Settings | File Templates.
 */
public class KeyRazbor implements NativeKeyListener {
    private static final org.slf4j.Logger _log = LoggerFactory.getLogger(KeyRazbor.class);
    private EventStop eventStop;
    public KeyRazbor(EventStop eventStop) {
        this.eventStop = eventStop;
        try {
            GlobalScreen.registerNativeHook();
        }catch (NativeHookException ex) {
            _log.error("There was a problem registering the native hook.");
            _log.error(ex.getMessage());

            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    public void unregisterNativeHook(){
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if(KeyEvent.VK_F==nativeKeyEvent.getKeyCode()){
            eventStop.firePressed();
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
