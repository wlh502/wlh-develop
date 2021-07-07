package com.wlh.develop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
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
    public void cpuUseRate(StompHeaderAccessor headerAccessor) {
        String sessionId =
                (String)headerAccessor.getSessionAttributes().get(HttpSessionHandshakeInterceptor.HTTP_SESSION_ID_ATTR_NAME);
        simpMessagingTemplate.convertAndSendToUser(sessionId,"/cpuinfo/cpuUseRate",sessionId);
    }
}
