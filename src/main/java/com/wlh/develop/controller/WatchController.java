package com.wlh.develop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class WatchController {

    @MessageMapping("/cpuUseRate")
    @SendToUser("/cpuinfo/cpuUseRate")
    public String cpuUseRate(){
       return null;
    }
}
