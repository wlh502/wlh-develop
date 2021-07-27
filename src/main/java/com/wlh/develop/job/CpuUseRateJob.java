package com.wlh.develop.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class CpuUseRateJob extends QuartzJobBean {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public CpuUseRateJob(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getTrigger().getJobDataMap();
        String userId = jobDataMap.getString("userId");
        simpMessagingTemplate.convertAndSend("/cpuinfo/cpuUseRate/"+userId,userId);
    }
}
