package com.example.n2eServer.service;

import com.example.n2eServer.dto.MailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;

    public ResponseEntity<?> sendEmail(MailDto mailDto) {
        try {
            if(mailDto.getTo() == null || mailDto.getTo().length == 0) {
                return ResponseEntity.badRequest().body("수신 이메일을 입력해주세요");
            }

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mailDto.getTo());
            message.setSubject(mailDto.getTitle());
            message.setText(mailDto.getContent());

            mailSender.send(message);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

        return ResponseEntity.ok("success");
    }
}

