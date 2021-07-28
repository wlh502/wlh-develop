package com.wlh.develop.job;

import com.wlh.develop.util.ShellUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.thymeleaf.util.StringUtils;

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
        String shellPath = jobDataMap.getString("shellPath");
        String result = ShellUtils.exec(shellPath);
        if(result != null){
            System.out.println(result);
        }
        //simpMessagingTemplate.convertAndSend("/cpuinfo/cpuUseRate/"+userId,userId);

    }
}
