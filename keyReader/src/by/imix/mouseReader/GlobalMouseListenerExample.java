package by.imix.mouseReader;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 31.12.13
 * Time: 10:47
 * To change this template use File | Settings | File Templates.
 */

public class GlobalMouseListenerExample implements NativeMouseInputListener {
    private static final Logger _log = LoggerFactory.getLogger(GlobalMouseListenerExample.class);
    public void nativeMouseClicked(NativeMouseEvent e) {
        _log.debug("Mouse Clicked: " + e.getClickCount());
    }

    public void nativeMousePressed(NativeMouseEvent e) {
        _log.debug("Mouse Pressed: " + e.getButton());
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
        _log.debug("Mouse Released: " + e.getButton());
    }

    public void nativeMouseMoved(NativeMouseEvent e) {
        _log.debug("Mouse Moved: " + e.getX() + ", " + e.getY());
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
        _log.debug("Mouse Dragged: " + e.getX() + ", " + e.getY());
    }

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        //Construct the example object.
        GlobalMouseListenerExample example = new GlobalMouseListenerExample();

        //Add the appropriate listeners for the example object.
        GlobalScreen.addNativeMouseListener(example);
        GlobalScreen.addNativeMouseMotionListener(example);
    }
}
