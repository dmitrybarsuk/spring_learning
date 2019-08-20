package com.dmytro_barsuk.spring_learning.loggers.impl;

import com.dmytro_barsuk.spring_learning.beans.Event;
import com.dmytro_barsuk.spring_learning.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("dbLogger")
public class DBLogger implements EventLogger {

    @Autowired
    private JdbcOperations jdbcTemplate;

    @Override
    public void logEvent(Event event) {
        jdbcTemplate.update("INSERT INTO log_event (`logId`, `logText`) VALUES (?, ?)",
                event.getId(),
                event.toString());
    }

    @PostConstruct
    private void init(){
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS `log_event` (" +
                "  `logId` int(10) unsigned NOT NULL," +
                "  `logText` text CHARACTER SET utf8 NOT NULL," +
                "  PRIMARY KEY (`logId`)," +
                "  UNIQUE KEY `logId_UNIQUE` (`logId`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8; ");
    }
}
