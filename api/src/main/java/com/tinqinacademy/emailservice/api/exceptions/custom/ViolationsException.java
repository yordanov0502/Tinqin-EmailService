package com.tinqinacademy.emailservice.api.exceptions.custom;

import com.tinqinacademy.emailservice.api.exceptions.Error;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ViolationsException extends RuntimeException{

    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private final List<Error> errorList;

    public ViolationsException(String message, List<Error> errorList) {
        super(message);
        this.errorList = errorList;
    }
}
