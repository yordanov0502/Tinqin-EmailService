package com.tinqinacademy.emailservice.core.utils;


import com.tinqinacademy.emailservice.api.exceptions.custom.NoMethodFoundException;

public class LoggingUtils {
    public static String getMethodName() {
        return StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .walk(frames -> frames
                        .skip(1)
                        .findFirst()
                        .orElseThrow(() -> new NoMethodFoundException("No method found."))
                        .getMethodName());
    }
}
