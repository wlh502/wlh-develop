package com.wlh.develop.config;

import com.wlh.develop.job.CpuUseRateJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobDetailConfig {

    @Bean
    public JobDetail cpuUseRateJob(){
      return  JobBuilder.newJob(CpuUseRateJob.class)
              .storeDurably()
              .withIdentity("cpuUseRate","cpuUseRate")
              .build();
    }

}
