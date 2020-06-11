package by.imix.keyReader;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 08.01.14
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class ObKeyPressed implements Serializable {
    private List<TimeEvent> listTimeEvents;//список нажатых кнопок
    private String title;//Название
    private String description;//Описание

    public ObKeyPressed() {
        listTimeEvents =new ArrayList<>();
    }

    public ObKeyPressed(List<TimeEvent> listTimeEvents) {
        this.listTimeEvents = listTimeEvents;
    }

    public List<TimeEvent> getListTimeEvents() {
        return listTimeEvents;
    }

    @XmlElementWrapper(name="events")
    @XmlElements( {
            @XmlElement( name="mouseevent", type = MouseTimeEvent.class ),
            @XmlElement( name="keyevent", type = KeyTimeEvent.class)} )
    public void setListTimeEvents(List<TimeEvent> listTimeEvents) {
        this.listTimeEvents = listTimeEvents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return title;
    }
}
