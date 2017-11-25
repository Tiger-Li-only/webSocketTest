package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * 聊天控制器
 */
@Controller
public class WsController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg) {
        if (principal.getName().equals("zhangsan")) {
            messagingTemplate.convertAndSendToUser("lisi", "/queue/notifications",
                    principal.getName() + "给您发来了消息：" + msg);
        }else{
            messagingTemplate.convertAndSendToUser("zhangsan", "/queue/notifications",
                    principal.getName() + "给您发来了消息：" + msg);
        }
    }
}
