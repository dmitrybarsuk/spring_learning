package com.dmytro_barsuk.spring_learning.loggers.impl;

import com.dmytro_barsuk.spring_learning.beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class CacheFileEventLogger extends FileEventLogger {

    @Value("${misc.cacheSize}")
    private int cacheSize;

    private List<Event> cache;

    public CacheFileEventLogger() {
        super();
        this.cache = new ArrayList<>();
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if(cache.size() == cacheSize){
            writeEventsFromCache();
            cache.clear();
        }
    }

    @PreDestroy
    public void destroy(){
        if(!cache.isEmpty()){
            writeEventsFromCache();
        }
    }

    private void writeEventsFromCache(){
        for(Event e : cache){
            super.logEvent(e);
        }
    }
}
