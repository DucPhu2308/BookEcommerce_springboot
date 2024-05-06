package hcmute.leettruyen.service;

public interface IEmailSenderService {
    void sendEmail(String to, String subject, String text);
}
