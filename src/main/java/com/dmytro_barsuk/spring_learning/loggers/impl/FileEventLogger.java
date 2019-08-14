package com.dmytro_barsuk.spring_learning.loggers.impl;

import com.dmytro_barsuk.spring_learning.beans.Event;
import com.dmytro_barsuk.spring_learning.loggers.EventLogger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileEventLogger implements EventLogger {

    private String filename;
    private File file;

    public FileEventLogger(String filename) {
        this.filename = filename;
        file = new File(filename);
    }

    public void init() throws IOException {
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
