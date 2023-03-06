package com.lccode.emailms.consumer;

import com.lccode.emailms.dto.EmailDTO;
import com.lccode.emailms.entity.EmailEntity;
import com.lccode.emailms.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailConsumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDTO emailDTO) {
        EmailEntity emailEntity = new EmailEntity();
        BeanUtils.copyProperties(emailDTO, emailEntity);
//        Mapping
        emailService.sendEmail(emailEntity);
        log.info("Email Status: {}", emailEntity.getStatusEmail().toString());
    }
}
