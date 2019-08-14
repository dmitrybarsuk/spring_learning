package com.dmytro_barsuk.spring_learning.loggers.impl;

import com.dmytro_barsuk.spring_learning.beans.Event;
import com.dmytro_barsuk.spring_learning.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CombinedEventLogger implements EventLogger {

    @Autowired
    private ConsoleEventLogger consoleEventLogger;

    @Autowired
    private FileEventLogger fileEventLogger;

    private List<EventLogger> loggers;

    @PostConstruct
    public void init() {
        loggers = new ArrayList<>();
        loggers.add(this.consoleEventLogger);
        loggers.add(fileEventLogger);
    }


    @Override
    public void logEvent(Event event) {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }
}
