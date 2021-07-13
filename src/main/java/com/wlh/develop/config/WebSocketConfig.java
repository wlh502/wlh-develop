package com.wlh.develop.config;

import com.wlh.develop.interceptor.WebSocketHttpSessionHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/systeminfo")
                .setAllowedOrigins("/*").
                // 引入HttpSessionHandshakeInterceptor,将httpSession复制到websocketSession
                addInterceptors(new WebSocketHttpSessionHandshakeInterceptor()).
                withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/cpu_watch","/memory_watch");
        registry.enableSimpleBroker("/cpuinfo","/memroyinfo")
                .setHeartbeatValue(new long[]{10000,10000}).setTaskScheduler(new DefaultManagedTaskScheduler());
    }
}
