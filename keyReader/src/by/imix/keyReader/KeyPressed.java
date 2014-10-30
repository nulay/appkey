package by.imix.keyReader;

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

    public void setKey(String key) {
        this.key = key;
    }

    public Long getPressed() {
        return pressed;
    }

    public void setPressed(Long pressed) {
        this.pressed = pressed;
    }

    public Long getRelessed() {
        return relessed;
    }

    public void setRelessed(Long relessed) {
        this.relessed = relessed;
    }

    public Long getTimePressed() {
        return relessed-pressed;
    }

    public Integer getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(Integer keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public String toString() {
        return "key "+getKey()+" start: "+getPressed()+" продолжительность: "+getTimePressed();
    }
}