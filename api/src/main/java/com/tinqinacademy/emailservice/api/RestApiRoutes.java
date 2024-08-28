package com.tinqinacademy.emailservice.api;

public class RestApiRoutes {
    public static final String API = "/api/v1";

    public static final String API_INTERNAL = API +"/internal";


    public static final String SEND_CODE_FOR_EMAIL_VERIFICATION = API_INTERNAL+"/send-register-code";
    public static final String SEND_PASSWORD_RECOVERY_CODE = API_INTERNAL+"/send-pass-recovery-code";
}