package com.dmytro_barsuk.spring_learning.core;

import com.dmytro_barsuk.spring_learning.beans.Client;
import com.dmytro_barsuk.spring_learning.beans.Event;
import com.dmytro_barsuk.spring_learning.beans.EventType;
import com.dmytro_barsuk.spring_learning.loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.Objects;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType,EventLogger> loggers;
    private static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    public App(Client client, EventLogger defaultLogger, Map<EventType,EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        App app = (App) context.getBean("app");

        app.logEvent("Some event for user 1", EventType.INFO);
        app.logEvent("Some event for user 2", EventType.ERROR);
        app.logEvent("Hi, mr. Doctor", EventType.INFO);

        context.close();
    }

    public void logEvent(String msg, EventType type){
        EventLogger logger = loggers.get(type);
        if(Objects.isNull(logger)){
            logger = defaultLogger;
        }

        Event event = context.getBean("event", Event.class);
        event.setMsg(msg.replaceAll(client.getId(), client.getFullName()));
        logger.logEvent(event);
    }

}
