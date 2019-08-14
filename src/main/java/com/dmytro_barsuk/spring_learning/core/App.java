package com.dmytro_barsuk.spring_learning.core;

import com.dmytro_barsuk.spring_learning.beans.Client;
import com.dmytro_barsuk.spring_learning.beans.Event;
import com.dmytro_barsuk.spring_learning.loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;
    private static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        App app = (App) context.getBean("app");

        app.logEvent("Some event for user 1");
        app.logEvent("Some event for user 2");

        context.close();
    }

    public void logEvent(String msg){
        String message = msg.replaceAll(client.getId(), client.getFullName());
        Event event = context.getBean("event", Event.class);
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

}
