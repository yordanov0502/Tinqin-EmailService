package com.tinqinacademy.emailservice.api.operations.sendcodeforemailverification;

import com.tinqinacademy.emailservice.api.base.OperationInput;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SendCodeForEmailVerificationInput implements OperationInput {
    @NotBlank
    private String userFirstName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String codeForEmailVerification;
}
