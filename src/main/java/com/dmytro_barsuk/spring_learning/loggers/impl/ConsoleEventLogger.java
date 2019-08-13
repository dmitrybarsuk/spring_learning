package com.dmytro_barsuk.spring_learning.loggers.impl;

import com.dmytro_barsuk.spring_learning.loggers.EventLogger;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(String msg){
        System.out.println(msg);
    }
}
