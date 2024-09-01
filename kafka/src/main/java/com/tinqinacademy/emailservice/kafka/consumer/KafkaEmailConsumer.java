package com.tinqinacademy.emailservice.kafka.consumer;

import com.tinqinacademy.emailservice.api.exceptions.Errors;
import com.tinqinacademy.emailservice.api.operations.sendcodeforemailverification.SendCodeForEmailVerificationInput;
import com.tinqinacademy.emailservice.api.operations.sendcodeforemailverification.SendCodeForEmailVerificationOperation;
import com.tinqinacademy.emailservice.api.operations.sendcodeforemailverification.SendCodeForEmailVerificationOutput;
import com.tinqinacademy.emailservice.kafka.model.EmailMessage;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEmailConsumer {
    private final SendCodeForEmailVerificationOperation sendCodeForEmailVerificationOperation;

    @KafkaListener(id = "consumerId", topics = "email", containerFactory = "kafkaListenerContainerFactory")
    public void listen(EmailMessage message) {
        SendCodeForEmailVerificationInput input = SendCodeForEmailVerificationInput.builder()
                .userFirstName(message.getTo())
                .email(message.getEmail())
                .codeForEmailVerification(message.getContent())
                .build();

        Either<Errors, SendCodeForEmailVerificationOutput> result = sendCodeForEmailVerificationOperation.process(input);
        if (result.isLeft()) {
            log.error(result.getLeft()
                    .toString());
        }
    }
}
