package com.example.n2eServer.controller;

import com.example.n2eServer.dto.MailDto;
import com.example.n2eServer.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final MailService mailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMail(@RequestBody MailDto mailDto) {
        return mailService.sendEmail(mailDto);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findMail(@RequestParam(required = false) String to, @RequestParam(required = false) Long id) {
        return mailService.findEmail(to, id);
    }
}
