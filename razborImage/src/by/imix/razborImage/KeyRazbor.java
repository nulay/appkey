package by.imix.razborImage;

import org.apache.log4j.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Ирина
 * Date: 13.01.14
 * Time: 0:07
 * To change this template use File | Settings | File Templates.
 */
public class KeyRazbor implements NativeKeyListener {
    private Logger _log=Logger.getLogger(KeyRazbor.class);
    private GlobalFrame screen4;
    public KeyRazbor(GlobalFrame screen4) {
        this.screen4=screen4;
        try {
            GlobalScreen.registerNativeHook();
        }catch (NativeHookException ex) {
            _log.error("There was a problem registering the native hook.");
            _log.error(ex.getMessage());

            System.exit(1);
        }
        GlobalScreen.getInstance().addNativeKeyListener(this);
    }

    public void unregisterNativeHook(){
        GlobalScreen.unregisterNativeHook();
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if(KeyEvent.VK_F==nativeKeyEvent.getKeyCode()){
            screen4.startRazbor();
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
