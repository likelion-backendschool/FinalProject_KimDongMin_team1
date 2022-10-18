package com.ll.mutbooks.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    @Value("${mail.sender}")
    private String sender;

    public void sendMail(String receiver) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setSubject("MUTBOOKS 회원 가입을 축하합니다!");

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, false, "UTF-8");
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(receiver);
        mimeMessageHelper.setText("<h1>회원 가입을 축하합니다!!</h1>", true);
        javaMailSender.send(message);
    }
}
