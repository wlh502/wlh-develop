package com.wlh.develop.config;

import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TriggerConfig {
//    @Autowired
//    private JobDetail cpuUseRateJob;

//    @Bean
//    public Trigger cpuUseRateTrigger(){
//      return   TriggerBuilder.newTrigger()
//                //.forJob("cpuUseRate","cpuUseRate")
//                .withIdentity("cpuUseRate","cpuUseRate")
//                .startNow()
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever())
//                .build();
//    }
}
