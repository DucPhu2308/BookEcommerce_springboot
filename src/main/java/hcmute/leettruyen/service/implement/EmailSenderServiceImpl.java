package hcmute.leettruyen.service.implement;

import hcmute.leettruyen.service.IEmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements IEmailSenderService {
    private final JavaMailSender emailSender;

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nguyenducphu200321@gmail.com");
        message.setText(text);
        message.setSubject(subject);
        message.setTo(to);
        emailSender.send(message);
    }
}
