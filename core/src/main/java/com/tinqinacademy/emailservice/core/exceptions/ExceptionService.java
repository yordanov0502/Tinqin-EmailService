package com.tinqinacademy.emailservice.core.exceptions;

import com.tinqinacademy.emailservice.api.exceptions.Errors;

public interface ExceptionService {
    Errors handle(Throwable throwable);
}
