package com.example.notificationservice.service;

import com.example.notificationservice.data.MailInfo;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;

public interface EmailService {

    @Async
    void sendHtmlMail(MailInfo mailInfo, String mailTemplate, Map<String,Object>  data);
}
