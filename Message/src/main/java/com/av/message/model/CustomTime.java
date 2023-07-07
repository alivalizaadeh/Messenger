package com.av.message.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
public class CustomTime {
    private int hour;
    private int minute;
    private int second;

    public CustomTime(LocalTime localTime) {
        hour = localTime.getHour();
        minute = localTime.getMinute();
        second = localTime.getSecond();
    }

    @Override
    public String toString() {
        return ((hour < 10) ? "0" + hour : hour) + ":" + ((minute < 10) ? "0" + minute : minute) + ":" + ((second < 10) ? "0" + second : second);
    }
}
