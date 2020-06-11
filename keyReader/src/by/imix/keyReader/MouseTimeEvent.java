package by.imix.keyReader;

import java.awt.*;
import java.io.Serializable;


public class MouseTimeEvent extends TimeEvent implements Serializable {
    private Integer keyButton;
    private Integer clickCount;
    private Integer startX;
    private Integer stopX;
    private Integer startY;
    private Integer stopY;
    private int wheelDirection;
    private int wheelRotation;
    private int scrollAmount;
    private int scrollType;

    @Override
    public String toString() {
        return "key "+getKeyButton()+" start: "+ getTimeStartEvent()+" продолжительность: "+ (getTimeStopEvent()-getTimeStartEvent());
    }

    public Integer getKeyButton() {
        return keyButton;
    }

    public void setKeyButton(Integer keyButton) {
        this.keyButton = keyButton;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public Integer getStartX() {
        return startX;
    }

    public void setStartX(Integer startX) {
        this.startX = startX;
    }

    public Integer getStopX() {
        return stopX;
    }

    public void setStopX(Integer stopX) {
        this.stopX = stopX;
    }

    public Integer getStartY() {
        return startY;
    }

    public void setStartY(Integer startY) {
        this.startY = startY;
    }

    public Integer getStopY() {
        return stopY;
    }

    public void setStopY(Integer stopY) {
        this.stopY = stopY;
    }

    public int getWheelDirection() {
        return wheelDirection;
    }

    public void setWheelDirection(int wheelDirection) {
        this.wheelDirection = wheelDirection;
    }

    public int getWheelRotation() {
        return wheelRotation;
    }

    public void setWheelRotation(int wheelRotation) {
        this.wheelRotation = wheelRotation;
    }

    public int getScrollAmount() {
        return scrollAmount;
    }

    public void setScrollAmount(int scrollAmount) {
        this.scrollAmount = scrollAmount;
    }

    public int getScrollType() {
        return scrollType;
    }

    public void setScrollType(int scrollType) {
        this.scrollType = scrollType;
    }
}
