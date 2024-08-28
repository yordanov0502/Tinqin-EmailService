package com.tinqinacademy.emailservice.core.operations;

import com.tinqinacademy.emailservice.api.exceptions.Errors;
import com.tinqinacademy.emailservice.api.exceptions.custom.EmailException;
import com.tinqinacademy.emailservice.api.operations.sendpassrecoverycode.SendPassRecoveryCodeInput;
import com.tinqinacademy.emailservice.api.operations.sendpassrecoverycode.SendPassRecoveryCodeOperation;
import com.tinqinacademy.emailservice.api.operations.sendpassrecoverycode.SendPassRecoveryCodeOutput;
import com.tinqinacademy.emailservice.core.exceptions.ExceptionService;
import com.tinqinacademy.emailservice.core.utils.EmailService;
import com.tinqinacademy.emailservice.core.utils.LoggingUtils;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendPassRecoveryCodeOperationProcessor extends BaseOperationProcessor implements SendPassRecoveryCodeOperation {

    @Value("${env.EMAIL_USERNAME}")
    private String emailSender;
    private final JavaMailSender javaMailSender;
    private final EmailService emailService;

    public SendPassRecoveryCodeOperationProcessor(ConversionService conversionService, ExceptionService exceptionService, Validator validator, JavaMailSender javaMailSender, EmailService emailService) {
        super(conversionService, exceptionService, validator);
        this.javaMailSender = javaMailSender;
        this.emailService = emailService;
    }

    @Override
    public Either<Errors, SendPassRecoveryCodeOutput> process(SendPassRecoveryCodeInput input) {
        return Try.of(() -> {
                    log.info(String.format("Start %s %s input: %s", this.getClass().getSimpleName(), LoggingUtils.getMethodName(), input));

                    validate(input);

                    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                    try {
                        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
                        helper.setFrom(emailSender);
                        helper.setTo(input.getEmail());
                        helper.setSubject("Password recovery code");

                        String htmlMsg = "<h2>Dear " + input.getUserFirstName() + "!</h2>" +
                                "<p>You receive this email, because you have recently sent a request for password recovery code.</p>" +
                                "<p><b>This is your password recovery code: <span style='color:blue;'>" + input.getPasswordRecoveryCode() + "</span></b></p>" +
                                "<p>Yours faithfully,<br>The Hotel Service Team.</p>";
                        helper.setText(htmlMsg, true);
                        emailService.sendEmail(mimeMessage);
                    } catch (MessagingException e) {
                        throw new EmailException("Unexpected error occurred while sending email with password recovery code.");
                    }

                    SendPassRecoveryCodeOutput output = SendPassRecoveryCodeOutput.builder().build();
                    log.info(String.format("End %s %s output: %s", this.getClass().getSimpleName(), LoggingUtils.getMethodName(), output));

                    return output;
                })
                .toEither()
                .mapLeft(exceptionService::handle);
    }
}
