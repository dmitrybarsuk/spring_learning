package com.dmytro_barsuk.spring_learning.core;

import com.dmytro_barsuk.spring_learning.beans.Client;
import com.dmytro_barsuk.spring_learning.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    Client client;
    EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        App app = (App) context.getBean("app");

        app.logEvent("Some event for user 1");
        app.logEvent("Some event for user 2");
    }

    public void logEvent(String msg){
        String message = msg.replaceAll(client.getId(), client.getFullName());

        eventLogger.logEvent(message);
    }

}
