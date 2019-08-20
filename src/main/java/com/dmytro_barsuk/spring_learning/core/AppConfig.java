package com.dmytro_barsuk.spring_learning.core;

import com.dmytro_barsuk.spring_learning.aspects.AspectConfig;
import com.dmytro_barsuk.spring_learning.beans.EventType;
import com.dmytro_barsuk.spring_learning.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.EnumMap;
import java.util.Map;

@Configuration
@PropertySource({
        "classpath:properties/client.properties",
        "classpath:properties/misc.properties"
        })
@ComponentScan("com.dmytro_barsuk.spring_learning")
@Import(AspectConfig.class)
public class AppConfig {

    @Autowired
    @Qualifier("consoleEventLogger")
    private EventLogger consoleEventLogger;

    @Autowired
    @Qualifier("combinedEventLogger")
    private EventLogger combinedEventLogger;

    @Autowired
    private DataSource driverManagerDataSource;

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

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(driverManagerDataSource);
    }

    @Bean
    public DriverManagerDataSource driverManagerDataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/spring_testing");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("");
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return driverManagerDataSource;
    }
}
