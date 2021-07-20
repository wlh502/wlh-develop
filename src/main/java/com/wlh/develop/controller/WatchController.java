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
    public void cpuUseRate(StompHeaderAccessor headerAccessor) {
        //System.out.println("2:"+Thread.currentThread().getId());
        // 任务触发
        String sessionId =
                (String)headerAccessor.getSessionAttributes().get(HttpSessionHandshakeInterceptor.HTTP_SESSION_ID_ATTR_NAME);
        // 因为目前没有登录，通过session发送一对一消息，不然可以使用convertAndSendToUser方法
        simpMessagingTemplate.convertAndSend("/cpuinfo/cpuUseRate/"+sessionId,sessionId);
    }
}
