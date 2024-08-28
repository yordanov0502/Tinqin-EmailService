package com.tinqinacademy.emailservice.core.operations;

import com.tinqinacademy.emailservice.api.base.OperationInput;
import com.tinqinacademy.emailservice.api.exceptions.Error;
import com.tinqinacademy.emailservice.api.exceptions.custom.ViolationsException;
import com.tinqinacademy.emailservice.core.exceptions.ExceptionService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public abstract class BaseOperationProcessor {

    protected final ConversionService conversionService;
    protected final ExceptionService exceptionService;
    private final Validator validator;

    protected <I extends OperationInput> void validate(I input){
        Set<ConstraintViolation<I>> violationSet = validator.validate(input);

        if(!violationSet.isEmpty()){
        List<Error> errorList = violationSet
                .stream()
                .map(violation -> Error.builder()
                        .field(violation.getPropertyPath().toString())
                        .errMsg(violation.getMessage())
                        .build())
                .collect(Collectors.toList());
        throw new ViolationsException("Violation exception occurred.",errorList);
        }
    }

}