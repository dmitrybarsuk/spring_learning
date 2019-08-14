package com.dmytro_barsuk.spring_learning.loggers.impl;

import com.dmytro_barsuk.spring_learning.beans.Event;
import com.dmytro_barsuk.spring_learning.loggers.EventLogger;

import java.util.List;

public class CombinedEventLogger implements EventLogger {

    List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {

        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }
}
