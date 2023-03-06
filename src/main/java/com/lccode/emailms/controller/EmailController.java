package com.lccode.emailms.controller;

import com.lccode.emailms.dto.EmailDTO;
import com.lccode.emailms.entity.EmailEntity;
import com.lccode.emailms.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/sending-email")
    public ResponseEntity<EmailEntity> sendingEmail(@RequestBody @Valid EmailDTO emailDTO) {
        EmailEntity emailEntity = new EmailEntity();
        BeanUtils.copyProperties(emailDTO, emailEntity);
        //copyProperties do a mapper from emailDTO to emailEntity
        emailService.sendEmail(emailEntity);
        return new ResponseEntity<>(emailEntity, HttpStatus.CREATED);
    }

}
