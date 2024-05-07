package hcmute.leettruyen.service;

import org.springframework.scheduling.annotation.Async;

public interface IEmailSenderService {
    @Async
    void sendEmail(String to, String subject, String text);
}
