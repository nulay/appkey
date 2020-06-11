package by.imix.keyReader;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.*;
import java.util.List;

public class MouseCatcher implements NativeMouseListener, NativeMouseMotionListener, NativeMouseWheelListener, Catcher {
    private static final Logger _log = LoggerFactory.getLogger(NativeKeyListener.class);
    private Date startDate;
    private Map<Integer, MouseTimeEvent> mousePressed = new HashMap<>();
    private List<TimeEvent> listReleasedsKeys = new ArrayList<>();

    public MouseCatcher() {
        this(new Date());
    }

    public MouseCatcher(Date startDate) {
        this.startDate = startDate;
        GlobalScreen.addNativeMouseListener(this);
        GlobalScreen.addNativeMouseMotionListener(this);
        GlobalScreen.addNativeMouseWheelListener(this);
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
        _log.debug("Mouse Clicked " + nativeMouseEvent.getX() + "/" + nativeMouseEvent.getY());
        stopMoved(nativeMouseEvent.getX(),nativeMouseEvent.getY());
        MouseTimeEvent mouseTimeEvent = new MouseTimeEvent();
        mouseTimeEvent.setKeyButton(nativeMouseEvent.getButton());
        mouseTimeEvent.setClickCount(nativeMouseEvent.getClickCount());
        mouseTimeEvent.setTimeStartEvent(new Date().getTime() - startDate.getTime());
        mouseTimeEvent.setTimeStopEvent(new Date().getTime() - startDate.getTime());
        listReleasedsKeys.add(mouseTimeEvent);

    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        _log.debug("Mouse Clicked: " + nativeMouseEvent.getX() + "/" + nativeMouseEvent.getY());
        stopMoved(nativeMouseEvent.getX(),nativeMouseEvent.getY());
        MouseTimeEvent mousePressedR = mousePressed.get(nativeMouseEvent.getButton());

        if (mousePressedR != null) return;

        mousePressedR = new MouseTimeEvent();
        mousePressedR.setKeyButton(nativeMouseEvent.getButton());
        mousePressedR.setClickCount(nativeMouseEvent.getClickCount());
        mousePressedR.setTimeStartEvent(new Date().getTime() - startDate.getTime());

        mousePressed.put(nativeMouseEvent.getButton(), mousePressedR);
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        _log.debug("Key Released: " + nativeMouseEvent.getButton());
        stopMoved(nativeMouseEvent.getX(),nativeMouseEvent.getY());
        MouseTimeEvent mykp = mousePressed.get(nativeMouseEvent.getButton());
        if (mykp != null) {
            mousePressed.remove(nativeMouseEvent.getButton());
            mykp.setTimeStopEvent(new Date().getTime() - startDate.getTime());
            listReleasedsKeys.add(mykp);
        }
    }

    @Override
    public List<TimeEvent> getSimpleEvents() {
        return listReleasedsKeys;
    }


    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {
        _log.debug("Mouse Moved: " + nativeMouseEvent.getX() + ", " + nativeMouseEvent.getY());
        MouseTimeEvent mouseTimeEvent = mousePressed.get(456);
        if (mouseTimeEvent == null) {
            mouseTimeEvent = new MouseTimeEvent();
            mouseTimeEvent.setKeyButton(456);
            mouseTimeEvent.setStartX(nativeMouseEvent.getX());
            mouseTimeEvent.setStartY(nativeMouseEvent.getY());
            mouseTimeEvent.setTimeStartEvent(new Date().getTime() - startDate.getTime());

            mousePressed.put(mouseTimeEvent.getKeyButton(), mouseTimeEvent);
        }
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {
        _log.debug("Mouse Dragged: " + nativeMouseEvent.getX() + ", " + nativeMouseEvent.getY());
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeMouseWheelEvent) {
        _log.debug("Mouse Dragged: " + nativeMouseWheelEvent.getWheelDirection());
        stopMoved(nativeMouseWheelEvent.getX(),nativeMouseWheelEvent.getY());

        MouseTimeEvent mouseTimeEvent = new MouseTimeEvent();
        mouseTimeEvent.setKeyButton(400+nativeMouseWheelEvent.getScrollType());
        mouseTimeEvent.setWheelDirection(nativeMouseWheelEvent.getWheelDirection());
        mouseTimeEvent.setWheelRotation(nativeMouseWheelEvent.getWheelRotation());
        mouseTimeEvent.setScrollAmount(nativeMouseWheelEvent.getScrollAmount());
        mouseTimeEvent.setScrollType(nativeMouseWheelEvent.getScrollType());

        mouseTimeEvent.setTimeStartEvent(new Date().getTime());
        mouseTimeEvent.setTimeStopEvent(mouseTimeEvent.getTimeStartEvent());

        listReleasedsKeys.add(mouseTimeEvent);

    }

    private void stopMoved(Integer x, Integer y) {
        _log.debug("Mouse StopMoved");
        MouseTimeEvent mouseTimeEvent = mousePressed.get(456);
        if (mouseTimeEvent != null) {
            mouseTimeEvent.setTimeStopEvent(new Date().getTime() - startDate.getTime());
            mouseTimeEvent.setStopX(x);
            mouseTimeEvent.setStopY(y);
            mousePressed.remove(456);
            listReleasedsKeys.add(mouseTimeEvent);
        }
    }
}
