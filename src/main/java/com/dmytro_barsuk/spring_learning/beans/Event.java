package com.dmytro_barsuk.spring_learning.beans;

import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Random;

@Component
@Scope("prototype")
public class Event {

    private int id;
    private @Setter
    String msg;
    private Date date;
    private DateFormat format;

    public Event() {
        id = new Random().nextInt(100);
        date = new Date();
        format = DateFormat.getDateTimeInstance();
    }

    public String toString() {
        return "Event[id=" + this.id + ", msg=" + this.msg + ", date=" + format.format(date) + "]";
    }

    public static boolean isDay() {
        return ZonedDateTime.now().getHour() > 8 && ZonedDateTime.now().getHour() < 17;
    }
}
