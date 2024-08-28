package com.tinqinacademy.emailservice.core.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Async
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 3000)
    )
    public void sendEmail(MimeMessage mimeMessage) {
        javaMailSender.send(mimeMessage);
    }

    @Recover
    private void recover(Exception exception, MimeMessage mimeMessage) throws MessagingException {
        String recipientEmail = mimeMessage.getRecipients(MimeMessage.RecipientType.TO)[0].toString();
        log.error("Error occurred while sending email to {}. Exception: {}", recipientEmail,exception.toString());
    }
}
