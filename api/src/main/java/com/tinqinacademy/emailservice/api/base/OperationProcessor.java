package com.tinqinacademy.emailservice.api.base;

import com.tinqinacademy.emailservice.api.exceptions.Errors;
import io.vavr.control.Either;

public interface OperationProcessor<I extends  OperationInput, O extends OperationOutput > {
    Either<Errors,O> process(I input);
}