package by.imix.razborImage;

import org.apache.log4j.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 03.01.14
 * Time: 15:03
 * To change this template use File | Settings | File Templates.
 */
public class KeyScreen implements NativeKeyListener {
    private Logger _log=Logger.getLogger(KeyScreen.class);
    private Screen4 screen4;
    public KeyScreen(Screen4 screen4) {
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
              screen4.grabScreen();
        }

        if(KeyEvent.VK_ESCAPE==nativeKeyEvent.getKeyCode()){
            screen4.stopPoehali();
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
