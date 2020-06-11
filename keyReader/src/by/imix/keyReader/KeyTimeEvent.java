package by.imix.keyReader;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;


public class KeyTimeEvent extends TimeEvent implements Serializable {
    private String key;
    private Integer keyCode;

    public String getKey() {
        return key;
    }

    @XmlAttribute(name="keyName")
    public void setKey(String key) {
        this.key = key;
    }

    public Integer getKeyCode() {
        return keyCode;
    }

    @XmlAttribute
    public void setKeyCode(Integer keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public String toString() {
        return "key "+getKey()+" start: "+ getTimeStartEvent()+" продолжительность: "+ getTimeStartEvent();
    }
}