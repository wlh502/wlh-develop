package com.wlh.develop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@RestController
public class WatchController {

    private final SimpMessagingTemplate simpMessagingTemplate;


    @Autowired
    public WatchController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/cpuUseRate")
    //@SendToUser("/cpuinfo/cpuUseRate")
    //@Scheduled
    public void cpuUseRate(SimpMessageHeaderAccessor headerAccessor) {
        String sessionId =
                (String)headerAccessor.getSessionAttributes().get(HttpSessionHandshakeInterceptor.HTTP_SESSION_ID_ATTR_NAME);
        String sessionId2 = headerAccessor.getSessionId();
        System.out.println(sessionId);
        System.out.println(sessionId2);
        //cpuCore.sendCpuUseRate(SpringContextUtils);
        simpMessagingTemplate.convertAndSendToUser(sessionId2,"/cpuinfo/cpuUseRate",sessionId2);
    }
}
