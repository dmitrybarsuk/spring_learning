package com.dmytro_barsuk.spring_learning.core;

import com.dmytro_barsuk.spring_learning.beans.EventType;
import com.dmytro_barsuk.spring_learning.loggers.EventLogger;
import com.dmytro_barsuk.spring_learning.loggers.impl.CombinedEventLogger;
import com.dmytro_barsuk.spring_learning.loggers.impl.ConsoleEventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.EnumMap;
import java.util.Map;

@Configuration
@PropertySource({
        "classpath:properties/client.properties",
        "classpath:properties/misc.properties"
        })
public class AppConfig {

    @Autowired
    private ConsoleEventLogger consoleEventLogger;

    @Autowired
    private CombinedEventLogger combinedEventLogger;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Map<EventType, EventLogger> loggerMap() {
        Map<EventType, EventLogger> loggers = new EnumMap<>(EventType.class);
        loggers.put(EventType.INFO, consoleEventLogger);
        loggers.put(EventType.ERROR, combinedEventLogger);
        return loggers;
    }
}
