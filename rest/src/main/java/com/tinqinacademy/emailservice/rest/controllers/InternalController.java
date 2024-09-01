package com.tinqinacademy.emailservice.rest.controllers;

import com.tinqinacademy.emailservice.api.RestApiRoutes;
import com.tinqinacademy.emailservice.api.exceptions.Errors;
import com.tinqinacademy.emailservice.api.operations.sendcodeforemailverification.SendCodeForEmailVerificationInput;
import com.tinqinacademy.emailservice.api.operations.sendcodeforemailverification.SendCodeForEmailVerificationOperation;
import com.tinqinacademy.emailservice.api.operations.sendcodeforemailverification.SendCodeForEmailVerificationOutput;
import com.tinqinacademy.emailservice.api.operations.sendpassrecoverycode.SendPassRecoveryCodeInput;
import com.tinqinacademy.emailservice.api.operations.sendpassrecoverycode.SendPassRecoveryCodeOperation;
import com.tinqinacademy.emailservice.api.operations.sendpassrecoverycode.SendPassRecoveryCodeOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InternalController extends BaseController {

    private final SendCodeForEmailVerificationOperation sendCodeForEmailVerificationOperation;
    private final SendPassRecoveryCodeOperation sendPassRecoveryCodeOperation;

//    @Operation(summary = "Send one time code for email verification.",
//            description = "Sends one time code for email verification when a user has performed a registration.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "One time code for email verification sent successfully."),
//            @ApiResponse(responseCode = "400", description = "Bad request."),
//            @ApiResponse(responseCode = "404", description = "Not found.")
//    })
//    @PostMapping(RestApiRoutes.SEND_CODE_FOR_EMAIL_VERIFICATION)
//    public ResponseEntity<?> sendCodeForEmailVerification(@RequestBody SendCodeForEmailVerificationInput input) {
//        Either<Errors, SendCodeForEmailVerificationOutput> either = sendCodeForEmailVerificationOperation.process(input);
//        return mapToResponseEntity(either, HttpStatus.OK);
//    }

    @Operation(summary = "Send email with password recovery code.",
            description = "Sends email with password recovery code, which is valid only for 5 mins. After that it expires.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password recovery code sent successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "404", description = "Not found.")
    })
    @PostMapping(RestApiRoutes.SEND_PASSWORD_RECOVERY_CODE)
    public ResponseEntity<?> sendEmailWithPasswordRecoveryCode(@RequestBody SendPassRecoveryCodeInput input) {
        Either<Errors, SendPassRecoveryCodeOutput> either = sendPassRecoveryCodeOperation.process(input);
        return mapToResponseEntity(either, HttpStatus.OK);
    }

}
