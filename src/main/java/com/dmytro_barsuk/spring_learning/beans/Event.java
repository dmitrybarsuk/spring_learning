package com.dmytro_barsuk.spring_learning.beans;

import lombok.Setter;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {

    int id;
    @Setter String msg;
    Date date;
    DateFormat format;

    public Event(Date date, DateFormat format){
        id = new Random().nextInt(100);
        this.date = date;
        this.format = format;
    }

    public String toString() {
        return "Event[id=" + this.id + ", msg=" + this.msg + ", date=" + format.format(date) + "]";
    }
}
