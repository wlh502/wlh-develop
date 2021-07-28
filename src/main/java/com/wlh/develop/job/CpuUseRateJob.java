package com.wlh.develop.job;

import com.wlh.develop.util.ShellUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.thymeleaf.util.StringUtils;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.math.BigDecimal;

public class CpuUseRateJob extends QuartzJobBean {

    private static Long lastIdelTime = 0L;
    private static Long lastTotalTime = 0L;

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
            // 开始计算cpu使用率
            Long idleTime = Long.valueOf(result.split("_")[0]);
            Long totalCpuTime = Long.valueOf(result.split("_")[1]);
            Long idleDiff = idleTime - lastIdelTime;
            Long totalDiff = totalCpuTime - lastTotalTime;

            BigDecimal avgCpuUseRate = BigDecimal.valueOf(100).multiply(
                    BigDecimal.ONE
                        .subtract(
                            BigDecimal.valueOf(idleDiff)
                                    .divide(BigDecimal.valueOf(totalDiff),2,BigDecimal.ROUND_HALF_UP)
                    )
            );

            System.out.println(avgCpuUseRate);
            lastIdelTime = idleTime;
            lastTotalTime = totalCpuTime;
           // simpMessagingTemplate.convertAndSend("/cpuinfo/cpuUseRate/"+userId,userId);
        }
    }
}
