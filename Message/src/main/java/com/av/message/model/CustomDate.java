package com.av.message.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
public class CustomDate {
    private int year;
    private int month;
    private int day;

    public CustomDate(LocalDate localDate) {
        year = localDate.getYear();
        month = localDate.getMonthValue();
        day = localDate.getDayOfMonth();
    }

    @Override
    public String toString() {
        return year + "-" + ((month < 10) ? "0" + month : month) + "-" + ((day < 10) ? "0" + day : day);
    }
}
