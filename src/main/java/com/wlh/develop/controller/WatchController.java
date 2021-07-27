package com.wlh.develop.controller;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class WatchController {

    private final Scheduler scheduler;
    private final Map<String, JobDetail> jobMap;


    @Autowired
    public WatchController(Scheduler scheduler, Map<String, JobDetail> jobMap) {
        this.scheduler = scheduler;
        this.jobMap = jobMap;
    }

    @MessageMapping("/cpuUseRate")
    public void cpuUseRate(@Header("userId") String userId, @Header("jobName") String jobName) throws Exception {
        String identity = "cpuUseRate_" + userId;

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(identity, identity)
                .forJob(jobMap.get(jobName)) // 绑定对应的job
                .startNow() // 加入任务后，立刻开始
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever())// 定义执行时间
                .usingJobData("userId", userId) // 参数传递
                .build();

        if (!scheduler.checkExists(trigger.getKey())) {
            scheduler.scheduleJob(trigger);
        }
    }
}
