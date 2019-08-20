package com.dmytro_barsuk.spring_learning.core;

import com.dmytro_barsuk.spring_learning.aspects.StatisticsAspect;
import com.dmytro_barsuk.spring_learning.beans.Client;
import com.dmytro_barsuk.spring_learning.beans.Event;
import com.dmytro_barsuk.spring_learning.beans.EventType;
import com.dmytro_barsuk.spring_learning.loggers.EventLogger;
import com.dmytro_barsuk.spring_learning.utils.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

@Component
public class App {

    @Autowired
    private Client client;

    @Value("#{T(com.dmytro_barsuk.spring_learning.beans.Event).isDay() ? consoleEventLogger : fileEventLogger}")
    private EventLogger defaultLogger;

    @Autowired
    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggers;

    private static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);


    public static void main(String[] args) {

        App app = (App) context.getBean("app");

        app.logEvent("Some event for user 1", EventType.INFO);
        app.logEvent("Some event for user 2", EventType.ERROR);
        app.logEvent("Hi, mr. Doctor", EventType.INFO);

        Statistics.printStatisitcs();
        context.close();
    }

    public void logEvent(String msg, EventType type) {
        EventLogger logger = loggers.get(type);
        if (Objects.isNull(logger)) {
            logger = defaultLogger;
        }

        Event event = context.getBean("event", Event.class);
        event.setMsg(msg.replaceAll(client.getId(), client.getFullName()));
        logger.logEvent(event);
    }
}
