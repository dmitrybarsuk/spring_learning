package com.dmytro_barsuk.spring_learning.beans;

import lombok.Setter;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {

    private int id;
    private @Setter String msg;
    private Date date;
    private DateFormat format;

    public Event(Date date, DateFormat format){
        id = new Random().nextInt(100);
        this.date = date;
        this.format = format;
    }

    public String toString() {
        return "Event[id=" + this.id + ", msg=" + this.msg + ", date=" + format.format(date) + "]";
    }
}
