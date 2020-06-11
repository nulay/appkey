package by.imix.keyReader;

import com.sun.org.apache.xpath.internal.operations.Number;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class GlobalCatcher implements NativeKeyListener {
    private static final Logger _log = LoggerFactory.getLogger(GlobalCatcher.class);
    private Catcher keyCatcher;
    private Catcher mouseCatcher;
    private boolean keyRun;
    private Date startDate;
    private EventStopGKL es;

    public static void main(String[] args) {
        new GlobalCatcher(true, true);
    }

    public GlobalCatcher(boolean startKeyCatcher, boolean startMouseCatcher) {
        this(null, startKeyCatcher, startMouseCatcher);
    }

    public GlobalCatcher(EventStopGKL es, boolean startKeyCatcher, boolean startMouseCatcher) {
        this.es=es;
        startDate = new Date();
        if(startKeyCatcher){
            keyCatcher = new KeyCatcher(startDate);

        }
        if(startMouseCatcher){
            mouseCatcher = new MouseCatcher(startDate);

        }
        startKeyCatcher();
    }

    public void startKeyCatcher(){
        _log.info("Start catch devices events.");
        if(!keyRun){
            startDate=new Date();
            try {
                GlobalScreen.registerNativeHook();
            }
            catch (NativeHookException ex) {
                _log.error("There was a problem registering the native hook.");
                _log.error(ex.getMessage());
            }
            keyRun=true;
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            stopKeyCatcher();
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    public void stopKeyCatcher(){
        List<TimeEvent> allEvents = new ArrayList<>();

        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        keyRun=false;

        if(keyCatcher!=null) {
            allEvents.addAll(keyCatcher.getSimpleEvents());
        }
        if(mouseCatcher!=null){
            allEvents.addAll(mouseCatcher.getSimpleEvents());
        }

        Collections.sort(allEvents,new Comparator<TimeEvent>(){
            @Override
            public int compare(TimeEvent o1, TimeEvent o2){
                try {
                    return (o1.getTimeStartEvent()>o2.getTimeStartEvent())?1:(o1.getTimeStartEvent().equals(o2.getTimeStartEvent()))?0:-1;
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });

        if(_log.isDebugEnabled()) {
            for (TimeEvent kpr : allEvents) {
                _log.debug(kpr.toString());
            }
        }

        if(es!=null){
            es.fireStopped(allEvents);
        }
    }



    public boolean isRun() {
        return keyRun;
    }
}
