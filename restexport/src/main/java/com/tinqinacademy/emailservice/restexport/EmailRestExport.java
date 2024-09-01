package com.tinqinacademy.emailservice.restexport;

import com.tinqinacademy.emailservice.api.RestApiRoutes;
import com.tinqinacademy.emailservice.api.operations.sendcodeforemailverification.SendCodeForEmailVerificationInput;
import com.tinqinacademy.emailservice.api.operations.sendcodeforemailverification.SendCodeForEmailVerificationOutput;
import com.tinqinacademy.emailservice.api.operations.sendpassrecoverycode.SendPassRecoveryCodeInput;
import com.tinqinacademy.emailservice.api.operations.sendpassrecoverycode.SendPassRecoveryCodeOutput;
import feign.Headers;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

@Headers({"Content-Type: application/json"})
public interface EmailRestExport {

//    @RequestLine("POST " + RestApiRoutes.SEND_CODE_FOR_EMAIL_VERIFICATION)
//    SendCodeForEmailVerificationOutput sendCodeForEmailVerification(@RequestBody SendCodeForEmailVerificationInput input);

    @RequestLine("POST " + RestApiRoutes.SEND_PASSWORD_RECOVERY_CODE)
    SendPassRecoveryCodeOutput sendPasswordRecoveryCode(@RequestBody SendPassRecoveryCodeInput input);
}