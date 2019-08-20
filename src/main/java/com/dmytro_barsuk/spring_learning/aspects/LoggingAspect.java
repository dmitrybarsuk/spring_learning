package com.dmytro_barsuk.spring_learning.aspects;

import com.dmytro_barsuk.spring_learning.beans.Event;
import com.dmytro_barsuk.spring_learning.loggers.impl.ConsoleEventLogger;
import com.dmytro_barsuk.spring_learning.loggers.impl.FileEventLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
public class LoggingAspect {

//    @Autowired
//    private StatisticsAspect statisticsAspect;

//    @Autowired
//    private FileEventLogger logger;

    public static final int MAX_ALLOWED = 5;

    @Pointcut("execution(* *.*.*(..))")
    private void allLogEventMethods() {
    }
//
//    @Pointcut("allLogEventMethods() && within(*.*FileEventLogger)")
//    private void logEventInsideFileLoggers() {
//    }
//
//    @Pointcut("allLogEventMethods() && within(*.ConsoleEventLogger)")
//    private void consoleLoggerMethods(){
//
//    }


    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("BEFORE: " + joinPoint.getTarget().getClass().getSimpleName() + " " + joinPoint.getSignature().getName());
    }

    @AfterReturning(
            pointcut = "allLogEventMethods()",
            returning = "retVal")
    public void logAfter(Object retVal) {
        System.out.println("Returned value: " + retVal);
    }

    @AfterThrowing(
            pointcut = "allLogEventMethods()",
            throwing = "ex")
    public void logAfterThrowing(Throwable ex) {
        System.out.println("Thrown: " + ex);
    }

//    @Around(value = "consoleLoggerMethods() && args(event)", argNames = "event")
//    public void aroundLogEvent(ProceedingJoinPoint jp, Object event) throws Throwable {
//        if(statisticsAspect.getCountForLogger(ConsoleEventLogger.class) < MAX_ALLOWED){
//            jp.proceed(new Object[]{event});
//        }
//        else{
//            logger.logEvent((Event) event);
//        }
//    }
}
