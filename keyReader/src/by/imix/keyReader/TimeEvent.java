package by.imix.keyReader;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

public abstract class TimeEvent implements Serializable {
    protected Long timeStartEvent=0L;
    protected Long timeStopEvent=0L;

    @XmlAttribute
    public void setTimeStartEvent(Long timeStartEvent) {
        this.timeStartEvent = timeStartEvent;
    }
    public Long getTimeStartEvent() {
        return timeStartEvent;
    }

    @XmlAttribute
    public void setTimeStopEvent(Long timeStopEvent) {
        this.timeStopEvent = timeStopEvent;
    }

    public Long getTimeStopEvent() {
        return timeStopEvent;
    }

    public Long getDuration(){
        return timeStopEvent - timeStartEvent;
    }
}
