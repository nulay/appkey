package by.imix.keyReader;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;


public class KeyPressed implements Serializable {
    private String key;
    private Integer keyCode;
    private Long pressed;
    private Long relessed;
    private Long timePressed;


    public String getKey() {
        return key;
    }

    @XmlAttribute(name="keyName")
    public void setKey(String key) {
        this.key = key;
    }

    public Long getPressed() {
        return pressed;
    }

    @XmlAttribute
    public void setPressed(Long pressed) {
        this.pressed = pressed;
    }

    public Long getRelessed() {
        return relessed;
    }

    @XmlAttribute
    public void setRelessed(Long relessed) {
        this.relessed = relessed;
    }

    public Long getTimePressed() {
        return relessed-pressed;
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
        return "key "+getKey()+" start: "+getPressed()+" продолжительность: "+getTimePressed();
    }
}