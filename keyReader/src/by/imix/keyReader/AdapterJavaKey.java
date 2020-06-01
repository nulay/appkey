package by.imix.keyReader;

import org.jnativehook.keyboard.SwingKeyAdapter;

import java.awt.event.KeyEvent;

public class AdapterJavaKey extends SwingKeyAdapter {
    private KeyEvent keyEvent;
    public void keyPressed(KeyEvent keyEvent) {
        this.keyEvent = keyEvent;
    }

    public KeyEvent getKeyEvent() {
        return keyEvent;
    }
}
