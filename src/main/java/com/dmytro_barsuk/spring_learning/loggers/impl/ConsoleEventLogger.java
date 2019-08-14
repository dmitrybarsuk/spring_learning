package com.dmytro_barsuk.spring_learning.loggers.impl;

import com.dmytro_barsuk.spring_learning.beans.Event;
import com.dmytro_barsuk.spring_learning.loggers.EventLogger;
import org.springframework.stereotype.Component;

@Component("consoleEventLogger")
public class ConsoleEventLogger implements EventLogger {

    @Override
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
