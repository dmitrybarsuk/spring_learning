package com.dmytro_barsuk.spring_learning.loggers.impl;

import com.dmytro_barsuk.spring_learning.beans.Event;
import com.dmytro_barsuk.spring_learning.loggers.EventLogger;
import com.sun.istack.internal.NotNull;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class FileEventLogger implements EventLogger {

    @Value("${misc.filepath}")
    protected String filename;

    private File file;

    @PostConstruct
    public void init() throws IOException {
        file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        if (!file.canWrite()) {
            throw new IOException();
        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.write(file, event.toString() + "\n", StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
