package com.dmytro_barsuk.spring_learning.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class Statistics {
    public static Map<Class<?>, Integer> counter = new HashMap<>();


    public static void printStatisitcs(){
        for (Map.Entry<Class<?>, Integer> entry : counter.entrySet()) {
            System.out.println( entry.getKey().getSimpleName() + " :" + entry.getValue());
        }
    }

    public static int getCountForLogger(Class<?> clazz){
        return counter.get(clazz);
    }
}
