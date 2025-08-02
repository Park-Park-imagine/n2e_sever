package com.example.n2eServer.service;

import ch.qos.logback.core.util.StringUtil;
import com.example.n2eServer.dto.MailDto;
import com.example.n2eServer.entity.EmailEntity;
import com.example.n2eServer.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;

    private final EmailRepository emailRepository;

    @Value("${spring.mail.username}")
    private String from;

    public ResponseEntity<?> sendEmail(MailDto mailDto) {
        try {
//            if(mailDto.getTo() == null || mailDto.getTo().isEmpty()) {
//                return ResponseEntity.badRequest().body("수신 이메일을 입력해주세요");
//            }
//
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(mailDto.getTo().toArray(String[]::new));
//            message.setSubject(mailDto.getTitle());
//            message.setText(mailDto.getContent());
//
//            mailSender.send(message);

            List<EmailEntity> histories = mailDto.getTo().stream()
                    .map(to -> new EmailEntity(from, to, mailDto.getTitle(), mailDto.getContent()))
                    .collect(Collectors.toList());

            emailRepository.saveAll(histories);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

        return ResponseEntity.ok("success");
    }

    public ResponseEntity<?> findEmail(String to, Long id) {
        if(!StringUtil.isNullOrEmpty(to)) {
            return ResponseEntity.ok(emailRepository.findByTo(to));
        } else if(id != null) {
            return ResponseEntity.ok(emailRepository.findById(id));
        }
        return ResponseEntity.badRequest().body("to id 둘 중 하나는 핋수");
    }
}

