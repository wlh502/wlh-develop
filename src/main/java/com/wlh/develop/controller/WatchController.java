package com.wlh.develop.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WatchController {

    @MessageMapping("/cpuUseRate")
    @SendTo("/cpuinfo/cpuUseRate")
    public String cpuUseRate(){
        // do something
        return null;
    }
}
