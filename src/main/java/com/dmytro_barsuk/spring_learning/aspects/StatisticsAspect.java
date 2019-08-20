package com.dmytro_barsuk.spring_learning.aspects;

import com.dmytro_barsuk.spring_learning.utils.Statistics;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StatisticsAspect{


    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {

    }

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint jp) {
        Class<?> clazz = jp.getTarget().getClass();
        if (!Statistics.counter.containsKey(clazz)) {
            Statistics.counter.put(clazz, 0);
        }
        Statistics.counter.put(clazz, Statistics.counter.get(clazz) + 1);
    }
}
