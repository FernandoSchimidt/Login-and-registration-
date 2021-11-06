package br.com.fernandoschimidt.login.login.email.Email;


import br.com.fernandoschimidt.login.login.email.EmailSender;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailService.class);

    private final JavaMailSender mailSender;


    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("fernandoschimidt93@hotmail.com");
            mailSender.send(message);

        } catch (MessagingException e) {
            LOGGER.error("failed to send email ", e);
            throw new IllegalStateException("failed to sendo email");
        }
    }
}
