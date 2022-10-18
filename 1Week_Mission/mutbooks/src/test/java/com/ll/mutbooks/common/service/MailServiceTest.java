package com.ll.mutbooks.common.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MailServiceTest {

    @Autowired
    MailService mailService;

    @Test
    void sendMailTest() throws Exception {
        mailService.sendMail("ddmkim94@naver.com");
    }
}