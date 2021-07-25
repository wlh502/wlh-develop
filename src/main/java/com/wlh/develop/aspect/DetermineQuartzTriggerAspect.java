package com.wlh.develop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
public class DetermineQuartzTriggerAspect {
    private static final String SUFFX = "Trigger";

    @Pointcut("@annotation(org.springframework.messaging.handler.annotation.MessageMapping)")
    public void messageMappingAnnotation(){}

    @Around("messageMappingAnnotation()")
    public void around(ProceedingJoinPoint jp) throws Throwable{
        String methodName = jp.getSignature().getName();
        Class<?>[] parameterTypes = ((MethodSignature)jp.getSignature()).getParameterTypes();
        Class<?> targetClass = jp.getTarget().getClass();
        Method method = targetClass.getMethod(methodName,parameterTypes);
        MessageMapping messageMapping = method.getDeclaredAnnotation(MessageMapping.class);
        String[] values = messageMapping.value();

        List<Object> args = new ArrayList<>();
       if(jp.getArgs()!=null && jp.getArgs().length > 0){
           for (int i = 0; i < jp.getArgs().length; i++) {
               args.add(jp.getArgs()[i]);
           }
       }

        if(values!=null && values.length > 0){
            for(int i=0;i<values.length;i++){
                args.add(values[i].substring(values[i].lastIndexOf("/")+1)+SUFFX);
            }
        }

        jp.proceed();
        //jp.proceed(args.toArray(new Object[args.size()]));
    }


}
