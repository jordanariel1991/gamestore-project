package com.company.LevelUpService;

import com.company.LevelUpService.util.messages.LevelUpMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @RabbitListener(queues = LevelUpServiceApplication.QUEUE_NAME)
    public void receiveMessage(LevelUpMessage msg) {
        System.out.println(msg.toString());
    }
}
