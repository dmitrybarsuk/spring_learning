package com.dmytro_barsuk.spring_learning.loggers;

import com.dmytro_barsuk.spring_learning.beans.Event;

public interface EventLogger {

    void logEvent(Event event);
}
