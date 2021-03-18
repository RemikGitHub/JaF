package pl.justaforum.jaf.email;

import lombok.AllArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService{

    private final JavaMailSender javaMailSender;


    @Async
    public void sendEmail(String email, String token) {
        String link = "<a href=\"http://localhost:8080/signup/confirm?token=" + token + "\"> Click here to active</a>";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Confirm your account!");
        mimeMessageHelper.setText("<h2>JaF - Just a Forum</h2><h3>If you want to activate your account, click on the link.</h3>" + link, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }

}
